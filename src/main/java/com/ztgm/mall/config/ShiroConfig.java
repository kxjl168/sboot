package com.ztgm.mall.config;

import com.ztgm.mall.service.PermissionService;
import com.ztgm.mall.shiro.BackendShiroRealm;
import com.ztgm.mall.shiro.FrontShiroRealm;
import com.ztgm.mall.shiro.MainRealmAuthenticator;
import com.ztgm.mall.shiro.MyFormAuthenticationFilter;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro 配置
 */
@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }

    @Bean("mainRealmAuthenticator")
    public Authenticator getMainRealmAuthenticator(Map definedRealms) {
        MainRealmAuthenticator mra = new MainRealmAuthenticator();

        mra.setDefinedRealms(definedRealms);
        return mra;
    }

    @Bean("definedRealms")
    public Map definedRealms(Realm backendShiroRealm,Realm frontShiroRealm) {
        Map definedRealms = new HashMap();

        definedRealms.put("backendShiroRealm",backendShiroRealm);
        definedRealms.put("frontShiroRealm",frontShiroRealm);

        return definedRealms;
    }


    @Bean(name = "frontShiroRealm")
    public FrontShiroRealm frontShiroRealm(EhCacheManager cacheManager) {
        FrontShiroRealm realm = new FrontShiroRealm();
        realm.setCacheManager(cacheManager);

        //比对原始密码  密码不加密
        SimpleCredentialsMatcher credentialsMatcher = new SimpleCredentialsMatcher();
        /*credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1);//*/
        //credentialsMatcher.set
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

    @Bean(name = "backendShiroRealm")
    public BackendShiroRealm backendShiroRealm(EhCacheManager cacheManager) {
        BackendShiroRealm realm = new BackendShiroRealm();
        realm.setCacheManager(cacheManager);

        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1);//
        //credentialsMatcher.set
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(BackendShiroRealm myShiroRealm, Authenticator mainRealmAuthenticator) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setAuthenticator(mainRealmAuthenticator);

        dwsm.setRealm(myShiroRealm);
//      <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
        dwsm.setCacheManager(getEhCacheManager());	
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }



    /**
     * ShiroFilter<br/>
     * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
     * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
     *
     * @param myShiroRealm
     * @param stuService
     * @param scoreDao
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, PermissionService permissionService) {
        //
        ShiroFilterFactoryBean shiroFilterFactoryBean = new org.apache.shiro.spring.web.ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.action");
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/manager/admin/index.action");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized.action");

        Map filters = new HashMap<String, Filter>();
        MyFormAuthenticationFilter formAuthenticationFilter = new MyFormAuthenticationFilter();

        formAuthenticationFilter.setUsernameParam("account");//页面userName 输入域名称
        formAuthenticationFilter.setPasswordParam("password");//页面password 输入域名称
        formAuthenticationFilter.setValidateCodeKey("captchaCode");//页面验证码 输入域名称

        filters.put("authc", formAuthenticationFilter);


        //logoutFilter 设置RedirectUrl ，否则再次登录成功时会自动跳转到 项目根目录/
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/login.action");
        filters.put("logout", logoutFilter);

        shiroFilterFactoryBean.setFilters(filters);

        //必须用能够保证顺序的map，否则不能保证拦截的顺序
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/front/isLogin.action", "anon");
        filterChainDefinitionMap.put("/front/registPage.html", "anon");
        filterChainDefinitionMap.put("/front/regist.action", "anon");

        filterChainDefinitionMap.put("/front/loginPage.html", "anon");
        filterChainDefinitionMap.put("/front/login.action", "anon");

        
        
        filterChainDefinitionMap.put("/FileSvr/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        filterChainDefinitionMap.put("/file/**", "anon");

        filterChainDefinitionMap.put("/statics/**", "anon");
        
        
        filterChainDefinitionMap.put("/public/**", "anon");
        
        //用户中心管理-暂时不验证
        //filterChainDefinitionMap.put("/userCenter/**", "anon");
        
        
        filterChainDefinitionMap.put("/websocket/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/imgapp/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/vendor/**", "anon");
        filterChainDefinitionMap.put("/longin.action", "anon");
        filterChainDefinitionMap.put("/validateCode.action", "anon");

        filterChainDefinitionMap.put("/manager/admin/registerAdminPage.action", "anon");
        filterChainDefinitionMap.put("/manager/admin/registerSubmit.action", "anon");
        filterChainDefinitionMap.put("/interface/**", "anon");
        filterChainDefinitionMap.put("/mqtt/auth", "anon");




        filterChainDefinitionMap.put("/logout.action", "logout");

        //filterChainDefinitionMap.put("/admin/index.action", "perms[admin:view]");
//读取数据库中配置已实现功能
        List<Map> permissions = permissionService.selectPermissions();
        if(null!=permissions){
            for (Map p: permissions){

                filterChainDefinitionMap.put((String) p.get("url"), "perms[" + p.get("percode") + "]");
            }
        }


        filterChainDefinitionMap.put("/**", "authc");




        //shiroFilterFactoryBean.setFilterChainDefinitions("");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);


        return shiroFilterFactoryBean;
    }





    /*@Bean
    public MyFormAuthenticationFilter getMyFormAuthenticationFilter(){
        MyFormAuthenticationFilter formAuthenticationFilter = new MyFormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("account");//页面userName 输入域名称
        formAuthenticationFilter.setPasswordParam("password");//页面password 输入域名称
        formAuthenticationFilter.setValidateCodeKey("captchaCode");//页面验证码 输入域名称

        return formAuthenticationFilter;
    }*/
}