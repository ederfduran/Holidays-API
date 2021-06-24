package com.example.service;

import java.util.Optional;

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
	
	private Manager getById(Long id) {
		Optional<Manager> manager = managerRepository.findById(id);
		if (manager.isEmpty()) {
			return null;
		}
		return manager.get();
	}
	
	public Manager createManager(CreateManagerRequest createManagerRequest) {
		Manager manager = new Manager(createManagerRequest);
		return managerRepository.save(manager);
	}
	
	public Manager updateManager(UpdateManagerRequest updateManagerRequest, Long id) {
		Manager manager = this.getById(id);
		if (manager == null)
			return null;
		manager.updateManager(updateManagerRequest);
		return managerRepository.save(manager);
	}
	
	public Manager getManager(Long id) {
		return this.getById(id);
	}
	
	
	public String deleteManager(Long id) {
		Manager manager = this.getById(id);
		if (manager == null)
			return null;
		managerRepository.deleteById(id);
		return "Manager has been deleted";
	}


}
