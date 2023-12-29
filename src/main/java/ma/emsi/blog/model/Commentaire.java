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

@Entity
@Table(name = "Commentaire")
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

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Proprietaire getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Commentaire() {
		super();
	}

	public Visiteur getVisiteur() {
		return visiteur;
	}

	public void setVisiteur(Visiteur visiteur) {
		this.visiteur = visiteur;
	}

	public Article getArticle() {
		return article;
	}

	public Commentaire getCommentaireParent() {
		return commentaireParent;
	}

	public void setCommentaireParent(Commentaire commentaireParent) {
		this.commentaireParent = commentaireParent;
	}

	public Commentaire(int id, String texte, LocalDate date, Article article, Visiteur visiteur,
			Proprietaire proprietaire, Commentaire commentaireParent) {
		super();
		this.id = id;
		this.texte = texte;
		this.date = date;
		this.article = article;
		this.visiteur = visiteur;
		this.proprietaire = proprietaire;
		this.commentaireParent = commentaireParent;
	}

}
