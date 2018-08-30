package com.wojewodka.inteca.model.family;

import com.wojewodka.inteca.services.dbo.DatabaseObjectImpl;
import com.wojewodka.inteca.services.dbo.annotations.DBTable;
import com.wojewodka.inteca.services.dbo.annotations.Id;

@DBTable(name = "inteca_families")
public class Family extends DatabaseObjectImpl {

	@Id(name = "inteca_family_id")
	private int id;
	



}
