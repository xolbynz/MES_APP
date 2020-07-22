package com.common;

public class CompInfo {

    static public String COM_SAPU_NO = "";
    static public String COM_LOCATION = "";
    static public String COMPANY_NM = "";
    static public String SP_CODE = "";
    static public String PACK_GUBUN = "";
    static public String COM_LOGO = "";
    static public String STAFF_CD = "";
    static public String STAFF_NM = "";

    public CompInfo() {

    }

    public CompInfo(String COM_SAPU_NO, String COM_LOCATION, String COMPANY_NM, String SP_CODE, String PACK_GUBUN) {
        this.COM_SAPU_NO = COM_SAPU_NO;
        this.COM_LOCATION = COM_LOCATION;
        this.COMPANY_NM = COMPANY_NM;
        this.SP_CODE = SP_CODE;
        this.PACK_GUBUN = PACK_GUBUN;
    }

    public static String getComSapuNo() {
        return COM_SAPU_NO;
    }

    public static void setComSapuNo(String comSapuNo) {
        COM_SAPU_NO = comSapuNo;
    }

    public static String getComLocation() {
        return COM_LOCATION;
    }

    public static void setComLocation(String comLocation) {
        COM_LOCATION = comLocation;
    }

    public static String getCompanyNm() {
        return COMPANY_NM;
    }

    public static void setCompanyNm(String companyNm) {
        COMPANY_NM = companyNm;
    }

    public static String getSpCode() {
        return SP_CODE;
    }

    public static void setSpCode(String spCode) {
        SP_CODE = spCode;
    }

    public static String getPackGubun() {
        return PACK_GUBUN;
    }

    public static void setPackGubun(String packGubun) {
        PACK_GUBUN = packGubun;
    }

    public static String getComLogo() {
        return COM_LOGO;
    }

    public static void setComLogo(String comLogo) {
        COM_LOGO = comLogo;
    }

    public static String getStaffCd() {
        return STAFF_CD;
    }

    public static void setStaffCd(String staffCd) {
        STAFF_CD = staffCd;
    }

    public static String getStaffNm() {
        return STAFF_NM;
    }

    public static void setStaffNm(String staffNm) {
        STAFF_NM = staffNm;
    }
}
