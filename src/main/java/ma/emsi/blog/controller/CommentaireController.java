package ma.emsi.blog.controller;

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

import ma.emsi.blog.model.Commentaire;
import ma.emsi.blog.service.CommentaireService;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "*")
public class CommentaireController {
	@Autowired
	private CommentaireService commentaireService;

	@PostMapping(value = "/commentaire", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> add(@RequestBody Commentaire p) {
		commentaireService.create(p);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/commentaires", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<List<Commentaire>> findAll() {
		List<Commentaire> result = commentaireService.findAll();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/commentaire/id/{id}", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Commentaire> findcommentaire(@PathVariable int id) {
		Commentaire result = commentaireService.findById(id);
		return ResponseEntity.ok().body(result);
	}

	@PutMapping(value = "/commentaire", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> update(@RequestBody Commentaire p) {
		commentaireService.update(p);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/commentaire/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> delete(@PathVariable int id) {
		commentaireService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
