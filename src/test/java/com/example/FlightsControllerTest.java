package com.example;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(FlightsController.class)
public class FlightsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testFlightEndpoint() throws Exception {
        this.mvc.perform(get("/flights/flight")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                // why is it adding 6 hours to the original hour that I gave it??
                .andExpect(jsonPath("$.departs", is("2017-05-21 20:34")))
                .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Sai")))
                .andExpect(jsonPath("$.tickets[0].passenger.lastName", is("Sai")))
                .andExpect(jsonPath("$.tickets[0].price", is(200)));
    }


    @Test
    public void testFlightsEndpoint() throws Exception {
        this.mvc.perform(get("/flights")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                // why is it adding 6 hours to the original hour that I gave it??
                .andExpect(jsonPath("$[0].departs", is("2017-05-21 20:34")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.firstName", is("Sai")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.lastName", is("Sai")))
                .andExpect(jsonPath("$[0].tickets[0].price", is(200)))
                // why is it adding 6 hours to the original hour that I gave it??
                .andExpect(jsonPath("$[1].departs", is("2017-05-21 20:34")))
                .andExpect(jsonPath("$[1].tickets[0].passenger.firstName", is("Sai")))
                .andExpect(jsonPath("$[1].tickets[0].price", is(400)));
    }

    @Test
    public void testTotalEndpoint() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\": [{\"passenger\": {\"firstName\": \"Some name\",\"lastName\": \"Some other name\"}," +
                        "\"price\": 200},{\"passenger\": {\"firstName\": \"Name B\",\"lastName\": \"Name C\"},\"price\": 150}]}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":\"350\"}"));
    }

   

    @Test
    public void testRawBody() throws Exception {
        String json = getJSON("/data.json");

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":\"350\"}"));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}