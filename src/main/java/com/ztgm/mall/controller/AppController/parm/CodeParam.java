package com.ztgm.mall.controller.AppController.parm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.code")
public class CodeParam extends ComParam{

}
