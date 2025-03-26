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

import com.example.management.entity.Teacher;
import com.example.management.service.TeacherService;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
	private final TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@PostMapping
	public Teacher createTeacher(@RequestBody Teacher teacher) {
		return teacherService.saveTeacher(teacher);
	}

	@GetMapping
	public List<Teacher> getAllTeachers() {
		return teacherService.getAllTeachers();
	}

	@GetMapping("/{id}")
	public Teacher getTeacher(@PathVariable Long id) {
		return teacherService.getTeacherById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Teacher updateTeacher(@PathVariable Long id, @RequestBody Teacher teacherDetails) {
		return teacherService.updateTeacher(id, teacherDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteTeacher(@PathVariable Long id) {
		teacherService.deleteTeacher(id);
	}

}
