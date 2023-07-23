package com.gsr.newsfeed.service;

import com.gsr.newsfeed.model.Comment;
import com.gsr.newsfeed.model.Post;
import com.gsr.newsfeed.model.Session;
import com.gsr.newsfeed.model.User;
import com.gsr.newsfeed.repository.CommentsRepository;
import com.gsr.newsfeed.repository.PostRepository;
import com.gsr.newsfeed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentsRepository commentsRepository;
    public Post createPost(String content) throws Exception {
        Optional<Session> session = sessionService.currentLoggedInUser();
        if(session.isEmpty()){
            throw new Exception("No Logged In User Found");
        }
        User currentUser = userRepository.getById(session.get().getUserId());
        Post post = new Post();
        post.setUser(currentUser);
        post.setContent(content);
        return postRepository.save(post);
    }
    public Post likePost(Long postId) throws Exception {
        Optional<Session> session = sessionService.currentLoggedInUser();
        if(session.isEmpty()){
            throw new Exception("No Logged In User Found");
        }
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new Exception("Post Not Found, Enter valid postId");
        }
        Post p = post.get();
        p.setLikes(p.getLikes()+1);
        return postRepository.save(p);
    }
    public Post disLikePost(Long postId) throws Exception {
        Optional<Session> session = sessionService.currentLoggedInUser();
        if(session.isEmpty()){
            throw new Exception("No Logged In User Found");
        }
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new Exception("Post Not Found, Enter valid postId");
        }
        Post p = post.get();
        p.setDislikes(p.getDislikes()+1);
        return postRepository.save(p);
    }
    public Comment makeCommentOnPost(Long postId , String commentString) throws Exception {
        Optional<Session> session = sessionService.currentLoggedInUser();
        if(session.isEmpty()){
            throw new Exception("No Logged In User Found");
        }
        User currentUser = userRepository.getById(session.get().getUserId());
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new Exception("Post Not Found, Enter valid postId");
        }
        Comment comment = new Comment();
        comment.setOnPost(post.get());
        comment.setPostedBy(currentUser);
        comment.setContent(commentString);
        return commentsRepository.save(comment);
    }
    public Comment makeCommentOnComment(Long commentId , String commentString,Long postId) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new Exception("Post Not Found, Enter valid postId");
        }
        Optional<Session> session = sessionService.currentLoggedInUser();
        if(session.isEmpty()){
            throw new Exception("No Logged In User Found");
        }
        User currentUser = userRepository.getById(session.get().getUserId());
        Optional<Comment> comment = commentsRepository.findById(commentId);
        if(comment.isEmpty()){
            throw new Exception("Comment of id: "+commentId+" Not Found, Enter valid commentId");
        }
        Comment newComment = new Comment();
        newComment.setOnComment(comment.get());
        newComment.setOnPost(post.get());
        newComment.setPostedBy(currentUser);
        newComment.setContent(commentString);
        return commentsRepository.save(newComment);
    }
    public Post getPostByPostId(Long postId) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new Exception("Post Not Found, Enter valid postId");
        }
        return post.get();
    }
    public List<Comment> getAllCommentsOnPost(Long postId) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new Exception("Post Not Found, Enter valid postId");
        }
        return commentsRepository.findAllCommentsByPostId(postId);
    }

    public List<Comment> getAllCommentsOnComment(Long commentId) throws Exception {
        Optional<Comment> comment = commentsRepository.findById(commentId);
        if(comment.isEmpty()){
            throw new Exception("Comment of id: "+commentId+" Not Found, Enter valid commentId");
        }
        return commentsRepository.findAllCommentsByCommentId(commentId);
    }

    public Comment likeComment(Long commentId) throws Exception {
        Optional<Session> session = sessionService.currentLoggedInUser();
        if(session.isEmpty()){
            throw new Exception("No Logged In User Found");
        }
        Optional<Comment> comment = commentsRepository.findById(commentId);
        if(comment.isEmpty()){
            throw new Exception("Comment Not Found, Enter valid postId");
        }
        Comment c = comment.get();
        c.setLikes(c.getLikes()+1);
        return commentsRepository.save(c);
    }

    public Comment disLikeComment(Long commentId) throws Exception {
        Optional<Session> session = sessionService.currentLoggedInUser();
        if(session.isEmpty()){
            throw new Exception("No Logged In User Found");
        }
        Optional<Comment> comment = commentsRepository.findById(commentId);
        if(comment.isEmpty()){
            throw new Exception("Comment Not Found, Enter valid postId");
        }
        Comment c = comment.get();
        c.setDislikes(c.getDislikes()+1);
        return commentsRepository.save(c);
    }
}
