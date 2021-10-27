package com.cvs.poc.SpringMongoPoc;

import com.cvs.poc.SpringMongoPoc.controller.PersonController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringMongoPocApplicationTests {

	@Autowired
	private PersonController personController;

	@Test
	void contextLoads() {
		assertThat(personController).isNotNull();
	}

}
