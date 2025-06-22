package phenrique.com.A3.Medlink.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DisponibilidadeMedicoController.class)
public class DisponibilidadeMedicoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetDisponibilidadeUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/disponibilidade-medico"))
                .andExpect(status().isUnauthorized());
    }
} 