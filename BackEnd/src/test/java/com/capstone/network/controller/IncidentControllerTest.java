// package com.capstone.network.controller; import com.capstone.network.DTO.ForwardingRequest;
// import com.capstone.network.entities.Component;
// import com.capstone.network.entities.Incident;
// import com.capstone.network.entities.NetworkElement;
// import com.capstone.network.exception.BadRequestException;
// import com.capstone.network.exception.InternalServerErrorException;
// import com.capstone.network.service.ComponentService;
// import com.capstone.network.service.IncidentService;
// import com.capstone.network.service.NetworkElementService;
// import com.capstone.network.service.UserService;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
//
// import java.util.Collections;
// import java.util.List;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.Mockito.*;
//
// class IncidentControllerTest {
//
//     @Mock
//     private IncidentService incidentService;
//
//     @Mock
//     private ComponentService componentService;
//
//     @Mock
//     private UserService userService;
//
//     @Mock
//     private NetworkElementService networkElementService;
//
//     @InjectMocks
//     private IncidentController incidentController;
//
//     @Test
//     void createIncident_ValidIncident_ReturnsOkResponse() {
//         // Arrange
//         Incident incident = new Incident();
//         when(incidentService.saveIncident(incident)).thenReturn(incident);
//
//         // Act
//         ResponseEntity<Incident> response = incidentController.createIncident(incident);
//
//         // Assert
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(incident, response.getBody());
//     }
//
//     @Test
//     void createIncident_InvalidIncident_ReturnsBadRequestResponse() {
//         // Arrange
//         Incident invalidIncident = null;
//
//         // Act
//         ResponseEntity<Incident> response = incidentController.createIncident(invalidIncident);
//
//         // Assert
//         assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//     }
//
//     @Test
//     void createIncident_ServiceThrowsException_ReturnsInternalServerErrorResponse() {
//         // Arrange
//         Incident incident = new Incident();
//         when(incidentService.saveIncident(incident)).thenThrow(new RuntimeException("Some error"));
//
//         // Act & Assert
//         assertThrows(InternalServerErrorException.class, () -> incidentController.createIncident(incident));
//     }
//
//     @Test
//     void getNetworkElements_ValidSearch_ReturnsOkResponse() {
//         // Arrange
//         String search = "search";
//         List<NetworkElement> networkElements = Collections.singletonList(new NetworkElement());
//         when(networkElementService.searchNetworkElements(search)).thenReturn(networkElements);
//
//         // Act
//         ResponseEntity<List<NetworkElement>> response = incidentController.getNetworkElements(search);
//
//         // Assert
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(networkElements, response.getBody());
//     }
//
//     @Test
//     void getNetworkElements_ServiceThrowsException_ReturnsInternalServerErrorResponse() {
//         // Arrange
//         String search = "search";
//         when(networkElementService.searchNetworkElements(search)).thenThrow(new RuntimeException("Some error"));
//
//         // Act & Assert
//         assertThrows(InternalServerErrorException.class, () -> incidentController.getNetworkElements(search));
//     }
//
//     // Similar tests for other methods
// }
//
