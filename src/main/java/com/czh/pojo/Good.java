package com.czh.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.apache.ibatis.annotations.One;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "good_tab")
@Data
public class Good {

    //首页商品详情
    public interface OneView{};




    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer gid;
    private Integer uid;
    private String location;
    private String imgurl;
//    物品状态 0表待审核 1表示借出 2表示空闲
    private Integer status;
    private String detail;
    private Double price;

    @JsonView(OneView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String type;
    private String name;

    @JsonView(OneView.class)
    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @JsonView(OneView.class)
    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
