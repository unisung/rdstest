package org.zerock.apprds.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController//restAPI
@RequestMapping("/api/time")
@Log4j2 //log출력
@RequiredArgsConstructor//생성자 자동생성
public class TimeController {
	
	private final DataSource dataSource;
	
	@GetMapping("/now") /* localhost:8080/api/time/now */
	public Map<String, String> getNow(){
		String now="";
		try(Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select now()");
				ResultSet resultSet = preparedStatement.executeQuery();	
					){
				resultSet.next();//ResultSetMetadata -> ResultSet데이타 부분으로 이동.
			    now = resultSet.getString(1);	
				log.info("NOW: " + now);
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		   return Map.of("NOW", now);
	}
}
