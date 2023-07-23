package com.gsr.newsfeed;

import com.gsr.newsfeed.model.Comment;
import com.gsr.newsfeed.model.Follow;
import com.gsr.newsfeed.model.Post;
import com.gsr.newsfeed.model.User;
import com.gsr.newsfeed.service.NewsFeedService;
import com.gsr.newsfeed.service.PostService;
import com.gsr.newsfeed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@EnableJpaAuditing
@SpringBootApplication
public class NewsfeedApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;

	@Autowired
	private NewsFeedService newsFeedService;
	public static void main(String[] args) {
		SpringApplication.run(NewsfeedApplication.class, args);
	}

	/*
	--------------------------------------------------------------
	NOTE -
	You Don't need to change DB Details , Just run this class
	and you can test by entering commands, Commands and arguments are saperated by comma.

	Please let me know if you find any problem while running application
	---------------------------------------------------------------
	*/


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
//		postService.makeCommentOnPost(1L,"Wow");
//		postService.makeCommentOnComment(2L,"Hurry",1L);
//		postService.likePost(1L);
		Scanner sc = new Scanner(System.in);
		while (true){
			try {

				System.out.println("Enter command");
				System.out.println("signUp <name> <email> <password> , Ex : signUp,John,john1@gmail.com,asdf");
				System.out.println("login <email> <password> , Ex : login,john@gmail.com,asdf");
				System.out.println("createpost <content> ,Ex : createpost,This is my first post");
				System.out.println("follow <id>");
				System.out.println("makeCommentOnPost <post_id> <content>");
				System.out.println("makeCommentOnComment <comm_id> <content> <post_id>");
				System.out.println("likepost <post_id>");
				System.out.println("dislikepost <post_id>");
				System.out.println("likecomment <comm_id>");
				System.out.println("dislikecomment <comm_id>");
				System.out.println("showfeed");

				String[] args1 = sc.nextLine().split(",");
				switch (args1[0]){
					case "signUp":
						signUp(args1);
						break;
					case "login":
						login(args1);
						break;
					case "createpost":
						createPost(args1);
						break;
					case "follow":
						follow(args1);
						break;
					case "makeCommentOnPost":
						makeCommentOnPost(args1);
						break;
					case "makeCommentOnComment":
						makeCommentOnComment(args1);
						break;
					case "likepost":
						likePost(args1);
						break;
					case "dislikepost":
						disLikePost(args1);
						break;
					case "likecomment":
						likeComment(args1);
						break;
					case "dislikecomment":
						disLikeComment(args1);
						break;
					case "showfeed":
						newsFeed();
						break;
					default:
						System.out.println("Invalid command");
						break;
				}
				System.out.println("-------------");
				System.out.println();
			}catch (Exception e){
				System.out.println(e);
			}
		}

	}

	private void disLikeComment(String[] args) throws Exception {
		Long id = null;
		try {
			id=Long.valueOf(args[1]);
		}catch (Exception e){
			throw new Exception("Invalid Arguments");
		}
		postService.disLikeComment(id);
		System.out.println("disliked Comment Successfully");
	}

	private void likeComment(String[] args) throws Exception {
		Long id = null;
		try {
			id=Long.valueOf(args[1]);
		}catch (Exception e){
			throw new Exception("Invalid Arguments");
		}
		postService.likeComment(id);
		System.out.println("Liked Comment Successfully");
	}

	private void likePost(String[] args) throws Exception {
		Long id = null;
		try {
			id=Long.valueOf(args[1]);
		}catch (Exception e){
			throw new Exception("Invalid Arguments");
		}
		postService.likePost(id);
		System.out.println("Liked Post Successfully");
	}
	private void disLikePost(String[] args) throws Exception {
		Long id = null;
		try {
			id=Long.valueOf(args[1]);
		}catch (Exception e){
			throw new Exception("Invalid Arguments");
		}
		postService.disLikePost(id);
		System.out.println("disliked Post Successfully");
	}

	private void makeCommentOnComment(String[] args) throws Exception {
		Long id = null;
		String comment = null;
		Long postId = null;
		try {
			id=Long.valueOf(args[1]);
			comment=args[2];
			postId=Long.valueOf(args[3]);
		}catch (Exception e){
			throw new Exception("Invalid Arguments");
		}
		postService.makeCommentOnComment(id,comment,postId);
		System.out.println("Comment Successfully Added");
	}

	private void makeCommentOnPost(String[] args) throws Exception {
		Long id = null;
		String comment = null;
		try {
			id=Long.valueOf(args[1]);
			comment=args[2];
		}catch (Exception e){
			throw new Exception("Invalid Arguments");
		}
		postService.makeCommentOnPost(id,comment);
		System.out.println("Comment Successfully Added");
	}

	private void follow(String[] args) throws Exception {
		Long id = null;
		try {
			id=Long.valueOf(args[1]);
		}catch (Exception e){
			throw new Exception("Invalid Arguments");
		}
		Follow follow = userService.follow(id);
		System.out.println("Success");
		System.out.println("user :"+follow.getFollower().getName()+" is following : "+follow.getFollowee().getName());

	}

	private void createPost(String[] args) throws Exception {
//		createpost <content>
		String content = null;
		try {
			content=args[1];

		}catch (Exception e){
			throw new Exception("Invalid Arguments");
		}
		Post post = postService.createPost(content);
		System.out.println("Post generated Successfully :id : "+post.getId());
	}
	private void login(String[] args) throws Exception {
		//login <email> <password>
		String email = null;
		String password = null;
		try {
			email=args[1];
			password=args[2];

		}catch (Exception e){
			throw new Exception("Invalid Arguments");
		}
		userService.logIn(email,password);
	}

	void newsFeed() throws Exception {
		//newsFeed
		List<Post> l = newsFeedService.getNewsFeed();
		for (Post p : l){
			printPostWithComment(p.getId());
		}
	}
	void signUp(String[] args) throws Exception {
		//signUp <name> <email> <password>
		String name = null;
		String email = null;
		String password = null;
		try {
			name=args[1];
			email=args[2];
			password=args[3];
		}catch (Exception e){
			throw new Exception("Invalid Arguments");
		}
		User u = userService.signUp(name,email,password);
		System.out.println("SignUp Successful");
		System.out.println("Details : id : "+u.getId()+" Name : "+u.getName()+" email : "+u.getEmail());

	}
	private void printPostWithComment(long postId) throws Exception {
		Post post = postService.getPostByPostId(postId);
		System.out.println("------------------------------");
		System.out.println(post.getUser().getName()+"  "+timestampToReadableFormat(post.getGenerated_at()));
		System.out.println();
		System.out.println(post.getContent()+" PostId: "+post.getId());
		System.out.println("Likes : "+post.getLikes()+"  Dislikes : "+post.getDislikes());
		List<Comment> commentList = postService.getAllCommentsOnPost(postId);
		System.out.println();
		for(Comment c : commentList){
			printCommentOnComment(c,"  ");
		}
		System.out.println("------------------------------");
	}

	private void printCommentOnComment(Comment c,String indentation) throws Exception {
		List<Comment> commentList = postService.getAllCommentsOnComment(c.getId());
		System.out.println(indentation+c.getPostedBy().getName()+" "+timestampToReadableFormat(c.getCreatedAt()));
		System.out.println(indentation+c.getContent()+" Likes: "+c.getLikes()+" DisLikes :"+c.getDislikes()+" commentId : "+c.getId());
		System.out.println();
		for(Comment comment : commentList){
			printCommentOnComment(comment,indentation+" ");
		}
	}


	private static String timestampToReadableFormat(Date date){
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime targetDateTime = dateToLocalDateTime(date);
		Duration duration = Duration.between(targetDateTime, currentDateTime);
		long hours = duration.toHours();
		long minutes = duration.toMinutes() % 60;
		if(hours==0){
			return minutes+" minutes ago ";
		}
		return hours+" hours and "+minutes+" minutes ago ";
	}

	public static LocalDateTime dateToLocalDateTime(Date date) {
		// Convert Date to Instant
		Instant instant = date.toInstant();

		// Create LocalDateTime using the Instant and default time zone (UTC)
		LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

		return localDateTime;
	}

}
