package com.globalsoftwaresupport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globalsoftwaresupport.model.Student;

@Repository
public interface StudentRepository extends
					JpaRepository<Student, Integer> {

	// JPQL to find all the students that includes the
	// substring the user defined
	@Query("select s from Student s where lower(s.name) like lower(concat('%', :substring, '%'))")                                         
	List<Student> findStudents(String substring);

	// all the CRUD operations are implemented
}
