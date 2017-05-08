package com.example;

import com.example.DemoApplication;
import com.example.WordCounter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WordCounterController.class)
public class WordCounterControllerTest {

    @MockBean
    private WordCounter wordCounter;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup() {

        when(wordCounter.countWords(anyString()))
                .thenReturn( initMap() );
    }

    @Test
    public void testWordCountEndpoint () throws Exception {
        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON)
                .content("A brown cow jumps over a brown moon in the sun");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.a", is(2) ))
                .andExpect(jsonPath("$.brown", is(2) ))
                .andExpect(jsonPath("$.cow", is(1) ))
                .andExpect(jsonPath("$.jumps", is(1) ))
                .andExpect(jsonPath("$.over", is(1) ))
                .andExpect(jsonPath("$.moon", is(1) ))
                .andExpect(jsonPath("$.in", is(1) ))
                .andExpect(jsonPath("$.the", is(1) ))
                .andExpect(jsonPath("$.sun", is(1) ));
    }

    private Map<String, Integer> initMap() {
        Map<String, Integer> mockMap = new HashMap<>();

        mockMap.put("a", 2);
        mockMap.put("brown", 2);
        mockMap.put("cow", 1);
        mockMap.put("jumps", 1);
        mockMap.put("over", 1);
        mockMap.put("moon", 1);
        mockMap.put("in", 1);
        mockMap.put("the", 1);
        mockMap.put("sun", 1);

        return mockMap;
    }

}