package com.cts.projectmanagement.repositories;


import org.springframework.data.repository.CrudRepository;

import com.cts.projectmanagement.entities.*;


public interface UserRepository extends CrudRepository<User, Integer> {

}