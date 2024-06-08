package com.globalsoftwaresupport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.globalsoftwaresupport.model.Student;

@Repository
public interface StudentRepository extends 
					JpaRepository<Student, Integer>{
	@Query("select s from Student s where lower(s.name) like lower(concat('%', :term, '%'))")
	List<Student> find(@Param("term") String term);
}
