package com.wojewodka.inteca.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.wojewodka.inteca.api.creator.FamilyCreator;
import com.wojewodka.inteca.model.family.Family;
import com.wojewodka.inteca.model.request.FamilyRequestModel;

@RestController
@RequestMapping(value = "/api/family")
@CrossOrigin("*")
public class FamilyController {

	@Autowired
	private FamilyCreator familyCreator;

	@PostMapping("/create")
	@JsonView(ViewScope.Basic.class)
	public Family createFamily(@Valid @RequestBody FamilyRequestModel model) {
		return familyCreator.createFamilyFromRequest(model);
	}
}
