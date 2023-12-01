package com.fullstackbd.student.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackbd.student.common.exceptions.DatabaseAddException;
import com.fullstackbd.student.common.service.CrudService;
import com.fullstackbd.student.model.entity.Student;
import com.fullstackbd.student.model.dto.StudentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private CrudService<Student, StudentDTO, Long> mockService;

    @InjectMocks
    private StudentController studentController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllStudents() throws Exception {
        List<Student> mockStudents = Collections.singletonList(new Student(/* provide necessary parameters */));
        when(mockService.findAll()).thenReturn(mockStudents);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        mockMvc.perform(get("/api/students/"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockStudents)));
    }

    @Test
    void getStudentById() throws Exception {
        Long studentId = 1L;
        Student mockStudent = new Student(/* provide necessary parameters */);
        when(mockService.findById(anyLong())).thenReturn(mockStudent);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        mockMvc.perform(get("/api/students/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockStudent)));
    }

    @Test
    void saveStudent() throws Exception {
        StudentDTO studentDTO = StudentDTO
                .builder()
                .name("John Doe")
                .className(10)
                .roll(1)
                .section("A")
                .build();
        Student savedStudent = new Student(/* provide necessary parameters */);
        when(mockService.save(any(StudentDTO.class))).thenReturn(savedStudent);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        mockMvc.perform(post("/api/students/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(savedStudent)));
    }

    @Test
    void updateStudent() throws Exception {
        Long studentId = 1L;
        StudentDTO studentDTO = StudentDTO
                .builder()
                .name("John Doe")
                .className(10)
                .roll(1)
                .section("B")
                .build();
        Student updatedStudent = new Student(/* provide necessary parameters */);
        when(mockService.update(anyLong(), any(StudentDTO.class))).thenReturn(updatedStudent);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        mockMvc.perform(put("/api/students/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedStudent)));
    }

    @Test
    void deleteStudentById() throws Exception {
        Long studentId = 1L;
        Student deletedStudent = new Student(/* provide necessary parameters */);
        when(mockService.deleteById(anyLong())).thenReturn(deletedStudent);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        mockMvc.perform(delete("/api/students/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deletedStudent)));

        // Verify that the service method was called with the correct argument
        verify(mockService).deleteById(studentId);
    }
}
