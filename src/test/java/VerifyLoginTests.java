import model.User;
import org.junit.Assert;
import org.junit.Test;
import services.UserInfoTable;

/**
 * Created by Kavish on 11/13/2016.
 */
public class VerifyLoginTests {

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
    public void testUnregisteredUser() {
        User u = UserInfoTable.verifyUser("randomUser", "password");
        Assert.assertNull("That is a valid login", u);
    }
}
