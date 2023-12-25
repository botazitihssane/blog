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

import ma.emsi.blog.model.Categorie;
import ma.emsi.blog.service.CategorieService;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "*")
public class CategorieController {
	@Autowired
	private CategorieService categorieService;

	@PostMapping(value = "/categorie", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> add(@RequestBody Categorie p) {
		categorieService.create(p);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/categories", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<List<Categorie>> findAll() {
		List<Categorie> result = categorieService.findAll();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/categorie/id/{id}", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Categorie> findcategorie(@PathVariable int id) {
		Categorie result = categorieService.findById(id);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/categorie/nom/{nom}", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Categorie> findcategorieByNom(@PathVariable String nom) {
		Categorie result = categorieService.findByNom(nom);
		return ResponseEntity.ok().body(result);
	}

	@PutMapping(value = "/categorie", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> update(@RequestBody Categorie p) {
		categorieService.update(p);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/categorie/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> delete(@PathVariable int id) {
		categorieService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
