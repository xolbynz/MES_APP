package com.VO;

public class TraceListVO {

    String Input_date;
    String Input_cd;
    String Lot_no;
    String W_inst_date;
    String W_inst_cd;
    String Delivery_date;
    String Inst_notice;
    String Complete_yn;
    String Raw_mat_cd;
    String Inst_amt;

    public TraceListVO() {

    }

    public TraceListVO(String input_date, String input_cd, String lot_no, String w_inst_date,
                       String w_inst_cd, String delivery_date, String inst_notice,
                       String complete_yn, String raw_mat_cd, String inst_amt) {
        Input_date = input_date;
        Input_cd = input_cd;
        Lot_no = lot_no;
        W_inst_date = w_inst_date;
        W_inst_cd = w_inst_cd;
        Delivery_date = delivery_date;
        Inst_notice = inst_notice;
        Complete_yn = complete_yn;
        Raw_mat_cd = raw_mat_cd;
        Inst_amt = inst_amt;
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

    public String getLot_no() {
        return Lot_no;
    }

    public void setLot_no(String lot_no) {
        Lot_no = lot_no;
    }

    public String getW_inst_date() {
        return W_inst_date;
    }

    public void setW_inst_date(String w_inst_date) {
        W_inst_date = w_inst_date;
    }

    public String getW_inst_cd() {
        return W_inst_cd;
    }

    public void setW_inst_cd(String w_inst_cd) {
        W_inst_cd = w_inst_cd;
    }

    public String getDelivery_date() {
        return Delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        Delivery_date = delivery_date;
    }

    public String getInst_notice() {
        return Inst_notice;
    }

    public void setInst_notice(String inst_notice) {
        Inst_notice = inst_notice;
    }

    public String getComplete_yn() {
        return Complete_yn;
    }

    public void setComplete_yn(String complete_yn) {
        Complete_yn = complete_yn;
    }

    public String getRaw_mat_cd() {
        return Raw_mat_cd;
    }

    public void setRaw_mat_cd(String raw_mat_cd) {
        Raw_mat_cd = raw_mat_cd;
    }

    public String getInst_amt() {
        return Inst_amt;
    }

    public void setInst_amt(String inst_amt) {
        Inst_amt = inst_amt;
    }
}
