package com.globalsoftwaresupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globalsoftwaresupport.model.Student;

@Repository
public interface StudentRepository extends 
					JpaRepository<Student, Integer>{
}
