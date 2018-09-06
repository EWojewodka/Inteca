/**
 * 
 */
package com.wojewodka.inteca.services.family;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojewodka.inteca.services.NotImplementedException;

@Service
public class FamilyMemberServiceFactory {

	@Autowired
	private List<FamilyMemberService<?>> services;

	public FamilyMemberService<?> getService(String familyMemberCode) {
		// Get service for specific family member code or throw exception
		return services.parallelStream().filter(x -> x.getMemberCode().equals(familyMemberCode)).findFirst()
				.orElseThrow(() -> new NotImplementedException("service for (" + familyMemberCode + ") family member"));
	}

}
