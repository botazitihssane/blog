package ma.emsi.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.blog.model.Visiteur;
import ma.emsi.blog.service.VisiteurService;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "*")
public class VisiteurController {
	@Autowired
	private VisiteurService visiteurService;

	@PostMapping(value = "/visiteur", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> add(@RequestBody Visiteur v) {
		visiteurService.create(v);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/visiteur/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Visiteur> getById(@PathVariable int id) {
		Visiteur result = visiteurService.findById(id);
		if (result != null) {
			return ResponseEntity.ok().body(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/visiteur/nom/{nom}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Visiteur> getByNom(@PathVariable String nom) {
		Visiteur result = visiteurService.findByNom(nom);
		if (result != null) {
			return ResponseEntity.ok().body(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/visiteur/email/{email}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Visiteur> getByEmail(@PathVariable String email) {
		Visiteur result = visiteurService.findByEmail(email);
		if (result != null) {
			return ResponseEntity.ok().body(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/visiteurs", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Visiteur>> findAll() {
		List<Visiteur> result = visiteurService.findAll();
		return ResponseEntity.ok().body(result);
	}
}
