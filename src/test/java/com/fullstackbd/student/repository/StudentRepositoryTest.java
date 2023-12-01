package com.fullstackbd.student.repository;

import com.fullstackbd.student.model.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void saveStudent() {
        // Given
        Student student = Student.builder()
                .name("John Doe")
                .className(10)
                .roll(101)
                .section("A")
                .build();

        // When
        Student savedStudent = studentRepository.save(student);

        // Then
        assertThat(savedStudent.getId()).isNotNull();
        assertThat(savedStudent.getName()).isEqualTo(student.getName());
        assertThat(savedStudent.getClassName()).isEqualTo(student.getClassName());
        assertThat(savedStudent.getRoll()).isEqualTo(student.getRoll());
        assertThat(savedStudent.getSection()).isEqualTo(student.getSection());
    }

    @Test
    void findStudentById() {
        // Given
        Student student = Student.builder()
                .name("Jane Doe")
                .className(11)
                .roll(102)
                .section("B")
                .build();
        Student savedStudent = entityManager.persistAndFlush(student);

        // When
        Student foundStudent = studentRepository.findById(savedStudent.getId()).orElse(null);

        // Then
        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.getId()).isEqualTo(savedStudent.getId());
        assertThat(foundStudent.getName()).isEqualTo(savedStudent.getName());
        assertThat(foundStudent.getClassName()).isEqualTo(savedStudent.getClassName());
        assertThat(foundStudent.getRoll()).isEqualTo(savedStudent.getRoll());
        assertThat(foundStudent.getSection()).isEqualTo(savedStudent.getSection());
    }

    @Test
    void updateStudent() {
        // Given
        Student student = Student.builder()
                .name("Mark Smith")
                .className(12)
                .roll(103)
                .section("C")
                .build();
        Student savedStudent = entityManager.persistAndFlush(student);
        savedStudent = Student.builder()
                .name("Updated Name")
                .className(9)
                .roll(99)
                .section("X")
                .build();
        // When
        Student updatedStudent = studentRepository.save(savedStudent);

        // Then
        assertThat(updatedStudent.getId()).isEqualTo(savedStudent.getId());
        assertThat(updatedStudent.getName()).isEqualTo("Updated Name");
        assertThat(updatedStudent.getClassName()).isEqualTo(9);
        assertThat(updatedStudent.getRoll()).isEqualTo(99);
        assertThat(updatedStudent.getSection()).isEqualTo("X");
    }

    @Test
    void deleteStudent() {
        // Given
        Student student = Student.builder()
                .name("Michael Johnson")
                .className(8)
                .roll(104)
                .section("D")
                .build();
        Student savedStudent = entityManager.persistAndFlush(student);

        // When
        studentRepository.deleteById(savedStudent.getId());

        // Then
        assertThat(studentRepository.findById(savedStudent.getId())).isEmpty();
    }

    @Test
    void findAllStudents() {
        // Given
        Student student1 = Student.builder()
                .name("Alice Williams")
                .className(9)
                .roll(105)
                .section("E")
                .build();
        Student student2 = Student.builder()
                .name("Bob Davis")
                .className(10)
                .roll(106)
                .section("F")
                .build();
        entityManager.persistAndFlush(student1);
        entityManager.persistAndFlush(student2);

        // When
        List<Student> allStudents = studentRepository.findAll();

        // Then
        assertThat(allStudents).hasSize(2);
        assertThat(allStudents).extracting("name").containsExactlyInAnyOrder("Alice Williams", "Bob Davis");
        // Add more assertions based on your entity properties
    }
}
