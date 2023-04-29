package com.stormit.demo.testProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskMockMvcApi {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFetchNotesList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/notes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content()
                        .json("[{\"id\":0,\"name\":\"Note1\"},{\"id\":0,\"name\":\"Note2\"}]")
                );
    }

    @Test
    void shouldFetchSingleNote() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/notes/4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Note-4")
                );
    }

    @Test
    void shouldUpdateNote() throws Exception{
        Note note = new Note(3, "Note-3");
        ObjectMapper objectMapper = new ObjectMapper();

        String request = objectMapper.writeValueAsString(note);

        mockMvc.perform(
                MockMvcRequestBuilders.put("http://localhost:8080/api/notes/3")
                    .contentType(MediaType.APPLICATION_JSON)
                .content(request)
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(
                    MockMvcResultMatchers
                        .jsonPath("$.name")
                        .value("Note-3")
        );
    }

}
