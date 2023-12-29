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
	public ResponseEntity<Void> add(@RequestBody Commentaire commentaire) {
		commentaireService.create(commentaire);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/commentaires", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Commentaire>> findAll() {
		List<Commentaire> result = commentaireService.findAll();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/commentaire/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Commentaire> findcommentaire(@PathVariable int id) {
		Commentaire result = commentaireService.findById(id);
		if (result != null) {
			return ResponseEntity.ok().body(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping(value = "/commentaire", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> update(@RequestBody Commentaire commentaire) {
		Commentaire existingCommentaire = commentaireService.findById(commentaire.getId());
		if (existingCommentaire != null) {
			commentaireService.update(commentaire);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/commentaire/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> delete(@PathVariable int id) {
		Commentaire existingCommentaire = commentaireService.findById(id);
		if (existingCommentaire != null) {
			commentaireService.delete(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
