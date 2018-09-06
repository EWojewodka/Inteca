/**
 * 
 */
package com.wojewodka.inteca.services.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojewodka.inteca.model.family.Father;
import com.wojewodka.inteca.model.family.FatherRepository;
import com.wojewodka.inteca.services.repository.RepositoryImpl;

@Service
public class FatherService implements FamilyMemberService<Father> {

	@Autowired
	private FatherRepository fatherRepository;

	@Override
	public RepositoryImpl<Father> getRepository() {
		return fatherRepository;
	}

	@Override
	public String getMemberCode() {
		return "father";
	}

}
