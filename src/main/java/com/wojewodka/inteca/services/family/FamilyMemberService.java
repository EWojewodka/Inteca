/**
 * 
 */
package com.wojewodka.inteca.services.family;

import com.wojewodka.inteca.services.dbo.DatabaseObject;
import com.wojewodka.inteca.services.repository.RepositoryImpl;

public interface FamilyMemberService<T extends DatabaseObject> {

	RepositoryImpl<T> getRepository();
	
	String getMemberCode();

}
