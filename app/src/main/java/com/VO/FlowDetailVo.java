package com.VO;

class FlowDetailVo {

    String LOT_NO;
    String LOT_SUB;
    String F_STEP;
    String FLOW_CD;
    String SEQ;
    String F_SUB_DATE;
    String LOSS;
    String F_SUB_AMT;
    String POOR_CD;
    String POOR_AMT;
    String COMPLETE_YN;
    String CHECK_YN;
    String INPUT_YN;
    String INPUT_AMT;
    String FLOW_CHK_YN;
    String FLOW_NM;
    String INST_AMT;
    String Non_Input_amt;

    public FlowDetailVo() {

    }

    public FlowDetailVo(String LOT_NO, String LOT_SUB, String f_STEP, String FLOW_CD, String SEQ,
                        String f_SUB_DATE, String LOSS, String f_SUB_AMT, String POOR_CD,
                        String POOR_AMT, String COMPLETE_YN, String CHECK_YN, String INPUT_YN,
                        String INPUT_AMT, String FLOW_CHK_YN, String FLOW_NM, String INST_AMT,
                        String non_Input_amt) {
        this.LOT_NO = LOT_NO;
        this.LOT_SUB = LOT_SUB;
        F_STEP = f_STEP;
        this.FLOW_CD = FLOW_CD;
        this.SEQ = SEQ;
        F_SUB_DATE = f_SUB_DATE;
        this.LOSS = LOSS;
        F_SUB_AMT = f_SUB_AMT;
        this.POOR_CD = POOR_CD;
        this.POOR_AMT = POOR_AMT;
        this.COMPLETE_YN = COMPLETE_YN;
        this.CHECK_YN = CHECK_YN;
        this.INPUT_YN = INPUT_YN;
        this.INPUT_AMT = INPUT_AMT;
        this.FLOW_CHK_YN = FLOW_CHK_YN;
        this.FLOW_NM = FLOW_NM;
        this.INST_AMT = INST_AMT;
        Non_Input_amt = non_Input_amt;
    }

    public String getLOT_NO() {
        return LOT_NO;
    }

    public void setLOT_NO(String LOT_NO) {
        this.LOT_NO = LOT_NO;
    }

    public String getLOT_SUB() {
        return LOT_SUB;
    }

    public void setLOT_SUB(String LOT_SUB) {
        this.LOT_SUB = LOT_SUB;
    }

    public String getF_STEP() {
        return F_STEP;
    }

    public void setF_STEP(String f_STEP) {
        F_STEP = f_STEP;
    }

    public String getFLOW_CD() {
        return FLOW_CD;
    }

    public void setFLOW_CD(String FLOW_CD) {
        this.FLOW_CD = FLOW_CD;
    }

    public String getSEQ() {
        return SEQ;
    }

    public void setSEQ(String SEQ) {
        this.SEQ = SEQ;
    }

    public String getF_SUB_DATE() {
        return F_SUB_DATE;
    }

    public void setF_SUB_DATE(String f_SUB_DATE) {
        F_SUB_DATE = f_SUB_DATE;
    }

    public String getLOSS() {
        return LOSS;
    }

    public void setLOSS(String LOSS) {
        this.LOSS = LOSS;
    }

    public String getF_SUB_AMT() {
        return F_SUB_AMT;
    }

    public void setF_SUB_AMT(String f_SUB_AMT) {
        F_SUB_AMT = f_SUB_AMT;
    }

    public String getPOOR_CD() {
        return POOR_CD;
    }

    public void setPOOR_CD(String POOR_CD) {
        this.POOR_CD = POOR_CD;
    }

    public String getPOOR_AMT() {
        return POOR_AMT;
    }

    public void setPOOR_AMT(String POOR_AMT) {
        this.POOR_AMT = POOR_AMT;
    }

    public String getCOMPLETE_YN() {
        return COMPLETE_YN;
    }

    public void setCOMPLETE_YN(String COMPLETE_YN) {
        this.COMPLETE_YN = COMPLETE_YN;
    }

    public String getCHECK_YN() {
        return CHECK_YN;
    }

    public void setCHECK_YN(String CHECK_YN) {
        this.CHECK_YN = CHECK_YN;
    }

    public String getINPUT_YN() {
        return INPUT_YN;
    }

    public void setINPUT_YN(String INPUT_YN) {
        this.INPUT_YN = INPUT_YN;
    }

    public String getINPUT_AMT() {
        return INPUT_AMT;
    }

    public void setINPUT_AMT(String INPUT_AMT) {
        this.INPUT_AMT = INPUT_AMT;
    }

    public String getFLOW_CHK_YN() {
        return FLOW_CHK_YN;
    }

    public void setFLOW_CHK_YN(String FLOW_CHK_YN) {
        this.FLOW_CHK_YN = FLOW_CHK_YN;
    }

    public String getFLOW_NM() {
        return FLOW_NM;
    }

    public void setFLOW_NM(String FLOW_NM) {
        this.FLOW_NM = FLOW_NM;
    }

    public String getINST_AMT() {
        return INST_AMT;
    }

    public void setINST_AMT(String INST_AMT) {
        this.INST_AMT = INST_AMT;
    }

    public String getNon_Input_amt() {
        return Non_Input_amt;
    }

    public void setNon_Input_amt(String non_Input_amt) {
        Non_Input_amt = non_Input_amt;
    }
}