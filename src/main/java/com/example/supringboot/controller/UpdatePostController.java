package com.example.supringboot.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.example.supringboot.domain.Image;
import com.example.supringboot.domain.Post;
import com.example.supringboot.service.PostInsertValidator;
import com.example.supringboot.service.PostService;

@Controller
@SessionAttributes({"postForm"})
public class UpdatePostController {
	private static final Logger logger = LoggerFactory.getLogger(UpdatePostController.class);

	@Value("post/postUpdateForm")
	private String postFormView;
	
	@Autowired
	private PostService postService;
	
//	HttpServlet이 매개변수로 들어가면 오류남
	@ModelAttribute("postForm")
	public PostForm formBacking() {
		return new PostForm();
	}
	
	@ModelAttribute("trade_types")
	public String[] getTradeTypes() {
		return new String[] {"trade", "payment"};
	}
	
	@ModelAttribute("status")
	public String[] getStatus() {
		return new String[] {"0", "1"};
	}
	
	@ModelAttribute("ship_types")
	public String[] getShipTypes() {
		return new String[] {"0", "1"};
	}

	@GetMapping(value = "/post/updatePost")
	public String updatePostForm(@RequestParam(value = "post_id", required = false) Long idx, 
			Model model, @ModelAttribute("postForm") PostForm postForm) {
		
//		dao에서 Post 객체를 받아온 후 Service에서 PostForm으로 변환한 후 전달받는다.
		int post_id = idx.intValue();
		Post post = postService.getDetailPost(post_id);
		if (post == null) {
			return "redirect:/post/getPostList";
		}
//		Service에서 Post => PostForm으로 변환한다.
		postForm = postService.postToPostForm(post);
		logger.info("postUpdate() - updatePostForm의 post의 image: " + post.getImages().get(0).getImage());
		logger.info("postUpdate() - updatePostForm의 postForm의 image: " + postForm.getImages().get(0).getImage());
		
		model.addAttribute("postForm", postForm);
		
		return postFormView;
	}
	
	@PostMapping(value = "/post/updatePost")
	public String updatePost(HttpServletRequest request,
			@Valid @ModelAttribute("postForm") PostForm postForm, BindingResult bindingResult, SessionStatus sessionStatus) {
		
		
		new PostInsertValidator().validate(postForm, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return postFormView;
		}
//		post_id 및 user_id는 이미 들어가있다.
		
		logger.info("postUpdate() - postForm.getFile() != null 인가요? : " + (postForm.getFile() != null));
		logger.info("postUpdate() - postForm.getFileChanged() 값은? : " + postForm.getFileChanged());

//		이미지 처리
		if (postForm.getFile() != null && postForm.getFileChanged().equals("changed")) {
			MultipartFile imageFile = postForm.getFile();
			try {
				byte[] imageContentBytes = imageFile.getBytes();
				
				Image image = new Image(); 
				image.setImage(imageContentBytes);
				
				ArrayList<Image> imageList = new ArrayList<Image>();
				imageList.add(image);
				postForm.setImages(imageList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		logger.info("postUpdate() - updatePost : " + postForm.getImages().get(0).getImage());
		
		int registered_id = -1;
		try {
			registered_id = postService.updatePost(postForm);
			if (registered_id == -1) {
				// TODO => 게시글 수정에 실패하였다는 메시지를 전달
			}
			else {
				//	게시글 수정 성공시 session에서 삭제해야 할까?
				sessionStatus.setComplete();
			}
		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
		}
		logger.info("postUpdate() - registered_id : " + registered_id);
		//		게시글 확인하는 페이지로 리다이렉트할 것
		return "redirect:/post/viewPost?post_id=" + registered_id;
	
		
	}
}
