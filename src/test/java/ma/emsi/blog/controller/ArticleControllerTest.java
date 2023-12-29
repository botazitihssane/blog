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
		Article newArticle = new Article(1, "Test Article", "This is a test article.", "test.jpg", null,
				LocalDate.now(), 0, null, null);
		doNothing().when(articleService).create(any(Article.class));

		ResponseEntity<Void> response = articleController.add(newArticle);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	public void testGetAllArticles() {
		Article article1 = new Article(1, "Article 1", "This is article 1.", "article1.jpg", null, LocalDate.now(), 0,
				null, null);
		Article article2 = new Article(2, "Article 2", "This is article 2.", "article2.jpg", null, LocalDate.now(), 0,
				null, null);

		List<Article> articles = new ArrayList<>();
		articles.add(article1);
		articles.add(article2);

		when(articleService.findAll()).thenReturn(articles);

		ResponseEntity<List<Article>> expectedResponse = ResponseEntity.ok(articles);

		ResponseEntity<List<Article>> response = articleController.findAll();

		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());
	}

	@Test
	public void testGetArticleById() {
		Article article = new Article(1, "Test Article", "This is a test article.", "test.jpg", null, LocalDate.now(),
				0, null, null);
		when(articleService.findById(1)).thenReturn(article);

		ResponseEntity<Article> expectedResponse = ResponseEntity.ok(article);

		ResponseEntity<Article> response = articleController.findarticle(1);

		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());
	}

	@Test
	public void testUpdateArticle() {
		Article updatedArticle = new Article(1, "Updated Article", "This is an updated article.", "updated.jpg", null,
				LocalDate.now(), 0, null, null);
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
