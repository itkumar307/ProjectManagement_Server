package com.cts.projectmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.projectmanagement.entities.ParentTask;
import com.cts.projectmanagement.repositories.ParentTaskRepository;

@Controller
@RequestMapping(path="/parenttask")
public class ParentTaskController {

	@Autowired
	private ParentTaskRepository repo;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<ParentTask> getAllUsers() {
		return repo.findAll();
	}
	
	@PostMapping(path="/add")
	public @ResponseBody ParentTask addNew (@RequestBody String taske) {
		ParentTask task = new ParentTask();
		task.setParentTask(taske);
		return repo.save(task);
	}
}
