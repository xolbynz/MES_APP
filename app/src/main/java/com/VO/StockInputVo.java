package com.VO;

public class StockInputVo {


    String packing_date;
    String lot_no;
    String plan_no;
    String cust_nm;
    String item_nm;
    String item_std;
    String item_unit;
    String quantity;
    public  StockInputVo() {

    }



    public StockInputVo(String packing_date, String lot_no, String plan_no, String cust_nm, String item_nm, String item_std, String item_unit, String quantity) {
        this.packing_date = packing_date;
        this.lot_no = lot_no;
        this.plan_no = plan_no;
        this.cust_nm = cust_nm;
        this.item_nm = item_nm;
        this.item_std = item_std;
        this.item_unit = item_unit;
        this.quantity = quantity;
    }

    public void setPacking_date(String packing_date) {
        this.packing_date = packing_date;
    }

    public void setLot_no(String lot_no) {
        this.lot_no = lot_no;
    }

    public void setPlan_no(String plan_no) {
        this.plan_no = plan_no;
    }

    public void setCust_nm(String cust_nm) {
        this.cust_nm = cust_nm;
    }

    public void setItem_nm(String item_nm) {
        this.item_nm = item_nm;
    }

    public void setItem_std(String item_std) {
        this.item_std = item_std;
    }

    public void setItem_unit(String item_unit) {
        this.item_unit = item_unit;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPacking_date() {
        return packing_date;
    }

    public String getLot_no() {
        return lot_no;
    }

    public String getPlan_no() {
        return plan_no;
    }

    public String getCust_nm() {
        return cust_nm;
    }

    public String getItem_nm() {
        return item_nm;
    }

    public String getItem_std() {
        return item_std;
    }

    public String getItem_unit() {
        return item_unit;
    }

    public String getQuantity() {
        return quantity;
    }
}
