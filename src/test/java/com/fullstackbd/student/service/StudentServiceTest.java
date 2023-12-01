package com.fullstackbd.student.service;

import com.fullstackbd.student.common.exceptions.*;
import com.fullstackbd.student.model.dto.StudentDTO;
import com.fullstackbd.student.model.entity.Student;
import com.fullstackbd.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testFindAll() {
        // Mocking repository behavior
        when(repository.findAll()).thenReturn(Arrays.asList(new Student(), new Student()));

        // Calling the service method
        List<Student> students = studentService.findAll();

        // Assertions
        assertEquals(2, students.size());
    }

    @Test
    void testSave() throws DatabaseAddException {
        // Mocking repository behavior
        when(repository.save(any(Student.class))).thenReturn(new Student());

        // Calling the service method
        StudentDTO studentDTO = new StudentDTO();
        Student savedStudent = studentService.save(studentDTO);

        // Assertions
        assertNotNull(savedStudent);
    }

    @Test
    void testFindById() throws ModelNotFoundException {
        // Mocking repository behavior
        when(repository.findById(1L)).thenReturn(Optional.of(new Student()));

        // Calling the service method
        Student student = studentService.findById(1L);

        // Assertions
        assertNotNull(student);
    }

    @Test
    void testUpdate() throws DatabaseUpdateException, ModelNotFoundException {
        // Mocking repository behavior
        when(repository.findById(1L)).thenReturn(Optional.of(new Student()));
        when(repository.save(any(Student.class))).thenReturn(new Student());

        // Calling the service method
        StudentDTO studentDTO = new StudentDTO();
        Student updatedStudent = studentService.update(1L, studentDTO);

        // Assertions
        assertNotNull(updatedStudent);
    }

    @Test
    void testDeleteById() throws DatabaseDeleteException, ModelNotFoundException {
        // Mocking repository behavior
        when(repository.findById(1L)).thenReturn(Optional.of(new Student()));

        // Calling the service method
        Student deletedStudent = studentService.deleteById(1L);

        // Assertions
        assertNotNull(deletedStudent);

        // Verifying that deleteById method of repository was called
        verify(repository, times(1)).deleteById(1L);
    }

    // Add more test cases for exception scenarios...

}
