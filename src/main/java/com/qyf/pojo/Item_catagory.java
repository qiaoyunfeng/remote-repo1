package com.qyf.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Item_catagory implements Serializable {

    private static final long serialVersionUID = 2957363214992592359L;
    private String id;

    private Integer parentId;

    private String name;

    private BigDecimal status; //状态

    private BigDecimal sortOrder; //种类

    private Integer isParent;//表示叶子节点
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updated;

    private String state = "closed";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public BigDecimal getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(BigDecimal sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {

        this.isParent = isParent;
        if (this.isParent != null && this.isParent == 0) {
            this.state = "open";
        }

    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item_catagory that = (Item_catagory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (sortOrder != null ? !sortOrder.equals(that.sortOrder) : that.sortOrder != null) return false;
        if (isParent != null ? !isParent.equals(that.isParent) : that.isParent != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (updated != null ? !updated.equals(that.updated) : that.updated != null) return false;
        return state != null ? state.equals(that.state) : that.state == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (sortOrder != null ? sortOrder.hashCode() : 0);
        result = 31 * result + (isParent != null ? isParent.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item_catagory{" +
                "id='" + id + '\'' +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", sortOrder=" + sortOrder +
                ", isParent=" + isParent +
                ", created=" + created +
                ", updated=" + updated +
                ", state='" + state + '\'' +
                '}';
    }

    public Item_catagory(String id, Integer parentId, String name, BigDecimal status, BigDecimal sortOrder, Integer isParent, Date created, Date updated, String state) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.status = status;
        this.sortOrder = sortOrder;
        this.isParent = isParent;
        this.created = created;
        this.updated = updated;
        this.state = state;
    }

    public Item_catagory() {
    }
}