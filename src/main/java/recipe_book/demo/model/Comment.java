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
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long recipeId; // Yorum yapılan tarifin ID'si

    @Column(nullable = false)
    private Long userId; // Yorumu yapan kullanıcının ID'si

    @Column(nullable = false, length = 500)
    private String content; // Yorum içeriği

    @Column(nullable = true)
    private Long parentId; // Eğer bu yorum bir başka yorumun alt yorumu ise parentId'yi tutar

    @Column(nullable = false)
    private LocalDateTime createdAt; // Yorumun oluşturulma zamanı
}
