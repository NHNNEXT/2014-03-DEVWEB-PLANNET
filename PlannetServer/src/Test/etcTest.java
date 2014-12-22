package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
