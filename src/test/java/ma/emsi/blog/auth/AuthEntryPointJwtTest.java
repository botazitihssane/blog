package ma.emsi.blog.auth;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import jakarta.servlet.http.HttpServletResponse;

public class AuthEntryPointJwtTest {

	@Mock
	private AuthenticationException authenticationException;

	private AuthEntryPointJwt authEntryPointJwt;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		authEntryPointJwt = new AuthEntryPointJwt();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	    public void testCommence() throws Exception {
	        when(authenticationException.getMessage()).thenReturn("Unauthorized error");

	        authEntryPointJwt.commence(request, response, authenticationException);

	        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
	        assertEquals("Error: Unauthorized", response.getErrorMessage());
	    }
}
