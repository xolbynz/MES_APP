package com.VO;

public class TraceListDetailVo {

    String Gubun;
    String Order1;
    String Order2;
    String Input_date;
    String Input_cd;
    String Intime;
    String Cust_nm;
    String Raw_mat_nm;
    String Spec;
    String Lot_no;
    String Lot_sub;
    String Unit_nm;
    String Total_amt;
    String Loss_amt;
    String poor;

    public TraceListDetailVo(){

    }

    public TraceListDetailVo(String gubun, String order1, String order2, String input_date,
                             String input_cd, String intime, String cust_nm, String raw_mat_nm,
                             String spec, String lot_no, String lot_sub, String unit_nm,
                             String total_amt, String loss_amt, String poor) {
        Gubun = gubun;
        Order1 = order1;
        Order2 = order2;
        Input_date = input_date;
        Input_cd = input_cd;
        Intime = intime;
        Cust_nm = cust_nm;
        Raw_mat_nm = raw_mat_nm;
        Spec = spec;
        Lot_no = lot_no;
        Lot_sub = lot_sub;
        Unit_nm = unit_nm;
        Total_amt = total_amt;
        Loss_amt = loss_amt;
        this.poor = poor;
    }

    public String getGubun() {
        return Gubun;
    }

    public void setGubun(String gubun) {
        Gubun = gubun;
    }

    public String getOrder1() {
        return Order1;
    }

    public void setOrder1(String order1) {
        Order1 = order1;
    }

    public String getOrder2() {
        return Order2;
    }

    public void setOrder2(String order2) {
        Order2 = order2;
    }

    public String getInput_date() {
        return Input_date;
    }

    public void setInput_date(String input_date) {
        Input_date = input_date;
    }

    public String getInput_cd() {
        return Input_cd;
    }

    public void setInput_cd(String input_cd) {
        Input_cd = input_cd;
    }

    public String getIntime() {
        return Intime;
    }

    public void setIntime(String intime) {
        Intime = intime;
    }

    public String getCust_nm() {
        return Cust_nm;
    }

    public void setCust_nm(String cust_nm) {
        Cust_nm = cust_nm;
    }

    public String getRaw_mat_nm() {
        return Raw_mat_nm;
    }

    public void setRaw_mat_nm(String raw_mat_nm) {
        Raw_mat_nm = raw_mat_nm;
    }

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String spec) {
        Spec = spec;
    }

    public String getLot_no() {
        return Lot_no;
    }

    public void setLot_no(String lot_no) {
        Lot_no = lot_no;
    }

    public String getLot_sub() {
        return Lot_sub;
    }

    public void setLot_sub(String lot_sub) {
        Lot_sub = lot_sub;
    }

    public String getUnit_nm() {
        return Unit_nm;
    }

    public void setUnit_nm(String unit_nm) {
        Unit_nm = unit_nm;
    }

    public String getTotal_amt() {
        return Total_amt;
    }

    public void setTotal_amt(String total_amt) {
        Total_amt = total_amt;
    }

    public String getLoss_amt() {
        return Loss_amt;
    }

    public void setLoss_amt(String loss_amt) {
        Loss_amt = loss_amt;
    }

    public String getPoor() {
        return poor;
    }

    public void setPoor(String poor) {
        this.poor = poor;
    }
}
