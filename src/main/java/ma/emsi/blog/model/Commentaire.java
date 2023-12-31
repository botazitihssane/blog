package ma.emsi.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Commentaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Texte ne peut pas etre vide")
    private String texte;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "article_id")
    @NotNull(message = "Article ne peut pas etre vide")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "visiteur_id")
    private Visiteur visiteur;

    @ManyToOne
    @JoinColumn(name = "proprietaire_id")
    private Proprietaire proprietaire;

    @ManyToOne
    @JoinColumn(name = "commentaire_parent_id")
    private Commentaire commentaireParent;
}
