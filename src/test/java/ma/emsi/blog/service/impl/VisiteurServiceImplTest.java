package ma.emsi.blog.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ma.emsi.blog.model.Visiteur;
import ma.emsi.blog.repository.VisiteurRepository;

public class VisiteurServiceImplTest {

    @Mock
    private VisiteurRepository visiteurRepository;

    @InjectMocks
    private VisiteurServiceImpl visiteurService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        Visiteur visiteur = new Visiteur(); // Assuming Visiteur is a properly defined class
        visiteurService.create(visiteur);
        verify(visiteurRepository, times(1)).save(visiteur);
    }

    @Test
    public void testFindById() {
        int id = 1;
        Visiteur expectedVisiteur = new Visiteur();
        when(visiteurRepository.findById(id)).thenReturn(expectedVisiteur);

        Visiteur result = visiteurService.findById(id);
        assertEquals(expectedVisiteur, result);
    }

    @Test
    public void testFindAll() {
        List<Visiteur> expectedList = new ArrayList<>();
        when(visiteurRepository.findAll()).thenReturn(expectedList);

        List<Visiteur> result = visiteurService.findAll();
        assertEquals(expectedList, result);
    }

    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        Visiteur expectedVisiteur = new Visiteur();
        when(visiteurRepository.findByEmail(email)).thenReturn(expectedVisiteur);

        Visiteur result = visiteurService.findByEmail(email);
        assertEquals(expectedVisiteur, result);
    }

    @Test
    public void testFindByNom() {
        String nom = "Doe";
        Visiteur expectedVisiteur = new Visiteur();
        when(visiteurRepository.findByNom(nom)).thenReturn(expectedVisiteur);

        Visiteur result = visiteurService.findByNom(nom);
        assertEquals(expectedVisiteur, result);
    }
}