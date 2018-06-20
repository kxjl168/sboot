package com.ztgm.mall.pojo;

import java.util.Date;

import com.ztgm.mall.util.DateUtil;
import lombok.Data;

/**
 * @author handsome Xiao
 */
@Data
public class UserQuestion {
    private String id;

    private String commodityId;

    private Integer state;

    private String userId;

    private String createTime;
    
    private String question;
    
    private String answer;
    

    private String userName;
    private String goodName;
}