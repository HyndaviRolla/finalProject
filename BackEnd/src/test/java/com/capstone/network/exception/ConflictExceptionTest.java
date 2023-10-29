package com.capstone.network.exception;

import static org.junit.jupiter.api.Assertions.*;
import com.capstone.network.exception.ConflictException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConflictExceptionTest {

    @Test
    public void testConstructorAndGetMessage() {
        // Arrange
        String errorMessage = "This is a conflict message";

        // Act
        ConflictException conflictException = new ConflictException(errorMessage);

        // Assert
        assertEquals(errorMessage, conflictException.getMessage());
    }

    @Test
    public void testDefaultConstructor() {
        // Act
        ConflictException conflictException = new ConflictException();

      
        assertEquals(null, conflictException.getMessage());
    }

    // Add more tests as needed based on your specific requirements.
}
