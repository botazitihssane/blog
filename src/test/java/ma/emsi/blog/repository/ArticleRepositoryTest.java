package ma.emsi.blog.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ma.emsi.blog.model.Article;
import ma.emsi.blog.model.Categorie;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleRepositoryTest {

	@Autowired
	private ArticleRepository articleRepository;

	private Article testArticle;

	@Autowired
	private TestEntityManager entityManager;

	@Before
	public void setUp() {
		Categorie categorie = new Categorie();
		categorie.setId(1);
		categorie.setNom("categorie");

		testArticle = new Article();
		testArticle.setTitre("Titre");
		testArticle.setTexte("Texte");
		testArticle.setDate(LocalDate.now());
		testArticle.setNombreCommentaire(5);
		testArticle.setCategorie(categorie);
		entityManager.persist(testArticle);
	}

	@Test
	public void testFindByDate() {
		List<Article> articles = articleRepository.findByDate(testArticle.getDate());
		assertThat(articles).isNotNull().hasSize(1).contains(testArticle);
	}

	/*
	 * @Test public void testFindById_existingId() { Article found =
	 * articleRepository.findById(testArticle.getId()); assertNotNull(found);
	 * assertEquals(testArticle.getId(), found.getId()); }
	 * 
	 * @Test public void testFindById_nonExistingId() { Article notFound =
	 * articleRepository.findById(-1); assertNull(notFound); }
	 * 
	 * @Test public void testFindByDate_withArticles() { LocalDate testDate =
	 * testArticle.getDate(); List<Article> articles =
	 * articleRepository.findByDate(testDate); assertFalse(articles.isEmpty());
	 * assertTrue(articles.stream().anyMatch(a -> a.getDate().equals(testDate))); }
	 * 
	 * @Test public void testFindByDate_noArticles() { List<Article> articles =
	 * articleRepository.findByDate(LocalDate.of(2000, 1, 1));
	 * assertTrue(articles.isEmpty()); }
	 * 
	 * @Test public void testFindByCategory() { List<Article> articles =
	 * articleRepository.findByCategory(testArticle.getCategorie().getId());
	 * assertFalse(articles.isEmpty()); assertTrue(articles.stream().anyMatch(a ->
	 * a.getCategorie().getId() == testArticle.getCategorie().getId())); }
	 * 
	 * @Test public void testSearchByKeyword() { List<Article> articles =
	 * articleRepository.searchByKeyword("Test"); assertFalse(articles.isEmpty());
	 * assertTrue(articles.stream().anyMatch(a -> a.getTitre().contains("Test") ||
	 * a.getTexte().contains("Test"))); }
	 * 
	 * @Test public void testSortByComments() { List<Article> sortedArticles =
	 * articleRepository.sortByComments(); assertFalse(sortedArticles.isEmpty());
	 * assertTrue(sortedArticles.get(0).getNombreCommentaire() >=
	 * sortedArticles.get(1).getNombreCommentaire()); }
	 * 
	 * @Test public void testSortByDate() { List<Article> sortedArticles =
	 * articleRepository.sortByDate(); assertFalse(sortedArticles.isEmpty());
	 * assertTrue(sortedArticles.get(0).getDate().compareTo(sortedArticles.get(1).
	 * getDate()) >= 0); }
	 */
}
