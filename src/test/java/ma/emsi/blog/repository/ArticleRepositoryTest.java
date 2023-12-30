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

}
