package recipe_book.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // İlişkili Ingredient kayıtları
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "recipe")
    @JsonManagedReference
    private List<Ingredient> ingredients;

    // İlişkili Instruction kayıtları
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "recipe")
    @JsonManagedReference
    private List<Instruction> instructions;

    // Kategori ile ilişki
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Tags: Birden fazla etiket
    @ElementCollection
    @CollectionTable(name = "recipe_tags", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "tag")
    private List<String> tags;

    private String imageUrl;
    private String difficulty;
    private Integer cookTime;
    private Integer prepTime;

    // Recipe'in yazarı
    @Column(nullable = false)
    private Long authorId;

    private Integer likes;
}
