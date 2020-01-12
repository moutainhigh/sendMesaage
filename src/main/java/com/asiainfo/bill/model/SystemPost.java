package com.asiainfo.bill.model;

import java.util.Date;

public class SystemPost {
    private Long sysPostId;

    private String sysPostCode;

    private String sysPostName;

    private String sysPostType;

    private String sysPostDesc;

    private Short initFlag;

    private Long orgId;

    private Long regionId;

    private Long systemInfoId;

    private String statusCd;

    private Date statusDate;

    private Date createDate;

    private Long createStaff;

    private Date updateDate;

    private Long updateStaff;

    public Long getSysPostId() {
        return sysPostId;
    }

    public void setSysPostId(Long sysPostId) {
        this.sysPostId = sysPostId;
    }

    public String getSysPostCode() {
        return sysPostCode;
    }

    public void setSysPostCode(String sysPostCode) {
        this.sysPostCode = sysPostCode == null ? null : sysPostCode.trim();
    }

    public String getSysPostName() {
        return sysPostName;
    }

    public void setSysPostName(String sysPostName) {
        this.sysPostName = sysPostName == null ? null : sysPostName.trim();
    }

    public String getSysPostType() {
        return sysPostType;
    }

    public void setSysPostType(String sysPostType) {
        this.sysPostType = sysPostType == null ? null : sysPostType.trim();
    }

    public String getSysPostDesc() {
        return sysPostDesc;
    }

    public void setSysPostDesc(String sysPostDesc) {
        this.sysPostDesc = sysPostDesc == null ? null : sysPostDesc.trim();
    }

    public Short getInitFlag() {
        return initFlag;
    }

    public void setInitFlag(Short initFlag) {
        this.initFlag = initFlag;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
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