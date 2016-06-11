package com.wxj.mdnote.cpf.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wxj.mdnote.RetrofitUtil;
import com.wxj.mdnote.cpf.net.CPFAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CPFCityModel {

    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("result")
    @Expose
    private List<Result> result = new ArrayList<Result>();
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;

    /**
     * @return The reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason The reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return The result
     */
    public List<Result> getResult() {
        return result;
    }

    /**
     * @param result The result
     */
    public void setResult(List<Result> result) {
        this.result = result;
    }

    /**
     * @return The errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode The error_code
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }


}
