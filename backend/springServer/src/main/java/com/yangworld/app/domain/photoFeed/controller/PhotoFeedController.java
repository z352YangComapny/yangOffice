package com.yangworld.app.domain.photoFeed.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.yangworld.app.commons.FileUploadUtils;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.comments.dto.CommentAllDto;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.service.CommentsService;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.service.MemberService;
import com.yangworld.app.domain.photoFeed.dto.FeedCreateDto;
import com.yangworld.app.domain.photoFeed.dto.FeedDeleteDto;
import com.yangworld.app.domain.photoFeed.dto.PhotoAttachmentFeedDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.Like;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import com.yangworld.app.domain.photoFeed.service.PhotoFeedService;

import lombok.extern.slf4j.Slf4j;
//import oracle.jdbc.proxy.annotation.Post;

@Controller
@Slf4j
@RequestMapping("/member/userPage/{id}")
public class PhotoFeedController {

    @Autowired
    private PhotoFeedService photoFeedService;

    @Autowired
    @Qualifier("FeedCommentsServiceImpl")
    private CommentsService commentService;


    // 페이지 이동
    @GetMapping("/feedCreate")
    public String feedCreate(@PathVariable("id") int id,
                             Model model) {
        model.addAttribute("id", id);
        return "feed/feedCreate";
    }

    @GetMapping("/feed/feedDetail")
    public String feedDetails(
            @PathVariable("id") int id,
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestParam int photoFeedId,
            Model model) {

        int writerId = principalDetails.getId();

        // 피드 조회
        List<PhotoAttachmentFeedDto> photoDetail = photoFeedService.selectFeedDetail(writerId, photoFeedId);

        List<CommentAllDto> commentList = commentService.getCommentsByPhotoFeedId(photoFeedId);

        PhotoFeed photoFeed = photoFeedService.findById(photoFeedId);

        int likeCount = photoFeedService.getLikeCountForFeed(photoFeedId);

        FeedDetails response = FeedDetails.builder()
                .id(photoFeedId)
                .writerId(photoFeed.getWriterId())
                .likeCount(likeCount)
                .content(photoFeed.getContent())
                .build();

        Collections.sort(commentList, (c1, c2) -> c2.getRegDate().compareTo(c1.getRegDate()));
        model.addAttribute("id", id);
        model.addAttribute("commentList", commentList);
        model.addAttribute("response", response);
        model.addAttribute("photoDetail", photoDetail);
        model.addAttribute("principalDetails", principalDetails);
        return "feed/feedDetail";
    }


    // 피드 만들기
    @PostMapping("/feedCreated")
    public String peedCreate(
            @ModelAttribute("feedFrm") @Valid FeedCreateDto _feed,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails member,
            @RequestPart(value = "photo", required = false) List<MultipartFile> upFiles)
            throws IllegalStateException, IOException {


        List<Attachment> attachments = new ArrayList<>();

        for (MultipartFile upFile : upFiles) {
            if (!upFile.isEmpty()) {
                String originalFilename = upFile.getOriginalFilename();
                String renamedFilename = FileUploadUtils.getRenameFilename(originalFilename);
                File destFile = new File(renamedFilename);
                upFile.transferTo(destFile);

                Attachment attach =
                        Attachment.builder()
                                .originalFilename(originalFilename)
                                .renamedFilename(renamedFilename)
                                .build();
                attachments.add(attach);
            }
        }

        FeedDetails feed = FeedDetails.builder()
                .writerId(member.getId())
                .content(_feed.getContent())
                .attachments(attachments)
                .build();


        int result = photoFeedService.insertFeed(feed);

        if (result > 0) {

            return "redirect:/member/userPage/" + member.getId();

        } else {
            return "forward:/index.do";
        }
    }


    @PostMapping("/feedDetails/feedDelete")
    public String deleteFeed(
            @RequestParam int feedId,
            @AuthenticationPrincipal PrincipalDetails member,
            @PathVariable("id") int id
    ) {

        int result = photoFeedService.deleteFeed(feedId);

        return "redirect:/member/userPage/" + id;
    }

    @PostMapping("/feedDetails/feedUpdate")
    public String updateFeed(
            @RequestParam int feedId,
            @RequestParam String content,
            @PathVariable("id") int id
    ) {

        int result = photoFeedService.updateFeed(feedId, content);

        return "redirect:/member/userPage/" + id + "/feed/feedDetail?photoFeedId=" + feedId;
    }

    @PostMapping("/feedDetails/feedLikeUpdate")
    public String feedLikeUpdate(
            @RequestParam int feedId,
            @RequestParam int memberId,
            @PathVariable("id") int id
    ) {

        log.info("id = {}", id);

        Like likeCount = photoFeedService.getLikeCount(feedId, memberId);

        if (likeCount != null) {
            photoFeedService.deleteLike(feedId, memberId);
        } else {
            photoFeedService.insertLike(feedId, memberId);
        }

        return "redirect:/member/userPage/" + id + "/feed/feedDetail?photoFeedId=" + feedId;
    }


}
