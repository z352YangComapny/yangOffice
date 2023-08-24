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

import com.yangworld.app.commons.HelloSpringUtils;
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
//@RequestMapping("/feed")
public class PhotoFeedController {
	
	@Autowired
	private PhotoFeedService photoFeedService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	@Qualifier("FeedCommentsServiceImpl")
	private CommentsService commentService;
	
	@GetMapping("/somePage")
	public String somePage(Model model) {
	    // 실제 값을 설정하여 모델에 추가
	    String writer = "some_writer_value";
	    int photoFeedId = 123; // 실제 값으로 변경

	    model.addAttribute("writer", writer);
	    model.addAttribute("photoFeedId", photoFeedId);

	    return "your_html_page"; // 실제 페이지의 이름으로 변경
	}


	// 페이지 이동
	@GetMapping("/feed/feedCreate.do")
	public void feedCreate() {}
	
	// 피드 디테일
	@GetMapping("/feed/feedDetail")
	public void feedDetails(@AuthenticationPrincipal PrincipalDetails principalDetails,
			@RequestParam int photoFeedId,
			Model model) {
		
	
		int writerId = principalDetails.getId();
		
		
        // 피드 조회
		List<PhotoAttachmentFeedDto> photoDetail = photoFeedService.selectFeedDetail(writerId, photoFeedId); 
		List<CommentAllDto> commentList = commentService.getCommentsByPhotoFeedId(photoFeedId);
    	PhotoFeed photoFeed = photoFeedService.findById(photoFeedId);

        FeedDetails response = FeedDetails.builder()
                .id(photoFeedId)
                .writerId(writerId)
                .content(photoFeed.getContent())
                .build();
        Collections.sort(commentList, (c1, c2) -> c2.getRegDate().compareTo(c1.getRegDate()));
        log.info("commentList ={}",commentList);
        
        model.addAttribute("commentList", commentList);
        model.addAttribute("response", response);
        model.addAttribute("photoDetail", photoDetail);
        model.addAttribute("principalDetails", principalDetails);
	}

	
	// 피드 만들기
	@PostMapping("/feedCreated.do")
	public String peedCreate(
			@ModelAttribute("feedFrm") @Valid FeedCreateDto _feed,
	        BindingResult bindingResult,
	        @AuthenticationPrincipal Member member,
	        @RequestPart(value = "photo", required = false) List<MultipartFile> upFiles)
	        throws IllegalStateException, IOException {

		
		
		if(member == null) {
			return "forward:/index.jsp";
		}
		
		List<Attachment> attachments = new ArrayList<>();
		
		for(MultipartFile upFile : upFiles){
			if(!upFile.isEmpty()) { 
				String originalFilename = upFile.getOriginalFilename(); 
				String renamedFilename = HelloSpringUtils.getRenameFilename(originalFilename); 
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
			return "redirect:/";
	    } else {
	        // 생성 중 오류가 발생한 경우
	        return "forward:/index.do";
	    }
	}
	/**
	 * 회원 조회
	 */
	@GetMapping("/userPage/{id}")
	@PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능
	public String selectFeed(
			@PathVariable("id") int id,
			@AuthenticationPrincipal @Valid PrincipalDetails principalDetails,
			Model model
			) {
		
		if (principalDetails == null) {
	        return "forward:/index.jsp";  
	    }
		
		int writerId = principalDetails.getId();
		
		List<PhotoAttachmentFeedDto> photoList = photoFeedService.selectFeed(id);

	    model.addAttribute("photoList", photoList);
	    
		return "forward:/index.jsp"; 

	}
	
	
	@PostMapping("/feedDetails/feedDelete")
	public String deleteFeed(@RequestParam int feedId){
		
		 int result = photoFeedService.deleteFeed(feedId);
		
		 return "redirect:/";
	}
	
	@PostMapping("/feedDetails/feedUpdate")
	public String updateFeed(
			@RequestParam int feedId,
			@RequestParam String content
			){
		
		int result = photoFeedService.updateFeed(feedId, content);
		
		return "redirect:/feed/feedDetail?photoFeedId=" + feedId;
	}
	
	@PostMapping("/feedDetails/feedLikeUpdate")
	public String feedLikeUpdate(
			@RequestParam int feedId,
			@RequestParam int memberId) {
		
		
		log.info("feedId = {}", feedId);
		log.info("memberId = {}", memberId);
		
		Like likeCount = photoFeedService.getLikeCount(feedId, memberId);
		
		if (likeCount != null) {
	        photoFeedService.deleteLike(feedId, memberId);
	    } else {
	    	photoFeedService.insertLike(feedId, memberId);
	    }
		
		return "redirect:/feed/feedDetail?photoFeedId=" + feedId;
	}
	

	
	
	
	
	

	

	
	
	
	
	
	
	
	
	
	
}
