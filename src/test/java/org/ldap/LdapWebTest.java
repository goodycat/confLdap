package org.ldap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

/**
 * Тестирование веб-сервиса на предмет аутентификации.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class LdapWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginWithValidUserThenAuthenticatedJohn() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin().user("john").password("secret");
        mockMvc.perform(login).andExpect(authenticated().withUsername("john"));
    }

    @Test
    public void loginWithValidUserThenAuthenticatedJihn() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin().user("jihn").password("secret");
        mockMvc.perform(login).andExpect(authenticated().withUsername("jihn"));
    }

    @Test
    public void loginWithInvalidUserThenUnauthenticated() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin().user("invalid").password("invalidpassword");
        mockMvc.perform(login).andExpect(unauthenticated());
    }
}