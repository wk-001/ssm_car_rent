package com.wk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wk.sys.dao.SysMenuMapper;
import com.wk.sys.pojo.SysMenu;
import com.wk.sys.pojo.SysUser;
import com.wk.sys.service.SysUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration    //获取web的IOC
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springMVC.xml"})
public class CodeTest {

    @Autowired
    WebApplicationContext context;


    //虚拟mvc，获取到处理后的结果
    MockMvc mockMvc;

    @Resource
    private SysUserService userService;

    @Resource
    private SysMenuMapper menuMapper;

   /* @Autowired*/
    private ObjectMapper mapper;        //jackson对象

    private MockHttpSession session;

    //使用MockMvc需要进行初始化
    @Before
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        /*模拟session*/
        session = new MockHttpSession();
        SysUser user =new SysUser();
        user.setType(1);
        user.setRealname("tom");
        session.setAttribute("user", user);
    }

    //发送请求测试controller方法
    @Test
    public void testPage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/menu/initLeftMenu")       //这里路径必须要加"/"
                        .session(session)                           //请求添加session
                        .param("id", "1"))
                .andReturn();
        String str = result.getResponse().getContentAsString();
        System.out.println("str = " + str);
    }

    @Test
    public void testSession() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/menu/test")       //这里路径必须要加"/"
                        .session(session)
                        .param("id", "1"))
                .andReturn();
        String str = result.getResponse().getContentAsString();
        System.out.println("str = " + str);
    }

}
