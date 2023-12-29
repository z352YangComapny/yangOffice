package com.yangworld.app.domain.question.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.question.dto.QuestionUpdateQnaDto;
import com.yangworld.app.domain.question.entity.Comment;
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
	
	@Override
	public List<Question> findAllQuestion(Map<String, Object> params) {
		int limit = (int) params.get("limit");
		int page = (int) params.get("page");
		int offset = (page - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		return questionRepository.findAllQuestion(rowBounds);
	}
	
	@Override
	public Question findQuestionById(int id) {
		Question question = questionRepository.findQuestionById(id);
		return question;
	}
	
	@Override
	public int deleteNoticeById(int questionId) {
		
		return questionRepository.deleteNoticeById(questionId);
	}

	@Override
	public int updateQuestion(QuestionUpdateQnaDto updateDto) {
		int result = questionRepository.updateQuestion(updateDto); 
		return result;
	}
}
