import com.xzh.sso.Application;
import com.xzh.sso.domain.User;
import com.xzh.sso.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = User.builder().username("admin").password("123456").deleteFlag(1).build();
        User save = userRepository.save(user);
        List<User> allUsers = userRepository.findAll();
        System.out.println(allUsers);
    }

    @Test
    public void testFindUser() {
        User xzh = userRepository.findByUsername("xzh");
        System.out.println(xzh);
    }
}