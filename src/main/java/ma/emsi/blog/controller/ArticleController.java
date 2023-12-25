package ma.emsi.blog.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.blog.model.Article;
import ma.emsi.blog.service.ArticleService;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "*")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@PostMapping(value = "/article", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> add(@RequestBody Article p) {
		articleService.create(p);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/articles", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<List<Article>> findAll() {
		List<Article> result = articleService.findAll();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/article/id/{id}", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Article> findarticle(@PathVariable int id) {
		Article result = articleService.findById(id);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/article/categorie/{id}", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<List<Article>> findArticleByCategorie(@PathVariable int id) {
		List<Article> result = articleService.searchByCategory(id);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/article/sort/date", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<List<Article>> sortByDate() {
		List<Article> result = articleService.sortByDate();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/article/sort/comments", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<List<Article>> sortByComments() {
		List<Article> result = articleService.sortByComments();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/article/search/keyword/{keyword}", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<List<Article>> sortByComments(@PathVariable String keyword) {
		List<Article> result = articleService.searchByKeyword(keyword);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/article/search/date/{date}", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<List<Article>> sortByComments(@PathVariable LocalDate date) {
		List<Article> result = articleService.findByDate(date);
		return ResponseEntity.ok().body(result);
	}

	@PutMapping(value = "/article", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> update(@RequestBody Article p) {
		articleService.update(p);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/article/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> delete(@PathVariable int id) {
		articleService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
