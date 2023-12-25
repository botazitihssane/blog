package ma.emsi.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Visiteur")
public class Visiteur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nom;

	@NotBlank(message = "Email ne peut pas etre vide")
	private String email;

	@Min(value = 0, message = "Nombre de commentaire ne peut pas etre négatif")
	private int nombreCommentaire;

	public Visiteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Visiteur(int id, String nom, @NotBlank(message = "Email ne peut pas etre vide") String email,
			@Min(value = 0, message = "Nombre de commentaire ne peut pas etre négatif") int nombreCommentaire) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.nombreCommentaire = nombreCommentaire;
	}

	public int getNombreCommentaire() {
		return nombreCommentaire;
	}

	public void setNombreCommentaire(int nombreCommentaire) {
		this.nombreCommentaire = nombreCommentaire;
	}

	public Visiteur(String nom, @NotBlank(message = "Email ne peut pas etre vide") String email,
			@Min(value = 0, message = "Nombre de commentaire ne peut pas etre négatif") int nombreCommentaire) {
		super();
		this.nom = nom;
		this.email = email;
		this.nombreCommentaire = nombreCommentaire;
	}

}
