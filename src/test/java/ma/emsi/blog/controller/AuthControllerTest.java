package ma.emsi.blog.controller;

import ma.emsi.blog.auth.JwtUtils;
import ma.emsi.blog.repository.UtilisateurRepository;
import ma.emsi.blog.request.LoginRequest;
import ma.emsi.blog.request.SignupRequest;
import ma.emsi.blog.response.UserInfoResponse;
import ma.emsi.blog.service.impl.UserDetailsImpl;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    private static final String SIGNUP_URL = "/blog/auth/signup";
    private static final String TEST_USERNAME = "testUser";
    private static final String TEST_PASSWORD = "password";
    private static final String TEST_EMAIL = "test@example.com";
    private static final String EMAIL_PASSWORD_SEPARATOR = "\",\"password\":\"";
    private static final String JSON_EMAIL_PREFIX = "{\"email\":\"";

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
        UserDetailsImpl userDetails = new UserDetailsImpl(1, TEST_USERNAME, TEST_EMAIL, TEST_PASSWORD);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(jwtUtils.generateJwtCookie(any(UserDetailsImpl.class)))
                .thenReturn(ResponseCookie.from("jwt", "mocked-jwt").httpOnly(true).secure(false).maxAge(3600).build());
        LoginRequest loginRequest = new LoginRequest(TEST_USERNAME, TEST_PASSWORD);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/blog/auth/signin").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testUser\",\"password\":\"password\"}"))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String cookieHeader = response.getHeader(HttpHeaders.SET_COOKIE);
        UserInfoResponse expectedResponse = new UserInfoResponse(1, TEST_USERNAME, TEST_EMAIL);

        assertEquals(200, response.getStatus());
        assertEquals("{\"id\":1,\"username\":\"testUser\",\"email\":\"test@example.com\"}",
                response.getContentAsString());
        assertTrue(cookieHeader.contains("jwt=mocked-jwt"));

        assertEquals(expectedResponse.getId(), userDetails.getId());
        assertEquals(expectedResponse.getUsername(), userDetails.getUsername());
        assertEquals(expectedResponse.getEmail(), userDetails.getEmail());

        assertEquals(TEST_USERNAME, loginRequest.getUsername());
        assertEquals(TEST_PASSWORD, loginRequest.getPassword());
    }

    @Test
    public void testRegisterUser() throws Exception {
        when(userRepository.existsByUsername(TEST_USERNAME)).thenReturn(false);
        when(userRepository.existsByEmail(TEST_EMAIL)).thenReturn(false);

        SignupRequest signupRequest = new SignupRequest(TEST_EMAIL, TEST_PASSWORD, TEST_USERNAME);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(SIGNUP_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(JSON_EMAIL_PREFIX + TEST_EMAIL + EMAIL_PASSWORD_SEPARATOR + TEST_PASSWORD + "\",\"username\":\"" + TEST_USERNAME + "\"}"))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
        assertEquals(200, response.getStatus());

        assertEquals("{\"message\":\"User registered successfully!\"}", content);
        assertEquals(TEST_EMAIL, signupRequest.getEmail());
        assertEquals(TEST_PASSWORD, signupRequest.getPassword());
        assertEquals(TEST_USERNAME, signupRequest.getUsername());
    }

    @Test
    public void testLogoutUser() throws Exception {
        when(userRepository.existsByUsername(TEST_USERNAME)).thenReturn(true);

        mockMvc.perform(post(SIGNUP_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_EMAIL_PREFIX + TEST_EMAIL + EMAIL_PASSWORD_SEPARATOR + TEST_PASSWORD + "\",\"username\":\"" + TEST_USERNAME + "\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterUserUsernameAlreadyTaken() throws Exception {
        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        MvcResult result = mockMvc.perform(post(SIGNUP_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_EMAIL_PREFIX + TEST_EMAIL + EMAIL_PASSWORD_SEPARATOR + TEST_PASSWORD + "\",\"username\":\"existingUser\"}"))
                .andReturn();


        MockHttpServletResponse response = result.getResponse();
        assertEquals(400, response.getStatus());
        assertTrue(response.getContentAsString().contains("Error: Username is already taken!"));
    }

    @Test
    public void testRegisterUserEmailAlreadyInUse() throws Exception {
        when(userRepository.existsByEmail(TEST_EMAIL)).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(SIGNUP_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(JSON_EMAIL_PREFIX + TEST_EMAIL + EMAIL_PASSWORD_SEPARATOR + TEST_PASSWORD + "\",\"username\":\"newUser\"}"))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();

        assertTrue(content.contains("Error: Email is already in use!"));
    }

    @Test
    public void testRegisterUserSuccess() throws Exception {
        when(userRepository.existsByUsername("newUser")).thenReturn(false);
        when(userRepository.existsByEmail("new@example.com")).thenReturn(false);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(SIGNUP_URL)
                        .contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"new@example.com\",\"password\":\"" + TEST_PASSWORD + "\",\"username\":\"newUser\"}"))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
        assertEquals(200, response.getStatus());
        assertEquals("{\"message\":\"User registered successfully!\"}", content);
    }
}
