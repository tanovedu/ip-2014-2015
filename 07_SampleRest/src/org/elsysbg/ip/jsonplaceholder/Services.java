package org.elsysbg.ip.jsonplaceholder;

import org.elsysbg.ip.jsonplaceholder.service.PostsService;

/**
 * This is used instead of injection.
 * Injection should be used in real projects.
 * see https://github.com/google/guice
 */
public class Services {

	private static PostsService postsService;

	// TODO synchronized should be done in better way in real projects
	public synchronized static PostsService getPostsService() {
		// lazy loading
		if (postsService == null) {
			postsService = new PostsService();
		}
		return postsService;
	}
	
	// for tests purposes
	static void setPostsService(PostsService postsService) {
		Services.postsService = postsService;
	}
}
