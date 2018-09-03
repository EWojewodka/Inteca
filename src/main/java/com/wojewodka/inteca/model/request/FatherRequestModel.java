package com.wojewodka.inteca.model.request;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.wojewodka.inteca.model.family.Person;

public class FatherRequestModel extends Person {

	@Past(message = "Father can't be from future! :( ")
	@NotNull
	private Date born;

	public Date getBorn() {
		return born;
	}

	public void setBorn(Date born) {
		this.born = born;
	}
}
