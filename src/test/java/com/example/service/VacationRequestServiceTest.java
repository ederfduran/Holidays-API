package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.entity.Employee;
import com.example.entity.VacationRequest;
import com.example.entity.VacationRequest.Status;
import com.example.repository.EmployeeRepository;
import com.example.repository.ManagerRepository;
import com.example.repository.VacationRequestRepository;
import com.example.request.UpdateVacationRequest;

@ExtendWith(MockitoExtension.class)
public class VacationRequestServiceTest {
	
	@Mock
	private VacationRequestRepository vacationRequestRepository;
	@Mock
	private EmployeeRepository employeeRepository;
	@Mock
	private ManagerRepository managerRepository;
	
	private VacationRequestService vacationRequestService;
	
	@Captor
    private ArgumentCaptor<VacationRequest> vacationRequestArgumentCaptor;
	
	private Date stringToDate(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {

            Date date = formatter.parse(dateInString);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	@BeforeEach
	public void setup() {
		vacationRequestService = new VacationRequestService();
		vacationRequestService.setVacationRequestRepository(vacationRequestRepository);
		vacationRequestService.setEmployeeRepository(employeeRepository);
		vacationRequestService.setManagerRepository(managerRepository);
	}
	
	@Test
	@DisplayName("Get Employee Vacation request by passing employee id and vacation request id")
	void getEmployeeVacationRequestEmployeeIdValid() {
		Long employeeId = 123L;
		Long vacationId = 456L;
		
		Employee employee = new Employee();
		VacationRequest vacationRequest = new VacationRequest();
		vacationRequest.setId(vacationId);
		
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		Mockito.when(vacationRequestRepository.findByAuthorAndId(employee, vacationId)).thenReturn(vacationRequest);
		
		VacationRequest actualResponse = vacationRequestService.getEmployeeVacationRequest(employeeId, vacationId);
		
		Assertions.assertThat(actualResponse).isNotNull();
		Assertions.assertThat(actualResponse.getId()).isEqualTo(vacationId);
	}
	
	@Test
	@DisplayName("Get Employee Vacation request by passing invalid employee id and vacation request id must return null")
	void getEmployeeVacationRequestEmployeeIdInvalid() {
		Long employeeId = 123L;
		Long vacationId = 456L;
		VacationRequest vacationRequest = new VacationRequest();
		vacationRequest.setId(vacationId);
		
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
		VacationRequest actualResponse = vacationRequestService.getEmployeeVacationRequest(employeeId, vacationId);
		
		Assertions.assertThat(actualResponse).isNull();
	}
	
	@Test
	@DisplayName("Update EmployeeVacationRequest with invalid EmployeeId")
	void updateEmployeeVacationRequestEmployeeIdInvalid() {
		Long employeeId = 123L;
		Long vacationId = 456L;
		UpdateVacationRequest updateVacationRequest = new UpdateVacationRequest();
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
		Pair<VacationRequest, String> actualResponse = vacationRequestService.updateEmployeeVacationRequest(updateVacationRequest, employeeId, vacationId);
		
		Assertions.assertThat(actualResponse.getValue0()).isNull();
		Assertions.assertThat(actualResponse.getValue1()).hasToString("EmployeeId invalid "+ employeeId);
	}
	
	@Test
	@DisplayName("Update EmployeeVacationRequest with invalid vacationIdInvalid")
	void updateEmployeeVacationRequestIdInvalid() {
		Long employeeId = 123L;
		Long vacationId = 456L;
		Employee employee = new Employee();
		UpdateVacationRequest updateVacationRequest = new UpdateVacationRequest();
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		Mockito.when(vacationRequestRepository.findByAuthorAndId(employee, vacationId)).thenReturn(null);
		
		Pair<VacationRequest, String> actualResponse = vacationRequestService.updateEmployeeVacationRequest(updateVacationRequest, employeeId, vacationId);
		
		Assertions.assertThat(actualResponse.getValue0()).isNull();
		Assertions.assertThat(actualResponse.getValue1()).hasToString("Vacation RequestId invalid " + vacationId);
	}
	
	@Test
	@DisplayName("Update EmployeeVacationRequest with Inconsistent dates(start date greather than end date)")
	void updateEmployeeVacationRequestInconsistentDates() {
		Long employeeId = 123L;
		Long vacationId = 456L;
		
		Employee employee = new Employee();
		VacationRequest vacationRequest = new VacationRequest();
		vacationRequest.setId(vacationId);
		UpdateVacationRequest updateVacationRequest = new UpdateVacationRequest();
		Date vacationStartDate= this.stringToDate("2020-02-2");
		Date vacationEndDate= this.stringToDate("2020-02-1");
		updateVacationRequest.setVacationStartDate(vacationStartDate);
		updateVacationRequest.setVacationEndDate(vacationEndDate);
	    
		
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		Mockito.when(vacationRequestRepository.findByAuthorAndId(employee, vacationId)).thenReturn(vacationRequest);
		
		Pair<VacationRequest, String> actualResponse = vacationRequestService.updateEmployeeVacationRequest(updateVacationRequest, employeeId, vacationId);
		
		Assertions.assertThat(actualResponse.getValue0()).isNull();
		Assertions.assertThat(actualResponse.getValue1()).hasToString("Iconsistent dates");
	}
	
	@Test
	@DisplayName("Update EmployeeVacationRequest with Invalid ManagerId")
	void updateEmployeeVacationRequestInvalidManagerId() {
		Long employeeId = 123L;
		Long vacationId = 456L;
		Long managerId = 789L;
		
		Employee employee = new Employee();
		VacationRequest vacationRequest = new VacationRequest();
		vacationRequest.setId(vacationId);
		UpdateVacationRequest updateVacationRequest = new UpdateVacationRequest();
		Date vacationStartDate= this.stringToDate("2020-02-2");
		Date vacationEndDate= this.stringToDate("2020-03-1");
		updateVacationRequest.setVacationStartDate(vacationStartDate);
		updateVacationRequest.setVacationEndDate(vacationEndDate);
		updateVacationRequest.setResolvedBy(managerId);
	    
		
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		Mockito.when(vacationRequestRepository.findByAuthorAndId(employee, vacationId)).thenReturn(vacationRequest);
		Mockito.when(managerRepository.findById(managerId)).thenReturn(Optional.empty());
		
		Pair<VacationRequest, String> actualResponse = vacationRequestService.updateEmployeeVacationRequest(updateVacationRequest, employeeId, vacationId);
		
		Assertions.assertThat(actualResponse.getValue0()).isNull();
		Assertions.assertThat(actualResponse.getValue1()).hasToString("resolvedBy invalid "+  managerId);
	}
	
	@Test
	@DisplayName("Update EmployeeVacationRequest when Employee doesn't have enough days available")
	void updateEmployeeVacationRequestNoDaysAvailable() {
		Long employeeId = 123L;
		Long vacationId = 456L;
		
		Date employeeStartDate= this.stringToDate("2021-03-18");
		Employee employee = new Employee();
		employee.setStartDate(employeeStartDate);;
		
		VacationRequest vacationRequest = new VacationRequest();
		vacationRequest.setId(vacationId);
		UpdateVacationRequest updateVacationRequest = new UpdateVacationRequest();
		Date vacationStartDate= this.stringToDate("2021-06-02");
		Date vacationEndDate= this.stringToDate("2021-06-25");
		updateVacationRequest.setVacationStartDate(vacationStartDate);
		updateVacationRequest.setVacationEndDate(vacationEndDate);
	    
		
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		Mockito.when(vacationRequestRepository.findByAuthorAndId(employee, vacationId)).thenReturn(vacationRequest);
		
		Pair<VacationRequest, String> actualResponse = vacationRequestService.updateEmployeeVacationRequest(updateVacationRequest, employeeId, vacationId);
		
		Assertions.assertThat(actualResponse.getValue0()).isNull();
		Mockito.verify(vacationRequestRepository, Mockito.times(0)).save(vacationRequestArgumentCaptor.capture());
		Assertions.assertThat(actualResponse.getValue1()).hasToString("No Vacations available");
	}
	
	@Test
	@DisplayName("Update EmployeeVacationRequest applied Successfully")
	void updateEmployeeVacationRequestSuccessfull() {
		Long employeeId = 123L;
		Long vacationId = 456L;
		
		Date employeeStartDate= this.stringToDate("2019-03-18");
		Employee employee = new Employee();
		employee.setStartDate(employeeStartDate);;
		
		VacationRequest vacationRequest = new VacationRequest();
		vacationRequest.setId(vacationId);
		UpdateVacationRequest updateVacationRequest = new UpdateVacationRequest();
		Date vacationStartDate= this.stringToDate("2021-06-02");
		Date vacationEndDate= this.stringToDate("2021-06-25");
		Status status = Status.approved;
		updateVacationRequest.setVacationStartDate(vacationStartDate);
		updateVacationRequest.setVacationEndDate(vacationEndDate);
		updateVacationRequest.setStatus(status.name());
		
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		Mockito.when(vacationRequestRepository.findByAuthorAndId(employee, vacationId)).thenReturn(vacationRequest);
		
		Pair<VacationRequest, String> actualResponse = vacationRequestService.updateEmployeeVacationRequest(updateVacationRequest, employeeId, vacationId);
		
		Assertions.assertThat(actualResponse.getValue1()).isNull();
		Assertions.assertThat(employee.getVacationDaysTaken()).isGreaterThan(0);
		Mockito.verify(vacationRequestRepository, Mockito.times(1)).save(vacationRequestArgumentCaptor.capture());
	}
	
}
