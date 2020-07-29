package com.VO;

public class WorkProcessVo {
    String Inst_Date;
    String Deli_date;
    String Item_cd;
    String Item_nm;
    String Lot_no;
    String Flow_Count;
    String Inst_amt;
    String Input_amt;
    String Input_per;

    public  WorkProcessVo(){

    }

    public WorkProcessVo(String inst_Date, String deli_date, String item_cd, String item_nm,
                         String lot_no, String flow_Count, String inst_amt, String input_amt,
                         String input_per) {
        Inst_Date = inst_Date;
        Deli_date = deli_date;
        Item_cd = item_cd;
        Item_nm = item_nm;
        Lot_no = lot_no;
        Flow_Count = flow_Count;
        Inst_amt = inst_amt;
        Input_amt = input_amt;
        Input_per = input_per;
    }

    public String getInst_Date() {
        return Inst_Date;
    }

    public void setInst_Date(String inst_Date) {
        Inst_Date = inst_Date;
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

    public String getFlow_Count() {
        return Flow_Count;
    }

    public void setFlow_Count(String flow_Count) {
        Flow_Count = flow_Count;
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

    public String getInput_per() {
        return Input_per;
    }

    public void setInput_per(String input_per) {
        Input_per = input_per;
    }
}




