package ma.emsi.blog.auth;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Before;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import jakarta.servlet.http.HttpServletResponse;

public class AuthEntryPointJwtTest {

	private AuthEntryPointJwt authEntryPointJwt;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Before
	public void setUp() {
		authEntryPointJwt = new AuthEntryPointJwt();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void testCommence() throws Exception {
		AuthenticationException authenticationException = new AuthenticationException("Unauthorized error") {
			private static final long serialVersionUID = 1L;
		};

		authEntryPointJwt.commence(request, response, authenticationException);

		assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
		assertEquals("Error: Unauthorized", response.getErrorMessage());
	}
}
