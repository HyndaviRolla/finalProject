package com.capstone.network.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
 

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InternalServerErrorExceptionTest {

    @Test
    public void testConstructorAndGetMessage() {
        // Arrange
        String errorMessage = "This is a internalException  message";

        // Act
        InternalServerErrorException internalException = new InternalServerErrorException(errorMessage);

        // Assert
        assertEquals(errorMessage, internalException.getMessage());
    } 
}
