package com.wojewodka.inteca.api;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.wojewodka.inteca.api.creator.FamilyCreator;
import com.wojewodka.inteca.common.AjaxException;
import com.wojewodka.inteca.model.family.Child;
import com.wojewodka.inteca.model.family.Family;
import com.wojewodka.inteca.model.family.FamilyMember;
import com.wojewodka.inteca.model.family.FamilyRepository;
import com.wojewodka.inteca.model.family.Father;
import com.wojewodka.inteca.model.family.FatherRepository;
import com.wojewodka.inteca.model.request.ChildRequestModel;
import com.wojewodka.inteca.model.request.FamilyRequestModel;
import com.wojewodka.inteca.services.family.FamilyMemberService;
import com.wojewodka.inteca.services.family.FamilyMemberServiceFactory;
import com.wojewodka.inteca.services.repository.RepositoryImpl;

@RestController
@RequestMapping(value = "/api/family")
@CrossOrigin("*")
@JsonView(ViewScope.Basic.class)
public class FamilyController {

	@Autowired
	private FamilyCreator familyCreator;

	@Autowired
	private FatherRepository fatherRepo;

	@Autowired
	private FamilyRepository familyRepo;

	@Autowired
	private FamilyMemberServiceFactory familyServiceFactroy;

	@PostMapping("/create")
	public Family createFamily(@Valid @RequestBody FamilyRequestModel model) {
		return familyCreator.createFamilyFromRequest(model);
	}

	@GetMapping("/search")
	public List<Father> searchFamily(ChildRequestModel model) {
		return fatherRepo.findByChildrenData(model);
	}

	@GetMapping("/get")
	public Family getFamilyByFatherId(@PathParam("fatherId") int fatherId) {
		Family result = familyRepo.findByFatherId(fatherId);
		if (result == null)
			throw new AjaxException("Cannot find family with father id (" + fatherId + ")", 400);
		return result;
	}

	/**
	 * Return instance of family member {@link Father}, {@link Child} or
	 * {@link Family} with specific id. You should look also {@link FamilyMember}
	 * and {@link FamilyMemberService}
	 * 
	 * @param memberId
	 * @param memberType
	 * @return
	 */
	@GetMapping("/member")
	public FamilyMember readFamilyMember(@PathParam("memberId") Integer memberId,
			@PathParam("memberType") String memberType) {
		FamilyMemberService<?> service = familyServiceFactroy.getService(memberType);
		RepositoryImpl<?> repo = service.getRepository();
		return (FamilyMember) repo.findById(memberId);
	}

}
