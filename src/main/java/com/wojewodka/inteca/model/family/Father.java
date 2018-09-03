package com.wojewodka.inteca.model.family;

import java.util.Date;

import com.wojewodka.inteca.model.request.FatherRequestModel;
import com.wojewodka.inteca.services.dbo.DatabaseObjectImpl;
import com.wojewodka.inteca.services.dbo.annotations.DBColumn;
import com.wojewodka.inteca.services.dbo.annotations.DBTable;
import com.wojewodka.inteca.services.dbo.annotations.Id;

@DBTable(name = "inteca_fathers")
public class Father extends DatabaseObjectImpl{

	@Id(name = "father_id")
	private int id;

	@DBColumn(name = "first_name")
	private String firstname;

	@DBColumn(name = "second_name")
	private String secondName;

	@DBColumn(name = "pesel")
	private String pesel;

	@DBColumn(name = "birth_date")
	private Date born;

	public Father() {
		
	}
	
	public Father(FatherRequestModel requestModel) {
		this.firstname = requestModel.getFirstname();
		this.secondName = requestModel.getSecondName();
		this.pesel = requestModel.getPesel();
		this.born = requestModel.getBorn();
	}
	
	public Date getBorn() {
		return born;
	}

	public void setBorn(Date born) {
		this.born = born;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
