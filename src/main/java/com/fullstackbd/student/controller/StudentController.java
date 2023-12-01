package com.fullstackbd.student.controller;

import com.fullstackbd.student.common.controller.CrudController;
import com.fullstackbd.student.common.exceptions.DatabaseAddException;
import com.fullstackbd.student.common.service.CrudService;
import com.fullstackbd.student.model.entity.Student;
import com.fullstackbd.student.model.dto.StudentDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController implements CrudController<Student, StudentDTO, Long> {
    private final CrudService<Student, StudentDTO, Long> service;
    private final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    @Override
    public List<Student> getAll() {
        LOG.info("Student Controller Get All Triggered");
        return service.findAll();
    }

    @Override
    public Student getById(Long id) {
        LOG.info("Student Controller Get By ID Triggered");
        return this.service.findById(id);
    }

    @Override
    public Student save(StudentDTO dto) throws DatabaseAddException {
        LOG.info("Student Controller Save Triggered");
        return this.service.save(dto);
    }

    @Override
    public Student update(Long id, StudentDTO dto) {
        LOG.info("Student Controller Update Triggered");
        return this.service.update(id, dto);
    }

    @Override
    public Student deleteById(Long id) {
        LOG.info("Student Controller Delete Triggered");
        return this.service.deleteById(id);
    }
}
