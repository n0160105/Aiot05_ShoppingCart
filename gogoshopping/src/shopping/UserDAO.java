package shopping;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/shopping?useSSL=false&serverTimezone=CST";
	private String jdbcUsername = "root";
	private String jdbcPassword = "12345678";

	private static final String SELECT_USER_BY_TOKEN = "select user from users where token=?;";
	private static final String SELECT_USER_BY_EMAIL = "select user, password from users where email=?;";
	private static final String SELECT_DATA_BY_USER  = "select user, email, password from users where user=?;";
	private static final String DELETE_TOKEN_BY_USER = "update users set token=? where user=?;";
	
	public UserDAO() {
	}

	protected Connection getConnection() {
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
		
	}

	public UserModel selectUserbyToken(String token) throws IOException {
		
		UserModel user = null;
		
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_TOKEN)) {
			
			preparedStatement.setString(1, token);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				user = new UserModel(rs.getString("user"));
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return user;
		
	}
	
	
	public UserModel selectUserbyEmail(String email) throws IOException {
		
		UserModel user = null;

		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
			
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				user = new UserModel(rs.getString("user"), rs.getString("password"));
				
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return user;
		
	}
	
	public UserModel selectDatabyUser(String username) throws IOException {
		
		UserModel user = null;

		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DATA_BY_USER)) {
			
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				user = new UserModel(rs.getString("user"), rs.getString("email"), rs.getString("password"));
				
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return user;
		
	}
	
	public void deleteToken(String name) throws IOException {
		
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TOKEN_BY_USER)) {

			preparedStatement.setString(1, " ");
			preparedStatement.setString(2, name);
			preparedStatement.executeUpdate();
				
		} catch (SQLException e) {
			printSQLException(e);
		}
		
	}
	
	private void printSQLException(SQLException ex) {
		
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
		
	}

}