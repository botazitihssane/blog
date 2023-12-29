package ma.emsi.blog.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ma.emsi.blog.model.Categorie;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategorieRepositoryTest {

	@Autowired
	private CategorieRepository categorieRepository;

	private Categorie savedCategorie;

	@Autowired
	private TestEntityManager entityManager;

	@Before
	public void setUp() {
		savedCategorie.setId(1);
		savedCategorie.setNom("Test Categorie");
		entityManager.persist(savedCategorie);
	}

	@Test
	public void testFindAll() {
	    List<Categorie> categories = categorieRepository.findAll();
	    assertThat(categories).isNotNull().hasSize(1).contains(savedCategorie);
	}

	/*@Test
	public void testFindById_thenReturnCategorie() {
		Optional<Categorie> found = categorieRepository.findById(savedCategorie.getId());
		assertTrue(found.isPresent());
		assertEquals("Test Categorie", found.get().getNom());
	}*/

	/*
	 * @Test public void testFindByNonExistentId_thenReturnEmpty() {
	 * Optional<Categorie> notFound = categorieRepository.findById(-99);
	 * assertFalse(notFound.isPresent()); }
	 * 
	 * @Test public void testFindByNom_thenReturnCategorie() { Optional<Categorie>
	 * found = categorieRepository.findByNom("Test Categorie");
	 * assertTrue(found.isPresent()); assertEquals(savedCategorie.getId(),
	 * found.get().getId()); }
	 * 
	 * @Test public void testFindByNonExistentNom_thenReturnNull() {
	 * Optional<Categorie> notFound = categorieRepository.findByNom("Non Existent");
	 * assertFalse(notFound.isPresent()); }
	 */
}
