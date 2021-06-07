package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Manager;
import com.example.repository.ManagerRepository;
import com.example.request.CreateManagerRequest;
import com.example.request.UpdateManagerRequest;

@Service
public class ManagerService {
	
	@Autowired
	ManagerRepository managerRepository;
	
	public Manager createManager(CreateManagerRequest createManagerRequest) {
		Manager manager = new Manager(createManagerRequest);
		return managerRepository.save(manager);
	}
	
	public Manager updateManager(UpdateManagerRequest updateManagerRequest, Long id) {
		Manager manager = managerRepository.findById(id).get();
		manager.updateManager(updateManagerRequest);
		return managerRepository.save(manager);
	}
	
	public Manager getManager(Long id) {
		return managerRepository.findById(id).get();
	}
	
	public String deleteManager(Long id) {
		managerRepository.deleteById(id);
		return "Manager has been deleted";
	}


}
