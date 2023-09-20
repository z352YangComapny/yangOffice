package com.yangworld.app.domain.profile.repository;


import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.profile.dto.AttachmentProfileDto;
import com.yangworld.app.domain.profile.dto.ProfileAll;
import com.yangworld.app.domain.profile.entity.Profile;
import com.yangworld.app.domain.profile.entity.ProfileDetails;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProfileRepository {

    @Select("select * from profile where member_id = #{id}")
    Profile findProfileByMemberIdForAdmin(int id);

    @Select("select * from member where userName = #{userName}")
    Member findByUsername(String userName);

    @Select("select * from profile where member_id = #{memberId}")
    List<ProfileAll> findAllProfileByMemberId(int memberId);

    @Select("select * from attachment_profile where profile_id = #{profileId}")
    List<AttachmentProfileDto> findAttachmentProfileByProfileId(int profileId);

    @Select("select * from attachment where id = #{id}")
    List<Attachment> findAttachmentsById(int attachmentId);

    @Select("select * from profile where profile_id = #{memberId} and rownum <= 1")
    Profile findbyId(int memberId);


    @Update("update profile set state = #{state}, introduction = #{introduction} where member_id = #{memberId}")
    int updateProfile(ProfileDetails profile);

    @Update("update attachment set original_filename = #{originalFilename}, renamed_filename= #{renamedFilename} where id = #{id}")
    int updateAttachment(Attachment attach);


    @Insert("insert into profile (id, member_id, state, introduction) values (seq_profile_id.nextval, #{memberId}, #{state}, #{introduction})")
    int insertProfile(ProfileDetails profile);

    @Insert("insert into attachment (id, original_filename, renamed_filename) values (seq_attachment_id.nextval, #{originalFilename}, #{renamedFilename})")
    int insertAttachment(Attachment attach);

    @Insert("insert into attachment_profile (attachment_id, profile_id) values (seq_attachment_id.currval, seq_profile_id.currval)")
    int insertAttachmentProfile();

    
}



