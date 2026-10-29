package com.forum.sprintbootadmin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SprintBootAdminApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SprintBootAdminApplicationTests {

	@Autowired
	private TestRestTemplate rest;

	@Test
	public void registersAndListsForumApp() {
		Map<String, String> forum = new HashMap<>();
		forum.put("name", "forum");
		forum.put("healthUrl", "http://localhost:8080/actuator/health");

		ResponseEntity<String> registered = rest.postForEntity("/instances", forum, String.class);
		ResponseEntity<String> listed = rest.getForEntity("/instances", String.class);

		assertEquals(HttpStatus.CREATED, registered.getStatusCode());
		assertTrue(listed.getBody().contains("\"name\":\"forum\""));
	}

}
