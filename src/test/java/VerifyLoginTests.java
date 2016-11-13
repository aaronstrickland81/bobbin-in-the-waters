import model.User;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import services.UserInfoTable;

import java.util.concurrent.TimeUnit;

/**
 * Created by Kavish on 11/13/2016.
 */
public class VerifyLoginTests {

    @Rule
    public Timeout globalTimeout = new Timeout(5000L, TimeUnit.MILLISECONDS);

    @Test
    public void testVerifiedUser() {
        User u = UserInfoTable.verifyUser("user", "pass");
        Assert.assertNotNull("Not a valid login", u);

        u = UserInfoTable.verifyUser("worker", "pass");
        Assert.assertNotNull("Not a valid login", u);

        u = UserInfoTable.verifyUser("manager", "pass");
        Assert.assertNotNull("Not a valid login", u);
    }

    @Test
    public void testUnauthorizedAttempt() {
        User u = UserInfoTable.verifyUser("randomUser", "password");
        Assert.assertNull("That is a valid login", u);
    }

    @Test
    public void testIncorrectPassword() {
        User u = UserInfoTable.verifyUser("user", "wrongpass");
        Assert.assertNull("That is a correct password", u);
    }
}
