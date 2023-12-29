package ma.emsi.blog.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.emsi.blog.exception.ResourceNotFoundException;
import ma.emsi.blog.model.Article;
import ma.emsi.blog.model.Categorie;
import ma.emsi.blog.model.Proprietaire;
import ma.emsi.blog.service.ArticleService;

@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ArticleService articleService;

	private Article sampleArticle;
	private Categorie sampleCategorie;
	private Proprietaire sampleProprietaire;

	@Before
	public void setup() {
		sampleCategorie = new Categorie();
		sampleCategorie.setId(1);
		sampleCategorie.setNom("Tech");

		sampleProprietaire = new Proprietaire();
		sampleProprietaire.setId(1);
		sampleProprietaire.setEmail("proprietaire@example.com");
		sampleProprietaire.setPassword("strongPassword123");
		sampleProprietaire.setUsername("proprietaireUser");
		sampleProprietaire.setPhoto("photoUrl.jpg");
		sampleProprietaire.setBiographie("This is a sample biography.");

		// Initialize the Article
		sampleArticle = new Article();
		sampleArticle.setId(1);
		sampleArticle.setTitre("Titre");
		sampleArticle.setTexte("Texte");
		sampleArticle.setPhoto("photo.jpg");
		sampleArticle.setLien("http://lien.com");
		sampleArticle.setDate(LocalDate.now());
		sampleArticle.setNombreCommentaire(0);
		sampleArticle.setCategorie(sampleCategorie);
		sampleArticle.setUser(sampleProprietaire);
	}

	@Test
    public void testGetArticle() throws Exception {
        when(articleService.findById(1)).thenReturn(sampleArticle);
        mockMvc.perform(get("/blog/article/id/1").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }

	@Test
	public void testFindAllArticles() throws Exception {
		List<Article> articles = new ArrayList<>();
		articles.add(sampleArticle);
		when(articleService.findAll()).thenReturn(articles);

		mockMvc.perform(get("/blog/articles").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	public void testFindArticleByCategorie() throws Exception {
		int categoryId = 1;
		List<Article> articles = new ArrayList<>();
		when(articleService.searchByCategory(categoryId)).thenReturn(articles);

		mockMvc.perform(get("/blog/article/categorie/" + categoryId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(articles.size())));
	}

	@Test
	public void testSortByDate() throws Exception {
		List<Article> articles = new ArrayList<>();
		when(articleService.sortByDate()).thenReturn(articles);

		mockMvc.perform(get("/blog/article/sort/date").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(articles.size())));
	}

	@Test
	public void testSortByComments() throws Exception {
		List<Article> articles = new ArrayList<>();
		when(articleService.sortByComments()).thenReturn(articles);

		mockMvc.perform(get("/blog/article/sort/comments").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(articles.size())));
	}

	@Test
	public void testSearchByKeyword() throws Exception {
		String keyword = "Texte";
		List<Article> articles = new ArrayList<>();
		when(articleService.searchByKeyword(keyword)).thenReturn(articles);

		mockMvc.perform(get("/blog/article/search/keyword/" + keyword).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(articles.size())));
	}

	@Test
	@WithMockUser
	public void testAddArticle() throws Exception {
		Article article = new Article();
		mockMvc.perform(post("/blog/article").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(article))).andExpect(status().isNoContent());
		verify(articleService, times(1)).create(any(Article.class));
	}

	@Test
	@WithMockUser
	public void testUpdateArticle() throws Exception {
		Article article = new Article();
		sampleArticle.setTitre("chengedTitre");
		mockMvc.perform(put("/blog/article").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(article))).andExpect(status().isNoContent());
		verify(articleService, times(1)).update(any(Article.class));
	}

	@Test
	@WithMockUser
	public void testDeleteArticle() throws Exception {
		int articleId = 1;
		mockMvc.perform(delete("/blog/article/" + articleId).header("Authorization", "Bearer mock-jwt-token")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
		verify(articleService, times(1)).delete(articleId);
	}

	@Test
	public void testFindArticleByCategorieException() throws Exception {
		int categoryId = 999;
		when(articleService.searchByCategory(categoryId))
				.thenThrow(new ResourceNotFoundException("Category not found"));

		mockMvc.perform(get("/blog/article/categorie/" + categoryId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
				.andExpect(result -> assertEquals("Category not found", result.getResolvedException().getMessage()));
	}

	@Test
	public void testGetNonExistentArticle() throws Exception {
		int nonExistentId = 9999;
		when(articleService.findById(nonExistentId))
				.thenThrow(new ResourceNotFoundException("Article not found with id: " + nonExistentId));

		mockMvc.perform(get("/blog/article/id/" + nonExistentId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
				.andExpect(result -> assertEquals("Article not found with id: " + nonExistentId,
						result.getResolvedException().getMessage()));
	}
}
