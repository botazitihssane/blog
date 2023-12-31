package ma.emsi.blog.service.impl;

import ma.emsi.blog.model.Commentaire;
import ma.emsi.blog.model.Visiteur;
import ma.emsi.blog.repository.CommentaireRepository;
import ma.emsi.blog.repository.VisiteurRepository;
import ma.emsi.blog.service.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommentaireServiceImplTest {

    @Mock
    private CommentaireRepository commentaireRepository;

    @Mock
    private VisiteurRepository visiteurRepository;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private CommentaireServiceImpl commentaireService;

    @SuppressWarnings("deprecation")
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateNewVisitor() {
        // Mocking
        Visiteur visitor = new Visiteur();
        visitor.setEmail("test@example.com");
        visitor.setNom("Test User");

        Commentaire commentaire = new Commentaire();
        commentaire.setVisiteur(visitor);

        when(visiteurRepository.findByEmail(anyString())).thenReturn(null);

        // Method call
        commentaireService.create(commentaire);

        // Verification
        verify(visiteurRepository).save(any(Visiteur.class));
        verify(commentaireRepository).save(commentaire);
        verify(articleService).addCommentaire(commentaire);
    }

    @Test
    public void testCreateExistingVisitor() {
        // Mocking
        Visiteur existingVisitor = new Visiteur();
        existingVisitor.setEmail("existing@example.com");
        existingVisitor.setNom("Existing User");
        existingVisitor.setNombreCommentaire(5);

        Commentaire commentaire = new Commentaire();
        commentaire.setVisiteur(existingVisitor);

        when(visiteurRepository.findByEmail(anyString())).thenReturn(existingVisitor);

        // Method call
        commentaireService.create(commentaire);

        // Verification
        verify(visiteurRepository).save(existingVisitor);
        verify(commentaireRepository).save(commentaire);
        verify(articleService).addCommentaire(commentaire);
        assertEquals(6, existingVisitor.getNombreCommentaire());
    }

    // Additional tests can be added for edge cases and null checks

    @Test
    public void testUpdateExistingCommentaire() {
        // Mocking
        Commentaire existingCommentaire = new Commentaire();
        existingCommentaire.setId(1);
        existingCommentaire.setTexte("Original Text");


        Commentaire updatedCommentaire = new Commentaire();
        updatedCommentaire.setId(1);
        updatedCommentaire.setTexte("Updated Text");


        when(commentaireRepository.findById(1)).thenReturn(existingCommentaire);

        // Method call
        commentaireService.update(updatedCommentaire);

        // Verification
        verify(commentaireRepository).save(existingCommentaire);
        assertEquals("Updated Text", existingCommentaire.getTexte());
    }

    @Test
    public void testUpdateNonExistingCommentaire() {
        // Mocking
        Commentaire nonExistingCommentaire = new Commentaire();
        nonExistingCommentaire.setId(999);

        when(commentaireRepository.findById(999)).thenReturn(nonExistingCommentaire);

        // Method call
        commentaireService.update(nonExistingCommentaire);

    }

}