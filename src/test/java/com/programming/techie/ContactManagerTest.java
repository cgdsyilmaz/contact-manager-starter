package com.programming.techie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//@Nested
class ContactManagerTest {

    ContactManager contactManager;

    @BeforeEach
    void setUp() {
        contactManager = new ContactManager();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void shouldCreateContact() {
        contactManager.addContact("John", "Doe", "0123456789");

        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
        assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe") &&
                        contact.getPhoneNumber().equals("0123456789")
                ));
    }

    @Test
    @DisplayName("Should not create contact when first name is null")
    public void shouldThrowRuntimeExceptionWhenFirstNameisNull() {
        assertThrows(RuntimeException.class, () -> {
           contactManager.addContact(null, "Doe", "0123456789");
        });
    }

    @Test
    @DisplayName("Should not create contact when last name is null")
    public void shouldThrowRuntimeExceptionWhenLastNameisNull() {
        assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John", null, "0123456789");
        });
    }

    @Test
    @DisplayName("Should not create contact when phone number is null")
    public void shouldThrowRuntimeExceptionWhenLPhoneNumberisNull() {
        assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John", "Doe", null);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0123456789", "0123456789"})
    public void shouldCreateContactParameterized(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);

        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
        assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe") &&
                        contact.getPhoneNumber().equals("0123456789")
                ));
    }

    @ParameterizedTest
    @MethodSource("generatePhoneNumberList")
    public void shouldCreateContactParameterizedWithMethodSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);

        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
        assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe") &&
                        contact.getPhoneNumber().equals("0123456789")
                ));
    }

    private static List<String> generatePhoneNumberList() {
        return Arrays.asList("0123456789", "0123456789", "0123456789");
    }
}