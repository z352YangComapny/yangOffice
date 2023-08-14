package com.yangworld.app.domain.question.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.yangworld.app.domain.question.entity.Question;

@Mapper
public interface QuestionRepository {

	@Insert("insert into question values(seq_question_id.nextval, #{writerId}, #{title}, #{content},'Q', default)")
	@SelectKey(
		    statement = "select seq_question_id.currval from dual",
		    keyColumn = "id",
		    keyProperty = "id",
		    before = false,
		    resultType = int.class
		)
	int insertQna(Question qna);

	@Update("update question set title=#{title}, content=#{content} where id =#{id}")
	int updateQna(Question updateQna);

	@Select("select * from question order by type")
	List<Question> findAllQuestion();

}
