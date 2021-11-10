package com.smkn9semarang.absensisiswa.login;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("hasil")
	private String hasil;

	public String getHasil(){
		return hasil;
	}
}