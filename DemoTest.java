import static org.junit.Assert.*;

import org.junit.Test;

public class DemoTest {

    @Test
    public void testLogin(){
        User user = new User("ai", "Farid", "ai");
        User user1 = new User("ai", "Farid", "ai");
        User user2 = User.login("a1", "ai");
        assertEquals(user.getName(), user1.getName());
        assertEquals(null, user2);
    }
}
