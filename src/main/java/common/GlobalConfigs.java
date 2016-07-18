package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.mysql.cj.jdbc.PreparedStatement;

import javassist.convert.TransformWriteField;

public class GlobalConfigs {

	public static void main(String args[]) {
		// url = "jdbc:mysql://localhost:3306/toaleProj";
		// username = "root";
		// password = "MyNewPass";
		EntityManager em;
		toConnect();
		System.out.println("Connecting database...");

		// try (
		// Connection connection = DriverManager.getConnection(url, username,
		// password)) {
		// System.out.println("Database connected!");
		// } catch (SQLException e) {
		// throw new IllegalStateException("Cannot connect the database!", e);
		// }
	}

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/toaleProj";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "MyNewPass";

	public static SessionFactory getSessionFactory() {
		SessionFactory sf;
		try {
			sf = new Configuration()
					.configure(
							"D:\\_WORK\\eclipse_workspace\\toale\\toale-proj\\src\\main\\resources\\hibernate.cfg.xml")
					.buildSessionFactory();
		} catch (Throwable e) {
			System.out.println("SF error");
			throw new ExceptionInInitializerError(e);
		}
		return sf;
	}

	public static List usersList() {
		SessionFactory sf = getSessionFactory();
		Session session = sf.openSession();

		List users = session.createQuery("from user").list();
		session.close();
		return users;
	}

	public static void toConnect() {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id FROM user where name='adrian'";
			ResultSet rs = stmt.executeQuery(sql);

			String i = rs.toString();
			PreparedStatement pstm = null;
			System.out.println("in tabel sunt " + i + " rezultate");
			// String id = rs.getString("id");
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name

				String id = rs.getString("id");
				System.out.println("in tabel este " + id);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end main

	public static void connect(String url, String username, String password) {
		// TODO Auto-generated method stub
		url = "jdbc:mysql://localhost:3306/toaleProj";
		username = "root";
		password = "MyNewPass";

		System.out.println("Connecting database..." + url + username);

		// try (Connection connection = DriverManager.getConnection(url,
		// username, password)) {
		// System.out.println("Database connected!");
		// } catch (SQLException e) {
		// throw new IllegalStateException("Cannot connect the database!", e);
		// }
	}

}