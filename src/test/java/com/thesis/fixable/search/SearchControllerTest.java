package com.thesis.fixable.search;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.fixable.technician.TechnicianEntity;
import com.thesis.fixable.technician.TechnicianResponse;
import com.thesis.fixable.technician.Technicians;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SearchControllerTest {

    @MockBean
    SearchService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    Technicians technicians = new Technicians();

    @Test
    void testFindNearByTechnician_WithLatAndLng() throws Exception {
        TechnicianEntity entity1 = technicians.getBudapestCarpenter();
        TechnicianEntity entity2 = technicians.getBudapestElectrician();

        when(service.findAllTechnicianNearBy(47.5555, 65.2333)).thenReturn(List.of(entity1, entity2));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/search?lat=47.5555&lng=65.2333")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String Content = result.getResponse().getContentAsString();

        List<TechnicianResponse> actual = objectMapper.readValue(Content, new TypeReference<List<TechnicianResponse>>() {});
        List<TechnicianResponse> expected = List.of(
                TechnicianResponse.fromTechnicianEntity(entity1),
                TechnicianResponse.fromTechnicianEntity(entity2)
        );
        assertEquals(expected, actual);
    }

    @Test
    void testFindNearByTechnician_WithLatAndLngAndProfession() throws Exception {
        TechnicianEntity entity1 = technicians.getBudapestCarpenter();
        TechnicianEntity entity2 = technicians.getBudapestElectrician();

        when(service.findAllTechnicianNearBy(47.5555, 65.2333, "Electrician")).thenReturn(List.of(entity1, entity2));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/search?lat=47.5555&lng=65.2333&profession=Electrician")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String Content = result.getResponse().getContentAsString();

        List<TechnicianResponse> actual = objectMapper.readValue(Content, new TypeReference<List<TechnicianResponse>>() {});
        List<TechnicianResponse> expected = List.of(
                TechnicianResponse.fromTechnicianEntity(entity1),
                TechnicianResponse.fromTechnicianEntity(entity2)
        );
        assertEquals(expected, actual);
    }

}