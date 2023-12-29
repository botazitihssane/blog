package ma.emsi.blog.service.impl;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import ma.emsi.blog.model.Article;
import ma.emsi.blog.repository.ArticleRepository;
import ma.emsi.blog.service.ArticleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {

	@MockBean
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleService articleService;

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
}
