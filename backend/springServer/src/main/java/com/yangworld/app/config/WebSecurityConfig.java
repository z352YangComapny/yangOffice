@Override
	public List<PhotoAttachmentFeedDto> selectFeed(int writerId) {
	    if (writerId < 0) {
	        log.error("username is null");
	        throw new NullPointerException("유저이름이 없습니다.");
	    } else {
	    	
	    	// 인증된 회원 아이디를 갖고 피드 검색
	        List<PhotoAttachmentFeedDto> photoFeedList = photoFeedRepository.selectFeed(writerId);
	        
	        log.info("List size: [{}]", photoFeedList.size());
	        
	        for (PhotoAttachmentFeedDto photoFeed : photoFeedList) {
	        	// 검색 결과 id를 가지고 연결 테이블 검색
	            List<AttachmentPhotoDto> attachmentPhotoDto = photoFeedRepository.selectAttachmentPhoto(photoFeed.getId());
	            
	            // list만들어주기
	            List<Attachment> attachmentList = new ArrayList<>();
	            
	            
	            // photoFeed에 attachmentPhotoDto 라는 List<AttachmentPhotoDto>에 1번째 검색결과 넣기
	            photoFeed.setAttachmentPhotoDto(attachmentPhotoDto);
	            
//	            log.info("photo feed check: {}", photoFeed);
	            
	            for (AttachmentPhotoDto attachments : attachmentPhotoDto) {
	            	// 두번째 검색 결과를 받음
	                int id = attachments.getAttachmentId();
	                
	                // 3번째 검색 = attachment 테이블에 2번째 검색결과들로 조회
	                Attachment attachment = photoFeedRepository.selectAttachment(id);
	                
	                attachmentList.add(attachment);
	            }
	            
	            // set
	            photoFeed.setAttachments(attachmentList);
	            
	            // 좋아연
	            int likeCount = photoFeedRepository.getLikeCount(photoFeed.getId());
	            
	            
	            // 댓글 수 조회
	            int commentCount = photoFeedRepository.getCommentCount(photoFeed.getId());
	            
	            
	            
	            photoFeed.setLikeCount(likeCount);
	            
	        }
	        
	        return photoFeedList;
	    }
	}
