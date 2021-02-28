package com.zoolife.app.ResponseModel.UpdateDeviceInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("update_status")
@Expose
private String updateStatus;

public String getUpdateStatus() {
return updateStatus;
}

public void setUpdateStatus(String updateStatus) {
this.updateStatus = updateStatus;
}

}