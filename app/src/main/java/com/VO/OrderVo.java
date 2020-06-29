package com.VO;

public class OrderVo {

    String Order_Date;
    String Order_Cd;
    String Order_Seq;
    String Input_Req_Date;
    String Complete_Yn;
    String Rawmat_Cd;
    String Rawmat_Nm;
    String Rawmat_Gubun;
    String Rawmat_Gubun_Nm;
    String Spec;
    String Unit_cd;
    String Unit_Nm;
    String Cust_Cd;
    String Cust_Nm;
    String Price;
    String TotalMoney;
    String Check;
    String Check_Yn;
    String Order_Amt;
    String Input_Amt;
    String Input_NeedAmt;
    String Storage;

    public OrderVo() {

    }

    public OrderVo(String order_Date, String order_Cd, String order_Seq, String input_Req_Date,
                   String complete_Yn, String rawmat_Cd, String rawmat_Nm, String rawmat_Gubun,
                   String rawmat_Gubun_Nm, String spec, String unit_cd, String unit_Nm,
                   String cust_Cd, String cust_Nm, String price, String totalMoney, String check,
                   String check_Yn, String order_Amt, String input_Amt, String input_NeedAmt,
                   String storage) {
        Order_Date = order_Date;
        Order_Cd = order_Cd;
        Order_Seq = order_Seq;
        Input_Req_Date = input_Req_Date;
        Complete_Yn = complete_Yn;
        Rawmat_Cd = rawmat_Cd;
        Rawmat_Nm = rawmat_Nm;
        Rawmat_Gubun = rawmat_Gubun;
        Rawmat_Gubun_Nm = rawmat_Gubun_Nm;
        Spec = spec;
        Unit_cd = unit_cd;
        Unit_Nm = unit_Nm;
        Cust_Cd = cust_Cd;
        Cust_Nm = cust_Nm;
        Price = price;
        TotalMoney = totalMoney;
        Check = check;
        Check_Yn = check_Yn;
        Order_Amt = order_Amt;
        Input_Amt = input_Amt;
        Input_NeedAmt = input_NeedAmt;
        Storage = storage;
    }

    public String getOrder_Date() {
        return Order_Date;
    }

    public void setOrder_Date(String order_Date) {
        Order_Date = order_Date;
    }

    public String getOrder_Cd() {
        return Order_Cd;
    }

    public void setOrder_Cd(String order_Cd) {
        Order_Cd = order_Cd;
    }

    public String getOrder_Seq() {
        return Order_Seq;
    }

    public void setOrder_Seq(String order_Seq) {
        Order_Seq = order_Seq;
    }

    public String getInput_Req_Date() {
        return Input_Req_Date;
    }

    public void setInput_Req_Date(String input_Req_Date) {
        Input_Req_Date = input_Req_Date;
    }

    public String getComplete_Yn() {
        return Complete_Yn;
    }

    public void setComplete_Yn(String complete_Yn) {
        Complete_Yn = complete_Yn;
    }

    public String getRawmat_Cd() {
        return Rawmat_Cd;
    }

    public void setRawmat_Cd(String rawmat_Cd) {
        Rawmat_Cd = rawmat_Cd;
    }

    public String getRawmat_Nm() {
        return Rawmat_Nm;
    }

    public void setRawmat_Nm(String rawmat_Nm) {
        Rawmat_Nm = rawmat_Nm;
    }

    public String getRawmat_Gubun() {
        return Rawmat_Gubun;
    }

    public void setRawmat_Gubun(String rawmat_Gubun) {
        Rawmat_Gubun = rawmat_Gubun;
    }

    public String getRawmat_Gubun_Nm() {
        return Rawmat_Gubun_Nm;
    }

    public void setRawmat_Gubun_Nm(String rawmat_Gubun_Nm) {
        Rawmat_Gubun_Nm = rawmat_Gubun_Nm;
    }

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String spec) {
        Spec = spec;
    }

    public String getUnit_cd() {
        return Unit_cd;
    }

    public void setUnit_cd(String unit_cd) {
        Unit_cd = unit_cd;
    }

    public String getUnit_Nm() {
        return Unit_Nm;
    }

    public void setUnit_Nm(String unit_Nm) {
        Unit_Nm = unit_Nm;
    }

    public String getCust_Cd() {
        return Cust_Cd;
    }

    public void setCust_Cd(String cust_Cd) {
        Cust_Cd = cust_Cd;
    }

    public String getCust_Nm() {
        return Cust_Nm;
    }

    public void setCust_Nm(String cust_Nm) {
        Cust_Nm = cust_Nm;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        TotalMoney = totalMoney;
    }

    public String getCheck() {
        return Check;
    }

    public void setCheck(String check) {
        Check = check;
    }

    public String getCheck_Yn() {
        return Check_Yn;
    }

    public void setCheck_Yn(String check_Yn) {
        Check_Yn = check_Yn;
    }

    public String getOrder_Amt() {
        return Order_Amt;
    }

    public void setOrder_Amt(String order_Amt) {
        Order_Amt = order_Amt;
    }

    public String getInput_Amt() {
        return Input_Amt;
    }

    public void setInput_Amt(String input_Amt) {
        Input_Amt = input_Amt;
    }

    public String getInput_NeedAmt() {
        return Input_NeedAmt;
    }

    public void setInput_NeedAmt(String input_NeedAmt) {
        Input_NeedAmt = input_NeedAmt;
    }

    public String getStorage() {
        return Storage;
    }

    public void setStorage(String storage) {
        Storage = storage;
    }
}
