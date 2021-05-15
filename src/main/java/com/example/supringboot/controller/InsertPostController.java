package com.example.supringboot.controller;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.supringboot.domain.Post;
import com.example.supringboot.service.PostService;




@Controller
@RequestMapping({"/post/createPost"})
@SessionAttributes({"postForm"})
public class InsertPostController {
	private static final Logger logger = LoggerFactory.getLogger(InsertPostController.class);

	
	@Value("post/postInsertForm")
	private String postFormView;
	
	@Autowired
	private PostService postService;
	
//	HttpServlet이 매개변수로 들어가면 오류남
	@ModelAttribute("postForm")
	public Post formBacking() {
		return new Post();
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

	@GetMapping()
	public String postForm() {
		logger.info("postForm()");
		
		return postFormView;
	}
	
	@PostMapping()
	public String postInsert(HttpServletRequest request,
			@ModelAttribute("postForm") Post newPost) {
		logger.info("postInsert()");
		
//		유효성 검사 추가
		
//		newPost의 날짜를 Timestamp로 변환
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		    Date parsedDate = (Date) dateFormat.parse(newPost.getExp_dt_string());
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    newPost.setExp_dt(timestamp);
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		
		try {
			boolean isRegistered = postService.registerPost(newPost);
			if (isRegistered == false) {
				// TODO => 게시글 등록에 실패하였다는 메시지를 전달
			}
			else {
				//	게시글 등록 성공시 session에서 삭제해야 할까?
			}
		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
		}
		//		게시글 확인하는 페이지로 리다이렉트할 것
		return "index";
	}
}
