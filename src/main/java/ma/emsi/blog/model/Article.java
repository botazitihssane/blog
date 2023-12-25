package ma.emsi.blog.model;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Article")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Titre ne peut pas etre vide")
	private String titre;
	@Column(columnDefinition = "TEXT")
	@NotBlank(message = "Texte ne peut pas etre vide")
	private String texte;
	private String photo;
	private String lien;
	@NotNull(message = "Date ne peut pas etre vide")
	private LocalDate date;
	@Min(value = 0, message = "Nombre de commentaire ne peut pas etre négatif")
	private int nombreCommentaire;
	@ManyToOne
	@NotBlank(message = "Categorie ne peut pas etre vide")
	private Categorie categorie;
	@ManyToOne
	@NotBlank(message = "Le proprietaire ne peut pas etre vide")
	private Proprietaire user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public int getNombreCommentaire() {
		return nombreCommentaire;
	}

	public void setNombreCommentaire(int nombreCommentaire) {
		this.nombreCommentaire = nombreCommentaire;
	}

	public Proprietaire getUser() {
		return user;
	}

	public void setUser(Proprietaire user) {
		this.user = user;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Article(int id, String titre, String texte, String photo, String lien, LocalDate date, int nombreCommentaire,
			Categorie categorie, Proprietaire user) {
		super();
		this.id = id;
		this.titre = titre;
		this.texte = texte;
		this.photo = photo;
		this.lien = lien;
		this.date = date;
		this.nombreCommentaire = nombreCommentaire;
		this.categorie = categorie;
		this.user = user;
	}

	public Article() {
		super();
	}

}
