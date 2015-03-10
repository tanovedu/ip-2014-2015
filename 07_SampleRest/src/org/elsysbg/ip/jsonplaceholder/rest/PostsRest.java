package org.elsysbg.ip.jsonplaceholder.rest;

import java.util.List;

import org.elsysbg.ip.jsonplaceholder.Services;
import org.elsysbg.ip.jsonplaceholder.model.Post;
import org.elsysbg.ip.jsonplaceholder.model.User;
import org.elsysbg.ip.jsonplaceholder.service.PostsService;

public class PostsRest {
	private final PostsService postsService;
	private final User defaultAuthor;


// In real world projects this is done by injection
// see https://github.com/google/guice
//	@Inject
//	public PostsRest(PostsService postsService) {
	public PostsRest() {
		postsService = Services.getPostsService();
		
		// TODO should be get from session
		defaultAuthor = new User();
		defaultAuthor.setEmail("hello@world");
		defaultAuthor.setPassword("secret");
	}

	public List<Post> getPosts() {
		return postsService.getPosts();
	}
	public Post getPost(long postId) {
		return postsService.getPost(postId);
	}
	public Post createPost(Post post) {
		// TODO set author by user session
		post.setUser(defaultAuthor);
		return postsService.createPost(post);
	}
	public Post updatePost(Post post) {
		return postsService.updatePost(post);
	}
	public void deletePost(long postId) {
		postsService.deletePost(postId);
	}
}
