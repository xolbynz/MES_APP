package com.VO;

public class WorkListVo {


    String custNm;
    String lotNo;
    String itemNm;
    String instAmt;
    String spec;
    String completeYN;
    String instDate;
    String delivertDate;
    String maxSeq;
    String itemCd;

    public String getItemCd() {
        return itemCd;
    }

    public void setItemCd(String itemCd) {
        this.itemCd = itemCd;
    }

    public String getCustCd() {
        return custCd;
    }

    public void setCustCd(String custCd) {
        this.custCd = custCd;
    }

    String custCd;

    public String getInstDate() {
        return instDate;
    }

    public void setInstDate(String instDate) {
        this.instDate = instDate;
    }

    public String getDelivertDate() {
        return delivertDate;
    }

    public void setDelivertDate(String delivertDate) {
        this.delivertDate = delivertDate;
    }

    public String getMaxSeq() {
        return maxSeq;
    }

    public void setMaxSeq(String maxSeq) {
        this.maxSeq = maxSeq;
    }

    public  WorkListVo(

    ){

    }
    public WorkListVo(String custNm, String lotNo, String itemNm, String instAmt, String spec, String completeYN,String instDate,String delivertDate,String maxSeq,String itemCd,String custCd) {
        this.custNm = custNm;
        this.lotNo = lotNo;
        this.itemNm = itemNm;
        this.instAmt = instAmt;
        this.spec = spec;
        this.completeYN = completeYN;
        this.instDate= instDate;
        this.delivertDate= delivertDate;
        this.maxSeq= maxSeq;
        this.itemCd=itemCd;
        this.custCd=custCd;
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
