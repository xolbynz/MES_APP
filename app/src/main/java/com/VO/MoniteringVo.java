package com.VO;

public class MoniteringVo {

    String Cust_cd;
    String Cust_nm;
    String Inst_date;
    String Deli_date;
    String Item_cd;
    String Item_nm;
    String Lot_no;
    String Flow_count; // 공정 갯수
    String Inst_amt; // 지시 수량
    String Processing; // 진행 현황
    String Input_amt; // 생산 수량
    String Input_date; // 생산 일자
    String Poor_amt; // 불량
    String Input_per;
    String Poor_per;

  public MoniteringVo(){

  }

    public MoniteringVo(String cust_cd, String cust_nm, String inst_date, String deli_date,
                        String item_cd, String item_nm, String lot_no, String flow_count,
                        String inst_amt, String processing, String input_amt, String input_date,
                        String poor_amt, String input_per, String poor_per) {
        Cust_cd = cust_cd;
        Cust_nm = cust_nm;
        Inst_date = inst_date;
        Deli_date = deli_date;
        Item_cd = item_cd;
        Item_nm = item_nm;
        Lot_no = lot_no;
        Flow_count = flow_count;
        Inst_amt = inst_amt;
        Processing = processing;
        Input_amt = input_amt;
        Input_date = input_date;
        Poor_amt = poor_amt;
        Input_per = input_per;
        Poor_per = poor_per;
    }

    public String getCust_cd() {
        return Cust_cd;
    }

    public void setCust_cd(String cust_cd) {
        Cust_cd = cust_cd;
    }

    public String getCust_nm() {
        return Cust_nm;
    }

    public void setCust_nm(String cust_nm) {
        Cust_nm = cust_nm;
    }

    public String getInst_date() {
        return Inst_date;
    }

    public void setInst_date(String inst_date) {
        Inst_date = inst_date;
    }

    public String getDeli_date() {
        return Deli_date;
    }

    public void setDeli_date(String deli_date) {
        Deli_date = deli_date;
    }

    public String getItem_cd() {
        return Item_cd;
    }

    public void setItem_cd(String item_cd) {
        Item_cd = item_cd;
    }

    public String getItem_nm() {
        return Item_nm;
    }

    public void setItem_nm(String item_nm) {
        Item_nm = item_nm;
    }

    public String getLot_no() {
        return Lot_no;
    }

    public void setLot_no(String lot_no) {
        Lot_no = lot_no;
    }

    public String getFlow_count() {
        return Flow_count;
    }

    public void setFlow_count(String flow_count) {
        Flow_count = flow_count;
    }

    public String getInst_amt() {
        return Inst_amt;
    }

    public void setInst_amt(String inst_amt) {
        Inst_amt = inst_amt;
    }

    public String getProcessing() {
        return Processing;
    }

    public void setProcessing(String processing) {
        Processing = processing;
    }

    public String getInput_amt() {
        return Input_amt;
    }

    public void setInput_amt(String input_amt) {
        Input_amt = input_amt;
    }

    public String getInput_date() {
        return Input_date;
    }

    public void setInput_date(String input_date) {
        Input_date = input_date;
    }

    public String getPoor_amt() {
        return Poor_amt;
    }

    public void setPoor_amt(String poor_amt) {
        Poor_amt = poor_amt;
    }

    public String getInput_per() {
        return Input_per;
    }

    public void setInput_per(String input_per) {
        Input_per = input_per;
    }

    public String getPoor_per() {
        return Poor_per;
    }

    public void setPoor_per(String poor_per) {
        Poor_per = poor_per;
    }
}
