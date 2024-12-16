package recipe_book.demo.service;


import org.springframework.stereotype.Service;
import recipe_book.demo.model.Comment;
import recipe_book.demo.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addComment(Long recipeId, Long userId, String content, Long parentId) {
        Comment comment = new Comment();
        comment.setRecipeId(recipeId);
        comment.setUserId(userId);
        comment.setContent(content);
        comment.setParentId(parentId); // Alt yorum için parentId'yi ayarla
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    public List<Comment> getCommentsByRecipeId(Long recipeId) {
        return commentRepository.findByRecipeId(recipeId);
    }

    public List<Comment> getReplies(Long parentId) {
        return commentRepository.findByParentId(parentId);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
