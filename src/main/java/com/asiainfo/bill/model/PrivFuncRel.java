package com.asiainfo.bill.model;

import java.util.Date;

public class PrivFuncRel {
    private Long privFuncRelId;

    private Long privId;

    private String privRefType;

    private String privRefId;

    private Date effDate;

    private Date expDate;

    private Long systemInfoId;

    private String statusCd;

    private Date statusDate;

    private Date createDate;

    private Long createStaff;

    private Date updateDate;

    private Long updateStaff;

    public Long getPrivFuncRelId() {
        return privFuncRelId;
    }

    public void setPrivFuncRelId(Long privFuncRelId) {
        this.privFuncRelId = privFuncRelId;
    }

    public Long getPrivId() {
        return privId;
    }

    public void setPrivId(Long privId) {
        this.privId = privId;
    }

    public String getPrivRefType() {
        return privRefType;
    }

    public void setPrivRefType(String privRefType) {
        this.privRefType = privRefType == null ? null : privRefType.trim();
    }

    public String getPrivRefId() {
        return privRefId;
    }

    public void setPrivRefId(String privRefId) {
        this.privRefId = privRefId == null ? null : privRefId.trim();
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

    public Long getSystemInfoId() {
        return systemInfoId;
    }

    public void setSystemInfoId(Long systemInfoId) {
        this.systemInfoId = systemInfoId;
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
}