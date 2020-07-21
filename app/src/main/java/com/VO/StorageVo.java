package com.VO;

public class StorageVo {

    String Storage_cd;
    String Storage_nm;
    String Comment;

    public StorageVo() {
    }

    public StorageVo(String storage_cd, String storage_nm, String comment) {
        Storage_cd = storage_cd;
        Storage_nm = storage_nm;
        Comment = comment;
    }

    public String getStorage_cd() {
        return Storage_cd;
    }

    public void setStorage_cd(String storage_cd) {
        Storage_cd = storage_cd;
    }

    public String getStorage_nm() {
        return Storage_nm;
    }

    public void setStorage_nm(String storage_nm) {
        Storage_nm = storage_nm;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
