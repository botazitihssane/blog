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

import ma.emsi.blog.model.Proprietaire;
import ma.emsi.blog.service.ProprietaireService;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "*")
public class ProprietaireController {

	@Autowired
	private ProprietaireService proprietaireService;

	@PostMapping(value = "/proprietaire", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> add(@RequestBody Proprietaire p) {
		proprietaireService.create(p);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/proprietaires", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<List<Proprietaire>> findAll() {
		List<Proprietaire> result = proprietaireService.findAll();
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/proprietaire/id/{id}", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Proprietaire> findProprietaire(@PathVariable int id) {
		Proprietaire result = proprietaireService.findById(id);
		return ResponseEntity.ok().body(result);
	}
	
	@PutMapping(value = "/proprietaire", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> update(@RequestBody Proprietaire p) {
		proprietaireService.update(p);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/proprietaire/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> delete(@PathVariable int id) {
		proprietaireService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
