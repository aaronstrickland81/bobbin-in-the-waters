import model.Model;
import model.User;
import model.enums.AccountType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.UserInfoTable;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test cases for the editUser method
 *
 * Created by Aaron Strickland on 11/12/2016.
 */
public class EditUserTests {

    private User testUser;

    @Before
    public void init() {
        testUser = new User("user123", "pass", AccountType.USER,
                "example@test.com", "fName", "lName");
        Model.addUser(testUser);
    }

    /**
     * Executed after every test. Asserts that the user was properly edited in
     * the database and then cleans up (removes the user from the database).
     */
    @After
    public void checkEqual() {
        Model.editUser(testUser);
        assertEquals(testUser, UserInfoTable.getUserFromUserName(
                testUser.getUname()));
        //TODO: remove user
    }

    @Test
    public void editPass() {
        testUser.setPassword("newPass");
    }

    @Test
    public void editAccountType() {
        testUser.setAccountType(AccountType.WORKER);
    }

    @Test
    public void editEmail() {
        testUser.setEmail("newExample@test.com");
    }

    @Test
    public void editFirstName() {
        testUser.setFname("newFName");
    }

    @Test
    public void editLastName() {
        testUser.setLname("newLName");
    }

    @Test
    public void editAddress() {
        testUser.setHomeAddress("1234 Example Lane");
    }

    @Test
    public void editTitle() {
        testUser.setTitle("Mr.");
    }
}