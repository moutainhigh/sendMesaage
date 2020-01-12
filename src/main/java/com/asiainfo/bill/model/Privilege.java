package com.asiainfo.bill.model;

import java.util.Date;

public class Privilege {
    private Long privId;

    private String privCode;

    private String privName;

    private String privType;

    private String privDesc;

    private String privManageClass;

    private Date effDate;

    private Date expDate;

    private Long systemInfoId;

    private String statusCd;

    private Date statusDate;

    private Date createDate;

    private Long createStaff;

    private Date updateDate;

    private Long updateStaff;

    private Long regionId;

    public Long getPrivId() {
        return privId;
    }

    public void setPrivId(Long privId) {
        this.privId = privId;
    }

    public String getPrivCode() {
        return privCode;
    }

    public void setPrivCode(String privCode) {
        this.privCode = privCode == null ? null : privCode.trim();
    }

    public String getPrivName() {
        return privName;
    }

    public void setPrivName(String privName) {
        this.privName = privName == null ? null : privName.trim();
    }

    public String getPrivType() {
        return privType;
    }

    public void setPrivType(String privType) {
        this.privType = privType == null ? null : privType.trim();
    }

    public String getPrivDesc() {
        return privDesc;
    }

    public void setPrivDesc(String privDesc) {
        this.privDesc = privDesc == null ? null : privDesc.trim();
    }

    public String getPrivManageClass() {
        return privManageClass;
    }

    public void setPrivManageClass(String privManageClass) {
        this.privManageClass = privManageClass == null ? null : privManageClass.trim();
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

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }
}