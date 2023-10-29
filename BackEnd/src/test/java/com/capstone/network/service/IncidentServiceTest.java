//package com.capstone.network.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.capstone.network.DTO.ForwardingRequest;
//import com.capstone.network.entities.Incident;
//import com.capstone.network.exception.ConflictException;
//import com.capstone.network.exception.NotFoundException;
//import com.capstone.network.repository.IncidentRepository;
//import com.capstone.network.service.EmailServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//class IncidentServiceTest {
//
//	@Mock
//	private IncidentRepository incidentRepository;
//
//	@Mock
//	private EmailServiceImpl emailService;
//
//	@InjectMocks
//	private IncidentService incidentService;
//
//
//
//	@Test
//	void testDetermineAssignmentGroup() {
//		IncidentService incidentService = new IncidentService();
//
//		// Test for each case
//		assertEquals("HardwareGroup", incidentService.determineAssignmentGroup("hardware"));
//		assertEquals("SoftwareGroup", incidentService.determineAssignmentGroup("software"));
//		assertEquals("Connectivity Issues", incidentService.determineAssignmentGroup("Connectivity Support"));
//		assertEquals("Security Operations Center", incidentService.determineAssignmentGroup("Security Issues"));
//
//		// Test for unknown issue type
//		assertEquals("DefaultGroup", incidentService.determineAssignmentGroup("unknownType"));
//	}
//
//	@Test
//	void testGetIncidentsByAssignmentGroup() {
//		// Arrange
//		String assignmentGroup = "TestGroup";
//		List<Incident> mockIncidents = Arrays.asList(new Incident(), new Incident());
//		when(incidentRepository.findByAssignmentGroup(assignmentGroup)).thenReturn(mockIncidents);
//
//		// Act
//		List<Incident> result = incidentService.getIncidentsByAssignmentGroup(assignmentGroup);
//
//		// Assert
//		assertEquals(mockIncidents, result);
//	}
//
//	@Test
//	void testFindById() {
//		// Arrange
//		Long incidentId = 1L;
//		Incident mockIncident = new Incident();
//		when(incidentRepository.findById(incidentId)).thenReturn(Optional.of(mockIncident));
//
//		// Act
//		Optional<Incident> result = incidentService.findById(incidentId);
//
//		// Assert
//		assertTrue(result.isPresent());
//		assertEquals(mockIncident, result.get());
//	}
//
//
//	@Test
//	public void testForwardIncident() {
//		// Arrange
//		Long incidentId = 1L;
//		ForwardingRequest forwardingRequest = new ForwardingRequest("TargetGroup", "Test message");
//
//		Incident originalIncident = new Incident();
//		originalIncident.setId(incidentId);
//
//		when(incidentRepository.findById(incidentId)).thenReturn(Optional.of(originalIncident));
//		when(incidentRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
//
//		// Act
//		Incident result = incidentService.forwardIncident(incidentId, forwardingRequest);
//
//		// Assert
//		assertTrue(result.isForwarded());
//		assertEquals("Forwarded", result.getStatus());
//		assertEquals(forwardingRequest.getMessage(), result.getForwardingmessage());
//		assertEquals(forwardingRequest.getTargetGroup(), result.getForwardedTo());
//
//		verify(incidentRepository, times(1)).findById(incidentId);
//		verify(incidentRepository, times(1)).save(originalIncident);
//	}
//
//	@Test
//	public void testForwardIncidentIncidentNotFound() {
//		// Arrange
//		Long incidentId = 1L;
//		ForwardingRequest forwardingRequest = new ForwardingRequest("TargetGroup", "Test message");
//
//		when(incidentRepository.findById(incidentId)).thenReturn(Optional.empty());
//
//		// Act and Assert
//		assertThrows(RuntimeException.class, () -> incidentService.forwardIncident(incidentId, forwardingRequest));
//
//		verify(incidentRepository, times(1)).findById(incidentId);
//		verify(incidentRepository, never()).save(any());
//	}
// 
//
//	    @Test
//	    public void searchIncidentsById_Success() {
//	        // Arrange
//	        Long id = 1L;
//	        IncidentService incidentService = mock(IncidentService.class);
//        when(incidentService.findById(id)).thenReturn(Optional.of(new Incident(id, "Test Incident")));
// 
//	        ResponseEntity<List<Incident>> response = incidentService.searchIncidents(id, null);
// 
//	        assertEquals(HttpStatus.OK, response.getStatusCode());
//	        assertEquals(1, response.getBody().size());
//	        // Add more assertions based on your actual implementation and data
//	    }
//
//	    @Test
//	    public void searchIncidentsByUserName_Success() {
//	        // Arrange
//	        String userName = "testUser";
//	        IncidentService incidentService = mock(IncidentService.class);
//	        when(incidentService.getIncidentsByUser(userName)).thenReturn(Collections.singletonList(new Incident(1L, "Test Incident")));
//
//	        // Act
//	        ResponseEntity<List<Incident>> response = incidentService.searchIncidents(null, userName);
//
//	        // Assert
//	        assertEquals(HttpStatus.OK, response.getStatusCode());
//	        assertEquals(1, response.getBody().size());
//	        // Add more assertions based on your actual implementation and data
//	    }
//
//	    
//	    @Test
//	    public void searchIncidentsWithBadRequest() {
//	        // Arrange
//	        IncidentService incidentService = new IncidentService(); // Assuming your service is not mockable
//
//	        // Act
//	        ResponseEntity<List<Incident>> response = incidentService.searchIncidents(null, null);
//
//	        // Assert
//	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//	        assertNull(response.getBody());
//	    }
//
//	    @Test
//	    public void searchIncidentsWithNotFound() {
//	        // Arrange
//	        Long id = 1L;
//	        IncidentService incidentService = mock(IncidentService.class);
//	        when(incidentService.findById(id)).thenThrow(NotFoundException.class);
//
//	        // Act
//	        ResponseEntity<List<Incident>> response = incidentService.searchIncidents(id, null);
//
//	        // Assert
//	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//	        assertNull(response.getBody());
//	    }
//	 
//
//	
//	@Test
//	void testGetIncidentsByUser() {
//		// Arrange
//		String userName = "TestUser";
//		List<Incident> mockIncidents = Arrays.asList(new Incident(), new Incident());
//		when(incidentRepository.findByUser(userName)).thenReturn(mockIncidents);
//
//		// Act
//		List<Incident> result = incidentService.getIncidentsByUser(userName);
//
//		// Assert
//		assertEquals(mockIncidents, result);
//	} 
//	@Test
//	public void testGetOtherIncidents() {
//		// Create a mock incidentRepository
//		IncidentRepository incidentRepository = mock(IncidentRepository.class);
//
//		// Create an instance of your service and inject the mock repository
//		IncidentService incidentService = new IncidentService();
//		incidentService.setIncidentRepository(incidentRepository);
//
//		// Create test data
//		Incident incident1 = new Incident();
//		incident1.setPriority("medium");
//		incident1.setSeverity("low");
//
//		Incident incident2 = new Incident();
//		incident2.setPriority("low");
//		incident2.setSeverity("medium");
//
//		// Mock the findByPriorityNotAndSeverityNot method of the incidentRepository
//		when(incidentRepository.findByPriorityNotAndSeverityNot("high", "high"))
//		.thenReturn(Arrays.asList(incident1, incident2));
//
//		// Call the method to be tested
//		List<Incident> result = incidentService.getOtherIncidents();
//
//		// Verify that the findByPriorityNotAndSeverityNot method is called once
//		verify(incidentRepository, times(1)).findByPriorityNotAndSeverityNot("high", "high");
//
//		// Assert the result
//		assertEquals(2, result.size());
//		assertTrue(result.contains(incident1));
//		assertTrue(result.contains(incident2));
//	}
//
//
//	// Similarly, you can write tests for other methods in the IncidentService class
//
//	// Example: Test for getForwardedIncidents method
//	@Test
//	void testGetForwardedIncidents() {
//		// Arrange
//		String group = "TestGroup";
//		List<Incident> mockIncidents = Arrays.asList(new Incident(), new Incident());
//		when(incidentRepository.findByForwardedToAndForwardedIsTrue(group)).thenReturn(mockIncidents);
//
//		// Act
//		List<Incident> result = incidentService.getForwardedIncidents(group);
//
//		// Assert
//		assertEquals(mockIncidents, result);
//	}
//
//	// Example: Test for getHighPriorityHighSeverityIncidents method
//	@Test
//	void testGetHighPriorityHighSeverityIncidents() {
//		// Arrange
//		List<Incident> mockIncidents = Arrays.asList(new Incident(), new Incident());
//		when(incidentRepository.findByPriorityAndSeverity("high", "high")).thenReturn(mockIncidents);
//
//		// Act
//		List<Incident> result = incidentService.getHighPriorityHighSeverityIncidents();
//
//		// Assert
//		assertEquals(mockIncidents, result);
//	}
//
//	@Test
//	void testGetIncidentsByStatus() {
//		// Arrange
//		String status = "Open";
//		List<Incident> mockIncidents = Arrays.asList(new Incident(), new Incident());
//
//		// Stub the repository method to return the mock incidents
//		when(incidentRepository.findByStatus(status)).thenReturn(mockIncidents);
//
//		// Act
//		List<Incident> result = incidentService.getIncidentsByStatus(status);
//
//		// Assert
//		assertEquals(mockIncidents, result);
//	}
//
//	@Test
//	void testUpdateIncidentResolution() throws NotFoundException {
//		// Arrange
//		Long incidentId = 1L;
//		String resolution = "Resolved the incident";
//
//		Incident mockIncident = new Incident();
//		mockIncident.setId(incidentId);
//
//		// Stub the repository method to return the mock incident
//		when(incidentRepository.findById(incidentId)).thenReturn(Optional.of(mockIncident));
//
//		// Act
//		assertDoesNotThrow(() -> incidentService.updateIncidentResolution(incidentId, resolution));
//
//		// Assert
//		assertEquals(resolution, mockIncident.getResolution());
//		assertEquals("Resolved", mockIncident.getStatus());
//
//		// Verify that the save method is called once
//		verify(incidentRepository, times(1)).save(mockIncident);
//
//		// Verify that the sendResolutionMail method is called once
//		verify(emailService, times(1)).sendResolutionMail(incidentId, mockIncident.getUserEmailId(), resolution);
//	}
//
//
//
//
//}
