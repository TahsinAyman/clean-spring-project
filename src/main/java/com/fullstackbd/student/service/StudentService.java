package com.fullstackbd.student.service;

import com.fullstackbd.student.common.exceptions.DatabaseAddException;
import com.fullstackbd.student.common.exceptions.DatabaseDeleteException;
import com.fullstackbd.student.common.exceptions.DatabaseUpdateException;
import com.fullstackbd.student.common.exceptions.ModelNotFoundException;
import com.fullstackbd.student.common.service.CrudService;
import com.fullstackbd.student.model.entity.Student;
import com.fullstackbd.student.model.dto.StudentDTO;
import com.fullstackbd.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService implements CrudService<Student, StudentDTO, Long> {

    private final StudentRepository repository;
    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

    @Override
    public List<Student> findAll() {
        LOG.info("Fetch All Student Service Triggered");
        return this.repository.findAll();
    }

    @Override
    public Student save(StudentDTO dto) throws DatabaseAddException {
        Student student = Student
                .builder()
                .build();
        BeanUtils.copyProperties(dto, student);
        LOG.info("Converted Student DTO to Student Entity");
        try {
            LOG.info("Saved Student Entity to Database");
            return this.repository.save(student);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new DatabaseAddException("Student Cannot be Added.");
        }
    }

    @Override
    public Student findById(Long id) throws ModelNotFoundException {
        return this.repository.findById(id).orElseThrow(() -> new ModelNotFoundException("Student Not Found"));
    }

    @Override
    public Student update(Long id, StudentDTO dto) throws DatabaseUpdateException, ModelNotFoundException {
        Student student = this.findById(id);
        if (dto.getName() != null) {
            student.setName(dto.getName());
        }
        if (dto.getClassName() != null) {
            student.setClassName(dto.getClassName());
        }
        if (dto.getRoll() != null) {
            student.setRoll(dto.getRoll());
        }
        if (dto.getSection() != null) {
            student.setSection(dto.getSection());
        }
        try {
            LOG.info("Updated Student Entity to Database");
            return this.repository.save(student);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new DatabaseUpdateException("Student Cannot be Updated.");
        }
    }

    @Override
    public Student deleteById(Long id) throws DatabaseDeleteException, ModelNotFoundException {
        Student student = this.findById(id);
        try {
            LOG.info("Deleted Student Entity from Database");
            this.repository.deleteById(id);
            return student;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new DatabaseDeleteException("Student Cannot be Deleted.");
        }
    }
}
