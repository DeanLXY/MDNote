package com.wxj.mdnote.cpf.model;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CPFCityModel {

@SerializedName("reason")
@Expose
private String reason;
@SerializedName("cityCPF")
@Expose
private List<CityCPF> cityCPF = new ArrayList<CityCPF>();
@SerializedName("error_code")
@Expose
private Integer errorCode;

/**
* 
* @return
* The reason
*/
public String getReason() {
return reason;
}

/**
* 
* @param reason
* The reason
*/
public void setReason(String reason) {
this.reason = reason;
}

/**
* 
* @return
* The cityCPF
*/
public List<CityCPF> getCityCPF() {
return cityCPF;
}

/**
* 
* @param cityCPF
* The cityCPF
*/
public void setCityCPF(List<CityCPF> cityCPF) {
this.cityCPF = cityCPF;
}

/**
* 
* @return
* The errorCode
*/
public Integer getErrorCode() {
return errorCode;
}

/**
* 
* @param errorCode
* The error_code
*/
public void setErrorCode(Integer errorCode) {
this.errorCode = errorCode;
}

}
