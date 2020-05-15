package test;

import domain.AuthenticationManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticationManagerTest {

    AuthenticationManager authenticationManager;

    @Before
    public void setUp() throws Exception {
        authenticationManager = new AuthenticationManager();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCurrentUser() {
        assertTrue(authenticationManager.login("admin", "admin"));
        assertEquals("admin", authenticationManager.getCurrentUser().getUsername());
    }

    @Test
    public void login() {
        assertTrue(authenticationManager.login("admin", "admin"));
    }

    @Test
    public void checkPermission() {
        assertTrue(authenticationManager.login("producer", "producer"));
        assertFalse(authenticationManager.checkPermission());
    }
}