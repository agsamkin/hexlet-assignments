package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getAllComments(@PathVariable("postId") Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable("postId") Long postId,
                                        @PathVariable("commentId") Long commentId) {
        return commentRepository.findByPostIdAndId(postId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment no found"));
    }


    @PostMapping(path = "/{postId}/comments")
    public Comment createComment(@PathVariable("postId") Long postId,
                                 @RequestBody Comment comment) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post no found"));
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable("postId") Long postId,
                           @PathVariable("commentId") Long commentId,
                           @RequestBody Comment newComment) {

        Comment comment = commentRepository.findByPostIdAndId(postId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment no found"));

        newComment.setId(commentId);
        newComment.setPost(comment.getPost());
        return commentRepository.save(newComment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable("postId") Long postId,
                                 @PathVariable("commentId") Long commentId) {

        Comment comment = commentRepository.findByPostIdAndId(postId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment no found"));

        commentRepository.delete(comment);
    }
    // END
}
