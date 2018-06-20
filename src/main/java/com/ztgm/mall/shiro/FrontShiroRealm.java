package com.ztgm.mall.shiro;

import com.ztgm.mall.pojo.MUser;
import com.ztgm.mall.pojo.Manager;
import com.ztgm.mall.pojo.Permission;
import com.ztgm.mall.service.CustomerService;
import com.ztgm.mall.service.ManagerService;
import com.ztgm.mall.service.PermissionService;
import com.ztgm.mall.service.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BackendShiroRealm
 *
 * @create 2016年1月13日
 */
public class FrontShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(FrontShiroRealm.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ManagerService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 权限类型 1：菜单 2：具体功能连接
     */
    public static final String permissionType_1 = "1";
    public static final String permissionType_2 = "2";

    public static Map users = new HashMap();
    public static Map roles = new HashMap();
    public static Map perms = new HashMap();

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     *
     * @see 经测试：本例中该方法的调用时机为需授权资源被访问时
     * @see 经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * @see 经测试：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("##################执行Shiro权限认证##################");
        /*// 获取当前登录输入的用户名，等价于(String)
        // principalCollection.fromRealm(getName()).iterator().next();
        Map principal = (Map) principalCollection.getPrimaryPrincipal();

        List<Permission> userPermissions = (List<Permission>) principal.get("userPermissions");*/

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /*if (null != userPermissions) {
            for (Permission up : userPermissions) {
                String pStr = (String) up.getPercode();
                if (pStr != null && !pStr.equals("null"))
                    info.addStringPermission(pStr);
            }
        }*/

        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // UsernamePasswordToken对象用来存放提交的登录信息

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        /*Manager user = new Manager();

        user.setUsername(userName);*/

        MUser mUser = customerService.getCustomerByPhone(token.getUsername());
        //List<Manager> users = null;
        // 未找到用户信息
        if (null == mUser) {
            logger.info("登陆未找到用户信息，用户名：" + userName);
            return null;
        }


        Map principal = new HashMap();
        /*List<Permission> allUserPermissions = new ArrayList<>();
        List<Permission> userPermissions;

        List<Permission> userMenus = new ArrayList<>();

        // 查询用户角色和权限
        List<Map> roles = roleService.selectRoleByManagerId(user.getId());

        List<Permission> rolePermissions = permissionService.selectPermissionsByManagerId(user.getId());
        if (null != roles) {

            if (null != rolePermissions) {
                for (Permission rr : rolePermissions) {

                    if (!allUserPermissions.contains(rr))
                        allUserPermissions.add(rr);
                }

                for (Permission rr : rolePermissions) {

                    if (permissionType_1.equals(rr.getType())) {
                        userPermissions = new ArrayList();
                        String menuid = String.valueOf(rr.getId());
                        for (Permission r2 : rolePermissions) {
                            String pid = String.valueOf(r2.getParentid());
                            if (pid.equals(menuid))
                                userPermissions.add(r2);
                        }

                        rr.setPermissions(userPermissions);

                        userMenus.add(rr);

                    }

                }


            }
        }*/

        principal.put("userId", mUser.getId());
        principal.put("userPhone", mUser.getPhone());
        principal.put("userName", mUser.getName());
        //principal.put("userMenus", userMenus);
        //principal.put("userPermissions", allUserPermissions);
        //principal.put("roles", roles);

        AuthenticationInfo authenticationInfo = null;

        // 明文方式验证密码
        // authenticationInfo = new
        // SimpleAuthenticationInfo(token.getUsername(),users.get(token.getUsername()),getName());

        // 明文方式验证密码
        authenticationInfo = new SimpleAuthenticationInfo(principal, mUser.getPass(),getName());

        return authenticationInfo;
    }
}