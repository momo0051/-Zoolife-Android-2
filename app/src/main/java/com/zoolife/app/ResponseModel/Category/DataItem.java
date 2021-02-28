package com.zoolife.app.ResponseModel.Category;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("img_unSelected")
	private String imgUnSelected;

	@SerializedName("img_selected")
	private String imgSelected;

	@SerializedName("removeAt")
	private String removeAt;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("mainCategoryId")
	private String mainCategoryId;

	@SerializedName("createAt")
	private String createAt;

	public String getImgUnSelected(){
		return imgUnSelected;
	}

	public String getImgSelected(){
		return imgSelected;
	}

	public String getRemoveAt(){
		return removeAt;
	}

	public String getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getMainCategoryId(){
		return mainCategoryId;
	}

	public String getCreateAt(){
		return createAt;
	}
}