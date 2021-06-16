package com.example.supringboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.constant.Method;
import com.example.supringboot.domain.Post;
import com.example.supringboot.service.PostService;
import com.example.supringboot.util.UiUtils;

@Controller
public class DeletePostController extends UiUtils{
	private static final Logger logger = LoggerFactory.getLogger(DeletePostController.class);


	@Autowired
	private PostService postService;
	
	
	@PostMapping(value = "/post/deletePost") 
	public String deletePost(@RequestParam(value = "post_id", required = false) Long idx, 
			Model model, HttpServletRequest request) {
		if (idx == null) {
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
//			return "redirect:/post/getPostList";
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/post/getPostList", Method.GET, null, model);
		}
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		int user_id = -1;
		if (userSession != null) {
			user_id = userSession.getAccount().getUser_id();
		}
		
		int post_id = idx.intValue();
		// URL로 삭제하려는 시도를 막는다.
		Post post = postService.getDetailPost(post_id);
		
		if (post.getUser().getUser_id() != user_id) {
			return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/post/getPostList", Method.GET, null, model);
		}
			
		try {
			int deleted = postService.deletePost(post_id);
			if (deleted <= 0) {
				// TODO => 게시글 삭제에 실패하였다는 메시지를 전달
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/post/getPostList", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/post/getPostList", Method.GET, null, model);

		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/post/getPostList", Method.GET, null, model);
		}
//		return "redirect:/post/getPostList";
		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/post/getPostList", Method.GET, null, model);
	}
}
