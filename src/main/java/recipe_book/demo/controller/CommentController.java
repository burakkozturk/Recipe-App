package recipe_book.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_book.demo.model.Comment;
import recipe_book.demo.service.CommentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("")
    public ResponseEntity<String> addComment(@RequestBody Map<String, String> request) {
        Long recipeId = Long.parseLong(request.get("recipeId"));
        Long userId = Long.parseLong(request.get("userId"));
        String content = request.get("content");
        Long parentId = request.get("parentId") != null ? Long.parseLong(request.get("parentId")) : null; // Alt yorum için parentId kontrolü
        commentService.addComment(recipeId, userId, content, parentId);
        return ResponseEntity.ok("Comment added successfully.");
    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<List<Comment>> getCommentsByRecipeId(@PathVariable Long recipeId) {
        List<Comment> comments = commentService.getCommentsByRecipeId(recipeId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/replies/{parentId}")
    public ResponseEntity<List<Comment>> getReplies(@PathVariable Long parentId) {
        List<Comment> replies = commentService.getReplies(parentId);
        return ResponseEntity.ok(replies);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully.");
    }
}
