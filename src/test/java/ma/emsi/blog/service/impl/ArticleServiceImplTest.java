package ma.emsi.blog.service.impl;

import ma.emsi.blog.model.Article;
import ma.emsi.blog.model.Categorie;
import ma.emsi.blog.model.Commentaire;
import ma.emsi.blog.model.Proprietaire;
import ma.emsi.blog.repository.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {

    @MockBean
    private ArticleRepository articleRepository;
    @InjectMocks
    private ArticleServiceImpl articleService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateArticle() {
        Article article = new Article();
        when(articleRepository.save(any(Article.class))).thenReturn(article);
        articleService.create(article);
        verify(articleRepository, times(1)).save(article);
    }

    @Test(timeout = 1000)
    public void testPerformanceGetAllArticles() {
        List<Article> articles = articleService.findAll();
        assertNotNull(articles);
    }

    @Test
    public void testUpdate() {

        Article existingArticle = new Article();
        existingArticle.setId(1);
        existingArticle.setTitre("Old Title");
        existingArticle.setTexte("Old Text");
        existingArticle.setPhoto("old_photo.jpg");
        existingArticle.setLien("old_link");
        existingArticle.setDate(LocalDate.now());
        existingArticle.setNombreCommentaire(5);
        existingArticle.setCategorie(new Categorie());
        existingArticle.setUser(new Proprietaire());

        Article updatedArticle = new Article();
        updatedArticle.setId(1);
        updatedArticle.setTitre("New Title");
        updatedArticle.setTexte("New Text");
        updatedArticle.setPhoto("new_photo.jpg");
        updatedArticle.setLien("new_link");
        updatedArticle.setDate(LocalDate.now().plusDays(1));
        updatedArticle.setNombreCommentaire(10);
        updatedArticle.setCategorie(new Categorie());
        updatedArticle.setUser(new Proprietaire());

        when(articleRepository.findById(1)).thenReturn(existingArticle);


        articleService.update(updatedArticle);


        verify(articleRepository).save(existingArticle);
        assertEquals("New Title", existingArticle.getTitre());
        assertEquals("New Text", existingArticle.getTexte());
        assertEquals("new_photo.jpg", existingArticle.getPhoto());
        assertEquals("new_link", existingArticle.getLien());
        assertEquals(LocalDate.now().plusDays(1), existingArticle.getDate());
        assertEquals(10, existingArticle.getNombreCommentaire());
    }

    @Test
    public void testAddCommentaire() {
        Article article = new Article();
        article.setId(1);
        article.setNombreCommentaire(0);

        Commentaire commentaire = new Commentaire();
        commentaire.setArticle(article);

        when(articleRepository.findById(1)).thenReturn(article);

        articleService.addCommentaire(commentaire);

        verify(articleRepository).save(article);
        assertEquals(1, article.getNombreCommentaire());
    }

    @Test
    public void testDeleteCommentaire() {
        Article article = new Article();
        article.setId(1);
        article.setNombreCommentaire(1);

        Commentaire commentaire = new Commentaire();
        commentaire.setArticle(article);

        when(articleRepository.findById(1)).thenReturn(article);

        articleService.deleteCommentaire(commentaire);

        verify(articleRepository).save(article);
        assertEquals(0, article.getNombreCommentaire());
    }
    @Test
    public void testDelete() {

        int articleId = 1;
        articleService.delete(articleId);
        verify(articleRepository).deleteById(articleId);
    }

    @Test
    public void testFindAll() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article());
        articles.add(new Article());

        when(articleRepository.findAll()).thenReturn(articles);

        List<Article> result = articleService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        // Given
        int articleId = 1;
        Article expectedArticle = new Article();
        expectedArticle.setId(articleId);

        // Mock the behavior of articleRepository.findById to return Optional
        when(articleRepository.findById(articleId)).thenReturn(expectedArticle);

        // When
        Article result = articleService.findById(articleId);

        // Then
        assertNotNull(result);
        assertEquals(articleId, result.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        // Given
        int articleId = 1;

        // Mock the behavior of articleRepository.findById to return an empty Optional
        when(articleRepository.findById(articleId)).thenReturn(null);

        // When
        Article result = articleService.findById(articleId);

        // Then
        assertNull(result);
    }

    @Test
    public void testSortByDate() {
        // Given
        List<Article> expectedArticles = new ArrayList<>(); // Replace with your expected list of articles

        // Mock the behavior of articleRepository.sortByDate
        when(articleRepository.sortByDate()).thenReturn(expectedArticles);

        // When
        List<Article> result = articleService.sortByDate();

        // Then
        assertNotNull(result);
        assertEquals(expectedArticles.size(), result.size());
        // Add more assertions based on your specific sorting logic
    }

    @Test
    public void testSortByComments() {
        // Given
        List<Article> expectedArticles = new ArrayList<>(); // Replace with your expected list of articles

        // Mock the behavior of articleRepository.sortByComments
        when(articleRepository.sortByComments()).thenReturn(expectedArticles);

        // When
        List<Article> result = articleService.sortByComments();

        // Then
        assertNotNull(result);
        assertEquals(expectedArticles.size(), result.size());
        // Add more assertions based on your specific sorting logic
    }

    @Test
    public void testFindByDate() {
        // Given
        LocalDate date = LocalDate.now(); // Replace with your specific date
        List<Article> expectedArticles = new ArrayList<>(); // Replace with your expected list of articles

        // Mock the behavior of articleRepository.findByDate
        when(articleRepository.findByDate(date)).thenReturn(expectedArticles);

        // When
        List<Article> result = articleService.findByDate(date);

        // Then
        assertNotNull(result);
        assertEquals(expectedArticles.size(), result.size());
    }

    @Test
    public void testSearchByKeyword() {
        // Given
        String keyword = "example"; // Replace with your specific keyword
        List<Article> expectedArticles = new ArrayList<>(); // Replace with your expected list of articles

        // Mock the behavior of articleRepository.searchByKeyword
        when(articleRepository.searchByKeyword(keyword)).thenReturn(expectedArticles);

        // When
        List<Article> result = articleService.searchByKeyword(keyword);

        // Then
        assertNotNull(result);
        assertEquals(expectedArticles.size(), result.size());
    }

    @Test
    public void testSearchByCategory() {
        // Given
        int categoryId = 1; // Replace with your specific category ID
        List<Article> expectedArticles = new ArrayList<>(); // Replace with your expected list of articles

        // Mock the behavior of articleRepository.findByCategory
        when(articleRepository.findByCategory(categoryId)).thenReturn(expectedArticles);

        // When
        List<Article> result = articleService.searchByCategory(categoryId);

        // Then
        assertNotNull(result);
        assertEquals(expectedArticles.size(), result.size());
    }


}