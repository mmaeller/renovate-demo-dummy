package de.maeller.renovatedemodummy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RenovateDemoDummyApplicationTests {

  @Autowired private MockMvc mockMvc;

  @Test
  void greetingSuccessful() throws Exception {
    mockMvc
        .perform(get("/api/hello/DevCamp").accept(MediaType.ALL_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().string("Hello DevCamp"))
        .andReturn();
  }

  @Test
  void greetingFails() throws Exception {
    mockMvc
        .perform(get("/api/hello/").accept(MediaType.ALL_VALUE))
        .andExpect(status().isNotFound())
        .andReturn();
  }
}
