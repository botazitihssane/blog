package ma.emsi.blog.model;

import jakarta.validation.ConstraintViolationException;
import ma.emsi.blog.repository.CategorieRepository;
import ma.emsi.blog.service.impl.CategorieServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CategorieTest {

    @Mock
    private CategorieRepository categorieRepository;

    @InjectMocks
    private CategorieServiceImpl categorieService;

    @Test
    public void testSaveCategorie() {
        // Given
        Categorie categorie = new Categorie();
        categorie.setNom("TestCategory");

        // When
        when(categorieRepository.save(categorie)).thenReturn(categorie);

        // Then
        Categorie savedCategorie = categorieService.create(categorie);
        assertNotNull(savedCategorie);
        assertNotNull(savedCategorie.getId());
        assertEquals("TestCategory", savedCategorie.getNom());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testCategorieValidation() {
        // Given
        Categorie categorie = new Categorie();
        categorie.setNom("TestCategory");

        // Mocking behavior for the save method
        when(categorieRepository.save(categorie)).thenThrow(ConstraintViolationException.class);

        // When and Then
        categorieService.create(categorie);
    }
}
