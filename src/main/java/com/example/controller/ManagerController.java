package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Manager;
import com.example.request.CreateManagerRequest;
import com.example.request.UpdateManagerRequest;
import com.example.response.ManagerResponse;
import com.example.service.ManagerService;

@RestController
@RequestMapping("/v1/managers")
public class ManagerController {
	
	@Autowired
	ManagerService managerService;
	
	@PostMapping("/")
	public ManagerResponse createManager(@Valid @RequestBody CreateManagerRequest createManagerRequest) {
		Manager manager = managerService.createManager(createManagerRequest);
		return new ManagerResponse(manager);
	}
	
	@GetMapping("/{id}")
	public ManagerResponse getManager(@PathVariable Long id){
		Manager manager = managerService.getManager(id);
		return new ManagerResponse(manager);
	}
	
	@PutMapping("/{id}")
	public ManagerResponse updateManager(@Valid @RequestBody UpdateManagerRequest updateManagerRequest, @PathVariable Long id) {
		Manager manager = managerService.updateManager(updateManagerRequest, id);
		return new ManagerResponse(manager);
	}
	
	@DeleteMapping("/{id}")
	public String deleteManager(@PathVariable Long id) {
		return managerService.deleteManager(id);
	}

}
