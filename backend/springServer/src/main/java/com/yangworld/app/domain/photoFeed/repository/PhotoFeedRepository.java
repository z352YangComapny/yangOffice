package com.yangworld.app.domain.photoFeed.repository;

import com.yangworld.app.domain.photoFeed.dto.PhotoFeedDailyDto;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface PhotoFeedRepository {

	@Insert("insert into photo_feed (id, writer_id, content) values (seq_attachment_id.nextval, #{writerId}, #{content})")
	int insertPeed(FeedDetails peed);


    @Insert("insert into attachment (id, original_filename, renamed_filename) values (seq_attachment_id.nextval, #{originalFilename}, #{renamedFilename})")
    int insertAttachment(Attachment attach);

    @Select("select count(*) from photo_feed")
    int getPhotoFeedTotalCount();
    @Select("select * from photo_feed order by id DESC")
    List<PhotoFeed> getPhotoRawFeed(RowBounds rowBounds);

    @Select("select * from photo_feed where id = #{feedId}")
    PhotoFeed findPhotoFeedById(int feedId);

    @Select("SELECT\n" +
            "    TRUNC(dates.photofeed_date) AS photofeed_date,\n" +
            "    NVL(COUNT(PHOTO_FEED.reg_date), 0) AS photofeed_count\n" +
            "FROM\n" +
            "    (SELECT TRUNC(SYSDATE) - LEVEL + 1 AS photofeed_date\n" +
            "     FROM dual\n" +
            "     CONNECT BY LEVEL <= 10) dates\n" +
            "        LEFT JOIN\n" +
            "    PHOTO_FEED ON TRUNC(PHOTO_FEED.reg_date) = dates.photofeed_date\n" +
            "GROUP BY\n" +
            "    TRUNC(dates.photofeed_date)\n" +
            "ORDER BY\n" +
            "    TRUNC(dates.photofeed_date)")
    List<PhotoFeedDailyDto> findPhotoFeedDaily();
}
