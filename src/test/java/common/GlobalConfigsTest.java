package common;

import static org.junit.Assert.*;

import org.junit.Test;

public class GlobalConfigsTest {

	@Test
	public void test() {
//		GlobalConfigs.connect("au", "mama", "mea");
//		GlobalConfigs.toConnect();
		GlobalConfigs.getSessionFactory();
		GlobalConfigs.usersList();
//		fail("Not yet implemented");
	}

}
