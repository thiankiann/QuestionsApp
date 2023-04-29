package com.stormit.demo.testProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskMockMvcView {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCheckNoteForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/notes/add"))
                .andExpect(xpath("//input[@name='name']").exists())
                .andExpect(xpath("//h1").string("Add new note"));
    }
    @Test
    void shouldCatch404clientError() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/notes/"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }
}
