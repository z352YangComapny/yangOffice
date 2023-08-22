package com.yangworld.app.domain.photoFeed.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.dto.AttachmentPhotoDto;
import com.yangworld.app.domain.photoFeed.dto.FeedCreateDto;
import com.yangworld.app.domain.photoFeed.dto.PhotoAttachmentFeedDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.Like;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import com.yangworld.app.domain.question.entity.Comment;

@Mapper
public interface PhotoFeedRepository {
    // 피드 create
	@Insert("insert into photo_feed (id, writer_id, content) values (seq_photo_feed_id.nextval, #{writerId}, #{content})")
	int insertFeed(FeedDetails peed);

    // 피드 create
    @Insert("insert into attachment (id, original_filename, renamed_filename) values (seq_attachment_id.nextval, #{originalFilename}, #{renamedFilename})")
    int insertAttachment(Attachment attach);
    // 피드 create    
    @Insert("insert into attachment_photo_feed (attachment_id, photo_feed_id) values (seq_attachment_id.currval, seq_photo_feed_id.currval)")
    int insertLink();
    
    // 피드 조회
    @Select("select * from photo_feed where writer_id = #{writerId}") 
    List<PhotoAttachmentFeedDto> selectFeed(int writerId);
    
    // 피드 조회
    @Select("select * from attachment_photo_feed where photo_feed_id = #{photoFeedId}") 
    List<AttachmentPhotoDto> selectAttachmentPhoto(int photoFeedId);
    
    // 사진 조회
	@Select("select * from attachment where id = #{id}")
	Attachment selectAttachment(int id);
	
	
	// photo_feed delete
	@Delete("delete from photo_feed where id = #{feedId}")
	int deleteFeed(int feedId);

	// attachment_photo_feed delete (link table)
	@Delete("delete from attachment where id not in (select attachment_id from attachment_photo_feed)")
	int deleteAttachment(int feedId);

	// attachment delete
	@Delete("delete from attachment_photo_feed where photo_feed_id = #{feedId}")
	int deleteLink(int feedId);

    // 피드 글 수정
	@Update("update photo_feed set content = #{content} where id = #{feedId}")
	int updateFeed(@Param("feedId") int feedId, @Param("content") String content);

	@Select("select * from photo_feed where id = #{photoFeedId}")
	PhotoFeed findById(int photoFeedId);

	@Select("select * from likes where photo_feed_id = #{photoFeedId}")
	List<Like> findLikeById(int photoFeedId);

	@Insert("insert into likes (photo_feed_id, member_id) values(#{photoFeedId}, #{memberId})")
	int insertLike(@Param(value = "photoFeedId") int photoFeedId, @Param(value = "memberId") int memberId);

	@Delete("delete from likes where photo_feed_id = #{photoFeedId} and member_id = #{memberId}")
	int deleteLike(@Param(value = "photoFeedId")int photoFeedId,  @Param(value = "memberId")int memberId);

	@Select("select count(*) from likes where photo_feed_id = #{id}")
	int getLikeCount(int id);

	@Select("select count(*) as comment_count from comments_feed where photo_feed_id = #{id}")
	int getCommentCount(int id);
	
//	@Select("select * from photo_feed where id = #{photoFeedId}")
	// 이거 가지고 조회하면 되겠따 ㅋ
	@Select("select a.* from attachment a join attachment_photo_feed af on a.id = af.attachment_id where af.photo_feed_id = #{photoFeedId}")
	List<PhotoAttachmentFeedDto> selectFeedDetail(int photoFeedId);

	@Select("select * from attachment_photo_feed where photo_feed_id = #{id}")
	List<AttachmentPhotoDto> selectAttachmentPhotoDetail(int id);
	
	@Select("select * from attachment where id = #{id}")
	Attachment selectAttachmentDetail(int id);



	


    
    
}
