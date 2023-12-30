package ma.emsi.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "Utilisateur")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "L'e-mail ne peut pas être vide")
    private String email;

    @Size(min = 8, message = "Le mot de passe doit avoir au moins 8 caractères")
    private String password;

    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    private String username;

    public Utilisateur(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
