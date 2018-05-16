package com.ztgm.iot.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/manager/*", "/decorators/admin/admin_index.ftl");
        builder.addDecoratorPath("/privilege/*", "/decorators/permission/admin_index.ftl");
        builder.addDecoratorPath("/user/*", "/decorators/user/user_index.ftl");
        builder.addDecoratorPath("/login.action", "/decorators/login_index.ftl");
        builder.addDecoratorPath("/", "/decorators/default_index.ftl");
        builder .addExcludedPath("/img/*")
                .addExcludedPath("/imgapp/*")
                .addExcludedPath("/js/*")
                .addExcludedPath("/vendor/*")
                .addExcludedPath("/interface/*");

    }
}
