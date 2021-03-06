package com.gsoft.shiro.config;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gsoft.shiro.entity.SysPermission;
import com.gsoft.shiro.entity.SysRole;
import com.gsoft.shiro.entity.SysUser;
import com.gsoft.shiro.service.SysUserService;

public class ShiroRealm extends AuthorizingRealm {
	
	 /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ShiroRealm.class);
	
    @Resource
    private SysUserService userService;

    //权限信息，包括角色以及权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	log.info("开始执行shiroRealm-->doGetAuthorizationInfo--获取权限信息");
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        SysUser user  = (SysUser)principals.getPrimaryPrincipal();
        List<SysRole> roles = user.getRoles();
        for(SysRole role:roles){
            authorizationInfo.addRole(role.getRoleId());
            List<SysPermission> perms = role.getPerms();
            for(SysPermission p:perms){
                authorizationInfo.addStringPermission(p.getId());
            }
        }
        
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
    	log.info("开始执行shiroRealm-->doGetAuthenticationInfo--验证用户---");
        //获取用户的输入的账号.
        String userName = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser user = userService.findByUserName(userName);
        
        log.info("----->>user--->{}",user);
        if(user == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

	

}
