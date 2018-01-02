package com.cts.projectmanagement.repositories;

import org.springframework.data.repository.CrudRepository;

import com.cts.projectmanagement.entities.*;

public interface ParentTaskRepository extends CrudRepository<ParentTask, Integer> {

}
