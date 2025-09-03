package com.example.todo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;

    @Test
    void create_and_list_items() throws Exception {
        // Create one item
        TodoItem req = TodoItem.newItem("Write cover letter", "Keep it simple");
        mvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(req)))
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$.id", notNullValue()))
           .andExpect(jsonPath("$.title").value("Write cover letter"))
           .andExpect(jsonPath("$.done").value(false));

        // List should include it
        mvc.perform(get("/api/todos"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(1)))
           .andExpect(jsonPath("$[0].title").value("Write cover letter"));
    }

    @Test
    void validation_rejects_blank_title() throws Exception {
        TodoItem bad = new TodoItem(null, "", "notes", false);
        mvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(bad)))
           .andExpect(status().isBadRequest());
    }
}
