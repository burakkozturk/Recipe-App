package recipe_book.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_book.demo.model.Follow;
import recipe_book.demo.service.FollowService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("")
    public ResponseEntity<String> followUser(@RequestParam Long followerId, @RequestParam Long followingId) {
        followService.followUser(followerId, followingId);
        return ResponseEntity.ok("Followed successfully.");
    }

    @DeleteMapping("")
    public ResponseEntity<String> unfollowUser(@RequestParam Long followerId, @RequestParam Long followingId) {
        followService.unfollowUser(followerId, followingId);
        return ResponseEntity.ok("Unfollowed successfully.");
    }


    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<Follow>> getFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowers(userId));
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<Follow>> getFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowing(userId));
    }
}
