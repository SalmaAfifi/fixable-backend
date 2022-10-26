package com.thesis.fixable.technician;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DtoTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    void testDeserialization() throws JsonProcessingException {
        assertEquals(
                DtoUtil.TECHNICIAN_DTO,
                OBJECT_MAPPER.readValue(DtoUtil.TECHNICIAN_DTO_JSON, TechnicianDTO.class)
        );
    }

    @Test
    void testSerialization() throws JsonProcessingException {
        String json = OBJECT_MAPPER.writeValueAsString(DtoUtil.TECHNICIAN_DTO);
        TechnicianDTO actual = OBJECT_MAPPER.readValue(json, TechnicianDTO.class);
        assertEquals(DtoUtil.TECHNICIAN_DTO, actual);
    }
}
