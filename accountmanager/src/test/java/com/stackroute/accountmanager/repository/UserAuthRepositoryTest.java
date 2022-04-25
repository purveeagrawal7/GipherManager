package com.stackroute.accountmanager.repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.stackroute.accountmanager.model.User;
import com.stackroute.accountmanager.repository.UserAuthRepository;
import org.mockito.MockitoAnnotations;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserAuthRepositoryTest {
	@Autowired
    private UserAuthRepository userRepository;
    private User user;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUserId("u103");
        user.setPassword("new123");
        user.setCpassword("new123");
    }
    @AfterEach
    public void tearDown() throws Exception {
    	userRepository.deleteAll();
    }
    @Test
    public void testRegisterUserSuccess() {
    	userRepository.save(user);
        User fetchUser = userRepository.findById(user.getUserId()).get();
        assertThat(user.getUserId(), is(fetchUser.getUserId()));
    }
    @Test
    public void testLoginUserSuccess() {
    	userRepository.save(user);
        User fetchUser = userRepository.findById(user.getUserId()).get();
        assertThat(user.getUserId(), is(fetchUser.getUserId()));
    }
}