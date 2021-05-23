package com.example.supringboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.service.CommentService;

@RestController
public class InsertCommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(InsertCommentController.class);

	
	@Autowired
	private CommentService commentService;
	
	@PostMapping(value = "/comments")
	public JSONObject registerComment(@RequestBody Comment params, HttpServletRequest request) {
		logger.info("댓글 등록");
		
		// params에 usersession을 통해 user_id 삽입하기
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int user_id = userSession.getAccount().getUser_id();
		
		Account user = new Account();
		user.setUser_id(41);
		params.setUser(user);
		
		JSONObject jsonObject = new JSONObject();
		
		try {
			int result = commentService.insertComment(params);
			boolean isRegistered = false;
			if (result > 0) {
				isRegistered = true;
			}
//			댓글 등록 완료여부
			jsonObject.put("result", isRegistered);
		} catch (Exception e) {
			jsonObject.put("message", "시스템에 문제가 발생하였습니다.");
		}
		
		return jsonObject;
	}
	
	
}
