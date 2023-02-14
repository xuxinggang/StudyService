package com.xxg.study.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xxg
 * @since 2022-09-22
 */
@TableName("sys_member")
public class Member implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    /**
     * 会员名称
     */
    private String memNme;

    /**
     * 会员等级
     */
    private Long memLvl;

    /**
     * 会员所在地
     */
    private String menAddr;

    private LocalDateTime creTme;

    private LocalDateTime updTme;

    /**
     * 状态 0 待审批 1 审批
     */
    private String sts;

    private BigDecimal bal;

    private String rsvFld;

    private String rsvFld1;

    public Member(){}

    public Member(long l, long l1, String s) {
        this.id=l;
        this.userId=l;
        this.memNme=s;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMemNme() {
        return memNme;
    }

    public void setMemNme(String memNme) {
        this.memNme = memNme;
    }

    public Long getMemLvl() {
        return memLvl;
    }

    public void setMemLvl(Long memLvl) {
        this.memLvl = memLvl;
    }

    public String getMenAddr() {
        return menAddr;
    }

    public void setMenAddr(String menAddr) {
        this.menAddr = menAddr;
    }

    public LocalDateTime getCreTme() {
        return creTme;
    }

    public void setCreTme(LocalDateTime creTme) {
        this.creTme = creTme;
    }

    public LocalDateTime getUpdTme() {
        return updTme;
    }

    public void setUpdTme(LocalDateTime updTme) {
        this.updTme = updTme;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public BigDecimal getBal() {
        return bal;
    }

    public void setBal(BigDecimal bal) {
        this.bal = bal;
    }

    public String getRsvFld() {
        return rsvFld;
    }

    public void setRsvFld(String rsvFld) {
        this.rsvFld = rsvFld;
    }

    public String getRsvFld1() {
        return rsvFld1;
    }

    public void setRsvFld1(String rsvFld1) {
        this.rsvFld1 = rsvFld1;
    }

    @Override
    public String toString() {
        return "Member{" +
        "id=" + id +
        ", userId=" + userId +
        ", memNme=" + memNme +
        ", memLvl=" + memLvl +
        ", menAddr=" + menAddr +
        ", creTme=" + creTme +
        ", updTme=" + updTme +
        ", sts=" + sts +
        ", bal=" + bal +
        ", rsvFld=" + rsvFld +
        ", rsvFld1=" + rsvFld1 +
        "}";
    }
}
