package com.ztgm.mall.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author handsome Xiao
 */
@Data
public class MUser {
    private String id;

    private String nickname;

    private String name;

    private Integer sexuality;

    private String headImg;

    private Integer scoring;

    private String email;

    private Date createTime;

    private Date updateTime;

    private Integer state;

    private String note;

    private String pass;

    private String phone;
}