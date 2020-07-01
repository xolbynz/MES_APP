package com.VO;

public class MoniteringVo {

    String Inst_date;
    String Deli_date;
    String Item_cd;
    String Item_nm;
    String Lot_no;
    String Flow_count;
    String Inst_amt; // 투입
    String Input_amt; // 생산
    String Poor_amt; // 불량
    String Input_per;
    String Poor_per;

  public MoniteringVo(){

  }

    public MoniteringVo(String inst_date, String deli_date, String item_cd, String item_nm,
                        String lot_no, String flow_count, String inst_amt, String input_amt,
                        String poor_amt, String input_per, String poor_per) {
        Inst_date = inst_date;
        Deli_date = deli_date;
        Item_cd = item_cd;
        Item_nm = item_nm;
        Lot_no = lot_no;
        Flow_count = flow_count;
        Inst_amt = inst_amt;
        Input_amt = input_amt;
        Poor_amt = poor_amt;
        Input_per = input_per;
        Poor_per = poor_per;
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

    public String getInput_amt() {
        return Input_amt;
    }

    public void setInput_amt(String input_amt) {
        Input_amt = input_amt;
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
