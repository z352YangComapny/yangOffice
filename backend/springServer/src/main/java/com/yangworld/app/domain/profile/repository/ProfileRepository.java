package com.yangworld.app.domain.profile.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.profile.dto.ProfileDto;
import com.yangworld.app.domain.profile.entity.AttachmentProfile;
import com.yangworld.app.domain.profile.entity.ProfileDetails;

@Mapper
public interface ProfileRepository {

//	@Insert("insert into profile (id, member_id, state, introduction) " +
//            "values (seq_profile_id.nextval, #{memberId}, #{state}, #{introduction})")
//    @SelectKey(
//            statement = "select seq_profile_id.currval FROM dual",
//            keyColumn = "id",
//            keyProperty = "id",
//            before = false,
//            resultType = int.class
//    )
//    int insertProfile(ProfileDto profile);

	
	

	@Update("update profile set state = 'A', introduction = '' where member_id = #{memberId}")
	int resetProfile(int memberId);

	
	
	
// ------------------------- insert --------------------------------------------------------
	@Insert("insert into profile (id, member_id, state, introduction) " +
            "values (seq_profile_id.nextval, #{memberId}, #{state}, #{introduction})")
    @SelectKey(
            statement = "select seq_profile_id.currval FROM dual",
            keyColumn = "id",
            keyProperty = "id",
            before = false,
            resultType = int.class
    )
	int insertProfile(ProfileDetails profile);

	@Insert("insert into attachment (id, original_filename, renamed_filename) values (seq_attachment_id.nextval, #{originalFilename}, #{renamedFilename})")
	@SelectKey(
            statement = "select seq_attachment_id.currval FROM dual",
            keyColumn = "id",
            keyProperty = "id",
            before = false,
            resultType = int.class
    )
	int insertAttachment(Attachment attach);
	
	@Insert("insert into attachment_profile values(#{attachId}, #{profileId})")
	int insertAttachmentProfile( @Param("attachId") int attachId, @Param("profileId")int profileId);
//	@Insert("insert into attachment_profile (attachment_id, profile_id) "
//            "values (#{attachmentId}, #{profileId})")
//    int insertAttachmentProfile(AttachmentProfile attachmentProfile);
// ------------------------- insert ----------------------------------------------------------

	

	@Update("update profile set state = #{state}, introduction = #{introduction} where member_id = #{memberId}")
	int updateProfile(ProfileDetails profile);

	@Update("update attachment set original_filename = #{originalFilename}, renamed_filename = #{renamedFilename} where id = #{id}")
	int updateAttachment(Attachment attach);

	@Update("update attachment_profile set attachment_id = #{attachmentId}, profile_id = #{profileId} where attachment_id = #{attachmentId} and profile_id = #{profileId}")
	int updateAttachmentProfile(AttachmentProfile attachmentProfile);




	@Select("select * from profile where member_id = #{memberId}")
    ProfileDetails getProfileByMemberId(int memberId);



	
	
	
	
	
	
	

//	@Update("update profile set state = 'A', introduction = '' where member_id = #{memberId}")
//	int resetProfile(ProfileDto profile);
	
}