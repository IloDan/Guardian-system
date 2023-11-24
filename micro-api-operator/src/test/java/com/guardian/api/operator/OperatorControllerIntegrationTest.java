package com.guardian.api.operator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.guardian.api.operator.controllers.OperatorController;
import com.guardian.api.operator.repositories.OperatorRepository;
import com.guardian.api.operator.repositories.TimeslotRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.ws.rs.core.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OperatorControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OperatorRepository operatorRepository;
    @MockBean
    TimeslotRepository timeslotRepository;

    @Test
    public void testGetAllOperators() {
        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/operators")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
