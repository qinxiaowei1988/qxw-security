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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
                                                   .contentType(MediaType.TEXT_EVENT_STREAM_VALUE)).andReturn();
                                                    //.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
                                                   //.andExpect(MockMvcResultMatchers.jsonPath("$.lenth()").value(4));

    }
}
