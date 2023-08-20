package com.yangworld.app.domain.question.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.question.dto.QuestionCreateQnaDto;
import com.yangworld.app.domain.question.dto.QuestionUpdateQnaDto;
import com.yangworld.app.domain.question.entity.Question;
import com.yangworld.app.domain.question.entity.QuestionType;
import com.yangworld.app.domain.question.repository.QuestionRepository;
import com.yangworld.app.domain.question.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	/**
	 * 윤아
	 * 공지사항 & 이용문의 디테일 
	 */
	@GetMapping("/questionDetail")
	public void questionDetail(@RequestParam int id, Model model) {
		Question question = questionService.findQuestionById(id);
		log.info("question = {}", question);
		model.addAttribute("question", question);
		
	}
	
	/**
	 * 윤아
	 * 공지사항 orderby로 상단에뜨게 하는 findAllQuestionList 
	 */
	@GetMapping("/questionList")
	public void questionList(@RequestParam(defaultValue = "1") int page,
						Model model
						){
		int limit = 10;
		Map<String, Object> params = Map.of(
				"page",page,
				"limit",limit
			);
		List<Question> questions = questionService.findAllQuestion(params);	
		// 페이징 정보를 계산하고 전달
	    int totalCount = questionRepository.countAllQuestion(); // 전체 데이터 개수 조회
	    int totalPages = (int) Math.ceil((double) totalCount / limit); // 총 페이지 개수 계산
	    model.addAttribute("questions", questions);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", totalPages);
	}
	
	 @GetMapping("/questionCreate")
	    public void createQna(Model model, Authentication authentication) {
	        boolean isAdmin = authentication.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .anyMatch(role -> role.equals("ROLE_ADMIN"));
	        
	        model.addAttribute("isAdmin", isAdmin);
	    
	    
	}
	
	/**
	 * 윤아
	 * - 이용문의작성 
	 */
	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("/createQna")
	public String createQna(@AuthenticationPrincipal PrincipalDetails principal , @ModelAttribute QuestionCreateQnaDto _qnaDto, Model model) {
		log.info("createQna info = {}", _qnaDto);
		
		// writerId 가져오기
		int writerId = principal.getId();
		Question qna = _qnaDto.toQna();
		log.info("writerId={}", writerId);
		qna.setWriterId(writerId);
		
		log.info("qna.getType() = {}", qna.getType());
		// 'type' 값을 설정합니다.
	    if (QuestionType.N.equals(qna.getType())) {
	        qna.setType(QuestionType.N); // 공지사항인 경우 'N'으로 설정
	    } else {
	        qna.setType(QuestionType.Q); // 이용문의인 경우 'Q'로 설정
	    }
		
		// question 테이블 insert
		questionService.insertQna(qna);
		model.addAttribute("writerId", writerId);
		
		
		return "redirect:/question/questionList";
	}
	
	@PostMapping("/updateQna")
	public ResponseEntity<?> updateQna(@AuthenticationPrincipal PrincipalDetails principal , @RequestBody QuestionUpdateQnaDto _qnaDto) {
		
		Question updateQna = _qnaDto.toQna();
		int writerId = principal.getId();
		updateQna.setWriterId(writerId);

		// question 데이터 update
		questionService.updateQna(updateQna);
		
		return ResponseEntity.ok().build();
		
	}
	

	
	 @PostMapping("/deleteNotice")
	    public ResponseEntity<String> deleteNotice(@RequestParam int questionId) {
		
		 log.info("questionId = {}", questionId);
		 try {
		        // 공지사항 삭제 로직을 구현하고 삭제된 여부를 확인하는 코드 작성
		        int deletedRows = questionService.deleteNoticeById(questionId);
		        
		        if (deletedRows > 0) {
		            return ResponseEntity.ok().body("공지사항이 삭제되었습니다.");
		        } else {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("공지사항 삭제 중 오류가 발생했습니다.");
		        }
		    } catch (Exception e) {
		    	 e.printStackTrace(); // 예외 출력
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("공지사항 삭제 중 오류가 발생했습니다.");
		    }
	
	
	 }
	
	
	
}
