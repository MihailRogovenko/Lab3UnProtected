package com.tpp.tpplab3.controllers;

import com.tpp.tpplab3.models.Students;
import com.tpp.tpplab3.service.StudentService;
import com.tpp.tpplab3.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/add")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Students());
        model.addAttribute("groups", groupService.getAllGroups());
        return "add-student";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") Students student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable("id") Integer id, Model model) {
        Optional<Students> studentOpt = studentService.findStudentById(id);
        if (studentOpt.isPresent()) {
            model.addAttribute("student", studentOpt.get());
            model.addAttribute("groups", groupService.getAllGroups());
            return "edit-student";
        }
        return "redirect:/students";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Integer id, @ModelAttribute("student") Students student) {
        student.setStudentId(id);
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    @PostMapping("/execute-query")
    public String executeQuery(@RequestParam("sqlQuery") String sqlQuery, Model model) {
        try {
            List<Map<String, Object>> result = jdbcTemplate.query(sqlQuery, new ColumnMapRowMapper());
            if (!result.isEmpty()) {
                model.addAttribute("queryResult", Map.of(
                    "columns", result.get(0).keySet(),
                    "rows", result.stream().map(Map::values).toList()
                ));
            } else {
                model.addAttribute("queryResult", null);
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error executing query: " + e.getMessage());
        }
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }
}
