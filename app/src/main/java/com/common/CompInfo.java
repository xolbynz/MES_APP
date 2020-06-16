package com.common;

import java.io.Serializable;

public class CompInfo {

    static public String saupNo = "";
    static public String saupNm = "";
    static public String spCode = "";
    static public String spSite = "";

    public String getSaupNo() {
        return saupNo;
    }

    public String getSaupNm() {
        return saupNm;
    }

    public String getSpCode() {
        return spCode;
    }

    public String getSpSite() {
        return spSite;
    }

    public void setSaupNo(String saupNo) {
        this.saupNo = saupNo;
    }

    public void setSaupNm(String saupNm) {
        this.saupNm = saupNm;
    }

    public void setSpCode(String spCode) {
        this.spCode = spCode;
    }

    public void setSpSite(String spSite) {
        this.spSite = spSite;
    }


}
