package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.request.CreateManagerRequest;
import com.example.request.UpdateManagerRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "manager")
public class Manager extends EntityUtil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	public Manager(CreateManagerRequest createManagerRequest) {
		this.name = createManagerRequest.getName();
	}
	
	public void updateManager(UpdateManagerRequest updateManagerRequest) {
		// This might be no neeeded as there is only one possible value to update
		// and it is mandatory however this could change in the future
		if (this.exists(updateManagerRequest.getName())) {
			this.setName(updateManagerRequest.getName());
		}
		
	}

}
