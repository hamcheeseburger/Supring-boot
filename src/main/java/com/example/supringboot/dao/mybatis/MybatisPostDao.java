package com.example.supringboot.dao.mybatis;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.dao.PostDao;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Post;
import com.example.supringboot.mybatis.mapper.CommentMapper;
import com.example.supringboot.mybatis.mapper.ImageMapper;
import com.example.supringboot.mybatis.mapper.PostMapper;

@Repository
public class MybatisPostDao  implements PostDao{
	@Autowired
	private PostMapper postMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ImageMapper imageMapper;
	
	
	@Override
	public ArrayList<Post> getAllPostList() {
		return postMapper.getAllPost();
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
		post.setComments(comments);
		return post;
		
	}
	
	@Override
	public int updatePost(Post post) {
//		추후 이미지 수정 부분도 구현한다.
//		해당 post의 이미지를 모두 삭제 후 다시 삽입해야 하나?
		return postMapper.updatePost(post);
	}
	
	@Override
	public int removePost(int post_id) {
		return postMapper.deletePost(post_id);
	}
	
	@Override
	public int getPostCount() {
		return postMapper.getPostCount();
	}


}
