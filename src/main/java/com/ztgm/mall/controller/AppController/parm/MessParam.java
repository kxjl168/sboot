package com.ztgm.mall.controller.AppController.parm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.message")
public class MessParam extends ComParam{


}
