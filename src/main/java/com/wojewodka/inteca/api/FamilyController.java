package com.wojewodka.inteca.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wojewodka.inteca.model.family.Family;
import com.wojewodka.inteca.model.family.FamilyRepository;
import com.wojewodka.inteca.services.database.DBAWrapper;
import com.wojewodka.inteca.services.dbo.DatabaseObject;

@RestController
@RequestMapping(value = "/family")
public class FamilyController {

	@Autowired
	private FamilyRepository repo;

	@Autowired
	private DBAWrapper wrapper;

	@GetMapping("/test")
	public Family getFamily() throws Exception {
		wrapper.run(con -> {

			return null;
		});
		Family family = new Family();
		family.setFatherId(11123);
		repo.save(family);
		DatabaseObject rs = repo.findById(family.getId());
		System.out.println(rs);
		return family;
	}

}
