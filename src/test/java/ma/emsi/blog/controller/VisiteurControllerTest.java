package ma.emsi.blog.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import ma.emsi.blog.model.Visiteur;
import ma.emsi.blog.service.VisiteurService;

public class VisiteurControllerTest {

	private static final String TEST_VISITEUR_NOM = "Visiteur Test";
	private static final String TEST_VISITEUR_EMAIL = "visiteur.com";

	@Mock
	private VisiteurService visiteurService;

	@InjectMocks
	private VisiteurController visiteurController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetVisiteurById() {
		Visiteur visiteur = new Visiteur(1, TEST_VISITEUR_NOM, TEST_VISITEUR_EMAIL, 5);
		when(visiteurService.findById(1)).thenReturn(visiteur);

		ResponseEntity<Visiteur> expectedResponse = ResponseEntity.ok(visiteur);

		ResponseEntity<Visiteur> response = visiteurController.getById(1);

		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());
	}

	@Test
    public void testGetVisiteurByIdNotFound() {
        when(visiteurService.findById(1)).thenReturn(null);

        ResponseEntity<Visiteur> response = visiteurController.getById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

	@Test
	public void testGetVisiteurByNom() {
		Visiteur visiteur = new Visiteur(1, TEST_VISITEUR_NOM, TEST_VISITEUR_EMAIL, 5);
		when(visiteurService.findByNom(TEST_VISITEUR_NOM)).thenReturn(visiteur);

		ResponseEntity<Visiteur> expectedResponse = ResponseEntity.ok(visiteur);

		ResponseEntity<Visiteur> response = visiteurController.getByNom(TEST_VISITEUR_NOM);

		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());
	}

	@Test
    public void testGetVisiteurByNomNotFound() {
        when(visiteurService.findByNom(TEST_VISITEUR_NOM)).thenReturn(null);

        ResponseEntity<Visiteur> response = visiteurController.getByNom(TEST_VISITEUR_NOM);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

	@Test
	public void testGetVisiteurByEmail() {
		Visiteur visiteur = new Visiteur(1, TEST_VISITEUR_NOM, TEST_VISITEUR_EMAIL, 5);
		when(visiteurService.findByEmail(TEST_VISITEUR_EMAIL)).thenReturn(visiteur);

		ResponseEntity<Visiteur> expectedResponse = ResponseEntity.ok(visiteur);

		ResponseEntity<Visiteur> response = visiteurController.getByEmail(TEST_VISITEUR_EMAIL);

		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());
	}

	@Test
    public void testGetVisiteurByEmailNotFound() {
        when(visiteurService.findByEmail(TEST_VISITEUR_EMAIL)).thenReturn(null);

        ResponseEntity<Visiteur> response = visiteurController.getByEmail(TEST_VISITEUR_EMAIL);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

	@Test
	public void testGetAllVisiteurs() {
		Visiteur visiteur1 = new Visiteur(1, TEST_VISITEUR_NOM, TEST_VISITEUR_EMAIL, 5);
		Visiteur visiteur2 = new Visiteur(2, "Alice Smith", "alice@example.com", 3);

		List<Visiteur> visiteurs = new ArrayList<>();
		visiteurs.add(visiteur1);
		visiteurs.add(visiteur2);

		when(visiteurService.findAll()).thenReturn(visiteurs);

		ResponseEntity<List<Visiteur>> expectedResponse = ResponseEntity.ok(visiteurs);

		ResponseEntity<List<Visiteur>> response = visiteurController.findAll();

		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());
	}
}