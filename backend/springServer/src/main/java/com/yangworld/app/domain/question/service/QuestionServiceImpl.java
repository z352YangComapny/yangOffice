package com.yangworld.app.domain.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.question.entity.Question;
import com.yangworld.app.domain.question.repository.QuestionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public int insertQna(Question qna) {
		int result = questionRepository.insertQna(qna);
		return result;
	}
	
	@Override
	public int updateQna(Question updateQna) {
		int result = questionRepository.updateQna(updateQna);
		return result;
	}
	
}
