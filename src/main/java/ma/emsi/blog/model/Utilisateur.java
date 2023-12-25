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

@Entity
@Table(name = "Utilisateur")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Utilisateur(int id, @NotBlank(message = "L'e-mail ne peut pas être vide") String email,
			@Size(min = 8, message = "Le mot de passe doit avoir au moins 8 caractères") String password,
			@NotBlank(message = "Le nom d'utilisateur ne peut pas être vide") String username) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
	}

	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(@NotBlank(message = "L'e-mail ne peut pas être vide") String email,
			@Size(min = 8, message = "Le mot de passe doit avoir au moins 8 caractères") String password,
			@NotBlank(message = "Le nom d'utilisateur ne peut pas être vide") String username) {
		super();
		this.email = email;
		this.password = password;
		this.username = username;
	}

}
