package recipe_book.demo.service;

import org.springframework.stereotype.Service;
import recipe_book.demo.model.Follow;
import recipe_book.demo.model.User;
import recipe_book.demo.repository.FollowRepository;
import recipe_book.demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public void followUser(Long followerId, Long followingId) {
        if (followRepository.existsByFollowerIdAndFollowingId(followerId, followingId)) {
            throw new IllegalArgumentException("You are already following this user.");
        }

        // Follow işlemini kaydet
        Follow follow = new Follow();
        follow.setFollowerId(followerId);
        follow.setFollowingId(followingId);
        follow.setCreatedAt(LocalDateTime.now());
        followRepository.save(follow);

        // Follower ve Following kullanıcılarını bul
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new IllegalArgumentException("Follower user not found"));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new IllegalArgumentException("Following user not found"));

        // followersCount ve followingCount güncelle
        follower.setFollowingCount(follower.getFollowingCount() + 1);
        following.setFollowersCount(following.getFollowersCount() + 1);

        // Güncellenen kullanıcıları kaydet
        userRepository.save(follower);
        userRepository.save(following);
    }

    public void unfollowUser(Long followerId, Long followingId) {
        if (!followRepository.existsByFollowerIdAndFollowingId(followerId, followingId)) {
            throw new IllegalArgumentException("You are not following this user.");
        }

        // Follow kaydını sil
        followRepository.deleteByFollowerIdAndFollowingId(followerId, followingId);

        // Follower ve Following kullanıcılarını bul
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new IllegalArgumentException("Follower user not found"));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new IllegalArgumentException("Following user not found"));

        // followersCount ve followingCount azalt
        follower.setFollowingCount(Math.max(0, follower.getFollowingCount() - 1));
        following.setFollowersCount(Math.max(0, following.getFollowersCount() - 1));

        // Güncellenen kullanıcıları kaydet
        userRepository.save(follower);
        userRepository.save(following);
    }
    public List<Follow> getFollowers(Long userId) {
        return followRepository.findByFollowingId(userId);
    }

    public List<Follow> getFollowing(Long userId) {
        return followRepository.findByFollowerId(userId);
    }
}
