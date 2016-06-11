package com.wxj.mdnote.cpf.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("cname")
    @Expose
    private String cname;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("pname")
    @Expose
    private String pname;
    @SerializedName("prov")
    @Expose
    private String prov;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("rule")
    @Expose
    private Integer rule;
    @SerializedName("subdl")
    @Expose
    private Integer subdl;
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * @param cname The cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * @return The month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month The month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return The pname
     */
    public String getPname() {
        return pname;
    }

    /**
     * @param pname The pname
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * @return The prov
     */
    public String getProv() {
        return prov;
    }

    /**
     * @param prov The prov
     */
    public void setProv(String prov) {
        this.prov = prov;
    }

    /**
     * @return The region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region The region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return The rule
     */
    public Integer getRule() {
        return rule;
    }

    /**
     * @param rule The rule
     */
    public void setRule(Integer rule) {
        this.rule = rule;
    }

    /**
     * @return The subdl
     */
    public Integer getSubdl() {
        return subdl;
    }

    /**
     * @param subdl The subdl
     */
    public void setSubdl(Integer subdl) {
        this.subdl = subdl;
    }

    /**
     * @return The timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp The timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
