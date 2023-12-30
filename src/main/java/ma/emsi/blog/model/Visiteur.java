package ma.emsi.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
