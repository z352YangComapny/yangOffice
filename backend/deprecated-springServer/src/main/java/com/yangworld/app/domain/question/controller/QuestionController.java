package com.yangworld.app.domain.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.question.dto.QuestionCreateQnaDto;
import com.yangworld.app.domain.question.dto.QuestionUpdateQnaDto;
import com.yangworld.app.domain.question.entity.Question;
import com.yangworld.app.domain.question.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;

	/**
	 * 윤아
	 * GET : http://localhost:8080/question/questionList
	 * - Headers : Authorization ** 필수
	 */
	@GetMapping("/questionList")
	public ResponseEntity<?> questionList(){
		List<Question> questions = questionService.findAllQuestion();	
		return ResponseEntity.ok(questions);
	}

	/**
	 * POST : http://localhost:8080/question/createQna
	 * Key : title, content
	 * value : {title, content}
	 * - Headers : Authorization ** 필수
	 */
	@PostMapping("/createQna")
	public ResponseEntity<?> createQna(@AuthenticationPrincipal PrincipalDetails principal , @RequestBody QuestionCreateQnaDto _qnaDto) {
		log.info("createQna info = {}", _qnaDto);
		
		// writerId 가져오기
		int writerId = principal.getId();
		Question qna = _qnaDto.toQna();
		log.info("writerId={}", writerId);
		qna.setWriterId(writerId);
		
		// question 테이블 insert
		questionService.insertQna(qna);
		
		return ResponseEntity.ok().build();
	}
	/**
	 * PATCH  : http://localhost:8080/question/updateQna
	 * Key : id(수정할 문의사항 번호/ view), title, content
	 * value : {id, title, content}
	 * - Headers : Authorization ** 필수
	 */
	@PatchMapping ("/updateQna")
	public ResponseEntity<?> updateQna(@AuthenticationPrincipal PrincipalDetails principal , @RequestBody QuestionUpdateQnaDto _qnaDto) {
		
		Question updateQna = _qnaDto.toQna();
		int writerId = principal.getId();
		updateQna.setWriterId(writerId);

		// question 데이터 update
		questionService.updateQna(updateQna);
		return ResponseEntity.ok().build();
	}

}
