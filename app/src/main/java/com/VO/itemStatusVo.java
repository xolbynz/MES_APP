package com.VO;

public class itemStatusVo {

    String itemNm;
    String spec;
    String pstvAmt;
    String faultyAmt;
    String Amt;

    public itemStatusVo(String itemNm, String spec, String pstvAmt, String faultyAmt, String amt) {
        this.itemNm = itemNm;
        this.spec = spec;
        this.pstvAmt = pstvAmt;
        this.faultyAmt = faultyAmt;
        this.Amt = amt;
    }

    public String getItemNm() {
        return itemNm;
    }

    public void setItemNm(String itemNm) {
        this.itemNm = itemNm;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getPstvAmt() {
        return pstvAmt;
    }

    public void setPstvAmt(String pstvAmt) {
        this.pstvAmt = pstvAmt;
    }

    public String getFaultyAmt() {
        return faultyAmt;
    }

    public void setFaultyAmt(String faultyAmt) {
        this.faultyAmt = faultyAmt;
    }

    public String getAmt() {
        return Amt;
    }

    public void setAmt(String amt) {
        Amt = amt;
    }
}
