package com.wojewodka.inteca.model.family;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wojewodka.inteca.services.repository.RepositoryImpl;
import com.wojewodka.inteca.services.repository.RepositorySearch;

@Repository
public class ChildRepository extends RepositoryImpl<Child> {

	public List<Child> findByFamily(int familyId) {
		RepositorySearch rs = new RepositorySearch();
		rs.where("family_id", familyId);
		return find(rs);
	}

}
