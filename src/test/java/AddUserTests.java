import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

import services.UserInfoTable;
import model.User;

/**
 * JUnit tests for addUser method
 *
 * Created by Karthik Praturu on 11/12/2016.
 */
public class AddUserTests {

    @Rule
    public Timeout globalTimeout = new Timeout(20000L, TimeUnit.MILLISECONDS);

    @Before
    public void init() {

    }

    @After
    public void removeFromDB() {
        UserInfoTable.removeUser("kp45");
        boolean userExists = UserInfoTable.checkUserExists("kp45");

        Assert.assertEquals("User should be removed from database", false, userExists);
    }

    @Test
    public void testDBInit() {
        boolean userExists = UserInfoTable.checkUserExists("kp45");
        Assert.assertFalse("Test user with username \"kp45\"" +
                            " should not exist in database", userExists);
    }

    @Test
    public void testAddUser() {
        User testUser = new User("kp45", "karlthik");
        UserInfoTable.addUser(testUser);
        User userDB = UserInfoTable.getUserFromUserName("kp45");

        Assert.assertEquals("Added user should be equal to" +
                            " user stored in database", testUser, userDB);
    }

}
