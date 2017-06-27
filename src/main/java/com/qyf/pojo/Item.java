package com.qyf.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Item  implements Serializable{
    private static final long serialVersionUID = -8494161436603939483L;
    private Integer id;

    private String title;

    private String sellPoint;

    private Integer price;

    private BigDecimal num;

    private String barcode;

    private String image;

    private BigDecimal cid;

    private BigDecimal status;
@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created;
@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updated;
  private  Item_catagory   catagory;

    public Item_catagory getCatagory() {
        return catagory;
    }

    public void setCatagory(Item_catagory catagory) {
        this.catagory = catagory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public BigDecimal getCid() {
        return cid;
    }

    public void setCid(BigDecimal cid) {
        this.cid = cid;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public Date getCreated() {
        if (created !=null){
            return this.created;
        }

        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        if(updated !=null){
            return this.updated;
        }
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Item(Integer id, String title, String sellPoint, Integer price, BigDecimal num, String barcode, String image, BigDecimal cid, BigDecimal status, Date created, Date updated, Item_catagory catagory) {

        this.id = id;
        this.title = title;
        this.sellPoint = sellPoint;
        this.price = price;
        this.num = num;
        this.barcode = barcode;
        this.image = image;
        this.cid = cid;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.catagory = catagory;
    }

    public Item() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (title != null ? !title.equals(item.title) : item.title != null) return false;
        if (sellPoint != null ? !sellPoint.equals(item.sellPoint) : item.sellPoint != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (num != null ? !num.equals(item.num) : item.num != null) return false;
        if (barcode != null ? !barcode.equals(item.barcode) : item.barcode != null) return false;
        if (image != null ? !image.equals(item.image) : item.image != null) return false;
        if (cid != null ? !cid.equals(item.cid) : item.cid != null) return false;
        if (status != null ? !status.equals(item.status) : item.status != null) return false;
        if (created != null ? !created.equals(item.created) : item.created != null) return false;
        if (updated != null ? !updated.equals(item.updated) : item.updated != null) return false;
        return catagory != null ? catagory.equals(item.catagory) : item.catagory == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (sellPoint != null ? sellPoint.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + (catagory != null ? catagory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", barcode='" + barcode + '\'' +
                ", image='" + image + '\'' +
                ", cid=" + cid +
                ", status=" + status +
                ", created=" + created +
                ", updated=" + updated +
                ", catagory=" + catagory +
                '}';
    }

}