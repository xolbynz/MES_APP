package com.VO;

public class WorkListVo {


    String custNm;
    String lotNo;
    String itemNm;
    String instAmt;
    String spec;
    String completeYN;

    public  WorkListVo(

    ){

    }
    public WorkListVo(String custNm, String lotNo, String itemNm, String instAmt, String spec, String completeYN) {
        this.custNm = custNm;
        this.lotNo = lotNo;
        this.itemNm = itemNm;
        this.instAmt = instAmt;
        this.spec = spec;
        this.completeYN = completeYN;
    }

    public String getCustNm() {
        return custNm;
    }

    public void setCustNm(String custNm) {
        this.custNm = custNm;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getItemNm() {
        return itemNm;
    }

    public void setItemNm(String itemNm) {
        this.itemNm = itemNm;
    }

    public String getInstAmt() {
        return instAmt;
    }

    public void setInstAmt(String instAmt) {
        this.instAmt = instAmt;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getCompleteYN() {
        return completeYN;
    }

    public void setCompleteYN(String completeYN) {
        this.completeYN = completeYN;
    }
}
