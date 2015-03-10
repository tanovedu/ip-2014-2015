package org.elsysbg.ip.jsonplaceholder.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.elsysbg.ip.jsonplaceholder.model.Post;
import org.junit.Before;
import org.junit.Test;

/**
 * For unit testing you can use JUnit or TestNG
 */
public class PostsServiceTest {
	private PostsService postsService;

	@Before
	public void setUp() throws Exception {
		postsService = new PostsService();
	}

	@Test
	public void testCreateGetPost() {
// not set as required in post model
//		final User author = new User();
//		author.setEmail("hello@world");
//		author.setPassword("secret");
		final Post post = new Post();
		post.setTitle("hello");
		post.setBody("world");
//		post.setUser(author);
		final Post result =
			postsService.createPost(post);
		assertNotEquals(0, result.getId());
		
		final Post fromService =
			postsService.getPost(post.getId());
		assertEquals("hello", fromService.getTitle());
		assertEquals("world", fromService.getBody());
	}
	
	// TODO add more tests (update, delete, increment ID, etc.)
}
