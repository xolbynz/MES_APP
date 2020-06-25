package com.VO;

public class RawVo {

    String raw_mat_cd;
    String raw_mat_nm;
    String spec;
    String unit_nm;
    String cust_nm;
    String input_amt;
    String output_amt;
    String curr_amt;
    String loc;
    String basic_stock;
    String bal_stock;

    public RawVo() {

    }

    public RawVo(String raw_mat_cd, String raw_mat_nm, String spec, String unit_nm,
                 String cust_nm, String input_amt, String output_amt, String curr_amt,
                 String loc, String basic_stock, String bal_stock) {
        this.raw_mat_cd = raw_mat_cd;
        this.raw_mat_nm = raw_mat_nm;
        this.spec = spec;
        this.unit_nm = unit_nm;
        this.cust_nm = cust_nm;
        this.input_amt = input_amt;
        this.output_amt = output_amt;
        this.curr_amt = curr_amt;
        this.loc = loc;
        this.basic_stock = basic_stock;
        this.bal_stock = bal_stock;
    }

    public String getRaw_mat_cd() {
        return raw_mat_cd;
    }

    public String getRaw_mat_nm() {
        return raw_mat_nm;
    }

    public String getSpec() {
        return spec;
    }

    public String getUnit_nm() {
        return unit_nm;
    }

    public String getCust_nm() {
        return cust_nm;
    }

    public String getInput_amt() {
        return input_amt;
    }

    public String getOutput_amt() {
        return output_amt;
    }

    public String getCurr_amt() {
        return curr_amt;
    }

    public String getLoc() {
        return loc;
    }

    public String getBasic_stock() {
        return basic_stock;
    }

    public String getBal_stock() {
        return bal_stock;
    }

    public void setRaw_mat_cd(String raw_mat_cd) {
        this.raw_mat_cd = raw_mat_cd;
    }

    public void setRaw_mat_nm(String raw_mat_nm) {
        this.raw_mat_nm = raw_mat_nm;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setUnit_nm(String unit_nm) {
        this.unit_nm = unit_nm;
    }

    public void setCust_nm(String cust_nm) {
        this.cust_nm = cust_nm;
    }

    public void setInput_amt(String input_amt) {
        this.input_amt = input_amt;
    }

    public void setOutput_amt(String output_amt) {
        this.output_amt = output_amt;
    }

    public void setCurr_amt(String curr_amt) {
        this.curr_amt = curr_amt;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setBasic_stock(String basic_stock) {
        this.basic_stock = basic_stock;
    }

    public void setBal_stock(String bal_stock) {
        this.bal_stock = bal_stock;
    }
}
