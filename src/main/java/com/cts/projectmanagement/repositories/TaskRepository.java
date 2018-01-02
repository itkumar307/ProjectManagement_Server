package com.cts.projectmanagement.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.projectmanagement.entities.*;

public interface TaskRepository extends CrudRepository<Task,Integer>{

	List<Task> findAllByProjectId(Integer id);
		
	List<Task> findAllByProjectIdAndStatus(Integer id, String status);
	
}
