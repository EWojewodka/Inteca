package com.wojewodka.inteca.model.family;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Person {

	@NotNull
	@NotEmpty
	private String firstname;

	@NotNull
	@NotEmpty
	private String secondname;

	@NotNull
	@NotEmpty
	@Pattern(regexp = "[\\d]{11}", message = "Pesel must contains 11 digits.")
	private String pesel;

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

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

}
