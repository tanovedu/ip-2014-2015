package org.elsysbg.ip.jsonplaceholder.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.elsysbg.ip.jsonplaceholder.Services;
import org.elsysbg.ip.jsonplaceholder.model.Post;
import org.elsysbg.ip.jsonplaceholder.model.User;

/**
 * TODO write tests for users service
 */
public class UsersService {
	
	private final EntityManagerFactory emf;
	public UsersService() {
		emf = Services.getEntityManagerFactory();
	}

	public User getUserByEmail(String email) {
		final EntityManager em =
			emf.createEntityManager();
		try {
			return em
				.createNamedQuery("userByEmail", User.class)
				.setParameter("email", email)
				.getSingleResult();
		} finally {
			em.close();
		}
	}
	
	public User getUser(long userId) {
		final EntityManager em =
			emf.createEntityManager();
		try {
// this solution can cause problems because
			// when we get post it will contain author
			// and author will have password
			// (we should remove password in
			// Posts endpoint and everywhere else, too
			// this will be very difficult to maintain) 
			// final User result = em.find(User.class, userId);
			// result.setPassword(null);
			// return result;
			
			// for better solution - see
			// https://jersey.java.net/documentation/latest/entity-filtering.html
			// how to attach attach entity filter

			return em.find(User.class, userId);
		} finally {
			em.close();
		}
	}
	// synchronized because of lastUserId
	public synchronized User createUser(User user) {
		EntityManager em =
			emf.createEntityManager();
		final EntityTransaction tx =
			em.getTransaction();
		try {
			tx.begin();
			em.persist(user);
			tx.commit();
			return user;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
		
	}
	// TODO update/delete of user

	public List<User> getUsersByLikedPost(Post likedPost) {
		final EntityManager em = emf.createEntityManager();
			try {
				return em
					.createNamedQuery("usersByLikedPost", User.class)
					.setParameter("likedPost", likedPost)
					.getResultList();
			} finally {
				em.close();
			}
	}

}
