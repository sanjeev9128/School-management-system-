package com.example.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.management.entity.Teacher;
import com.example.management.repository.TeacherRepository;

@Service
public class TeacherService {

	private final TeacherRepository teacherRepository;

	public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	@CachePut(value = "teachers", key = "#teacher.id")
	public Teacher saveTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Cacheable(value = "teacher", key = "#id")
	public Optional<Teacher> getTeacherById(Long id) {
		return teacherRepository.findById(id);
	}

	@CacheEvict(value = "teachers", key = "#id")
	public void deleteTeacher(Long id) {
		teacherRepository.deleteById(id);
	}

	@CachePut(value = "teachers", key = "#id")
	public Teacher updateTeacher(Long id, Teacher teacherDetails) {
		Optional<Teacher> teacher = teacherRepository.findById(id);

		if (teacher.isPresent()) {
			Teacher existingTeacher = teacher.get();
			existingTeacher.setName(teacherDetails.getName());
			existingTeacher.setEmail(teacherDetails.getEmail());

			return teacherRepository.save(existingTeacher);
		} else {
			throw new RuntimeException("Teacher not found with id: " + id);
		}
	}

}
