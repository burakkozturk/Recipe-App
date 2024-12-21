package recipe_book.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "follow")
public class Follow {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "follower_id",nullable = false)
    private Long followerId;

    @Column(name = "following_id",nullable = false)
    private Long followingId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
