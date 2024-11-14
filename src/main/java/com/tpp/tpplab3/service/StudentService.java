package com.tpp.tpplab3.service;

import com.tpp.tpplab3.models.Students;
import com.tpp.tpplab3.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentsRepository studentsRepository;

    public List<Students> getAllStudents() {
        return studentsRepository.findAll();
    }

    public Optional<Students> findStudentById(Integer id) {
        return studentsRepository.findById(id);
    }

    public void saveStudent(Students student) {
        studentsRepository.save(student);
    }

    public void updateStudent(Students updatedStudent) {
        studentsRepository.save(updatedStudent);
    }

    public void deleteStudentById(Integer id) {
        studentsRepository.deleteById(id);
    }
}
