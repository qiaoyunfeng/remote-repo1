package com.qyf.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class WorkBook implements Serializable{
    private static final long serialVersionUID = -3753333929037785084L;
    private BigDecimal id;

    private String code;

    private String describe;

    private String type;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public WorkBook(BigDecimal id, String code, String describe, String type) {
        this.id = id;
        this.code = code;
        this.describe = describe;
        this.type = type;
    }

    public WorkBook() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkBook workBook = (WorkBook) o;

        if (id != null ? !id.equals(workBook.id) : workBook.id != null) return false;
        if (code != null ? !code.equals(workBook.code) : workBook.code != null) return false;
        if (describe != null ? !describe.equals(workBook.describe) : workBook.describe != null) return false;
        return type != null ? type.equals(workBook.type) : workBook.type == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (describe != null ? describe.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WorkBook{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", describe='" + describe + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}