package phenrique.com.A3.Medlink.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MedicoController.class)
public class MedicoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetMedicoUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/medico"))
                .andExpect(status().isUnauthorized());
    }
} 