package ma.emsi.blog.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ma.emsi.blog.model.Proprietaire;
import ma.emsi.blog.service.ProprietaireService;

@RunWith(MockitoJUnitRunner.class)
public class ProprietaireControllerTest {

	@Mock
	private ProprietaireService proprietaireService;

	@InjectMocks
	private ProprietaireController proprietaireController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetProprietaireById() {
		Proprietaire proprietaire = new Proprietaire(1, "test@example.com", "password", "testuser", "photo.jpg",
				"Biography");
		when(proprietaireService.findById(1)).thenReturn(proprietaire);

		ResponseEntity<Proprietaire> expectedResponse = ResponseEntity.ok(proprietaire);

		ResponseEntity<Proprietaire> response = proprietaireController.findProprietaire(1);

		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());
	}

	@Test
	public void testGetAllProprietaires() throws Exception {
		Proprietaire proprietaire1 = new Proprietaire(1, "email1@example.com", "password1", "username1", "photo1",
				"bio1");
		Proprietaire proprietaire2 = new Proprietaire(2, "email2@example.com", "password2", "username2", "photo2",
				"bio2");

		List<Proprietaire> proprietaires = new ArrayList<>();
		proprietaires.add(proprietaire1);
		proprietaires.add(proprietaire2);

		when(proprietaireService.findAll()).thenReturn(proprietaires);
		ResponseEntity<List<Proprietaire>> response = proprietaireController.findAll();

		assertEquals(HttpStatus.OK, response.getStatusCode());

		List<Proprietaire> returnedProprietaires = response.getBody();
		assertNotNull(returnedProprietaires);
		assertEquals(2, returnedProprietaires.size());
		assertEquals("email1@example.com", returnedProprietaires.get(0).getEmail());
		assertEquals("email2@example.com", returnedProprietaires.get(1).getEmail());

	}

	@Test
	public void testUpdateProprietaire() {
		Proprietaire updatedProprietaire = new Proprietaire(1, "updated@example.com", "newpassword", "updateduser",
				"newphoto.jpg", "Updated Biography");
		doNothing().when(proprietaireService).update(any(Proprietaire.class));

		ResponseEntity<Void> response = proprietaireController.update(updatedProprietaire);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	public void testDeleteProprietaire() {
	    when(proprietaireService.existsById(1)).thenReturn(true);

	    ResponseEntity<Void> response = proprietaireController.delete(1);
	    verify(proprietaireService).delete(1);

	    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	public void testAddProprietaire() {
		Proprietaire newProprietaire = new Proprietaire(1, "test@example.com", "password", "testuser", "photo.jpg",
				"Biography");
		doNothing().when(proprietaireService).create(any(Proprietaire.class));

		ResponseEntity<Void> response = proprietaireController.add(newProprietaire);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	public void testGetProprietaireByIdNotFound() {
	    when(proprietaireService.findById(1)).thenReturn(null);

	    ResponseEntity<Proprietaire> response = proprietaireController.findProprietaire(1);

	    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    assertNull(response.getBody());
	}

}