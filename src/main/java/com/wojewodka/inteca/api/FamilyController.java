package com.wojewodka.inteca.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wojewodka.inteca.model.Family;

@RestController
@RequestMapping(value = "/family")
public class FamilyController {

	@Autowired
	private JdbcTemplate template;
	
	@PostMapping("/create-random")
	public Family createNewFamily() throws Exception{
		template.execute("CREATE TABLE inteca_families(id INTEGER);");
		return null;
	}
	
}
