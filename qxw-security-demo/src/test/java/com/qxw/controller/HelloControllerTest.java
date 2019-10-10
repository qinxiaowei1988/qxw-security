package com.qxw.controller;

import com.qxw.security.DemoApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class HelloControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();


    }
    @Test
    public void whenQuerySucceess() throws Exception {
        MvcResult mr=mockMvc.perform(MockMvcRequestBuilders.get("/hello/index")
                                                   .contentType(MediaType.TEXT_EVENT_STREAM_VALUE)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
                                                    //.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
                                                   //.andExpect(MockMvcResultMatchers.jsonPath("$.lenth()").value(4));

    }

    @Test
    public void wherGenInfoSouccess() throws Exception {
     String  str =mockMvc.perform(MockMvcRequestBuilders.get("/user/info/1").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
     System.out.println("json="+str);
    }
    @Test
    public void whenQueryUserSuccess() throws Exception {
        String str = mockMvc.perform(MockMvcRequestBuilders.get("/user/findAll")
                .param("userName","张三")
                .param("testKey","xxxxx")
                .param("size","15")
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println("json="+str);
    }

    @Test
    public void wenCreateSueccess() throws Exception {
        String content="{\"userId2\":1,\"userName\":\"张三\",\"userAge\":20,\"password\":null,\"birthDay\":"+new Date().getTime() +"}";
        String str = mockMvc.perform(MockMvcRequestBuilders.post("/user/save").contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();
        System.out.println("json="+str);
    }

    @Test
    public void wenUpdateSueccess() throws Exception {
        String content="{\"userId\":1,\"userName\":\"张三\",\"userAge\":20,\"password\":null,\"birthDay\":"+new Date().getTime() +"}";
        String str = mockMvc.perform(MockMvcRequestBuilders.post("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("json="+str);
    }

}
