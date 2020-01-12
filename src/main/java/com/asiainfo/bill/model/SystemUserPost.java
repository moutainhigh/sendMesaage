package com.asiainfo.bill.model;

import java.util.Date;

public class SystemUserPost {
    private Long sysUserPostId;

    private Long sysUserId;

    private Long sysPostId;

    private Long orgId;

    private Short defaultFlag;

    private Short hostFlag;

    private Date effDate;

    private Date expDate;

    private String statusCd;

    private Date statusDate;

    private Date createDate;

    private Long createStaff;

    private Date updateDate;

    private Long updateStaff;

    public Long getSysUserPostId() {
        return sysUserPostId;
    }

    public void setSysUserPostId(Long sysUserPostId) {
        this.sysUserPostId = sysUserPostId;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getSysPostId() {
        return sysPostId;
    }

    public void setSysPostId(Long sysPostId) {
        this.sysPostId = sysPostId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Short getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Short defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public Short getHostFlag() {
        return hostFlag;
    }

    public void setHostFlag(Short hostFlag) {
        this.hostFlag = hostFlag;
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
}