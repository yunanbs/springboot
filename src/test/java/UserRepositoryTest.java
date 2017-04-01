import com.spring.yu.app;
import com.spring.yu.comm.JwtUtils;
import com.spring.yu.entity.SubUserInfo;
import com.spring.yu.entity.User;
import com.spring.yu.entity.UserRepository;
import io.jsonwebtoken.Claims;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yunan on 2017/3/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = app.class)
public class UserRepositoryTest
{
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception
    {
        Date date = new Date();

        //userRepository.deleteAll();
        userRepository.save(new User("yunan0", "1234", 1, date, date));
        userRepository.save(new User("yunan1", "1234", 1, date, date));
        userRepository.save(new User("yunan2", "1234", 1, date, date));

        User user = userRepository.findById(17L);
        Assert.assertEquals((Long) 17L, user.getId());
    }

    @Test
    public void findByUsernameTest()
    {
        long st = System.currentTimeMillis();
        List<SubUserInfo> subUserInfos = userRepository.findByUsername("%yunan%");
        for (Iterator iterator = subUserInfos.iterator(); iterator.hasNext(); )
        {
            SubUserInfo tmp = (SubUserInfo) iterator.next();
            logger.debug("result:{} {}", tmp.getId(), tmp.getUsername());
        }
        Assert.assertEquals(6, subUserInfos.size());
        logger.info("findByUsernameTest use {} ms", System.currentTimeMillis() - st);
    }

    @Test
    public void getJwtString() throws Exception
    {
        long st = System.currentTimeMillis();
        User user = new User();
        user.setId(100L);
        user.setUsername("yunan");
        String jwtString = JwtUtils.createJWT(String.valueOf(user.getId()), JwtUtils.generalSubject(user), 1000 * 60 * 30);
        logger.info(jwtString);
        Assert.assertNotEquals(0, jwtString.length());
        logger.info("getJwtString use {} ms", System.currentTimeMillis() - st);
    }

    @Test
    public void getJwtClaims() throws Exception
    {
        long st = System.currentTimeMillis();
        User user = new User();
        user.setId(100L);
        user.setUsername("yunan");
        String jwtString = JwtUtils.createJWT(String.valueOf(user.getId()), JwtUtils.generalSubject(user), 1000 * 60 * 30);

        logger.info(jwtString);

        Claims claims = JwtUtils.parseJWT(jwtString);
        JSONObject jsonObject = new JSONObject(claims.getSubject());
        logger.info(claims.getIssuedAt().toString());
        logger.info(claims.getExpiration().toString());
        logger.info(jsonObject.toString());
        logger.info(claims.getExpiration().toString());
        Assert.assertTrue(claims.getExpiration().getTime()-new Date().getTime()>0);
        logger.info("getJwtClaims use {} ms", System.currentTimeMillis() - st);
    }


}
