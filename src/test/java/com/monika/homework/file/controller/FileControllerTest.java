package com.monika.homework.file.controller;

import com.monika.homework.file.service.FileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringJUnitWebConfig
@WebMvcTest(FileController.class)
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileService fileService;

    @Test
    public void readFileTest() throws Exception {
        //Given
        Optional<String> test = Optional.of("test");

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/file/read?path=test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"));

        verify(fileService, times(1)).save(test);

    }

    @Test
    public void processNewEntriesTest() throws Exception {
        //Given
        Optional<String> delimiter = Optional.of(";");

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/file/process?delimiter=;")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"));

        verify(fileService, times(1)).process(delimiter);

    }
}