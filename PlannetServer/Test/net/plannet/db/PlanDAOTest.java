package net.plannet.db;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class PlanDAOTest {

	@Test
	public void uuidTest(){
		try {
			new PlanDAO().saveUUID(3, "7c0555d9a0b448ab8da6c411f353be4d");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
