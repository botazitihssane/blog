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

public class ArticleTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testArticleValidation() {
        // Given
        Article article = Article.builder()
                .titre("Titre")
                .texte("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio.")
                .date(LocalDate.now())
                .nombreCommentaire(-1)
                .categorie(null)
                .user(null)
                .build();

        // When
        Set<ConstraintViolation<Article>> violations = validator.validate(article);

        // Then
        assertFalse(violations.isEmpty());

        int expectedViolationCount = 3;
        assertEquals("Unexpected number of violations. Actual violations: " + violations, expectedViolationCount, violations.size());

        // Debugging: Print details of each violation
        for (ConstraintViolation<Article> violation : violations) {
            System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
        }
    }

}
