package com.capstone.network.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {
	  @Test
	    public void testConstructorAndGetMessage() {
	        // Arrange
	        String errorMessage = "This is a internalException  message";

	        // Act
	        NotFoundException notFoundException = new NotFoundException(errorMessage);

	        // Assert
	        assertEquals(errorMessage, notFoundException.getMessage());
	    } 

}
