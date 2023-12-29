package ma.emsi.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.blog.model.Categorie;
import ma.emsi.blog.service.CategorieService;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "*")
public class CategorieController {
	@Autowired
	private CategorieService categorieService;

	@PostMapping(value = "/categorie", produces = "application/json")
	public ResponseEntity<Categorie> add(@RequestBody Categorie categorie) {
		Categorie savedCategorie = categorieService.create(categorie);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCategorie);
	}

	@GetMapping(value = "/categories", produces = "application/json")
	public ResponseEntity<List<Categorie>> findAll() {
		List<Categorie> categories = categorieService.findAll();
		return ResponseEntity.ok().body(categories);
	}

	@GetMapping(value = "/categorie/id/{id}", produces = "application/json")
	public ResponseEntity<Optional<Categorie>> findCategorie(@PathVariable int id) {
		Optional<Categorie> categorie = categorieService.findById(id);
		return ResponseEntity.ok().body(categorie);
	}

	@GetMapping(value = "/categorie/nom/{nom}", produces = "application/json")
	public ResponseEntity<Optional<Categorie>> findCategorieByNom(@PathVariable String nom) {
		Optional<Categorie> categorie = categorieService.findByNom(nom);
		return ResponseEntity.ok().body(categorie);
	}

	@PutMapping(value = "/categorie", produces = "application/json")
	public ResponseEntity<Categorie> update(@RequestBody Categorie categorie) {
		Categorie updatedCategorie = categorieService.update(categorie);
		return ResponseEntity.ok().body(updatedCategorie);
	}

	@DeleteMapping(value = "/categorie/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		categorieService.delete(id);
	}

}
