package ma.emsi.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.emsi.blog.exception.DuplicateUserException;
import ma.emsi.blog.model.Proprietaire;
import ma.emsi.blog.repository.ProprietaireRepository;
import ma.emsi.blog.repository.UtilisateurRepository;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProprietaireServiceImplTest {

    @Mock
    private ProprietaireRepository proprietaireRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ProprietaireServiceImpl proprietaireService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateWithNewUser() {
        Proprietaire newProprietaire = new Proprietaire(1, "test@example.com", "password123", "testUser", "photoUrl", "Bio");
        when(passwordEncoder.encode(newProprietaire.getPassword())).thenReturn("encodedPassword");

        proprietaireService.create(newProprietaire);
        verify(passwordEncoder).encode("password123");
        verify(proprietaireRepository).save(newProprietaire);
    }

    @Test
    public void testCreateWithDuplicateUser() {
        Proprietaire duplicateProprietaire = new Proprietaire(1, "test@example.com", "password123", "testUser", "photoUrl", "Bio");
        when(utilisateurRepository.existsByUsername(duplicateProprietaire.getUsername())).thenReturn(true);

        assertThrows(DuplicateUserException.class, () -> {
            proprietaireService.create(duplicateProprietaire);
        });
    }

    @Test
    public void testUpdateUser() {
        Proprietaire existingProprietaire = new Proprietaire(1, "original@example.com", "password123", "originalUser", "originalPhoto", "originalBio");
        when(proprietaireRepository.findById(1)).thenReturn(existingProprietaire);
        when(utilisateurRepository.existsByUsername(anyString())).thenReturn(false);
        when(utilisateurRepository.existsByEmail(anyString())).thenReturn(false);

        Proprietaire updatedProprietaire = new Proprietaire(1, "updated@example.com", "password123", "updatedUser", "updatedPhoto", "updatedBio");
        proprietaireService.update(updatedProprietaire);

        verify(proprietaireRepository).save(updatedProprietaire);
    }

    @Test
    public void testDeleteUser() {
        int userId = 1;
        proprietaireService.delete(userId);
        verify(proprietaireRepository).deleteById(userId);
    }

    @Test
    public void testFindAll() {
        List<Proprietaire> expectedList = new ArrayList<>();
        when(proprietaireRepository.findAll()).thenReturn(expectedList);

        List<Proprietaire> result = proprietaireService.findAll();
        assertEquals(expectedList, result);
    }

    @Test
    public void testFindById() {
        int id = 1;
        Proprietaire expectedProprietaire = new Proprietaire();
        when(proprietaireRepository.findById(id)).thenReturn(expectedProprietaire);

        Proprietaire result = proprietaireService.findById(id);
        assertEquals(expectedProprietaire, result);
    }

    @Test
    public void testExistsById() {
        int id = 1;
        when(proprietaireRepository.findById(id)).thenReturn(new Proprietaire());

        assertTrue(proprietaireService.existsById(id));
    }
}