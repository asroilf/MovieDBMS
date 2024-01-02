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
        User user = new User("ai", "Farid", "ai");
        User user1 = new User("ai", "Farid", "ai");
        User user2 = User.login("a1", "ai");
        assertEquals(user.getName(), user1.getName());
        assertEquals(null, user2);
    }

    /**
     * Test case for MovieDatabase.retrieveMovie() method.
     * It checks if the method provides correct movie details from the database or not.
     */
    @Test
    public void testRetrieveMovie(){
        Movie movie = new Movie("Tenet", "Nolan", 2000, 200);
        Movie movie2 = MovieDatabase.retrieveMovie("Tenet");
        assertEquals(movie.getDirector(), movie2.getDirector());
        assertEquals(null, MovieDatabase.retrieveMovie("Maze Runner"));
    }
}
