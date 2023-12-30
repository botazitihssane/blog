package ma.emsi.blog.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
