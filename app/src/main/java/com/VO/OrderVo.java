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
        this.Order_Date = order_Date;
        this.Order_Cd = order_Cd;
        this.Order_Seq = order_Seq;
        this.Input_Req_Date = input_Req_Date;
        this.Complete_Yn = complete_Yn;
        this.Rawmat_Cd = rawmat_Cd;
        this.Rawmat_Nm = rawmat_Nm;
        this.Rawmat_Gubun = rawmat_Gubun;
        this.Rawmat_Gubun_Nm = rawmat_Gubun_Nm;
        this.Spec = spec;
        this.Unit_cd = unit_cd;
        this.Unit_Nm = unit_Nm;
        this.Cust_Cd = cust_Cd;
        this.Cust_Nm = cust_Nm;
        this.Price = price;
        this.TotalMoney = totalMoney;
        this.Check = check;
        this.Check_Yn = check_Yn;
        this.Order_Amt = order_Amt;
        this.Input_Amt = input_Amt;
        this.Input_NeedAmt = input_NeedAmt;
        this.Storage = storage;
    }

    public String getOrder_Date() {
        return Order_Date;
    }

    public void setOrder_Date(String order_Date) {
        this.Order_Date = order_Date;
    }

    public String getOrder_Cd() {
        return Order_Cd;
    }

    public void setOrder_Cd(String order_Cd) {
        this.Order_Cd = order_Cd;
    }

    public String getOrder_Seq() {
        return Order_Seq;
    }

    public void setOrder_Seq(String order_Seq) {
        this.Order_Seq = order_Seq;
    }

    public String getInput_Req_Date() {
        return Input_Req_Date;
    }

    public void setInput_Req_Date(String input_Req_Date) {
        this.Input_Req_Date = input_Req_Date;
    }

    public String getComplete_Yn() {
        return Complete_Yn;
    }

    public void setComplete_Yn(String complete_Yn) {
        this.Complete_Yn = complete_Yn;
    }

    public String getRawmat_Cd() {
        return Rawmat_Cd;
    }

    public void setRawmat_Cd(String rawmat_Cd) {
        this.Rawmat_Cd = rawmat_Cd;
    }

    public String getRawmat_Nm() {
        return Rawmat_Nm;
    }

    public void setRawmat_Nm(String rawmat_Nm) {
        this.Rawmat_Nm = rawmat_Nm;
    }

    public String getRawmat_Gubun() {
        return Rawmat_Gubun;
    }

    public void setRawmat_Gubun(String rawmat_Gubun) {
        this.Rawmat_Gubun = rawmat_Gubun;
    }

    public String getRawmat_Gubun_Nm() {
        return Rawmat_Gubun_Nm;
    }

    public void setRawmat_Gubun_Nm(String rawmat_Gubun_Nm) {
        this.Rawmat_Gubun_Nm = rawmat_Gubun_Nm;
    }

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String spec) {
        this.Spec = spec;
    }

    public String getUnit_cd() {
        return Unit_cd;
    }

    public void setUnit_cd(String unit_cd) {
        this.Unit_cd = unit_cd;
    }

    public String getUnit_Nm() {
        return Unit_Nm;
    }

    public void setUnit_Nm(String unit_Nm) {
        this.Unit_Nm = unit_Nm;
    }

    public String getCust_Cd() {
        return Cust_Cd;
    }

    public void setCust_Cd(String cust_Cd) {
        this.Cust_Cd = cust_Cd;
    }

    public String getCust_Nm() {
        return Cust_Nm;
    }

    public void setCust_Nm(String cust_Nm) {
        this.Cust_Nm = cust_Nm;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.TotalMoney = totalMoney;
    }

    public String getCheck() {
        return Check;
    }

    public void setCheck(String check) {
        this.Check = check;
    }

    public String getCheck_Yn() {
        return Check_Yn;
    }

    public void setCheck_Yn(String check_Yn) {
        this.Check_Yn = check_Yn;
    }

    public String getOrder_Amt() {
        return Order_Amt;
    }

    public void setOrder_Amt(String order_Amt) {
        this.Order_Amt = order_Amt;
    }

    public String getInput_Amt() {
        return Input_Amt;
    }

    public void setInput_Amt(String input_Amt) {
        this.Input_Amt = input_Amt;
    }

    public String getInput_NeedAmt() {
        return Input_NeedAmt;
    }

    public void setInput_NeedAmt(String input_NeedAmt) {
        this.Input_NeedAmt = input_NeedAmt;
    }

    public String getStorage() {
        return Storage;
    }

    public void setStorage(String storage) {
        this.Storage = storage;
    }
}
