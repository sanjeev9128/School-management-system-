package com.example.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.management.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
