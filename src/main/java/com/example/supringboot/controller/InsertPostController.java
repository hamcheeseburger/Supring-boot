package com.example.supringboot.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.constant.Method;
import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Image;
import com.example.supringboot.domain.Post;
import com.example.supringboot.service.PostInsertValidator;
import com.example.supringboot.service.PostService;
import com.example.supringboot.util.UiUtils;

@Controller
//@RequestMapping({"/post/createPost"})
@SessionAttributes({"postForm"})
public class InsertPostController extends UiUtils {
	private static final Logger logger = LoggerFactory.getLogger(InsertPostController.class);

	

	@Value("post/postInsertForm")
	private String postFormView;
	
	@Autowired
	private PostService postService;
	
//	HttpServlet이 매개변수로 들어가면 오류남
	@ModelAttribute("postForm")
	public PostForm formBacking() {
		PostForm postForm = new PostForm();
		postForm.setTrade_type("payment");
		return postForm;
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

	@GetMapping("/post/createPost")
	public String postForm() {
		logger.info("postForm()");
		
		return postFormView;
	}
	
	@PostMapping("/post/createPost")
	public String postInsert(HttpServletRequest request,
			@Valid @ModelAttribute("postForm") PostForm postForm, BindingResult bindingResult, SessionStatus sessionStatus, Model model) {
		logger.info("postInsert()");
		
		new PostInsertValidator().validate(postForm, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return postFormView;
		}
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int user_id = userSession.getAccount().getUser_id();
		
		Account user = new Account();
		user.setUser_id(user_id);
		postForm.setUser(user);
		
//		이미지 처리
		if (postForm.getFile() != null) {
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
		int registered_id = -1;
		try {
			registered_id = postService.registerPost(postForm);
			if (registered_id == -1) {
				// TODO => 게시글 등록에 실패하였다는 메시지를 전달
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/post/getPostList", Method.GET, null, model);
			}
			else {
				//	게시글 등록 성공시 session에서 삭제
				sessionStatus.setComplete();
				
			} 
		}  catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/post/getPostList", Method.GET, null, model);

		} 
		catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
			e.printStackTrace();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/post/getPostList", Method.GET, null, model);
		}
		//		게시글 확인하는 페이지로 리다이렉트할 것
//		return "redirect:/post/viewPost?post_id=" + registered_id;
		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/post/getPostList", Method.GET, null, model);
	}
}
