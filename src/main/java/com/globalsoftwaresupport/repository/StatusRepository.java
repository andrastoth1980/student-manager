package com.globalsoftwaresupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.globalsoftwaresupport.model.Status;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface StatusRepository extends
				JpaRepository<Status , Integer>{
	
	

}
