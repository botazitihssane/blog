package ma.emsi.blog.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class ArticleTest {

	private Article article;
	private Categorie categorie;
	private Proprietaire user;

	@Before
	public void setUp() {
		categorie = new Categorie();
		user = new Proprietaire();
		article = new Article(1, "Test Title", "Test Text", "photo.jpg", "http://link.com", LocalDate.now(), 0,
				categorie, user);
	}

	@Test
	public void testGettersAndSetters() {
		// Test getters
		assertEquals(1, article.getId());
		assertEquals("Test Title", article.getTitre());
		assertEquals("Test Text", article.getTexte());
		assertEquals("photo.jpg", article.getPhoto());
		assertEquals("http://link.com", article.getLien());
		assertNotNull(article.getDate());
		assertEquals(0, article.getNombreCommentaire());
		assertEquals(categorie, article.getCategorie());
		assertEquals(user, article.getUser());

		// Test setters
		article.setId(2);
		article.setTitre("New Title");
		article.setTexte("New Text");
		article.setPhoto("new_photo.jpg");
		article.setLien("http://newlink.com");
		article.setDate(LocalDate.of(2023, 1, 1));
		article.setNombreCommentaire(10);
		Categorie newCategorie = new Categorie();
		Proprietaire newUser = new Proprietaire();
		article.setCategorie(newCategorie);
		article.setUser(newUser);
		assertEquals(2, article.getId());
		assertEquals("New Title", article.getTitre());
		assertEquals("New Text", article.getTexte());
		assertEquals("new_photo.jpg", article.getPhoto());
		assertEquals("http://newlink.com", article.getLien());
		assertEquals(LocalDate.of(2023, 1, 1), article.getDate());
		assertEquals(10, article.getNombreCommentaire());
		assertEquals(newCategorie, article.getCategorie());
		assertEquals(newUser, article.getUser());
	}
}