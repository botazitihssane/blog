package ma.emsi.blog.controller;

import ma.emsi.blog.model.Categorie;
import ma.emsi.blog.service.CategorieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CategorieControllerTest {

    private static final String JSON_PATH_NOM = "$.nom";

    private MockMvc mockMvc;

    @Mock
    private CategorieService categorieService;

    @InjectMocks
    private CategorieController categorieController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(categorieController).build();
    }

    @Test
    @WithMockUser
    public void testAddCategorie() throws Exception {
        Categorie newCategorie = new Categorie(1, "Tech");
        when(categorieService.create(any(Categorie.class))).thenReturn(newCategorie);

        mockMvc.perform(post("/blog/categorie").contentType(MediaType.APPLICATION_JSON).content("{\"nom\":\"Tech\"}"))
                .andExpect(status().isCreated()).andExpect(jsonPath(JSON_PATH_NOM).value("Tech"));
    }

    @Test
    public void testGetAllCategories() throws Exception {
        List<Categorie> categories = Arrays.asList(new Categorie(1, "Tech"));
        when(categorieService.findAll()).thenReturn(categories);

        mockMvc.perform(get("/blog/categories")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Tech"));
    }

    @Test
    public void testGetCategorieById() throws Exception {
        Categorie categorie = new Categorie(1, "Tech");
        when(categorieService.findById(1)).thenReturn(Optional.of(categorie));

        mockMvc.perform(get("/blog/categorie/id/1")).andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_NOM).value("Tech"));
    }

    @Test
    public void testGetCategorieByNom() throws Exception {
        Categorie categorie = new Categorie(1, "Tech");
        when(categorieService.findByNom("Tech")).thenReturn(Optional.of(categorie));

        mockMvc.perform(get("/blog/categorie/nom/Tech")).andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_NOM).value("Tech"));
    }

    @Test
    @WithMockUser
    public void testUpdateCategorie() throws Exception {
        Categorie updatedCategorie = new Categorie(1, "Updated Tech");
        when(categorieService.update(any(Categorie.class))).thenReturn(updatedCategorie);

        mockMvc.perform(put("/blog/categorie").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1, \"nom\":\"Updated Tech\"}")).andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_NOM).value("Updated Tech"));
    }

    @Test
    @WithMockUser
    public void testDeleteCategorie() throws Exception {
        doNothing().when(categorieService).delete(1);

        mockMvc.perform(delete("/blog/categorie/1")).andExpect(status().isNoContent());
    }
}
