package org.mskcc.cgds.test.dao;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.mskcc.cgds.model.User;
import org.mskcc.cgds.dao.DaoUser;
import org.mskcc.cgds.scripts.ResetDatabase;

public class TestDaoUser extends TestCase {

   public void testDaoUser() throws Exception {
      ResetDatabase.resetDatabase();

      User user = new User("joe@mail.com", "Joe Smith", false);
      DaoUser.addUser(user);

      assertEquals(null, DaoUser.getUserByEmail("foo"));
      assertEquals(user, DaoUser.getUserByEmail("joe@mail.com"));
      assertFalse(user.isEnabled());

      User user2 = new User("jane@yahoo.com", "Jane Doe", false);
      DaoUser.addUser(user2);

      ArrayList<User> allUsers = DaoUser.getAllUsers();
      assertEquals(2, allUsers.size());

      DaoUser.deleteUser(user.getEmail());
      allUsers = DaoUser.getAllUsers();
      assertEquals(user2, allUsers.get(0));

      assertEquals(user2, DaoUser.getUserByEmail("jane@yahoo.com"));
   }
}