
package com.caidapao.fgo.module.servant.entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;

/**
 * 从者
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "fgo_servant")
public class Servant {

    public static final String SERVANT_ID = "servantId";
    public static final String ENGLISH_NAME = "englishName";
    public static final String JAPANESE_NAME = "japaneseName";
    public static final String CHINESE_NAME = "chineseName";
    public static final String CLASS_CODE = "classCode";
    public static final String BASE_HP = "baseHp";
    public static final String BASE_DAMAGE = "baseDamage";
    public static final String MAX_HP = "maxHp";
    public static final String MAX_DAMAGE = "maxDamage";
    public static final String NOBLE_PHANTASM_TYPE = "noblePhantasmType";
    public static final String NOBLE_PHANTASM_NAME = "noblePhantasmName";

    /**
     * 从者id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SERVANT_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long servantId;
    /**
     * 从者英文名
     */
    @Column(name = "ENGLISH_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String englishName;
    /**
     * 从者日文名
     */
    @Column(name = "JAPANESE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String japaneseName;
    /**
     * 从者中文名
     */
    @Column(name = "CHINESE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String chineseName;
    /**
     * 职介编码
     */
    @Column(name = "CLASS_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
    private java.lang.String classCode;
    /**
     * 基础生命值
     */
    @Column(name = "BASE_HP", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private java.lang.Long baseHp;
    /**
     * 基础攻击力
     */
    @Column(name = "BASE_DAMAGE", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private java.lang.Long baseDamage;
    /**
     * 最大生命值
     */
    @Column(name = "MAX_HP", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private java.lang.Long maxHp;
    /**
     * 最大攻击力
     */
    @Column(name = "MAX_DAMAGE", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private java.lang.Long maxDamage;
    /**
     * 宝具类型
     */
    @Column(name = "NOBLE_PHANTASM_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
    private java.lang.String noblePhantasmType;
    /**
     * 宝具名称
     */
    @Column(name = "NOBLE_PHANTASM_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String noblePhantasmName;
    /**
     * 配音作者
     */
    @Column(name = "CV_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String cvName;
    /**
     * 画师
     */
    @Column(name = "PAINTER", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String painter;
    /**
     * 星级
     */
    @Column(name = "STAR", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
    private java.lang.String star;
    /**
     * 幸运值
     */
    @Column(name = "LUCKY", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
    private java.lang.String lucky;
    /**
     * 红卡数量
     */
    @Column(name = "CARD_BUSTER", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
    private java.lang.String cardBuster;
    /**
     * 蓝卡数量
     */
    @Column(name = "CARD_ARTS", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
    private java.lang.String cardArts;
    /**
     * 蓝卡数量
     */
    @Column(name = "CARD_QUICK", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
    private java.lang.String cardQuick;
    /**
     * 身高
     */
    @Column(name = "HEIGHT", unique = false, nullable = true, insertable = true, updatable = true, length = 8)
    private java.lang.String height;
    /**
     * 体重
     */
    @Column(name = "WEIGHT", unique = false, nullable = true, insertable = true, updatable = true, length = 8)
    private java.lang.String weight ;
    /**
     * 地域
     */
    @Column(name = "REGION", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
    private java.lang.String region;
    /**
     * 性别
     */
    @Column(name = "GENDER", unique = false, nullable = true, insertable = true, updatable = true, length = 8)
    private java.lang.String gender ;
    /**
     * 属性
     */
    @Column(name = "ATTRIBUTES", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String attributes;
    /**
     * 详情
     */
    @Column(name = "DETAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
    private byte[] detail;

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getLucky() {
        return lucky;
    }

    public void setLucky(String lucky) {
        this.lucky = lucky;
    }

    public String getCardBuster() {
        return cardBuster;
    }

    public void setCardBuster(String cardBuster) {
        this.cardBuster = cardBuster;
    }

    public String getCardArts() {
        return cardArts;
    }

    public void setCardArts(String cardArts) {
        this.cardArts = cardArts;
    }

    public String getCardQuick() {
        return cardQuick;
    }

    public void setCardQuick(String cardQuick) {
        this.cardQuick = cardQuick;
    }

    public Servant() {
    }

    public Servant(
            java.lang.Long servantId
    ) {
        this.servantId = servantId;
    }

    public byte[] getDetail() {
        return detail;
    }

    public void setDetail(byte[] detail) {
        this.detail = detail;
    }

    public void setDetailStr(String value) {
        try {
            this.detail = StringUtils.isBlank(value) ? null : value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getDetailStr() {
        try {
            return this.detail == null ? null : new String(this.detail, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }



    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getPainter() {
        return painter;
    }

    public void setPainter(String painter) {
        this.painter = painter;
    }

    public void setServantId(java.lang.Long value) {
        this.servantId = value;
    }

    public java.lang.Long getServantId() {
        return this.servantId;
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

    public void setEnglishName(java.lang.String value) {
        this.englishName = value;
    }

    public java.lang.String getEnglishName() {
        return this.englishName;
    }

    public void setJapaneseName(java.lang.String value) {
        this.japaneseName = value;
    }

    public java.lang.String getJapaneseName() {
        return this.japaneseName;
    }

    public void setChineseName(java.lang.String value) {
        this.chineseName = value;
    }

    public java.lang.String getChineseName() {
        return this.chineseName;
    }

    public void setClassCode(java.lang.String value) {
        this.classCode = value;
    }

    public java.lang.String getClassCode() {
        return this.classCode;
    }

    public void setBaseHp(java.lang.Long value) {
        this.baseHp = value;
    }

    public java.lang.Long getBaseHp() {
        return this.baseHp;
    }

    public void setBaseDamage(java.lang.Long value) {
        this.baseDamage = value;
    }

    public java.lang.Long getBaseDamage() {
        return this.baseDamage;
    }

    public void setMaxHp(java.lang.Long value) {
        this.maxHp = value;
    }

    public java.lang.Long getMaxHp() {
        return this.maxHp;
    }

    public void setMaxDamage(java.lang.Long value) {
        this.maxDamage = value;
    }

    public java.lang.Long getMaxDamage() {
        return this.maxDamage;
    }

    public void setNoblePhantasmType(java.lang.String value) {
        this.noblePhantasmType = value;
    }

    public java.lang.String getNoblePhantasmType() {
        return this.noblePhantasmType;
    }

    public void setNoblePhantasmName(java.lang.String value) {
        this.noblePhantasmName = value;
    }

    public java.lang.String getNoblePhantasmName() {
        return this.noblePhantasmName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("SERVANT_ID", getServantId())
                .append("ENGLISH_NAME", getEnglishName())
                .append("JAPANESE_NAME", getJapaneseName())
                .append("CHINESE_NAME", getChineseName())
                .append("CLASS_CODE", getClassCode())
                .append("BASE_HP", getBaseHp())
                .append("BASE_DAMAGE", getBaseDamage())
                .append("MAX_HP", getMaxHp())
                .append("MAX_DAMAGE", getMaxDamage())
                .append("NOBLE_PHANTASM_TYPE", getNoblePhantasmType())
                .append("NOBLE_PHANTASM_NAME", getNoblePhantasmName())
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getServantId())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getServantId() == null) {
            return false;
        }
        if (!(obj instanceof Servant)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Servant other = (Servant) obj;
        return new EqualsBuilder()
                .append(getServantId(), other.getServantId())
                .isEquals();
    }
}





