package com.yangworld.app.domain.question.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public void createQna(){}
	
	/**
	 * 윤아
	 * - 이용문의작성 
	 */
	@PostMapping("/createQna")
	public String createQna(@AuthenticationPrincipal PrincipalDetails principal , @ModelAttribute QuestionCreateQnaDto _qnaDto, Model model) {
		log.info("createQna info = {}", _qnaDto);
		
		// writerId 가져오기
		int writerId = principal.getId();
		Question qna = _qnaDto.toQna();
		log.info("writerId={}", writerId);
		qna.setWriterId(writerId);
		
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
	
	
}
