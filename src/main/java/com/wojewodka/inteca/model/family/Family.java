package com.wojewodka.inteca.model.family;

import com.wojewodka.inteca.services.dbo.DatabaseObjectImpl;
import com.wojewodka.inteca.services.dbo.annotations.DBColumn;
import com.wojewodka.inteca.services.dbo.annotations.DBTable;
import com.wojewodka.inteca.services.dbo.annotations.Id;

@DBTable(name = "inteca_families")
public class Family extends DatabaseObjectImpl {

	@Id(name = "family_id")
	private int id;

	@DBColumn(name = "father_id")
	private int fatherId;

	public int getFatherId() {
		return fatherId;
	}

	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Family [id=" + id + ", fatherId=" + fatherId + "]";
	}

}
