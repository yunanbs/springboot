import com.spring.yu.app;
import com.spring.yu.controller.DemoController;
import com.spring.yu.entity.User;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

/**
 * Created by yunan on 2017/3/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = app.class)
public class DemoControllerTest
{
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(new DemoController()).build();
    }

    @Test
    public void testHelloByName() throws Exception{
        JSONObject params = new JSONObject();
        User user = new User();
        user.setUsername("yunan");
        user.setPassword("123");
        user.setCreated(new Date());
        user.setModifyed(new Date());
        user.setState(1);
        user.setId(1L);
        params.put("user",user);
        String paramStr = params.toString();
        mockMvc.perform(MockMvcRequestBuilders.post("/hellobyname")
                .content(paramStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }





}
