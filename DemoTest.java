import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The DemoTest class contains test cases for the User class.
 *
 * @author  Melek, Asliddin
 * @version 3.0
 */ 
 
public class DemoTest {

    /**
     * Test case for the User.login() method.
     * It checks if the login method behaves as expected.
     */
    @Test
    public void testLogin() {
        // Create User objects
        User user = new User("ai", "Farid", "ai");
        User user1 = new User("ai", "Farid", "ai");
        User user2 = User.login("a1", "ai");

        // Check if user names match
        assertEquals(user.getName(), user1.getName());

        // Check if the login method returns null when invalid credentials are provided
        assertEquals(null, user2);
    }
}
