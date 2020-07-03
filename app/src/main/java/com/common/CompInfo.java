package com.common;

public  class CompInfo {

    static public  String COM_SAPU_NO = "";
   static public String COM_LOCATION = "";
   static public String COMPANY_NM = "";
   static public String SP_CODE = "";
   static public String PACK_GUBUN = "";
    static public String COM_LOGO="";
    static public  String STAFF_NM="";

    public static String getComSapuNo() {
        return COM_SAPU_NO;
    }

    public static String getComLogo() {
        return COM_LOGO;
    }

    public static void setComLogo(String comLogo) {
        COM_LOGO = comLogo;
    }

    public static void setComSapuNo(String comSapuNo) {
        COM_SAPU_NO = comSapuNo;
    }



    public String getSTAFF_NM() {
        return STAFF_NM;
    }

    public void setSTAFF_NM(String STAFF_NM) {
        this.STAFF_NM = STAFF_NM;
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
