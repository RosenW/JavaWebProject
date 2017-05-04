package project.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import project.entities.Meme;
import project.entities.User;
import project.repository.MemeRepository;
import java.util.List;

import static org.junit.Assert.assertEquals;;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class MemeRepositoryTest {

    public static final int EXPECTED_LIST_SIZE = 2;

    public static final int EXPECTED_LIST_SIZE_ZERO = 0;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MemeRepository memeRepository;

    private User testUser;
    private User imposterUser;

    @Before
    public void setUp() throws Exception {
        testUser = new User();
        imposterUser = new User();

        this.testEntityManager.persist(testUser);
        this.testEntityManager.persist(imposterUser);
    }

    @Test
    public void findAllMemesByUserShouldPass(){
        //Arrange
        Meme firstMeme = new Meme();
        Meme secondMeme = new Meme();
        Meme thirdMeme = new Meme();

        firstMeme.setUser(testUser);
        secondMeme.setUser(testUser);
        thirdMeme.setUser(imposterUser);

        this.testEntityManager.persist(firstMeme);
        this.testEntityManager.persist(secondMeme);
        this.testEntityManager.persist(thirdMeme);

        List<Meme> memes = memeRepository.findAllByUser(testUser);
        assertEquals(EXPECTED_LIST_SIZE, memes.size());
    }

    @Test
    public void findAllMemesByUserWithoutAnyMemesShouldReturnZero(){
        List<Meme> memes = memeRepository.findAllByUser(testUser);
        assertEquals(EXPECTED_LIST_SIZE_ZERO, memes.size());
    }
}
