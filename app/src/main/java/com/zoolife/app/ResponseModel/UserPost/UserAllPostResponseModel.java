package com.zoolife.app.ResponseModel.UserPost;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserAllPostResponseModel{

	@SerializedName("message")
	private String message;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("error")
	private boolean error;

	@SerializedName("status")
	private int status;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}

	public int getStatus(){
		return status;
	}

	public String getMessage() {
		return message;
	}
}