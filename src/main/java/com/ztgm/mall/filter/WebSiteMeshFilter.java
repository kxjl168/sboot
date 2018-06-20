package com.ztgm.mall.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

import com.ztgm.mall.config.CustomTagRuleBundle;

public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/manager/*", "/decorators/admin/admin_index.ftl");

        builder.addDecoratorPath("/privilege/*", "/decorators/permission/admin_index.ftl");
        builder.addDecoratorPath("/user/*", "/decorators/user/user_index.ftl");
        builder.addDecoratorPath("/public/index.action", "/decorators/public/index.ftl");
        builder.addDecoratorPath("/public/*", "/decorators/public/index1.ftl");

        builder.addDecoratorPath("/userCenter/*", "/decorators/public/userCenter.ftl");

        builder.addDecoratorPath("/front/*", "/decorators/public/index1.ftl");

        builder.addDecoratorPath("/login.action", "/decorators/login_index.ftl");
        builder.addDecoratorPath("/", "/decorators/default_index.ftl");
        builder .addExcludedPath("/img/*")
                .addExcludedPath("/imgapp/*")
                .addExcludedPath("/js/*")
                .addExcludedPath("/css/*")
                .addExcludedPath("/vendor/*")
                .addExcludedPath("/interface/*")
                .addExcludedPath("/FileSvr/*")
                .addExcludedPath("/upload/*")
                .addExcludedPath("/userCenter/order/exportOrders")
                
                
                ;

        
        //customer decorator  
        builder.addTagRuleBundle(new CustomTagRuleBundle()); 
    }
    
   
}
