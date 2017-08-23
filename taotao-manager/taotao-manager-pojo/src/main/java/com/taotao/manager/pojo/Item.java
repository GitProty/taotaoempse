package com.taotao.manager.pojo;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品信息数据模型
 * @author Steven
 *
 */
@Table(name = "tb_item")
public class Item extends BasePojo {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;	//商品id，同时也是商品编号

    @Column
    private String title;	//商品标题

    @Column(name = "sell_point")
    private String sellPoint;	//商品卖点

    @Column
    private Double price;	//商品价格，单位为：分

    @Column
    private Integer num;	//库存数量

    @Column
    private String barcode;		//商品条形码

    @Column
    private String image;	//商品图片

    @Column
    private Long cid;	//所属类目，叶子类目

    @Column
    private Integer status;		//商品状态，1-正常，2-下架，3-删除

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
    	//四舍五入，解决double计算精度的问题，保留两位小数
    	DecimalFormat format = new DecimalFormat("#.00");
    	if(price != null){
    		price = new Double(format.format(price));
    	}
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price
                + ", num=" + num + ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status="
                + status + "]";
    }

}
