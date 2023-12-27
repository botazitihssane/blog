package ma.emsi.blog.repository;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ma.emsi.blog.model.Article;
import ma.emsi.blog.model.Categorie;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleRepositoryTest {

	@Autowired
    private ArticleRepository articleRepository;

    private Article testArticle;

    @Before
    public void setUp() {
        
        Categorie categorie = new Categorie(1, "TestCategory");
        testArticle = new Article();
        articleRepository.save(testArticle);
    }

    @Test
    public void whenFindById_thenReturnArticle() {
        Article found = articleRepository.findById(testArticle.getId());
        assertNotNull("Article should not be null", found);
        assertEquals("Article title should match", testArticle.getTitre(), found.getTitre());
    }

    @Test
    public void whenFindByDate_thenReturnArticles() {
        List<Article> articles = articleRepository.findByDate(testArticle.getDate());
        assertFalse("Article list should not be empty", articles.isEmpty());
        assertTrue("Article list should contain the test article", articles.contains(testArticle));
    }

    @Test
    public void whenSortByComments_thenReturnSortedArticles() {
        List<Article> articles = articleRepository.sortByComments();
        assertFalse("Article list should not be empty", articles.isEmpty());
    }

    @Test
    public void whenSortByDate_thenReturnSortedArticles() {
        List<Article> articles = articleRepository.sortByDate();
        assertFalse("Article list should not be empty", articles.isEmpty());
       
    }

    @Test
    public void whenSearchByKeyword_thenReturnMatchingArticles() {
        List<Article> articles = articleRepository.searchByKeyword("Test");
        assertFalse("Article list should not be empty", articles.isEmpty());
        assertTrue("Article list should contain the test article", articles.contains(testArticle));
    }

    @Test
    public void whenFindByCategory_thenReturnArticles() {
        List<Article> articles = articleRepository.findByCategory(testArticle.getCategorie().getId());
        assertFalse("Article list should not be empty", articles.isEmpty());
        assertTrue("Article list should contain the test article", articles.contains(testArticle));
    }
}
