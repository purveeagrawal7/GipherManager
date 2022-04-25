package com.stackroute.accountmanager.service;
import com.stackroute.accountmanager.model.User;
import com.stackroute.accountmanager.repository.UserAuthRepository;
import com.stackroute.accountmanager.util.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.util.exception.UserNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
public class UserAuthServiceTest {
	 @Mock
	    private UserAuthRepository userAuthRepository;
	 private User user;
	    @InjectMocks
	    private UserAuthServiceImpl userServiceImpl;
	   
	    Optional<User> optional;
	   
	    @BeforeEach
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	        user = new User();
	        user.setUserId("u103");
	        user.setPassword("new123");
	        user.setCpassword("new123");
	        optional = Optional.of(user);
	    }
	   
	    @Test
	    public void testSaveUserSuccess() throws UserAlreadyExistsException {
	        Mockito.when(userAuthRepository.save(user)).thenReturn(user);
	        boolean flag = userServiceImpl.saveUser(user);
	        assertEquals(true, flag);
	    }
	   
	    @Test
	    public void testSaveUserFailure() {
	        Mockito.when(userAuthRepository.findById("u103")).thenReturn(optional);
	        Mockito.when(userAuthRepository.save(user)).thenReturn(user);
	        assertThrows(
	        		UserAlreadyExistsException.class,
	                    () -> { userServiceImpl.saveUser(user); });
	    }
	   
	    @Test
	    public void testFindByUserIdAndPassword() throws UserNotFoundException {
	        Mockito.when(userAuthRepository.findByUserIdAndPassword("u103", "new123")).thenReturn(user);
	        User fetchedUser = userServiceImpl.findByUserIdAndPassword("u103", "new123");
	        assertEquals("u103", fetchedUser.getUserId());
	    }
}