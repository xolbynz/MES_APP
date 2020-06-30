package com.VO;

class ItemVo {


    String Item_cd;
    String Item_nm;
    String Item_gubun;
    String Item_gubun_nm;
    String Cust_cd;
    String Cust_nm;
    String Spec;
    String Type_cd;
    String Type_nm;
    String Line_cd;
    String Line_nm;
    String Unit_cd;
    String Unit_nm;
    String Stock_loc;
    String Prop_stock;
    String Bal_stock;
    String Basix_stock;
    String Item_weight;
    String Input_price;
    String Output_price;
    String Charge_amt;
    String Pack_amt;
    String Print_Yn;
    String Used_cd;
    String Input_date;
    String Box_bar_cd;
    String Box_amt;
    String Vat_cd;
    String Comment;
    String Item_chk_yn;
    String Link_cd;
    String Mold_cd;
    public void ItemVo(){

    }

    public ItemVo(String item_cd, String item_nm, String item_gubun, String item_gubun_nm,
                  String cust_cd, String cust_nm, String spec, String type_cd, String type_nm,
                  String line_cd, String line_nm, String unit_cd, String unit_nm,
                  String stock_loc, String prop_stock, String bal_stock, String basix_stock,
                  String item_weight, String input_price, String output_price, String charge_amt,
                  String pack_amt, String print_Yn, String used_cd, String input_date,
                  String box_bar_cd, String box_amt, String vat_cd, String comment,
                  String item_chk_yn, String link_cd, String mold_cd) {
        Item_cd = item_cd;
        Item_nm = item_nm;
        Item_gubun = item_gubun;
        Item_gubun_nm = item_gubun_nm;
        Cust_cd = cust_cd;
        Cust_nm = cust_nm;
        Spec = spec;
        Type_cd = type_cd;
        Type_nm = type_nm;
        Line_cd = line_cd;
        Line_nm = line_nm;
        Unit_cd = unit_cd;
        Unit_nm = unit_nm;
        Stock_loc = stock_loc;
        Prop_stock = prop_stock;
        Bal_stock = bal_stock;
        Basix_stock = basix_stock;
        Item_weight = item_weight;
        Input_price = input_price;
        Output_price = output_price;
        Charge_amt = charge_amt;
        Pack_amt = pack_amt;
        Print_Yn = print_Yn;
        Used_cd = used_cd;
        Input_date = input_date;
        Box_bar_cd = box_bar_cd;
        Box_amt = box_amt;
        Vat_cd = vat_cd;
        Comment = comment;
        Item_chk_yn = item_chk_yn;
        Link_cd = link_cd;
        Mold_cd = mold_cd;
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

    public String getItem_gubun() {
        return Item_gubun;
    }

    public void setItem_gubun(String item_gubun) {
        Item_gubun = item_gubun;
    }

    public String getItem_gubun_nm() {
        return Item_gubun_nm;
    }

    public void setItem_gubun_nm(String item_gubun_nm) {
        Item_gubun_nm = item_gubun_nm;
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

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String spec) {
        Spec = spec;
    }

    public String getType_cd() {
        return Type_cd;
    }

    public void setType_cd(String type_cd) {
        Type_cd = type_cd;
    }

    public String getType_nm() {
        return Type_nm;
    }

    public void setType_nm(String type_nm) {
        Type_nm = type_nm;
    }

    public String getLine_cd() {
        return Line_cd;
    }

    public void setLine_cd(String line_cd) {
        Line_cd = line_cd;
    }

    public String getLine_nm() {
        return Line_nm;
    }

    public void setLine_nm(String line_nm) {
        Line_nm = line_nm;
    }

    public String getUnit_cd() {
        return Unit_cd;
    }

    public void setUnit_cd(String unit_cd) {
        Unit_cd = unit_cd;
    }

    public String getUnit_nm() {
        return Unit_nm;
    }

    public void setUnit_nm(String unit_nm) {
        Unit_nm = unit_nm;
    }

    public String getStock_loc() {
        return Stock_loc;
    }

    public void setStock_loc(String stock_loc) {
        Stock_loc = stock_loc;
    }

    public String getProp_stock() {
        return Prop_stock;
    }

    public void setProp_stock(String prop_stock) {
        Prop_stock = prop_stock;
    }

    public String getBal_stock() {
        return Bal_stock;
    }

    public void setBal_stock(String bal_stock) {
        Bal_stock = bal_stock;
    }

    public String getBasix_stock() {
        return Basix_stock;
    }

    public void setBasix_stock(String basix_stock) {
        Basix_stock = basix_stock;
    }

    public String getItem_weight() {
        return Item_weight;
    }

    public void setItem_weight(String item_weight) {
        Item_weight = item_weight;
    }

    public String getInput_price() {
        return Input_price;
    }

    public void setInput_price(String input_price) {
        Input_price = input_price;
    }

    public String getOutput_price() {
        return Output_price;
    }

    public void setOutput_price(String output_price) {
        Output_price = output_price;
    }

    public String getCharge_amt() {
        return Charge_amt;
    }

    public void setCharge_amt(String charge_amt) {
        Charge_amt = charge_amt;
    }

    public String getPack_amt() {
        return Pack_amt;
    }

    public void setPack_amt(String pack_amt) {
        Pack_amt = pack_amt;
    }

    public String getPrint_Yn() {
        return Print_Yn;
    }

    public void setPrint_Yn(String print_Yn) {
        Print_Yn = print_Yn;
    }

    public String getUsed_cd() {
        return Used_cd;
    }

    public void setUsed_cd(String used_cd) {
        Used_cd = used_cd;
    }

    public String getInput_date() {
        return Input_date;
    }

    public void setInput_date(String input_date) {
        Input_date = input_date;
    }

    public String getBox_bar_cd() {
        return Box_bar_cd;
    }

    public void setBox_bar_cd(String box_bar_cd) {
        Box_bar_cd = box_bar_cd;
    }

    public String getBox_amt() {
        return Box_amt;
    }

    public void setBox_amt(String box_amt) {
        Box_amt = box_amt;
    }

    public String getVat_cd() {
        return Vat_cd;
    }

    public void setVat_cd(String vat_cd) {
        Vat_cd = vat_cd;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getItem_chk_yn() {
        return Item_chk_yn;
    }

    public void setItem_chk_yn(String item_chk_yn) {
        Item_chk_yn = item_chk_yn;
    }

    public String getLink_cd() {
        return Link_cd;
    }

    public void setLink_cd(String link_cd) {
        Link_cd = link_cd;
    }

    public String getMold_cd() {
        return Mold_cd;
    }

    public void setMold_cd(String mold_cd) {
        Mold_cd = mold_cd;
    }
}
