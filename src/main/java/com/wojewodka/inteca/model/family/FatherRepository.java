package com.wojewodka.inteca.model.family;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wojewodka.inteca.model.request.ChildRequestModel;
import com.wojewodka.inteca.services.repository.RepositoryImpl;
import com.wojewodka.inteca.services.repository.RepositorySearch;
import com.wojewodka.inteca.services.repository.SearchJoiner;
import com.wojewodka.inteca.services.repository.SearchJoiner.JoinType;

@Repository
public class FatherRepository extends RepositoryImpl<Father> {

	public List<Father> findByChildrenData(ChildRequestModel model) {
		RepositorySearch rs = new RepositorySearch();
		rs.setFromTableAlias("father")
			.setDistinct(true)
			.join(new SearchJoiner(Father.class, Family.class, "family", "family.father_id=father.father_id", JoinType.LEFT_JOIN))
			.join(new SearchJoiner(Father.class, Child.class, "child", "child.family_id=family.family_id", JoinType.LEFT_JOIN))
			.where("child.first_name", model.getFirstname(), true)
			.where("child.second_name", model.getSecondname(), true)
			.where("child.pesel", model.getPesel(), true)
			.where("child.sex", model.getSex(), true);
		
		return find(rs);
	}

}
