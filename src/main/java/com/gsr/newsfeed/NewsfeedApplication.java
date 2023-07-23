package com.gsr.newsfeed;

import com.gsr.newsfeed.service.PostService;
import com.gsr.newsfeed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NewsfeedApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	public static void main(String[] args) {
		SpringApplication.run(NewsfeedApplication.class, args);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
//		userService.signUp("Giriraj","gsr@asd.com","asdf");
//		userService.logIn("gsr@asd.com","asdf");
//		userService.signUp("Giriraj","gsr1@asd.com","asdf");
//		userService.logIn("gsr@asd.com","asdf");
//		userService.follow(2L);
//		postService.createPost("This is my First Post !!");
//		userService.logIn("u1@asd.com","asdf");
//		postService.createPost("This is my First Post !!");
//		postService.makeCommentOnPost(7L,"Great");
//		postService.makeCommentOnComment(1L,"GreatAgain");
		
	}

}
