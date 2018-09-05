package com.wojewodka.inteca.model.family;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojewodka.inteca.services.repository.RepositoryImpl;
import com.wojewodka.inteca.services.repository.RepositorySearch;

@Service
public class FamilyRepository extends RepositoryImpl<Family> {

	@Autowired
	private ChildRepository childRepo;

	@Autowired
	private FatherRepository fatherRepo;

	/**
	 * Find family by father id and load children and father
	 * 
	 * @param fatherId
	 * @return
	 */
	public Family findByFatherId(int fatherId) {
		RepositorySearch rs = new RepositorySearch();
		rs.where("father_id", fatherId).setLimit(1);
		List<Family> result = find(rs);
		Family family = result.isEmpty() ? null : result.get(0);
		if (family == null)
			return null;

		family.setChildren(childRepo.findByFamily(family.getId()));
		family.setFather(fatherRepo.findById(fatherId));
		return family;
	}

}
