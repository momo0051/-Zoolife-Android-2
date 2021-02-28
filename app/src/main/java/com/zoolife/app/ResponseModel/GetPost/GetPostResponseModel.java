package com.zoolife.app.ResponseModel.GetPost;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GetPostResponseModel implements Parcelable {

	@SerializedName("data")
	private Data data;

	@SerializedName("error")
	private boolean error;

	@SerializedName("status")
	private int status;

	public Data getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}

	public int getStatus(){
		return status;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.data, flags);
		dest.writeByte(this.error ? (byte) 1 : (byte) 0);
		dest.writeInt(this.status);
	}

	public GetPostResponseModel() {
	}

	protected GetPostResponseModel(Parcel in) {
		this.data = in.readParcelable(Data.class.getClassLoader());
		this.error = in.readByte() != 0;
		this.status = in.readInt();
	}

	public static final Parcelable.Creator<GetPostResponseModel> CREATOR = new Parcelable.Creator<GetPostResponseModel>() {
		@Override
		public GetPostResponseModel createFromParcel(Parcel source) {
			return new GetPostResponseModel(source);
		}

		@Override
		public GetPostResponseModel[] newArray(int size) {
			return new GetPostResponseModel[size];
		}
	};
}