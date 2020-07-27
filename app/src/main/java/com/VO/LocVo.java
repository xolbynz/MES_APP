package com.VO;

public class LocVo {

    String Storage_cd;
    String Loc_cd;
    String Loc_nm;
    String Comment;

    public LocVo(){

    }

    public LocVo(String storage_cd, String loc_cd, String loc_nm, String comment) {
        Storage_cd = storage_cd;
        Loc_cd = loc_cd;
        Loc_nm = loc_nm;
        Comment = comment;
    }

    public String getStorage_cd() {
        return Storage_cd;
    }

    public void setStorage_cd(String storage_cd) {
        Storage_cd = storage_cd;
    }

    public String getLoc_cd() {
        return Loc_cd;
    }

    public void setLoc_cd(String loc_cd) {
        Loc_cd = loc_cd;
    }

    public String getLoc_nm() {
        return Loc_nm;
    }

    public void setLoc_nm(String loc_nm) {
        Loc_nm = loc_nm;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
