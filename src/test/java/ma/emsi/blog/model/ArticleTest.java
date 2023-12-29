package ma.emsi.blog.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RunWith(MockitoJUnitRunner.class)

public class ArticleTest {

	@InjectMocks
	private Article article;

	@Mock
	private Categorie categorie;

	@Mock
	private Proprietaire proprietaire;

	private Validator validator;

	@Before
	public void setUp() {
		article = new Article();
	}

	@Test
	public void getId() {
		article.setId(1);
		assertEquals(1, article.getId());
	}

	@Test
	public void getTitre() {
		article.setTitre("Test Title");
		assertEquals("Test Title", article.getTitre());
	}

	@Test
	public void setDate() {
		LocalDate currentDate = LocalDate.now();
		article.setDate(currentDate);
		assertEquals(currentDate, article.getDate());
	}

	@Test
	public void setCategorie() {
		article.setCategorie(categorie);
		assertEquals(categorie, article.getCategorie());
	}

	@Test
	public void setUser() {
		article.setUser(proprietaire);
		assertEquals(proprietaire, article.getUser());
	}

	@Test
	public void testConstructorAndSettersAndGetters() {
		Article newArticle = new Article(1, "Title", "Text", "photo.jpg", "http://example.com", LocalDate.now(), 5,
				categorie, proprietaire);

		assertEquals(1, newArticle.getId());
		assertEquals("Title", newArticle.getTitre());
		assertEquals("Text", newArticle.getTexte());
		assertEquals("photo.jpg", newArticle.getPhoto());
		assertEquals("http://example.com", newArticle.getLien());
		assertEquals(LocalDate.now(), newArticle.getDate());
		assertEquals(5, newArticle.getNombreCommentaire());
		assertEquals(categorie, newArticle.getCategorie());
		assertEquals(proprietaire, newArticle.getUser());

		// Test setters and getters
		article.setId(2);
		article.setTitre("New Title");
		article.setTexte("New Text");
		article.setPhoto("new_photo.jpg");
		article.setLien("http://new-example.com");
		article.setDate(LocalDate.now().plusDays(1));
		article.setNombreCommentaire(10);
		article.setCategorie(null);
		article.setUser(null);

		assertEquals(2, article.getId());
		assertEquals("New Title", article.getTitre());
		assertEquals("New Text", article.getTexte());
		assertEquals("new_photo.jpg", article.getPhoto());
		assertEquals("http://new-example.com", article.getLien());
		assertEquals(LocalDate.now().plusDays(1), article.getDate());
		assertEquals(10, article.getNombreCommentaire());
		assertNull(article.getCategorie());
		assertNull(article.getUser());
	}

	/*
	 * @Test public void testTitreNotBlank() { article.setTitre("");
	 * assertViolations("titre"); }
	 * 
	 * @Test public void testTexteNotBlank() { article.setTexte("");
	 * assertViolations("texte"); }
	 * 
	 * @Test public void testDateNotNull() { article.setDate(null);
	 * assertViolations("date"); }
	 * 
	 * @Test public void testNombreCommentaireMin() {
	 * article.setNombreCommentaire(-1); assertViolations("nombreCommentaire"); }
	 * 
	 * @Test public void testCategorieNotNull() { article.setCategorie(null);
	 * assertViolations("categorie"); }
	 * 
	 * @Test public void testUserNotNull() { article.setUser(null);
	 * assertViolations("user"); }
	 * 
	 * private void assertViolations(String fieldName) {
	 * Set<ConstraintViolation<Article>> violations =
	 * validator.validateProperty(article, fieldName);
	 * assertFalse("Expected validation errors for " + fieldName,
	 * violations.isEmpty()); }
	 * 
	 * @Test public void gettersAndSetters() { int id = 1; String titre =
	 * "Test Title"; String texte = "Test Text"; String photo = "Test Photo"; String
	 * lien = "Test Link"; LocalDate date = LocalDate.now(); int nombreCommentaire =
	 * 10; Categorie categorie = new Categorie(); // Assuming you have a Categorie
	 * class Proprietaire user = new Proprietaire(); // Assuming you have a
	 * Proprietaire class
	 * 
	 * article.setId(id); article.setTitre(titre); article.setTexte(texte);
	 * article.setPhoto(photo); article.setLien(lien); article.setDate(date);
	 * article.setNombreCommentaire(nombreCommentaire);
	 * article.setCategorie(categorie); article.setUser(user);
	 * 
	 * assertEquals(id, article.getId()); assertEquals(titre, article.getTitre());
	 * assertEquals(texte, article.getTexte()); assertEquals(photo,
	 * article.getPhoto()); assertEquals(lien, article.getLien());
	 * assertEquals(date, article.getDate()); assertEquals(nombreCommentaire,
	 * article.getNombreCommentaire()); assertEquals(categorie,
	 * article.getCategorie()); assertEquals(user, article.getUser()); }
	 * 
	 * @Test public void testConstructorWithParameters() { int id = 1; String titre
	 * = "Test Title"; String texte = "Test Text"; String photo = "Test Photo";
	 * String lien = "Test Link"; LocalDate date = LocalDate.now(); int
	 * nombreCommentaire = 10; Categorie categorie = new Categorie(); Proprietaire
	 * user = new Proprietaire();
	 * 
	 * Article articleWithParams = new Article(id, titre, texte, photo, lien, date,
	 * nombreCommentaire, categorie, user);
	 * 
	 * assertNotNull(articleWithParams); assertEquals(id,
	 * articleWithParams.getId()); assertEquals(titre,
	 * articleWithParams.getTitre()); assertEquals(texte,
	 * articleWithParams.getTexte()); }
	 * 
	 * @Test public void testNoArgsConstructor() { Article newArticle = new
	 * Article(); assertNotNull(newArticle); }
	 */

}