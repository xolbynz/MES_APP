package com.common;

public  class CompInfo {

    private static String COM_SAPU_NO = "";
    private String COM_LOCATION = "";
    private String COMPANY_NM = "";
    private String SP_CODE = "";
    private String PACK_GUBUN = "";
    private static String COM_LOOG="";

    public String getCOM_LOOG() {
        return COM_LOOG;
    }

    public void setCOM_LOOG(String COM_LOOG) {
        this.COM_LOOG = COM_LOOG;
    }

    public CompInfo(){

    }

    public CompInfo(String COM_SAPU_NO, String COM_LOCATION, String COMPANY_NM, String SP_CODE, String PACK_GUBUN) {
        this.COM_SAPU_NO = COM_SAPU_NO;
        this.COM_LOCATION = COM_LOCATION;
        this.COMPANY_NM = COMPANY_NM;
        this.SP_CODE = SP_CODE;
        this.PACK_GUBUN = PACK_GUBUN;
    }

    public String getCOM_SAPU_NO() {
        return COM_SAPU_NO;
    }

    public void setCOM_SAPU_NO(String COM_SAPU_NO) {
        this.COM_SAPU_NO = COM_SAPU_NO;
    }

    public String getCOM_LOCATION() {
        return COM_LOCATION;
    }

    public void setCOM_LOCATION(String COM_LOCATION) {
        this.COM_LOCATION = COM_LOCATION;
    }

    public String getCOMPANY_NM() {
        return COMPANY_NM;
    }

    public void setCOMPANY_NM(String COMPANY_NM) {
        this.COMPANY_NM = COMPANY_NM;
    }

    public String getSP_CODE() {
        return SP_CODE;
    }

    public void setSP_CODE(String SP_CODE) {
        this.SP_CODE = SP_CODE;
    }

    public String getPACK_GUBUN() {
        return PACK_GUBUN;
    }

    public void setPACK_GUBUN(String PACK_GUBUN) {
        this.PACK_GUBUN = PACK_GUBUN;
    }
}
