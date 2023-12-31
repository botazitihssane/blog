package ma.emsi.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "Visiteur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Visiteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    @NotBlank(message = "Email ne peut pas etre vide")
    private String email;

    @Min(value = 0, message = "Nombre de commentaire ne peut pas etre négatif")
    private int nombreCommentaire;

}
