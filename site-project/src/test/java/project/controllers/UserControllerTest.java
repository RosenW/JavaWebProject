package project.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.models.RegisterUserModel;
import project.services.interfaces.MemeService;
import project.services.interfaces.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private MemeService memeService;

    @Test
    public void registerUserShouldPass() throws Exception {
        mockMvc
                .perform(post("/register")
                        .param("email", "ivan@abv.bg")
                        .param("password", "1234")
                        .param("confirmPassword", "1234")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(redirectedUrl("/login"))
                .andExpect(model().hasNoErrors());

        ArgumentCaptor<RegisterUserModel> captor = ArgumentCaptor.forClass(RegisterUserModel.class);
        verify(userService).register(captor.capture());
        RegisterUserModel registerUserModel = captor.getValue();
        assertEquals("ivan@abv.bg", registerUserModel.getEmail());
    }

    @Test(expected = Exception.class)
    public void registerUserWithDifferentPasswordsShouldThrow() throws Exception {
        mockMvc
                .perform(post("/register")
                        .param("email", "ivan@abv.bg")
                        .param("password", "1234")
                        .param("confirmPassword", "5678")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(redirectedUrl("/login"))
                .andExpect(model().hasNoErrors());

        ArgumentCaptor<RegisterUserModel> captor = ArgumentCaptor.forClass(RegisterUserModel.class);
        verify(userService).register(captor.capture());
        RegisterUserModel registerUserModel = captor.getValue();
        assertEquals("ivan@abv.bg", registerUserModel.getEmail());
    }

    @Test(expected = Exception.class)
    public void registerUserWithShortPasswordsShouldThrow() throws Exception {
        mockMvc
                .perform(post("/register")
                        .param("email", "ivan@abv.bg")
                        .param("password", "1")
                        .param("confirmPassword", "1")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(redirectedUrl("/login"))
                .andExpect(model().hasNoErrors());

        ArgumentCaptor<RegisterUserModel> captor = ArgumentCaptor.forClass(RegisterUserModel.class);
        verify(userService).register(captor.capture());
        RegisterUserModel registerUserModel = captor.getValue();
        assertEquals("ivan@abv.bg", registerUserModel.getEmail());
    }
}