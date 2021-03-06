package com.csl.plus.portal.config;

import com.csl.plus.portal.component.JwtAuthenticationTokenFilter;
import com.csl.plus.portal.component.RestAuthenticationEntryPoint;
import com.csl.plus.portal.component.RestfulAccessDeniedHandler;
import com.csl.plus.portal.ums.service.IUmsMemberLevelService;
import com.csl.plus.portal.ums.service.IUmsMemberPermissionService;
import com.csl.plus.portal.ums.service.IUmsMemberRoleService;
import com.csl.plus.portal.ums.service.IUmsMemberService;
import com.csl.plus.portal.vo.UmsMemberUserDetails;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.ums.entity.UmsMemberLevel;
import com.csl.plus.ums.entity.UmsMemberPermission;
import com.csl.plus.ums.entity.UmsMemberRole;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.util.List;


/**
 * SpringSecurity的配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private IUmsMemberService umsMemberService;
    @Resource
    private IUmsMemberPermissionService umsMemberPermissionService;
    @Resource
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Resource
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Resource
    private IUmsMemberLevelService memberLevelService;
    @Resource
    private IUmsMemberRoleService umsMemberRoleService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()// 由于使用的是JWT，我们这里不需要csrf
                .disable().sessionManagement()// 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                        "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/swagger-resources/**",
                        "/v2/api-docs/**", "doc.html")
                .permitAll().antMatchers("/api/single/**", "/api/**/list")// 公共api要允许匿名访问
                .permitAll().antMatchers(HttpMethod.OPTIONS)// 跨域请求会先进行一次options请求
                .permitAll().anyRequest()// 除上面外的所有请求全部需要鉴权认证
                .authenticated();
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling().accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return username -> {
            UmsMember user = new UmsMember();
            user.setUsername(username);
            UmsMember umsMember = umsMemberService.getByUsername(username);
            if (umsMember == null) {
                throw new UsernameNotFoundException("用户名或密码错误.");
            } else {
                UmsMemberLevel level = memberLevelService.getById(umsMember.getMemberLevelId());

//                if (umsMember != null && level != null) {
                List<UmsMemberRole> umsMemberRoleList = umsMemberRoleService.getRoleListByUserId(umsMember.getId(), umsMember.getUsername());
                List<UmsMemberPermission> permissionList = umsMemberPermissionService.getMemberPermListByUserId(umsMember.getId(), umsMember.getUsername());
                return new UmsMemberUserDetails(umsMember, permissionList, umsMemberRoleList);
//                } else {
//                    throw new UsernameNotFoundException("用户名或密码错误.");
//                }
            }
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * 允许跨域调用的过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return new CorsFilter(source);
    }
}
