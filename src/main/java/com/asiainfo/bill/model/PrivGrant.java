package com.asiainfo.bill.model;

import java.util.Date;

public class PrivGrant {
    private Long privGrantId;

    private Long privId;

    private String manageClass;

    private String grantObjType;

    private Long grantObjId;

    private String operType;

    private Date effDate;

    private Date expDate;

    private String statusCd;

    private Date statusDate;

    private Date createDate;

    private Long createStaff;

    private Date updateDate;

    private Long updateStaff;

    private Long parPrivId;

    public Long getPrivGrantId() {
        return privGrantId;
    }

    public void setPrivGrantId(Long privGrantId) {
        this.privGrantId = privGrantId;
    }

    public Long getPrivId() {
        return privId;
    }

    public void setPrivId(Long privId) {
        this.privId = privId;
    }

    public String getManageClass() {
        return manageClass;
    }

    public void setManageClass(String manageClass) {
        this.manageClass = manageClass == null ? null : manageClass.trim();
    }

    public String getGrantObjType() {
        return grantObjType;
    }

    public void setGrantObjType(String grantObjType) {
        this.grantObjType = grantObjType == null ? null : grantObjType.trim();
    }

    public Long getGrantObjId() {
        return grantObjId;
    }

    public void setGrantObjId(Long grantObjId) {
        this.grantObjId = grantObjId;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType == null ? null : operType.trim();
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Long getParPrivId() {
        return parPrivId;
    }

    public void setParPrivId(Long parPrivId) {
        this.parPrivId = parPrivId;
    }
}