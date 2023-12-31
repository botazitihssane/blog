package ma.emsi.blog.auth;

import jakarta.servlet.http.Cookie;
import ma.emsi.blog.service.impl.UserDetailsImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseCookie;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;

public class JwtUtilsTest {

    @InjectMocks
    private JwtUtils jwtUtils;

    @Mock
    private MockHttpServletRequest request;

    private String jwtSecret = "======================blogPersonnel=secretKey==========================="; // key
    private int jwtExpirationMs = 86400000;
    private String jwtCookieName = "blogPersonnel";

    private UserDetailsImpl userDetails;

    @Before
    public void setUp() {
        jwtUtils = new JwtUtils();
        ReflectionTestUtils.setField(jwtUtils, "jwtSecret", jwtSecret);
        ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", jwtExpirationMs);
        ReflectionTestUtils.setField(jwtUtils, "jwtCookie", jwtCookieName);

        userDetails = new UserDetailsImpl(1, "user", "user@example.com", "password");
        request = new MockHttpServletRequest();
    }

    @Test
    public void testGenerateJwtCookie() {
        ResponseCookie cookie = jwtUtils.generateJwtCookie(userDetails);
        assertNotNull(cookie);
        assertEquals(jwtCookieName, cookie.getName());

        assertEquals("/api", cookie.getPath());
        assertTrue(cookie.isHttpOnly());

        long maxAgeInSeconds = cookie.getMaxAge().getSeconds();
        assertEquals(24 * 60 * 60, maxAgeInSeconds);

        String jwtToken = cookie.getValue();
        assertNotNull(jwtToken);
        assertFalse(jwtToken.isEmpty());
        assertTrue(jwtUtils.validateJwtToken(jwtToken));

        String usernameFromToken = jwtUtils.getUserNameFromJwtToken(jwtToken);
        assertEquals(userDetails.getUsername(), usernameFromToken);
    }

    @Test
    public void testGetCleanJwtCookie() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        assertNotNull(cookie);
        assertEquals(jwtCookieName, cookie.getName());
        assertEquals("", cookie.getValue());
    }

    @Test
    public void testGetJwtFromCookies() {
        Cookie[] cookies = new Cookie[]{new Cookie(jwtCookieName, "dummyToken")};
        request.setCookies(cookies);

        String token = jwtUtils.getJwtFromCookies(request);
        assertEquals("dummyToken", token);
    }

    @Test
    public void testGenerateTokenFromUsername() {
        String token = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    public void testValidateJwtTokenValidToken() {
        String token = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
        assertTrue(jwtUtils.validateJwtToken(token));
    }

    @Test
    public void testValidateJwtTokenInvalidToken() {
        String invalidToken = "invalidToken";
        assertFalse(jwtUtils.validateJwtToken(invalidToken));
    }

    @Test
    public void testGetUserNameFromJwtToken() {
        String token = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
        String username = jwtUtils.getUserNameFromJwtToken(token);
        assertEquals(userDetails.getUsername(), username);
    }
}
