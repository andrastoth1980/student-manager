package com.globalsoftwaresupport.services;

import java.util.List;

import com.globalsoftwaresupport.model.Status;

public interface StatusService {
	public void save(Status status);
	public List<Status> findAll();
}
