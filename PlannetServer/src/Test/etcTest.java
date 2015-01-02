package Test;

import java.util.ArrayList;

import net.plannet.db.SignUpDAO;
import net.plannet.dbutil.QuerySet;
import net.plannet.model.User;

import org.junit.Test;

public class etcTest {

	@Test
	public void test() {
		ArrayList<User> a = new SignUpDAO().selectQueryExecute(new QuerySet("select email, name, pw from user"),
				User.class);
		System.out.println("adsffdsa");
	}
}
