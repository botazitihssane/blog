package ma.emsi.blog.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ProprietaireTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testProprietaireValidation() {
        // Given
        Proprietaire proprietaire = new Proprietaire();
        proprietaire.setEmail("");
        proprietaire.setPassword("pass");
        proprietaire.setUsername("");

        // When
        Set<ConstraintViolation<Proprietaire>> violations = validator.validate(proprietaire);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(3, violations.size());

        for (ConstraintViolation<Proprietaire> violation : violations) {
            System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
        }
    }


    @Test
    public void testProprietaireConstructor() {
        Proprietaire proprietaire = new Proprietaire(1, "test@example.com", "password123",
                "username", "photo.jpg", "Biography");

        assertEquals(1, proprietaire.getId());
        assertEquals("test@example.com", proprietaire.getEmail());
        assertEquals("password123", proprietaire.getPassword());
        assertEquals("username", proprietaire.getUsername());
        assertEquals("photo.jpg", proprietaire.getPhoto());
        assertEquals("Biography", proprietaire.getBiographie());
    }
}
