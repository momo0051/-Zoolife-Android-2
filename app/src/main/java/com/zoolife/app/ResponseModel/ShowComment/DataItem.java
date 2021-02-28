package com.zoolife.app.ResponseModel.ShowComment;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("itemId")
	private String itemId;

	@SerializedName("userFullname")
	private String userFullname;

	@SerializedName("uo")
	private String uo;

	@SerializedName("userEmail")
	private String userEmail;

	@SerializedName("id")
	private String id;

	@SerializedName("message")
	private String message;

	@SerializedName("co")
	private String co;

	@SerializedName("userId")
	private String userId;

	public String getItemId(){
		return itemId;
	}

	public String getUserFullname(){
		return userFullname;
	}

	public String getUo(){
		return uo;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public String getId(){
		return id;
	}

	public String getMessage(){
		return message;
	}

	public String getCo(){
		return co;
	}

	public String getUserId(){
		return userId;
	}
}