package com.capstone.network.repository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capstone.network.entities.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
	
	@Query("SELECT i FROM Incident i WHERE i.forwardedTo = :group AND i.status IN ('Forwarded')")
	List<Incident> findByForwardedTo(String group); 
	 
	List<Incident> findByUser(String userName);
	
	@Query("SELECT i FROM Incident i WHERE i.slaEndTime >= :startOfDay AND i.slaEndTime < :endOfDay")
	List<Incident> findIncidentsDueToday(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
	
	List<Incident> findByStatus(String status);	 
	List<Incident> findByAssignmentGroupAndAssignedTo(String assignmentGroup, String assignedTo);
	@Query("SELECT i FROM Incident i WHERE i.assignedTo = :assignedTo AND i.status IN ('Assigned', 'InProgress')")
	List<Incident> findAssignedOrInProgressIncidentsForUser(String assignedTo);



}
