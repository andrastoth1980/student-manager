package com.globalsoftwaresupport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globalsoftwaresupport.model.Status;

// this annotation is optional: because the JpaRepository
// is extended - Spring knows that it is a repository
@Repository
public interface StatusRepository extends 
				JpaRepository<Status, Integer> {

	// all the CRUD operations are implemented
	// by default 
}
