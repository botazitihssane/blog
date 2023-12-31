package ma.emsi.blog.service.impl;

import ma.emsi.blog.model.Categorie;
import ma.emsi.blog.repository.CategorieRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class CategorieServiceImplTest {


    @Mock
    private CategorieRepository categorieRepository;

    @InjectMocks
    private CategorieServiceImpl categorieService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategorie() {
        // Given
        Categorie categorie = new Categorie(); // Replace with your specific Categorie object

        // Mock the behavior of categorieRepository.save
        when(categorieRepository.save(categorie)).thenReturn(categorie);

        // When
        Categorie result = categorieService.create(categorie);

        // Then
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    @Test
    public void testUpdateCategorie() {
        // Given
        Categorie categorie = new Categorie();
        categorie.setId(1);

        // Mock the behavior of categorieRepository.findById
        when(categorieRepository.findById(anyInt())).thenReturn(Optional.of(categorie));

        // When
        Categorie result = categorieService.update(categorie);

        // Then
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }

    @Test
    public void testDeleteCategorie() {
        // Given
        int categorieId = 1; // Replace with your specific Categorie ID

        // When
        categorieService.delete(categorieId);

        // Then
        // Add assertions or verifications based on your specific logic
    }

    @Test
    public void testFindAllCategories() {
        // Given
        List<Categorie> expectedCategories = new ArrayList<>(); // Replace with your expected list of categories

        // Mock the behavior of categorieRepository.findAll
        when(categorieRepository.findAll()).thenReturn(expectedCategories);

        // When
        List<Categorie> result = categorieService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(expectedCategories.size(), result.size());
        // Add more assertions based on your specific logic
    }

    @Test
    public void testFindCategoryById() {
        // Given
        int categoryId = 1; // Replace with your specific Categorie ID
        Categorie expectedCategory = new Categorie(); // Replace with the expected Categorie object

        // Mock the behavior of categorieRepository.findById
        when(categorieRepository.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

        // When
        Optional<Categorie> result = categorieService.findById(categoryId);

        // Then
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(expectedCategory, result.get());
    }

    @Test
    public void testFindCategoryByNom() {
        // Given
        String categoryName = "Example"; // Replace with your specific category name
        Categorie expectedCategory = new Categorie(); // Replace with the expected Categorie object

        // Mock the behavior of categorieRepository.findByNom
        when(categorieRepository.findByNom(categoryName)).thenReturn(Optional.of(expectedCategory));

        // When
        Optional<Categorie> result = categorieService.findByNom(categoryName);

        // Then
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(expectedCategory, result.get());
    }
}