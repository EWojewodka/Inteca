package com.wojewodka.inteca.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wojewodka.inteca.model.family.FamilyRepository;
import com.wojewodka.inteca.services.database.DBAWrapper;

@RestController
@RequestMapping(value = "/family")
public class FamilyController {


}
