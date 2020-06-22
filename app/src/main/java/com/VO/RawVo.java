package com.VO;

public class RawVo {

    String raw_mat_cd;
    String raw_mat_nm;
    String spec;
    String raw_mat_gubun;
    String type_cd;
    String input_unit;
    String output_unit;
    String input_price;
    String output_price;
    String st_status_yn;
    String raw_strage;
    String used_cd;
    String basic_stock;
    String bal_stock;
    String check_gubun;
    String prop_stock;

    public RawVo() {

    }

    public RawVo(String raw_mat_cd, String raw_mat_nm, String spec, String raw_mat_gubun, String type_cd,
                 String input_unit, String output_unit, String input_price, String output_price, String st_status_yn,
                 String raw_strage, String used_cd, String basic_stock, String bal_stock, String check_gubun, String prop_stock) {
        this.raw_mat_cd = raw_mat_cd;
        this.raw_mat_nm = raw_mat_nm;
        this.spec = spec;
        this.raw_mat_gubun = raw_mat_gubun;
        this.type_cd = type_cd;
        this.input_unit = input_unit;
        this.output_unit = output_unit;
        this.input_price = input_price;
        this.output_price = output_price;
        this.st_status_yn = st_status_yn;
        this.raw_strage = raw_strage;
        this.used_cd = used_cd;
        this.basic_stock = basic_stock;
        this.bal_stock = bal_stock;
        this.check_gubun = check_gubun;
        this.prop_stock = prop_stock;
    }

    public String getRaw_mat_cd() {
        return raw_mat_cd;
    }

    public void setRaw_mat_cd(String raw_mat_cd) {
        this.raw_mat_cd = raw_mat_cd;
    }

    public String getRaw_mat_nm() {
        return raw_mat_nm;
    }

    public void setRaw_mat_nm(String raw_mat_nm) {
        this.raw_mat_nm = raw_mat_nm;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getRaw_mat_gubun() {
        return raw_mat_gubun;
    }

    public void setRaw_mat_gubun(String raw_mat_gubun) {
        this.raw_mat_gubun = raw_mat_gubun;
    }

    public String getType_cd() {
        return type_cd;
    }

    public void setType_cd(String type_cd) {
        this.type_cd = type_cd;
    }

    public String getInput_unit() {
        return input_unit;
    }

    public void setInput_unit(String input_unit) {
        this.input_unit = input_unit;
    }

    public String getOutput_unit() {
        return output_unit;
    }

    public void setOutput_unit(String output_unit) {
        this.output_unit = output_unit;
    }

    public String getInput_price() {
        return input_price;
    }

    public void setInput_price(String input_price) {
        this.input_price = input_price;
    }

    public String getOutput_price() {
        return output_price;
    }

    public void setOutput_price(String output_price) {
        this.output_price = output_price;
    }

    public String getSt_status_yn() {
        return st_status_yn;
    }

    public void setSt_status_yn(String st_status_yn) {
        this.st_status_yn = st_status_yn;
    }

    public String getRaw_strage() {
        return raw_strage;
    }

    public void setRaw_strage(String raw_strage) {
        this.raw_strage = raw_strage;
    }

    public String getUsed_cd() {
        return used_cd;
    }

    public void setUsed_cd(String used_cd) {
        this.used_cd = used_cd;
    }

    public String getBasic_stock() {
        return basic_stock;
    }

    public void setBasic_stock(String basic_stock) {
        this.basic_stock = basic_stock;
    }

    public String getBal_stock() {
        return bal_stock;
    }

    public void setBal_stock(String bal_stock) {
        this.bal_stock = bal_stock;
    }

    public String getCheck_gubun() {
        return check_gubun;
    }

    public void setCheck_gubun(String check_gubun) {
        this.check_gubun = check_gubun;
    }

    public String getProp_stock() {
        return prop_stock;
    }

    public void setProp_stock(String prop_stock) {
        this.prop_stock = prop_stock;
    }

}
