package com.wojewodka.inteca.api;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wojewodka.inteca.common.AjaxException;
import com.wojewodka.inteca.model.family.Father;
import com.wojewodka.inteca.model.family.FatherRepository;
import com.wojewodka.inteca.model.request.FamilyRequestModel;
import com.wojewodka.inteca.model.request.FatherRequestModel;
import com.wojewodka.inteca.services.database.DBAWrapper;

@RestController
@RequestMapping(value = "/api/family")
@CrossOrigin("*")
public class FamilyController {

	@Autowired
	private DBAWrapper dba;

	@Autowired
	private FatherRepository fatherRepo;

	@PostMapping("/create")
	public void createFamily(@Valid @RequestBody FamilyRequestModel model) {
		FatherRequestModel fatherReq = model.getFather();
		if (fatherReq == null)
			throw new AjaxException("Can't create family without father.", HttpServletResponse.SC_BAD_REQUEST);
		dba.run(con -> {
			Father father = new Father(fatherReq);
			fatherRepo.save(father, con);
			return null;
		});
	}
}
