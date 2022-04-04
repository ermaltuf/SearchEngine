package com.project.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidationClassTest {

    @Test
    @DisplayName("Should check each token list before going to store in DB")
    void shouldValidateTokenIndex() {
        String[] test1 = {"albania", "russia", "France", "Britain", "USA", "Canada"};
        String[] test2 = {"Macedonia", "Greece", "montecarlo", "dontknow", "belgium", "southAfrica"};
        String[] test3 = {"cottoDivore2", "cristianoronaldo", "bellarus", "ukrahine", "USA", "Canada"};
        Set<String> setOfTerms = new HashSet<>(Arrays.asList(test1));
        Set<String> setOfTerms1 = new HashSet<>(Arrays.asList(test2));
        Set<String> setOfTerms3 = new HashSet<>(Arrays.asList(test3));

        Assertions.assertAll(() -> assertTrue(ValidationClass.validateTokenIndex(setOfTerms)),
                () -> assertTrue(ValidationClass.validateTokenIndex(setOfTerms1)),
                () -> assertTrue(ValidationClass.validateTokenIndex(setOfTerms3))
        );
    }

    @Test
    @DisplayName("Should check the token/tokenlist that is going to be searched")
    void shouldValidateTokenQuery() {
        String[] test1 = {"albania", "russia", "France", "Britain", "USA", "Canada"};
        String[] test2 = {"Macedonia", "Greece", "montecarlo", "dontknow", "belgium", "southAfrica"};
        String[] test3 = {"cottoDivore2", "cristianoronaldo", "bellarus", "ukrahine", "USA", "Canada"};
        ArrayList<String> setOfTerms = new ArrayList<>(Arrays.asList(test1));
        ArrayList<String> setOfTerms1 = new ArrayList<>(Arrays.asList(test2));
        ArrayList<String> setOfTerms3 = new ArrayList<>(Arrays.asList(test3));

        Assertions.assertAll(() -> assertTrue(ValidationClass.validateToken(setOfTerms)),
                () -> assertTrue(ValidationClass.validateToken(setOfTerms1)),
                () -> assertTrue(ValidationClass.validateToken(setOfTerms3))
        );
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Should check the value of the document ID")
    @ValueSource(ints = {3, 4, 5, 1, 1011, 111111, Integer.MAX_VALUE})
    void shouldValidateID(int expectedValues) {
        assertEquals(true, ValidationClass.validateID(expectedValues));
    }
}