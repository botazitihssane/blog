package ma.emsi.blog.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;

import java.util.Optional;

import ma.emsi.blog.model.Utilisateur;
import ma.emsi.blog.repository.UtilisateurRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImplTest {

    @Mock
    private UtilisateurRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserExists() {
        Utilisateur mockUser = new Utilisateur(1, "test@example.com", "password123", "testUser");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));

        UserDetails loadedUser = userDetailsService.loadUserByUsername("testUser");

        assertEquals(mockUser.getUsername(), loadedUser.getUsername());
        // Further assertions can be added here to check other user properties
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("unknownUser");
        });
    }
}