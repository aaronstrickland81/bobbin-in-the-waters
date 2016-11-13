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
    public Timeout globalTimeout = new Timeout(5000L, TimeUnit.MILLISECONDS);

    @Before
    public void init() {

    }

    @Test
    public void testDBInit() {
        boolean userExists = UserInfoTable.checkUserExists("kt456");
        Assert.assertFalse(userExists);
        Assert.assertFalse("Test user with username \"kt456\"" +
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
