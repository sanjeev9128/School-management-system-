package com.example.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.management.entity.Student;
import com.example.management.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping
	public Student createStudent(@RequestBody Student student) {
		return studentService.saveStudent(student);
	}

	@GetMapping
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public Student getStudent(@PathVariable Long id) {
		return studentService.getStudentById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
		return studentService.updateStudent(id, studentDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
	}

}
