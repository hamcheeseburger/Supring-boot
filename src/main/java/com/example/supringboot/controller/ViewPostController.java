package com.example.supringboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.Post;
import com.example.supringboot.service.PostService;

@Controller
public class ViewPostController {
	private static final Logger logger = LoggerFactory.getLogger(GetAllPostController.class);

	@Value("post/viewPost2")
	private String postDetailView;
	
	@Autowired
	private PostService postService;
	
	@GetMapping(value = "/post/viewPost")
	public String viewDetailPost(@RequestParam(value = "post_id", required = false) Long post_id,
			Model model, HttpServletRequest request) {
		logger.info("viewDetailPost()에 들어왔어요.");
		
		if (post_id == null) {
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/post/getPostList";
		}
		// null 처리는 위에서!
		int idx = post_id.intValue();
		Post post = postService.getDetailPost(idx);
		if (post == null) {
			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/post/getPostList";
		}
		model.addAttribute("post", post);

		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		int user_id = -1;
		if (userSession != null) {
			user_id = userSession.getAccount().getUser_id();
		}
		model.addAttribute("user", user_id);
		
		return postDetailView;
	}
}
