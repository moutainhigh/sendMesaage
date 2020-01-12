package com.asiainfo.bill.model;

import java.util.Date;

public class SystemUser {
    private Long sysUserId;

    private Long staffId;

    private String sysUserCode;

    private String password;

    private Integer pwdErrCnt;

    private Long pwdSmsTel;

    private String pwdStatus;

    private Date pwdNewtime;

    private Integer pwdEffectDays;

    private Long regionId;

    private Long systemInfoId;

    private Integer limitCount;

    private Integer loginedNum;

    private String sysUserDesc;

    private Date effDate;

    private Date expDate;

    private String statusCd;

    private Date statusDate;

    private Date createDate;

    private Long createStaff;

    private Date updateDate;

    private Long updateStaff;

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getSysUserCode() {
        return sysUserCode;
    }

    public void setSysUserCode(String sysUserCode) {
        this.sysUserCode = sysUserCode == null ? null : sysUserCode.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getPwdErrCnt() {
        return pwdErrCnt;
    }

    public void setPwdErrCnt(Integer pwdErrCnt) {
        this.pwdErrCnt = pwdErrCnt;
    }

    public Long getPwdSmsTel() {
        return pwdSmsTel;
    }

    public void setPwdSmsTel(Long pwdSmsTel) {
        this.pwdSmsTel = pwdSmsTel;
    }

    public String getPwdStatus() {
        return pwdStatus;
    }

    public void setPwdStatus(String pwdStatus) {
        this.pwdStatus = pwdStatus == null ? null : pwdStatus.trim();
    }

    public Date getPwdNewtime() {
        return pwdNewtime;
    }

    public void setPwdNewtime(Date pwdNewtime) {
        this.pwdNewtime = pwdNewtime;
    }

    public Integer getPwdEffectDays() {
        return pwdEffectDays;
    }

    public void setPwdEffectDays(Integer pwdEffectDays) {
        this.pwdEffectDays = pwdEffectDays;
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

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

    public Integer getLoginedNum() {
        return loginedNum;
    }

    public void setLoginedNum(Integer loginedNum) {
        this.loginedNum = loginedNum;
    }

    public String getSysUserDesc() {
        return sysUserDesc;
    }

    public void setSysUserDesc(String sysUserDesc) {
        this.sysUserDesc = sysUserDesc == null ? null : sysUserDesc.trim();
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