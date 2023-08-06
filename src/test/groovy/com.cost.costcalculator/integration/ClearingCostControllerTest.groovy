package com.cost.costcalculator.integration

import com.cost.costcalculator.dto.CalculateCostRequestDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


@AutoConfigureMockMvc
@WebMvcTest
class ClearingCostControllerTest extends Specification {

    @Autowired
    private MockMvc mvc

    private ObjectMapper objectMapper = new ObjectMapper()

    //needs fixing
    def "call the cost endpoint"() {
        expect: "Status is 200"
        mvc.perform(post("/clearing-cost")
                    .content(objectMapper.writeValueAsString(new CalculateCostRequestDto("111")))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .response
    }
}
