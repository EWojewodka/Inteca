package com.wojewodka.inteca.model.family;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.wojewodka.inteca.api.ViewScope;
import com.wojewodka.inteca.model.request.ChildRequestModel;
import com.wojewodka.inteca.services.dbo.DatabaseObjectImpl;
import com.wojewodka.inteca.services.dbo.annotations.DBColumn;
import com.wojewodka.inteca.services.dbo.annotations.DBTable;
import com.wojewodka.inteca.services.dbo.annotations.Id;

@DBTable(name = "inteca_children")
@JsonView(ViewScope.Basic.class)
public class Child extends DatabaseObjectImpl implements FamilyMember {

	@Id(name = "child_id")
	private int id;

	@DBColumn(name = "first_name")
	private String firstname;

	@DBColumn(name = "second_name")
	private String secondName;

	@DBColumn(name = "pesel")
	private String pesel;

	@DBColumn(name = "sex")
	private String sex;

	@DBColumn(name = "date_of_birth")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	@DBColumn(name = "family_id")
	private int familyId;

	public Child() {
	}

	public Child(ChildRequestModel requestModel) {
		this(requestModel, null);
	}

	public Child(ChildRequestModel requestModel, Integer familyId) {
		this.firstname = requestModel.getFirstname();
		this.secondName = requestModel.getSecondname();
		this.pesel = requestModel.getPesel();
		this.sex = requestModel.getSex();
		this.dateOfBirth = requestModel.getDateOfBirth();
		if (familyId != null)
			this.familyId = familyId;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	@Override
	public String getTypeCode() {
		return "child";
	}

}
