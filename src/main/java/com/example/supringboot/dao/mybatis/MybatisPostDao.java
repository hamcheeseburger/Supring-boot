package com.example.supringboot.dao.mybatis;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.controller.InsertPostController;
import com.example.supringboot.controller.PostForm;
import com.example.supringboot.dao.PostDao;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Criteria;
import com.example.supringboot.domain.Image;
import com.example.supringboot.domain.Post;
import com.example.supringboot.mybatis.mapper.CommentMapper;
import com.example.supringboot.mybatis.mapper.ImageMapper;
import com.example.supringboot.mybatis.mapper.PostMapper;

@Repository
public class MybatisPostDao  implements PostDao{
	private static final Logger logger = LoggerFactory.getLogger(MybatisPostDao.class);

	@Autowired
	private PostMapper postMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ImageMapper imageMapper;
	
	
	@Override
	public ArrayList<Post> getAllPostList(Post post) {
		return postMapper.getAllPost(post);
	}
	
	@Override
	public ArrayList<Post> searchPostList(String title) {
		return postMapper.getPostListByTitle(title);
	}
	
	@Override
	public Post detailPost(int post_id) {
//		1.post 정보 가져오기
//		2.comment list 가져와서 Post 객체에 삽입하기
		Post post = new Post();
		post = postMapper.getOnePostById(post_id);
		ArrayList<Comment> comments = commentMapper.selectCommentByPostId(post_id);
		if (post != null) { // 24,25번 게시글은 post가 null이다?
			logger.info("mybatis post의 image : " + post.getImages().get(0).getImage());
//			이미지 삽입시 이미지가 null이어도 db에 삽입되어 이런 처리가 필요함.
			if (post.getImages().get(0) == null) {
				post.setImages(null);
			}

			post.setComments(comments);
		}
		
		return post;
		
	}
	
	@Override
	public int updatePost(Post post, PostForm postForm) {
//		추후 이미지 수정 부분도 구현한다.
//		해당 post의 이미지를 모두 삭제 후 다시 삽입해야 하나?
		int result = postMapper.updatePost(post);
//		logger.info("mybatis post dao의 postForm의 File : " + post.getImages().get(0).getImage());
		
//		이미지가 null 일수도 있기에 첨부파일이 바뀐 경우에만 이미지 재삽입 과정을 진행함.
		if (postForm.getFileChanged().equals("changed")) {
//			기존 이미지를 모두 삭제한다.
			imageMapper.deleteImageByPostId(post.getPost_id());

			logger.info("mybatis post dao의 post의 image : " + post.getImages().get(0).getImage());
//				다시 이미지를 재 삽입한다.
			if (result != 0 && post.getImages() != null) {
				ArrayList<Image> images = post.getImages();
				for (Image img : images) {
					img.setPost_id(post.getPost_id());
					logger.info("mybatis post dao의 post의 image 2: " + img.getImage());
					imageMapper.insertImageWithPost(img);
				}
			}
		}
		return result;
	}
	
	@Override
	public int removePost(int post_id) {
		return postMapper.deletePost(post_id);
	}
	
	@Override
	public int getPostCount() {
		return postMapper.getPostCount();
	}

	@Override
	public int insertPost(Post post) {
//		게시글 삽입 후 생성되는 post_id를 이용하여 image 삽입 (일단 1장)
		int result = postMapper.insertPost(post);
		if (result != 0 && post.getImages() != null) {
			ArrayList<Image> images = post.getImages();
			for (Image img: images) {
				img.setPost_id(post.getPost_id());
				imageMapper.insertImageWithPost(img);
			}
		}		
		return result;
	}


	@Override
	public ArrayList<Post> selectPostList(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectPostTotalCount(Post post) {
		return postMapper.selectPostTotalCount(post);
	}


}
