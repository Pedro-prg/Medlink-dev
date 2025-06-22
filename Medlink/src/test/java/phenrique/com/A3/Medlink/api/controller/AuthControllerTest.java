package phenrique.com.A3.Medlink.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginEndpointUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"fake\",\"password\":\"fake\"}"))
                .andExpect(status().isUnauthorized());
    }
} 