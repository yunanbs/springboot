package com.spring.yu.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.yu.comm.JwtUtils;
import com.spring.yu.entity.User;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


/**
 * Created by yunan on 2017/3/10.
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.GET})
public class DemoController
{
    private final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("/")
    public String helloWorld()
    {
        return "hello world";
    }

    /**
     * 测试方法 返回输入的json
     * 支持 key-value 或 json
     *
     * @param req
     * @return
     */
    @RequestMapping(path = "/secret/hellobyname")
    public JSONObject helloWorldbyname(HttpServletRequest req, HttpServletResponse res)
    {
        long st = System.currentTimeMillis();
        JSONObject result = null;
        if (200 == res.getStatus())
        {
            String contenttype = req.getHeader("Content-Type");

            if (contenttype.equals("application/json"))
            {
                try
                {
                    BufferedReader bufferedReader = req.getReader();
                    StringBuilder sb = new StringBuilder();
                    String line = bufferedReader.readLine();
                    while (line != null)
                    {
                        sb.append(line);
                        line = bufferedReader.readLine();
                    }
                    result = JSONObject.fromObject(sb.toString());

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            } else
            {
                result = JSONObject.fromObject(req.getParameter("user"));
            }
            logger.info("finish hellobyname in {} ms", System.currentTimeMillis() - st);
        }
        return result;
    }


    @RequestMapping(path = "/hellobynamejson", consumes = "application/json")
    public User helloWorldbynamejson(@RequestBody String userdata)
    {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        User user = new User();
        try
        {
            user = om.readValue(userdata, user.getClass());
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return user;
    }

    @RequestMapping("/secret/hellobynameform")
    public String hellowworldbynameform(String user)
    {
        return user;
    }

    @RequestMapping("login")
    public String login(@RequestBody String user) throws Exception{
        JSONObject jsonObject = JSONObject.fromObject(user);
        String jwtHeader=JwtUtils.createJWT(jsonObject.getString("id"),user,30*60*1000);
        return  jwtHeader;
    }
}
