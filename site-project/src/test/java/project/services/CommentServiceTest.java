package project.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import project.entities.User;
import project.models.CommentModel;
import project.repository.CommentRepository;
import project.services.interfaces.CommentService;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
public class CommentServiceTest {

    public static final int EXPECTED_COMMENT_COUNT = 1;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @MockBean
    private CommentService commentServiceMockBean;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setEmail("www@www.com");
        user.setPassword("1234");
        testEntityManager.persist(user);
    }

    @Test
    public void addCommentTestShouldAddACommentToAMeme(){
        CommentModel commentModel = new CommentModel();
        commentModel.setUserName("www@www.com");
        commentModel.setContent("Test comment");
        commentModel.setMemeId(1);
        commentService.addComment(commentModel);
        assertEquals(EXPECTED_COMMENT_COUNT, commentRepository.findAll().size());
    }
}
