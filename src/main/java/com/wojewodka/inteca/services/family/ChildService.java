/**
 * 
 */
package com.wojewodka.inteca.services.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojewodka.inteca.model.family.Child;
import com.wojewodka.inteca.model.family.ChildRepository;
import com.wojewodka.inteca.services.repository.RepositoryImpl;

@Service
public class ChildService implements FamilyMemberService<Child> {

	@Autowired
	private ChildRepository childRepo;

	@Override
	public RepositoryImpl<Child> getRepository() {
		return childRepo;
	}

	@Override
	public String getMemberCode() {
		return "child";
	}

}
