package com.example.supringboot.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supringboot.controller.InsertPostController;
import com.example.supringboot.controller.PostForm;
import com.example.supringboot.dao.mybatis.MybatisPostDao;
import com.example.supringboot.domain.Criteria;
import com.example.supringboot.domain.Image;
import com.example.supringboot.domain.PaginationInfo;
import com.example.supringboot.domain.Post;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	MybatisPostDao postDao;

	private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

	
	@Override
	public int registerPost(PostForm postForm) {
		int result = 0;
	
		Post post = new Post();
		post.setUser(postForm.getUser());
		post.setTitle(postForm.getTitle());
		post.setContent(postForm.getContent());
		post.setFood_name(postForm.getFood_name());
//		str_quantity를 int형으로 변환 후 삽입 (거래방식이 교환일 경우 price는 없다.)
		if (postForm.getStr_price() != null && !postForm.getStr_price().equals("")) {
			post.setPrice(Integer.parseInt(postForm.getStr_price()));
		}	
		post.setTrade_type(postForm.getTrade_type());
		post.setTrade_status(postForm.getTrade_status());
//		str_quantity를 int형으로 변환 후 삽입
		post.setQuantity(Integer.parseInt(postForm.getStr_quantity()));
		post.setUnit(postForm.getUnit());	
//		postForm의 날짜를 Timestamp로 변환
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date parsedDate = (Date) dateFormat.parse(postForm.getExp_dt_string());
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    post.setExp_dt(timestamp);
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		post.setShip_type(postForm.getShip_type());
		if (postForm.getImages() != null)
			post.setImages(postForm.getImages());
		
		result = postDao.insertPost(post);
		
		return (result == 1) ? post.getPost_id() : -1;
		
	}

	@Override
	public int updatePost(PostForm postForm) {
		int result = 0;
		
		Post post = new Post();
//		수정할땐 post_id 지정해줘야 함.(수정시 post_id 기준으로 수정)
		post.setPost_id(postForm.getPost_id());
		post.setUser(postForm.getUser());
		post.setTitle(postForm.getTitle());
		post.setContent(postForm.getContent());
		post.setFood_name(postForm.getFood_name());
//		str_quantity를 int형으로 변환 후 삽입
		post.setPrice(Integer.parseInt(postForm.getStr_price()));
		post.setTrade_type(postForm.getTrade_type());
		post.setTrade_status(postForm.getTrade_status());
//		str_quantity를 int형으로 변환 후 삽입
		post.setQuantity(Integer.parseInt(postForm.getStr_quantity()));
		post.setUnit(postForm.getUnit());	
		
//		postForm의 날짜를 Timestamp로 변환
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date parsedDate = (Date) dateFormat.parse(postForm.getExp_dt_string());
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    post.setExp_dt(timestamp);
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		post.setShip_type(postForm.getShip_type());
		if (postForm.getImages() != null)
			post.setImages(postForm.getImages());
		
//		if (postForm.getImages() != null) {
//			ArrayList<Image> imagesList = new ArrayList<Image>();
//			Image image = new Image();
//			image = postForm.getImages().get(0);
//			imagesList.add(image);
//			post.setImages(imagesList);
//		}
		
		logger.info("post service의 postForm의 image : " + postForm.getImages().get(0).getImage());
		logger.info("post service의 post의 image : " + post.getImages().get(0).getImage());

		
		result = postDao.updatePost(post, postForm);
		
		return (result == 1) ? post.getPost_id() : -1;
	}

	@Override
	public ArrayList<Post> getPostList(Post post) {
		ArrayList<Post> postList = new ArrayList<Post>();
//		int postCnt = postDao.getPostCount();
		int postCnt = postDao.selectPostTotalCount(post);
		
		PaginationInfo paginationInfo = new PaginationInfo(post);
		paginationInfo.setTotalRecordCount(postCnt);
		
		post.setPaginationInfo(paginationInfo);
		
		ArrayList<Post> transferedPostList = new ArrayList<Post>();
		if (postCnt > 0) {
			postList = postDao.getAllPostList(post);
//			Post 객체의 timestamp 필드를 string형으로 변환함.
			for (Post p: postList) {
				transferedPostList.add(timestampToStr(p));
			}
		}
//		return postList;
		return transferedPostList;
	}
	
	@Override
	public Post getDetailPost(int post_id) {
		Post post = postDao.detailPost(post_id);
		post = timestampToStr(post);
		return post;
	}
	
	@Override
	public PostForm postToPostForm(Post post) {
//		post는 post_id를 통해 db로부터 가져온 게시글이다.
		PostForm postForm = new PostForm();
		
		postForm.setPost_id(post.getPost_id());
		postForm.setUser(post.getUser());
		postForm.setTitle(post.getTitle());
		postForm.setContent(post.getContent());
		postForm.setFood_name(post.getFood_name());
		postForm.setStr_price(String.valueOf(post.getPrice()));
		postForm.setTrade_type(post.getTrade_type());
		postForm.setTrade_status(post.getTrade_status());
		postForm.setStr_quantity(String.valueOf(post.getQuantity()));
		postForm.setUnit(post.getUnit());
//		post의 Timestamp형 => String 변환 (yyyy-MM-dd)
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			postForm.setExp_dt_string(formatter.format(post.getExp_dt()));
		} catch (Exception e) {
			// TODO: handle exception
			postForm.setExp_dt_string("");
		}
		postForm.setShip_type(post.getShip_type());
		postForm.setImages(post.getImages());
//		댓글은 삽입 필요없음 (게시글 수정에 안쓰임)
//		file 필드가 파일 첨부 안해도 null이 아닌거로 인식되어 BLOB에 null 이 들어감
		postForm.setFile(null);
		
		return postForm;
	}
	
	@Override
	public int deletePost(int post_id) {
		int result = postDao.removePost(post_id);
		
		return result;
	}

	@Override
	public ArrayList<Post> getPostLatest3Rows() {
		// TODO Auto-generated method stub
		return postDao.getPostLatest3Rows();
	}
	
	@Override
	public Post timestampToStr(Post post) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String cString = "";
		String mString = "";
		String eString = "";
		
		if (post.getCreated_dt() != null) {
			try {
				cString = formatter.format(post.getCreated_dt());
				String[] splited = cString.split(" ");
				post.setStr_created_dt(splited[0]);
//				post.setStr_created_dt(cString);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		if (post.getModified_dt() != null) {
			try {
				mString = formatter.format(post.getModified_dt());
				post.setStr_modified_dt(mString);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		if (post.getExp_dt() != null) {
			try {
				eString = formatter.format(post.getExp_dt());
				// 유통기한은 시간은 저장하지 않는다.
				String[] splited = eString.split(" ");
				post.setStr_exp_dt(splited[0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return post;
	}
	
}
