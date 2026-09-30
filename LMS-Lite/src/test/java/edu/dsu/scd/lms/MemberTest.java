package edu.dsu.scd.lms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @Test void storesRegistrationDetails() {
        Member m = new Member(" Alice ", " M01 ");
        assertEquals("Alice", m.getName());
        assertEquals("M01", m.getMemberId());
    }
    @Test void rejectsBlankDetails() {
        assertThrows(InvalidInputException.class, () -> new Member("", "M1"));
        assertThrows(InvalidInputException.class, () -> new Member("A", ""));
    }
}
