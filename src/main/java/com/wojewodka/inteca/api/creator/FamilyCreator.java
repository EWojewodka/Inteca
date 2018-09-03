/**
 * 
 */
package com.wojewodka.inteca.api.creator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojewodka.inteca.common.AjaxException;
import com.wojewodka.inteca.model.family.Child;
import com.wojewodka.inteca.model.family.ChildRepository;
import com.wojewodka.inteca.model.family.Family;
import com.wojewodka.inteca.model.family.FamilyRepository;
import com.wojewodka.inteca.model.family.Father;
import com.wojewodka.inteca.model.family.FatherRepository;
import com.wojewodka.inteca.model.request.ChildRequestModel;
import com.wojewodka.inteca.model.request.FamilyRequestModel;
import com.wojewodka.inteca.services.database.DBAWrapper;

/**
 * @author Emil Wojewódka
 *
 * @since 3 wrz 2018
 */
@Service
public class FamilyCreator {

	@Autowired
	private DBAWrapper dba;

	@Autowired
	private FatherRepository fatherRepo;

	@Autowired
	private FamilyRepository familyRepo;

	@Autowired
	private ChildRepository childRepo;

	public Family createFamilyFromRequest(FamilyRequestModel model) {
		System.out.println(model);
		Family result = dba.run(con -> {
			Father father = new Father(model.getFather());
			fatherRepo.save(father, con);
			Family family = new Family(father);
			familyRepo.save(family, con);
			List<ChildRequestModel> children = model.getChildren();
			if (children != null && !children.isEmpty()) {
				List<Child> _children = new ArrayList<>();
				for (ChildRequestModel reqChild : children) {
					Child child = new Child(reqChild, family.getId());
					childRepo.save(child, con);
					_children.add(child);
				}
				family.setChildren(_children);
			}
			return family;
		});

		if (result == null)
			throw new AjaxException("Internal error. Cannot create a family.",
					HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return result;
	}

}
