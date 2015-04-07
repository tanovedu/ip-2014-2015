package org.elsysbg.ip.jsonplaceholder.rest;

import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.elsysbg.ip.jsonplaceholder.Services;
import org.elsysbg.ip.jsonplaceholder.model.Post;
import org.elsysbg.ip.jsonplaceholder.model.User;
import org.elsysbg.ip.jsonplaceholder.service.PostsService;
import org.elsysbg.ip.jsonplaceholder.service.UsersService;

@Path("posts")
public class PostsRest {
	private final PostsService postsService;
	private final UsersService usersService;

// In real world projects this is done by injection
// see https://github.com/google/guice
//	@Inject
//	public PostsRest(PostsService postsService) {
	public PostsRest() {
		postsService = Services.getPostsService();
		usersService = Services.getUsersService();
	}

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Post> getPosts() {
		return postsService.getPosts();
	}
	
	@GET
	@Path("/{postId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	// @PathParam binds url parameter (postId) to method parameter (postId)
	public Post getPost(@PathParam("postId") long postId) {
		return postsService.getPost(postId);
	}
	@GET
	@Path("/{postId}/likedbyusers")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	// @PathParam binds url parameter (postId) to method parameter (postId)
	public List<User> getUsersByLikedPost(@PathParam("postId") long postId) {
		final Post likedPost = postsService.getPost(postId);
		return usersService.getUsersByLikedPost(likedPost);
	}
	@POST
	@Path("/")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Post createPost(@Context SecurityContext security, Post post) {
		final User author =
			usersService.getUserByEmail(security.getUserPrincipal().getName());
		post.setAuthor(author);
		return postsService.createPost(post);
	}
	@POST
	@Path("/{postId}/likedbyusers")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	// @PathParam binds url parameter (postId) to method parameter (postId)
	public Set<User> likePost(@Context SecurityContext security, @PathParam("postId") long postId) {
		final User likedByUser =
				usersService.getUserByEmail(security.getUserPrincipal().getName());
		return postsService.likePost(postId, likedByUser);
	}

	@PUT
	@Path("/{postId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Post updatePost(@Context SecurityContext security, @PathParam("postId") long postId,
		Post post) {
		final Post fromDb = postsService.getPost(postId);
		if (!fromDb.getAuthor().getEmail().equals(
			security.getUserPrincipal().getName())) {
			throw new SecurityException(
				"Insufficient permission for post " + postId +
				" issued by " + security.getUserPrincipal().getName());
		}
		// This should be performed by annotations

		return postsService.updatePost(postId, post);
	}
	
	@DELETE
	@Path("/{postId}")
	// only administrators can delete posts
	@RolesAllowed({"ADMINISTRATOR"})
	public void deletePost(@PathParam("postId") long postId) {
		postsService.deletePost(postId);
	}
}
