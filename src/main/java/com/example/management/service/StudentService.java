package com.example.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.example.management.entity.Student;
import com.example.management.repository.StudentRepository;

@Service
public class StudentService {
	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@CacheEvict(value = "student", key = "#id") 
	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}


	public Student updateStudent(Long id, Student studentDetails) {
		Optional<Student> student = studentRepository.findById(id);

		if (student.isPresent()) {
			Student existingStudent = student.get();
			existingStudent.setName(studentDetails.getName());
			existingStudent.setEmail(studentDetails.getEmail());

			return studentRepository.save(existingStudent);
		} else {
			throw new RuntimeException("Student not found with id: " + id);
		}
	}

}
