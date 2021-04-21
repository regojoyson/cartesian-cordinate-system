package com.cartesian.coordinateSystem.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * test cases for the Cartesian Controller
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartesianControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testIsLineUsingTwoPointsTrue() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/line-by-two-points/3/2/2/6"))
                .andExpect(status().isOk());
        Assert.assertTrue(Boolean.valueOf(result.andReturn().getResponse().getContentAsString()));
    }


    @Test
    public void testIsLineUsingTwoPointsFalse() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/line-by-two-points/3/3/3/3"))
                .andExpect(status().isOk());
        Assert.assertFalse(Boolean.valueOf(result.andReturn().getResponse().getContentAsString()));
    }


    @Test
    public void testFindGradientAndYIntercept() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/y-intercept/5/2/2/7"))
                .andExpect(status().isOk());
        Assert.assertEquals(result.andReturn().getResponse().getContentAsString(), "10");
    }


    @Test
    public void testIsParallelTrue() throws Exception {
        String input = "{ \"line1\": { \"start\": { \"x\": \"2\", \"y\": \"2\" }, \"end\": { \"x\": \"2\", \"y\": \"8\" } }, \"line2\": { \"start\": { \"x\": \"3\", \"y\": \"3\" }, \"end\": { \"x\": \"3\", \"y\": \"3\" } } }";
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/parallel").contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isOk());
        Assert.assertTrue(Boolean.valueOf(result.andReturn().getResponse().getContentAsString()));
    }


    @Test
    public void testIsParallelFalse() throws Exception {
        String input = "{ \"line1\": { \"start\": { \"x\": \"-8\", \"y\": \"-8\" }, \"end\": { \"x\": \"-2\", \"y\": \"-8\" } }, \"line2\": { \"start\": { \"x\": \"5\", \"y\": \"7\" }, \"end\": { \"x\": \"3\", \"y\": \"3\" } } }";
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/parallel").contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isOk());
        Assert.assertFalse(Boolean.valueOf(result.andReturn().getResponse().getContentAsString()));
    }

    @Test
    public void testIsPerpendicularTrue() throws Exception {
        String input = "{ \"line1\": { \"start\": { \"x\": \"1\", \"y\": \"2\" }, \"end\": { \"x\": \"1\", \"y\": \"8\" } }, \"line2\": { \"start\": { \"x\": \"0\", \"y\": \"1\" }, \"end\": { \"x\": \"8\", \"y\": \"1\" } } }";
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/perpendicular").contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isOk());
        Assert.assertTrue(Boolean.valueOf(result.andReturn().getResponse().getContentAsString()));
    }

    @Test
    public void testIsPerpendicularFalse() throws Exception {
        String input = "{ \"line1\": { \"start\": { \"x\": \"8\", \"y\": \"0\" }, \"end\": { \"x\": \"11\", \"y\": \"8\" } }, \"line2\": { \"start\": { \"x\": \"0\", \"y\": \"1\" }, \"end\": { \"x\": \"8\", \"y\": \"1\" } } }";
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/perpendicular").contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isOk());
        Assert.assertFalse(Boolean.valueOf(result.andReturn().getResponse().getContentAsString()));
    }

    @Test
    public void testLineIncidencePoint() throws Exception {
        String input = "{ \"line1\": { \"start\": { \"x\": \"1\", \"y\": \"1\" }, \"end\": { \"x\": \"4\", \"y\": \"4\" } }, \"line2\": { \"start\": { \"x\": \"1\", \"y\": \"8\" }, \"end\": { \"x\": \"5\", \"y\": \"0\" } } }";
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/line-incident-point").contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isOk());
        Assert.assertEquals(result.andReturn().getResponse().getStatus(), 200);
    }
}


