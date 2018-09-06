/**
 * 
 */
package com.wojewodka.inteca.services.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojewodka.inteca.model.family.Family;
import com.wojewodka.inteca.model.family.FamilyRepository;
import com.wojewodka.inteca.services.repository.RepositoryImpl;

@Service
public class FamilyService implements FamilyMemberService<Family> {

	@Autowired
	private FamilyRepository familyRepository;

	@Override
	public RepositoryImpl<Family> getRepository() {
		return familyRepository;
	}

	@Override
	public String getMemberCode() {
		return "family";
	}

}
