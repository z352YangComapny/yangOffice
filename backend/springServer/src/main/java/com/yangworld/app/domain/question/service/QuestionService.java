package com.yangworld.app.domain.question.service;

import java.util.List;

import com.yangworld.app.domain.question.entity.Question;

public interface QuestionService {

	int insertQna(Question qna);

	int updateQna(Question updateQna);

	List<Question> findAllQuestion();

	Question findQuestionById(int id);

}
