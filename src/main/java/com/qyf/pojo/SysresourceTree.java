package com.qyf.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SysresourceTree implements Serializable{
    private static final long serialVersionUID = 3520828859437965029L;
    private String id;

    private Date createdatetime;

    private String description;

    private String iconcls;

    private String name;

    private BigDecimal seq;

    private Date updatedatetime;

    private String url;

    private String pid;

    private String resourcetypeid;

    private BigDecimal leafnode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls == null ? null : iconcls.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public Date getUpdatedatetime() {
        return updatedatetime;
    }

    public void setUpdatedatetime(Date updatedatetime) {
        this.updatedatetime = updatedatetime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getResourcetypeid() {
        return resourcetypeid;
    }

    public void setResourcetypeid(String resourcetypeid) {
        this.resourcetypeid = resourcetypeid == null ? null : resourcetypeid.trim();
    }

    public BigDecimal getLeafnode() {
        return leafnode;
    }

    public void setLeafnode(BigDecimal leafnode) {
        this.leafnode = leafnode;
    }

    public SysresourceTree(String id, Date createdatetime, String description, String iconcls, String name, BigDecimal seq, Date updatedatetime, String url, String pid, String resourcetypeid, BigDecimal leafnode) {

        this.id = id;
        this.createdatetime = createdatetime;
        this.description = description;
        this.iconcls = iconcls;
        this.name = name;
        this.seq = seq;
        this.updatedatetime = updatedatetime;
        this.url = url;
        this.pid = pid;
        this.resourcetypeid = resourcetypeid;
        this.leafnode = leafnode;
    }

    public SysresourceTree() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysresourceTree that = (SysresourceTree) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (createdatetime != null ? !createdatetime.equals(that.createdatetime) : that.createdatetime != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (iconcls != null ? !iconcls.equals(that.iconcls) : that.iconcls != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (seq != null ? !seq.equals(that.seq) : that.seq != null) return false;
        if (updatedatetime != null ? !updatedatetime.equals(that.updatedatetime) : that.updatedatetime != null)
            return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (resourcetypeid != null ? !resourcetypeid.equals(that.resourcetypeid) : that.resourcetypeid != null)
            return false;
        return leafnode != null ? leafnode.equals(that.leafnode) : that.leafnode == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdatetime != null ? createdatetime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (iconcls != null ? iconcls.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (seq != null ? seq.hashCode() : 0);
        result = 31 * result + (updatedatetime != null ? updatedatetime.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (resourcetypeid != null ? resourcetypeid.hashCode() : 0);
        result = 31 * result + (leafnode != null ? leafnode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysresourceTree{" +
                "id='" + id + '\'' +
                ", createdatetime=" + createdatetime +
                ", description='" + description + '\'' +
                ", iconcls='" + iconcls + '\'' +
                ", name='" + name + '\'' +
                ", seq=" + seq +
                ", updatedatetime=" + updatedatetime +
                ", url='" + url + '\'' +
                ", pid='" + pid + '\'' +
                ", resourcetypeid='" + resourcetypeid + '\'' +
                ", leafnode=" + leafnode +
                '}';
    }
}