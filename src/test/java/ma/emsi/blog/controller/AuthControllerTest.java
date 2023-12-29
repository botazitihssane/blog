package ma.emsi.blog.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ma.emsi.blog.auth.JwtUtils;
import ma.emsi.blog.repository.UtilisateurRepository;
import ma.emsi.blog.request.LoginRequest;
import ma.emsi.blog.request.SignupRequest;
import ma.emsi.blog.response.UserInfoResponse;
import ma.emsi.blog.service.impl.UserDetailsImpl;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

	private MockMvc mockMvc;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private UtilisateurRepository userRepository;

	@Mock
	private JwtUtils jwtUtils;

	@InjectMocks
	private AuthController authController;

	@Mock
	private PasswordEncoder encoder;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
	}

	@Test
	public void testAuthenticateUser() throws Exception {
		Authentication authentication = mock(Authentication.class);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = new UserDetailsImpl(1, "testUser", "test@example.com", "password");
		when(authentication.getPrincipal()).thenReturn(userDetails);
		when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
				.thenReturn(authentication);

		// Mocking JwtUtils
		when(jwtUtils.generateJwtCookie(any(UserDetailsImpl.class)))
				.thenReturn(ResponseCookie.from("jwt", "mocked-jwt").httpOnly(true).secure(false).maxAge(3600).build());

		// Perform the request
		LoginRequest loginRequest = new LoginRequest("testUser", "password");
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/blog/auth/signin").contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\":\"testUser\",\"password\":\"password\"}"))
				.andReturn();

		// Verify the response
		MockHttpServletResponse response = result.getResponse();
		String cookieHeader = response.getHeader(HttpHeaders.SET_COOKIE);
		UserInfoResponse expectedResponse = new UserInfoResponse(1, "testUser", "test@example.com");

		assertEquals(200, response.getStatus());
		assertEquals("{\"id\":1,\"username\":\"testUser\",\"email\":\"test@example.com\"}",
				response.getContentAsString());
		assertTrue(cookieHeader.contains("jwt=mocked-jwt"));

		assertEquals(expectedResponse.getId(), userDetails.getId());
		assertEquals(expectedResponse.getUsername(), userDetails.getUsername());
		assertEquals(expectedResponse.getEmail(), userDetails.getEmail());

		assertEquals("testUser", loginRequest.getUsername());
		assertEquals("password", loginRequest.getPassword());
	}

	@Test
	public void testRegisterUser() throws Exception {
		when(userRepository.existsByUsername("testUser")).thenReturn(false);
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);

        // Perform the request
        SignupRequest signupRequest = new SignupRequest("test@example.com", "password", "testUser");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/blog/auth/signup")
                .contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"test@example.com\",\"password\":\"password\",\"username\":\"testUser\"}"))
                .andReturn();

        // Verify the response
        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
        assertEquals(200, response.getStatus());

        assertEquals("{\"message\":\"User registered successfully!\"}", content);
        assertEquals("test@example.com", signupRequest.getEmail());
        assertEquals("password", signupRequest.getPassword());
        assertEquals("testUser", signupRequest.getUsername());
	}

	@Test
	    public void testLogoutUser() throws Exception {
	        when(userRepository.existsByUsername("testUser")).thenReturn(true);

	        // Perform the request and expect a 400 status (Bad Request) due to the exception
	        mockMvc.perform(post("/blog/auth/signup")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"email\":\"test@example.com\",\"password\":\"password\",\"username\":\"testUser\"}"))
	                .andExpect(status().isBadRequest());
	    }

	@Test
	public void testRegisterUser_UsernameAlreadyTaken() throws Exception {
	    when(userRepository.existsByUsername("existingUser")).thenReturn(true);

	    MvcResult result = mockMvc.perform(post("/blog/auth/signup")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content("{\"email\":\"test@example.com\",\"password\":\"password\",\"username\":\"existingUser\"}"))
	            .andReturn();

	    MockHttpServletResponse response = result.getResponse();
	    assertEquals(400, response.getStatus());
	    assertTrue(response.getContentAsString().contains("Error: Username is already taken!"));
}

	@Test
	public void testRegisterUser_EmailAlreadyInUse() throws Exception {
	    when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

	    SignupRequest signupRequest = new SignupRequest("existing@example.com", "password", "newUser");
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/blog/auth/signup")
	            .contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"existing@example.com\",\"password\":\"password\",\"username\":\"newUser\"}"))
	            .andReturn();

	    MockHttpServletResponse response = result.getResponse();
	    String content = response.getContentAsString();

	    assertTrue(content.contains("Error: Email is already in use!"));
	}

	@Test
	public void testRegisterUser_Success() throws Exception {
	    // Mocking the case where both username and email are available
	    when(userRepository.existsByUsername("newUser")).thenReturn(false);
	    when(userRepository.existsByEmail("new@example.com")).thenReturn(false);

	    // Perform the request
	    SignupRequest signupRequest = new SignupRequest("new@example.com", "password", "newUser");
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/blog/auth/signup")
	            .contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"new@example.com\",\"password\":\"password\",\"username\":\"newUser\"}"))
	            .andReturn();

	    // Verify the response
	    MockHttpServletResponse response = result.getResponse();
	    String content = response.getContentAsString();
	    assertEquals(200, response.getStatus());
	    assertEquals("{\"message\":\"User registered successfully!\"}", content);
	}
}
