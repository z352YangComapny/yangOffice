package com.yangworld.app.domain.question.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.service.CommentsService;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.service.MemberService;
import com.yangworld.app.domain.question.dto.QuestionCreateQnaDto;
import com.yangworld.app.domain.question.dto.QuestionUpdateQnaDto;
import com.yangworld.app.domain.question.entity.Comment;
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
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CommentsService qnaCommentService;
	/**
	 * 윤아
	 * 공지사항 & 이용문의 디테일 
	 */
	@GetMapping("/questionDetail")
	public void questionDetail(@AuthenticationPrincipal PrincipalDetails principal ,@RequestParam int id, Model model, Authentication authentication) {
		Question question = questionService.findQuestionById(id);
		log.info("id = {}", id);
		log.info("question = {}", question);
		
		List<Comments> qnaComments = qnaCommentService.getCommentsByQuestionId(id);
		boolean isAdmin = authentication.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .anyMatch(role -> role.equals("ROLE_ADMIN"));
		
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("question", question);
		model.addAttribute("principalName",principal.getUsername());
		model.addAttribute("questionType", question.getType());
		model.addAttribute("questionId", question.getId());
		model.addAttribute("principalId", principal.getId());
		
		 if (!qnaComments.isEmpty()) {
		        model.addAttribute("qnaComments", qnaComments.get(0).getContent());
		    }
	}
	
	/**
	 * 윤아
	 * 공지사항 orderby로 상단에뜨게 하는 findAllQuestionList 
	 */
	@GetMapping("/questionList")
	public void questionList(
						@AuthenticationPrincipal PrincipalDetails principal ,
						@RequestParam(defaultValue = "1") int page,
						Model model
						){
		int limit = 10;
		Map<String, Object> params = Map.of(
				"page",page,
				"limit",limit
			);
		List<Question> questions = questionService.findAllQuestion(params);	
		List<Long> questionsWithComments = new ArrayList<>();
		// 페이징 정보를 계산하고 전달
	    int totalCount = questionRepository.countAllQuestion(); // 전체 데이터 개수 조회
	    int totalPages = (int) Math.ceil((double) totalCount / limit); // 총 페이지 개수 계산
//	    List<Comments> qnaComments = qnaCommentService.getCommentsByQuestionId(questionId);
	    List<String> writerNames = new ArrayList<>();
	    List<Comments> comments = new ArrayList<Comments>();
	    List<Boolean> hasCommentsList = new ArrayList<>();
	    for (Question question : questions) {
	        Member writer = memberService.findById(question.getWriterId());
	        List<Comments> questionComments = qnaCommentService.getCommentsByQuestionId(question.getId());
	         // 댓글 여부 판별
	        
	        if (!comments.isEmpty()) {
	            questionsWithComments.add((long) question.getId());
	        }
	        log.info("questionWithComments = {}", questionsWithComments);
	        log.info("Comments = {}", comments);
	        log.info("writer = {}", writer);
	        boolean hasComments = !questionComments.isEmpty(); // 댓글 여부 판별
	        hasCommentsList.add(hasComments);
	        
	        comments.addAll(questionComments); 
	        writerNames.add(writer.getUsername()); // 또는 다른 원하는 정보를 가져올 수 있음
	    }
//	        List<Comments> qnaComments = qnaCommentService.getCommentsByQuestionId(questions.get(0).getWriterId());
//	        int comment = qnaComments.get(0).getId();
//	        log.info("comment ={}", comment);
	    
	    log.info("prinUname = {}", principal.getUsername());
	    
	    model.addAttribute("writerNames", writerNames);
	    model.addAttribute("principalId", principal.getId());
	    model.addAttribute("principalUsername", principal.getUsername());
	    model.addAttribute("questions", questions);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("questionsWithComments", questionsWithComments);
	    model.addAttribute("comments",comments);
	    model.addAttribute("hasCommentsList", hasCommentsList);
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
	

	
	
	@GetMapping("/deleteNotice")
    public String deleteNotice(@RequestParam int questionId, RedirectAttributes redirectAttributes) {
        
	log.info("questionId = {}", questionId);
	try {
		questionService.deleteNoticeById(questionId);
        redirectAttributes.addFlashAttribute("successMsg", "공지사항이 삭제되었습니다.");
    } catch (Exception e) {
        e.printStackTrace(); // 예외 출력
        
        redirectAttributes.addFlashAttribute("errorMsg", "공지사항 삭제 중 오류가 발생했습니다.");
    }

    return "redirect:/question/questionList";
}
	@GetMapping("/questionUpdate")
    public String updateQuestionForm(@AuthenticationPrincipal PrincipalDetails principal, @RequestParam int id, Model model, Authentication authentication) {
        Question question = questionService.findQuestionById(id);
        boolean isAdmin = authentication.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .anyMatch(role -> role.equals("ROLE_ADMIN"));
        
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("principalId", principal.getId());
        model.addAttribute("question", question);
        log.info("principalId = {}", principal.getId());
        log.info("id = {} " , id);
        return "question/questionUpdate";
    }
	
	@PostMapping("/updateQuestion")
	public String updateQuestion(@ModelAttribute QuestionUpdateQnaDto updateDto) {
		
	        int updatedQuestion = questionService.updateQuestion(updateDto);
	        
	        
	        return "redirect:/question/questionList"; 
	        
	}
	
	
	
	
	public boolean questionHasComments(int questionId) {
        List<Comments> comments = qnaCommentService.getCommentsByQuestionId(questionId);
        return !comments.isEmpty();
    }
	
}
