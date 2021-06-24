package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Employee;
import com.example.entity.VacationRequest;
import com.example.entity.VacationRequest.Status;

@Repository
public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {
	
	List<VacationRequest> findByAuthor(Employee author);
	
	List<VacationRequest> findByStatus(Status status, Pageable pageable);
	
	VacationRequest findByAuthorAndId(Employee author, Long id);
	
	List<VacationRequest> findByAuthorAndStatus(Employee author, Status status);
	
	@Modifying
	@Transactional
	@Query("Delete From VacationRequest where author = :author and id = :id")
	Integer deleteByAuthorAndId(Employee author, Long id); 
	
	@Query(value = "with vr as (select *, lag(vacation_end_date) over (order by vacation_start_date) as end_temp, "
			+ "lead(vacation_start_date) over (order by vacation_start_date) as start_temp from vacation_request) "
			+ "select id, author, status, resolved_by, request_created_at, vacation_start_date, vacation_end_date from vr "
			+ "where (vacation_start_date < end_temp or vacation_end_date > start_temp) and status=?1", nativeQuery=true)
	List<VacationRequest> getOverlapVacationRequestsByStatus(String status, Pageable pageable);
	
	@Query(value = "with vr as (select *, lag(vacation_end_date) over (order by vacation_start_date) as end_temp, "
			+ "lead(vacation_start_date) over (order by vacation_start_date) as start_temp from vacation_request) "
			+ "select id, author, status, resolved_by, request_created_at, vacation_start_date, vacation_end_date from vr "
			+ "where vacation_start_date < end_temp or vacation_end_date > start_temp", nativeQuery=true)
	List<VacationRequest> getOverlapVacationRequests(Pageable pageable);
	
}
