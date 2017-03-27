import com.spring.yu.app;
import com.spring.yu.entity.User;
import com.spring.yu.entity.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yunan on 2017/3/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = app.class)
public class UserRepositoryTest
{
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception{
        Date date = new Date();

        userRepository.deleteAll();
        userRepository.save(new User("yunan0","1234",1,date,date));
        userRepository.save(new User("yunan1","1234",1,date,date));
        userRepository.save(new User("yunan2","1234",1,date,date));

        User user = userRepository.findById(10L);
        Assert.assertEquals((Long) 10L,user.getId());
    }


}
