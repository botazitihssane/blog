package ma.emsi.blog.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CommentaireTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCommentaireValidation() {
        // Given
        Commentaire commentaire = new Commentaire();
        commentaire.setTexte("");
        commentaire.setDate(LocalDate.now());
        commentaire.setArticle(null);
        commentaire.setVisiteur(null);
        commentaire.setProprietaire(null);
        commentaire.setCommentaireParent(null);

        // When
        Set<ConstraintViolation<Commentaire>> violations = validator.validate(commentaire);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());

        for (ConstraintViolation<Commentaire> violation : violations) {
            System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
        }
    }
}
