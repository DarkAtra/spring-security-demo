package de.darkatra.springsecuritydemo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = "SCOPE_SECURITY_DEMO:GET_HELLO")
    void shouldBeAuthorized() throws Exception {

        mockMvc.perform(get("/hello"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    void shouldNotBeAuthorizedDueToMissingAuthentication() throws Exception {

        mockMvc.perform(get("/hello"))
            .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @WithMockUser(authorities = "SCOPE_SECURITY_DEMO:SOMETHING_ELSE")
    void shouldNotBeAuthorizedDueToMissingScopes() throws Exception {

        mockMvc.perform(get("/hello"))
            .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }
}
