package org.zerock.apprds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppRdsApplicationTests {
	
	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() {
		try(Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select now()");
			ResultSet resultSet = preparedStatement.executeQuery();	
				){
			resultSet.next();//ResultSetMetadata -> ResultSet데이타 부분으로 이동.
			System.out.println("현재시각: "+resultSet.getString(1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
