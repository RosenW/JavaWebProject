package project.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.models.MemeModel;
import project.services.interfaces.MemeService;
import project.services.interfaces.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(MemeController.class)
@ActiveProfiles("test")
public class MemeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemeService memeService;

    @MockBean
    private UserService userService;

    @MockBean
    private MemeController memeController;

    @Before
    public void setUp() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void postAddMemeTestShouldPass() throws Exception {
        this.mockMvc
                .perform(post("/meme/add")
                        .param("Title", "Test Title")
                        .param("meme", "asdasd.img")
                        .param("type", "funny"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/"));

        ArgumentCaptor<MemeModel> captor = ArgumentCaptor.forClass(MemeModel.class);
        verify(memeService).addMeme(captor.capture());
        MemeModel memeModel = captor.getValue();
        assertEquals("Test Title", memeModel.getTitle());
        assertEquals("asdasd.img", memeModel.getMeme());
        assertEquals("funny", memeModel.getType());
    }

    @Test
    public void getAddMemeShouldReturnAddMemePage() throws Exception {
        this.mockMvc
                .perform(get("/meme/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-meme"));
    }
}
