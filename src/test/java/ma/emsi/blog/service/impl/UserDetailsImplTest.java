package ma.emsi.blog.service.impl;


import java.util.Collection;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ma.emsi.blog.model.Utilisateur;

import static org.junit.Assert.*;

public class UserDetailsImplTest {

    @Test
    public void testUserDetailsImplConstructorAndGetters() {
        UserDetailsImpl userDetails = new UserDetailsImpl(1, "testUser", "test@example.com", "password123");

        assertEquals(1, userDetails.getId());
        assertEquals("testUser", userDetails.getUsername());
        assertEquals("test@example.com", userDetails.getEmail());
        assertEquals("password123", userDetails.getPassword());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
    }

    @Test
    public void testBuildMethod() {
        Utilisateur user = new Utilisateur(1, "test@example.com", "password123", "testUser");
        UserDetails userDetails = UserDetailsImpl.build(user);

        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getEmail(), ((UserDetailsImpl) userDetails).getEmail());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserDetailsImpl userDetails1 = new UserDetailsImpl(1, "testUser", "test@example.com", "password123");
        UserDetailsImpl userDetails2 = new UserDetailsImpl(1, "testUser", "test@example.com", "password123");

        assertEquals(userDetails1, userDetails2);
        assertEquals(userDetails1.hashCode(), userDetails2.hashCode());
    }

    @Test
    public void testGetAuthorities() {
        UserDetailsImpl userDetails = new UserDetailsImpl(1, "testUser", "test@example.com", "password123");
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        // Since getAuthorities is not implemented, it should return null
        assertNull(authorities);
    }
}