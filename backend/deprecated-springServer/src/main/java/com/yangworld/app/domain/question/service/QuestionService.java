package com.yangworld.app.domain.question.service;

import java.util.List;
import java.util.Map;

import com.yangworld.app.domain.question.dto.QuestionUpdateQnaDto;
import com.yangworld.app.domain.question.entity.Question;

public interface QuestionService {

	int insertQna(Question qna);

	int updateQna(Question updateQna);

	List<Question> findAllQuestion(Map<String, Object> params);

	Question findQuestionById(int id);

	int deleteNoticeById(int questionId);

	int updateQuestion(QuestionUpdateQnaDto updateDto);


}
