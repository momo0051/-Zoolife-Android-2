package com.zoolife.app.ResponseModel.UpdateDeviceInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateDeviceInfoResponse {

@SerializedName("error")
@Expose
private boolean error;
@SerializedName("data")
@Expose
private Data data;
@SerializedName("status")
@Expose
private int status;

public boolean isError() {
return error;
}

public void setError(boolean error) {
this.error = error;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

public int getStatus() {
return status;
}

public void setStatus(int status) {
this.status = status;
}

}