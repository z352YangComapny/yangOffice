package com.yangworld.app.domain.question.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.yangworld.app.domain.question.entity.Question;

@Mapper
public interface QuestionRepository {

	@Insert("insert into question values(seq_question_id.nextval, #{writerId}, #{title}, #{content},#{type}, default)")
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

	@Select("SELECT * FROM question ORDER BY CASE WHEN type = 'N' THEN 0 ELSE 1 END, id")
	List<Question> findAllQuestion(RowBounds rowBounds);

	@Select("select * from question where id = #{id}")
	Question findQuestionById(int id);

	@Select("select count(*) from question")
	int countAllQuestion();

	@Delete("delete question where id = #{questionId}")
	int deleteNoticeById(int questionId);

}
