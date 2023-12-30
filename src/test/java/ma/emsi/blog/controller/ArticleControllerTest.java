package ma.emsi.blog.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ma.emsi.blog.model.Article;
import ma.emsi.blog.service.ArticleService;

public class ArticleControllerTest {

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddArticle() {
        Article newArticle = Article.builder()
                                .id(1)
                                .titre("Test Article")
                                .texte("This is a test article.")
                                .photo("test.jpg")
                                .date(LocalDate.now())
                                .build();
        doNothing().when(articleService).create(any(Article.class));

        ResponseEntity<Void> response = articleController.add(newArticle);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetAllArticles() {
        List<Article> articles = new ArrayList<>();
        articles.add(Article.builder().id(1).titre("Article 1").texte("This is article 1.").photo("article1.jpg").date(LocalDate.now()).build());
        articles.add(Article.builder().id(2).titre("Article 2").texte("This is article 2.").photo("article2.jpg").date(LocalDate.now()).build());

        when(articleService.findAll()).thenReturn(articles);

        ResponseEntity<List<Article>> expectedResponse = ResponseEntity.ok(articles);

        ResponseEntity<List<Article>> response = articleController.findAll();

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    public void testGetArticleById() {
        Article article =Article.builder()
                            .id(1)
                            .titre("Test Article")
                            .texte("This is a test article.")
                            .photo("test.jpg")
                            .date(LocalDate.now())
                            .build();
        when(articleService.findById(1)).thenReturn(article);

        ResponseEntity<Article> expectedResponse = ResponseEntity.ok(article);

        ResponseEntity<Article> response = articleController.findarticle(1);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    public void testUpdateArticle() {
        Article updatedArticle = Article.builder()
                                    .id(1)
                                    .titre("Updated Article")
                                    .texte("This is an updated article.")
                                    .photo("updated.jpg")
                                    .date(LocalDate.now())
                                    .build();
        doNothing().when(articleService).update(any(Article.class));

        ResponseEntity<Void> response = articleController.update(updatedArticle);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteArticle() {
        doNothing().when(articleService).delete(1);

        ResponseEntity<Void> response = articleController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
