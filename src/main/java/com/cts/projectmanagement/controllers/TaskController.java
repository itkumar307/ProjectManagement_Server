package com.cts.projectmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.projectmanagement.entities.ParentTask;
import com.cts.projectmanagement.entities.Project;
import com.cts.projectmanagement.entities.Task;
import com.cts.projectmanagement.entities.User;
import com.cts.projectmanagement.model.TaskObj;
import com.cts.projectmanagement.repositories.ParentTaskRepository;
import com.cts.projectmanagement.repositories.ProjectRepository;
import com.cts.projectmanagement.repositories.TaskRepository;
import com.cts.projectmanagement.repositories.UserRepository;


@Controller
@RequestMapping(path="/task")
public class TaskController {
	
	@Autowired 
	TaskRepository taskRepo;
	
	@Autowired
	private ParentTaskRepository repo;
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewTask (@RequestBody TaskObj task) {
		if(task.isParentTask()){
			ParentTask parentTask = new ParentTask();
			parentTask.setParentTask(task.getTaskName());
			repo.save(parentTask);
		}else{
			Task newTask = new Task();
			newTask.setParentId(task.getParentTaskId());
			newTask.setProjectId(task.getProjectId());
			newTask.setTask(task.getTaskName());
			newTask.setStartDate(task.getStartDate());
			newTask.setEndDate(task.getEndDate());
			newTask.setPriority(task.getPriority());
			newTask.setUserId(task.getUserId());	
			newTask.setStatus("STARTED");
			taskRepo.save(newTask);
		}
		
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody List<TaskObj> getAllTasks() {
		List<TaskObj> taskObjList = new ArrayList<>();
		List<Task> taskList =  (List<Task>) taskRepo.findAll();
		for(Task t: taskList){
			TaskObj taskObj = new TaskObj();
			taskObj.setTaskId(t.getTaskId());
			taskObj.setParentTaskId(t.getParentId());
			taskObj.setProjectId(t.getProjectId());
			taskObj.setTaskName(t.getTask());
			taskObj.setStartDate(t.getStartDate());
			taskObj.setEndDate(t.getEndDate());
			taskObj.setPriority(t.getPriority());
			taskObj.setStatus(t.getStatus());
			taskObj.setUserId(t.getUserId());
			if(t.getParentId() != null){
				ParentTask pTask = repo.findOne(t.getParentId());
				if(pTask != null){
					taskObj.setParentTaskName(pTask.getParentTask());
				}
			}
			if(t.getProjectId() != null){
				Project project = projectRepo.findOne(t.getProjectId());
				if(project != null){
					taskObj.setProjectName(project.getProject());
				}	
			}
			if(t.getUserId() != null){
				User user = userRepo.findOne(t.getUserId());
				if(user != null){
					taskObj.setUserName(user.getFirstName());
				}
			}
			
			taskObjList.add(taskObj);
			
		}
		return taskObjList;
	}
	
	@PutMapping(path="/update")
	public @ResponseBody Task updateTask(@RequestBody TaskObj task){
		
		Task taskObj = taskRepo.findOne(task.getTaskId());
		taskObj.setParentId(task.getParentTaskId());
		taskObj.setProjectId(task.getProjectId());
		taskObj.setTask(task.getTaskName());
		taskObj.setStartDate(task.getStartDate());
		taskObj.setEndDate(task.getEndDate());
		taskObj.setPriority(task.getPriority());
		taskObj.setStatus(task.getStatus());     
		return taskRepo.save(taskObj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteTask(@PathVariable("id") Integer id){
         taskRepo.delete(id);
	     return "return";
		
	}
	
	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
	public @ResponseBody Iterable<TaskObj> getTasksByProject(@PathVariable("id") Integer id){
 
		List<TaskObj> taskObjList = new ArrayList<>();
		List<Task> taskList = taskRepo.findAllByProjectId(id);
		for(Task t: taskList){
			TaskObj taskObj = new TaskObj();
			taskObj.setTaskId(t.getTaskId());
			taskObj.setParentTaskId(t.getParentId());
			taskObj.setProjectId(t.getProjectId());
			taskObj.setTaskName(t.getTask());
			taskObj.setStartDate(t.getStartDate());
			taskObj.setEndDate(t.getEndDate());
			taskObj.setPriority(t.getPriority());
			taskObj.setStatus(t.getStatus());
			taskObj.setUserId(t.getUserId());
			if(t.getParentId() != null){
				ParentTask pTask = repo.findOne(t.getParentId());
				if(pTask != null){
					taskObj.setParentTaskName(pTask.getParentTask());
				}
			}
			if(t.getProjectId() != null){
				Project p = projectRepo.findOne(t.getProjectId());
				if(p != null){
					taskObj.setProjectName(p.getProject());
				}	
			}
			if(t.getUserId() != null){
				User u = userRepo.findOne(t.getUserId());
				if(u != null){
					taskObj.setUserName(u.getFirstName());
				}
			}
			
			taskObjList.add(taskObj);
			
		}
		return taskObjList;
		
	}

}
