package com.VO;

public class WorkInstHalfVo {

    String custNM;
    String halfNm;
    String spec;
    String instAmt;
    String totalAmt;
    String unit;
    String stock;
    String afterStock;
    String inputUnit;

    public  WorkInstHalfVo(){

    }

    public WorkInstHalfVo(String custNM, String halfNm, String spec, String instAmt, String totalAmt, String unit, String stock, String afterStock, String inputUnit) {
        this.custNM = custNM;
        this.halfNm = halfNm;
        this.spec = spec;
        this.instAmt = instAmt;
        this.totalAmt = totalAmt;
        this.unit = unit;
        this.stock = stock;
        this.afterStock = afterStock;
        this.inputUnit = inputUnit;
    }

    public String getCustNM() {
        return custNM;
    }

    public void setCustNM(String custNM) {
        this.custNM = custNM;
    }

    public String getHalfNm() {
        return halfNm;
    }

    public void setHalfNm(String halfNm) {
        this.halfNm = halfNm;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getInstAmt() {
        return instAmt;
    }

    public void setInstAmt(String instAmt) {
        this.instAmt = instAmt;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getAfterStock() {
        return afterStock;
    }

    public void setAfterStock(String afterStock) {
        this.afterStock = afterStock;
    }

    public String getInputUnit() {
        return inputUnit;
    }

    public void setInputUnit(String inputUnit) {
        this.inputUnit = inputUnit;
    }
}
