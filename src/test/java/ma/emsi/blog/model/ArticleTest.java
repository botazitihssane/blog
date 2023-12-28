package ma.emsi.blog.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ArticleTest {

	private Validator validator;

	private Article article;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		article = new Article();
	}

	@Test
	public void testTitreNotBlank() {
		article.setTitre("");
		assertViolations("titre");
	}

	@Test
	public void testTexteNotBlank() {
		article.setTexte("");
		assertViolations("texte");
	}

	@Test
	public void testDateNotNull() {
		article.setDate(null);
		assertViolations("date");
	}

	@Test
	public void testNombreCommentaireMin() {
		article.setNombreCommentaire(-1);
		assertViolations("nombreCommentaire");
	}

	@Test
	public void testCategorieNotNull() {
		article.setCategorie(null);
		assertViolations("categorie");
	}

	@Test
	public void testUserNotNull() {
		article.setUser(null);
		assertViolations("user");
	}

	private void assertViolations(String fieldName) {
		Set<ConstraintViolation<Article>> violations = validator.validateProperty(article, fieldName);
		assertFalse("Expected validation errors for " + fieldName, violations.isEmpty());
	}

	@Test
	public void testGettersAndSetters() {
		int id = 1;
		String titre = "Test Title";
		String texte = "Test Text";
		String photo = "Test Photo";
		String lien = "Test Link";
		LocalDate date = LocalDate.now();
		int nombreCommentaire = 10;
		Categorie categorie = new Categorie(); // Assuming you have a Categorie class
		Proprietaire user = new Proprietaire(); // Assuming you have a Proprietaire class

		article.setId(id);
		article.setTitre(titre);
		article.setTexte(texte);
		article.setPhoto(photo);
		article.setLien(lien);
		article.setDate(date);
		article.setNombreCommentaire(nombreCommentaire);
		article.setCategorie(categorie);
		article.setUser(user);

		assertEquals(id, article.getId());
		assertEquals(titre, article.getTitre());
		assertEquals(texte, article.getTexte());
		assertEquals(photo, article.getPhoto());
		assertEquals(lien, article.getLien());
		assertEquals(date, article.getDate());
		assertEquals(nombreCommentaire, article.getNombreCommentaire());
		assertEquals(categorie, article.getCategorie());
		assertEquals(user, article.getUser());
	}

	@Test
	public void testConstructorWithParameters() {
		int id = 1;
		String titre = "Test Title";
		String texte = "Test Text";
		String photo = "Test Photo";
		String lien = "Test Link";
		LocalDate date = LocalDate.now();
		int nombreCommentaire = 10;
		Categorie categorie = new Categorie();
		Proprietaire user = new Proprietaire();

		Article articleWithParams = new Article(id, titre, texte, photo, lien, date, nombreCommentaire, categorie,
				user);

		assertNotNull(articleWithParams);
		assertEquals(id, articleWithParams.getId());
		assertEquals(titre, articleWithParams.getTitre());
		assertEquals(texte, articleWithParams.getTexte());
	}

	@Test
	public void testNoArgsConstructor() {
		Article newArticle = new Article();
		assertNotNull(newArticle);
	}

}