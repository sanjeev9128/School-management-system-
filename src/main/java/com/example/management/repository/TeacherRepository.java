package com.example.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.management.entity.Teacher;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
