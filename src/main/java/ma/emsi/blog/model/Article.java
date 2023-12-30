package ma.emsi.blog.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "Article")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Titre ne peut pas etre vide")
    @Size(min = 1, max = 255, message = "Titre must be between 1 and 255 characters")
    private String titre;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Texte ne peut pas etre vide")
    @Size(min = 1, max = 1000, message = "Texte must be between 1 and 1000 characters")
    private String texte;

    private String photo;
    private String lien;

    @NotNull(message = "Date ne peut pas etre vide")
    private LocalDate date;

    @Min(value = 0, message = "Nombre de commentaire ne peut pas etre négatif")
    private int nombreCommentaire;

    @ManyToOne
    @NotNull(message = "Categorie ne peut pas etre vide")
    private Categorie categorie;

    @ManyToOne
    @NotNull(message = "Le proprietaire ne peut pas etre vide")
    private Proprietaire user;
}
