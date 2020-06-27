package com.VO;

public class WorkInstVo {

    String lotNO;
    String custNm;
    String itemNm;
    String instAmt;

    public WorkInstVo()
    {

    }

    public WorkInstVo(String lotNO, String custNm, String itemNm, String instAmt) {
        this.lotNO = lotNO;
        this.custNm = custNm;
        this.itemNm = itemNm;
        this.instAmt = instAmt;
    }

    public String getLotNO() {
        return lotNO;
    }

    public String getCustNm() {
        return custNm;
    }

    public String getItemNm() {
        return itemNm;
    }

    public String getInstAmt() {
        return instAmt;
    }

    public void setLotNO(String lotNO) {
        this.lotNO = lotNO;
    }

    public void setCustNm(String custNm) {
        this.custNm = custNm;
    }

    public void setItemNm(String itemNm) {
        this.itemNm = itemNm;
    }

    public void setInstAmt(String instAmt) {
        this.instAmt = instAmt;
    }
}
