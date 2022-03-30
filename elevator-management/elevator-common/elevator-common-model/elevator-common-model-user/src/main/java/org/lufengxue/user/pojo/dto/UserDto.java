package org.lufengxue.user.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue.pojo.po
 * 日    期:  2022-03-2022/3/29
 * 时    间:  16:47
 * 描    述:
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name="ele_user")
public class UserDto implements Serializable {

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    @Column(name = "name")
    private String userName;

    @ApiModelProperty("用户密码")
    @Column(name = "password")
    private String password;

    @ApiModelProperty("用户性别")
    @Column(name = "sex")
    private String sex;

    @ApiModelProperty("用户电话号码")
    @Column(name = "phone")
    private String phone;

    @ApiModelProperty("创建时间")
    @Column(name = "created_date")
    private Date createdDate;

    @ApiModelProperty("更新时间")
    @Column(name = "updated_date")
    private Date updatedDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
