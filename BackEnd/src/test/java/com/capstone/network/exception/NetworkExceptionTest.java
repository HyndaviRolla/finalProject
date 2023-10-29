package com.capstone.network.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NetworkExceptionTest {

    @Test
    public void testConstructorAndGetMessage() {
        // Arrange
        String errorMessage = "This is a network message";

        // Act
     NetworkException networkException = new NetworkException(errorMessage);
 
        assertEquals(errorMessage, networkException.getMessage());
    }

}
