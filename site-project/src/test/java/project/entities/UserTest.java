package project.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserTest {

    public static final String EXPECTED_ROLE_NAME = "ROLE_USER";


    public static final int EXPECTED_LIST_SIZE = 1;

    private User user;

    @Mock
    private Role role;

    @Before
    public void setUp() throws Exception {
        //Arrange
        this.user = new User();
        when(this.role.getAuthority()).thenReturn(EXPECTED_ROLE_NAME);

    }

    @Test
    public void addRoleWhenUserRoleGivenShouldReturnCorrectRoleName() throws Exception {
        //Act
        this.user.addRole(this.role);
        //Assert
        String actualRoleName = this.user
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority();
        assertEquals(EXPECTED_ROLE_NAME, actualRoleName);
    }

    @Test
    public void addToLikedTestShouldReturnCorrectListSize() throws Exception {
        //Act
        this.user.addToLiked(new Meme());
        //Assert
        int likedMemesSize = this.user.getLikedMemes().size();
        assertEquals(EXPECTED_LIST_SIZE, likedMemesSize);
    }

    @Test
    public void addToDislikedTestShouldReturnCorrectListSize() throws Exception {
        //Act
        this.user.addToDisliked(new Meme());
        //Assert
        int dislikedMemesSize = this.user.getDislikedMemes().size();
        assertEquals(EXPECTED_LIST_SIZE, dislikedMemesSize);
    }
}
