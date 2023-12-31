package ma.emsi.blog.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebSecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void findAllProprietairesShouldReturnOk() throws Exception {
        mockMvc.perform(get("/blog/proprietaires")).andExpect(status().isOk());
    }

    @Test
    public void accessUnprotectedGetEndpointShouldBeOk() throws Exception {
        mockMvc.perform(get("/blog/articles").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void accessProtectedPostEndpointWithoutAuthShouldBeUnauthorized() throws Exception {
        mockMvc.perform(post("/blog/article").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Article\", \"content\":\"This is a test article\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void accessProtectedPostEndpointWithAuthShouldAddCategorie() throws Exception {
        String jsonRequestBody = """
                {
                    "nom": "Technology"
                }
                """;

        mockMvc.perform(post("/blog/categorie").contentType(MediaType.APPLICATION_JSON).content(jsonRequestBody))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    public void accessProtectedEndpointWithAuthShouldBeOk() throws Exception {
        mockMvc.perform(get("/blog/proprietaires").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
