package org.lufengxue.oauth.config;
import lombok.extern.slf4j.Slf4j;
import org.lufengxue.user.userFeign.UserFeign;
import org.lufengxue.response.Result;
import org.lufengxue.user.pojo.po.UserPo;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/***
 * 从数据库中获取用户名和密码进行匹配是否用户名和面正确
 * @author ljh
 * @packagename com.itheima.config
 * @version 1.0
 * @date 2020/1/10
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserFeign userFeign;

    /**
     *
     获取用户信息（一般是加密之后的密码，然后交给spring security的框架，框架自动的进行匹配成功，返回成功，失败报错）
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("获取到的用户名是：" + username);
        //设置权限 从数据库中查询用户对应的角色的信息
        String permission = "ROLE_ADMIN,ROLE_USER";

        //1.根据用户名获取用户的数据
            //1.1 创建一个feign接口 在changogu-service-user-api中
            //1.2 changgou-service-user中实现业务接口
            //1.3 在changgou-user-oauth 中添加依赖
            //1.4 启用feignclients
            //1.5 注入 调用
        //1.6 需要在user微服务中进行放行路径
        Result<UserPo> result = userFeign.findByName(username);
        //2.判断用户是否存在，如果不存在 抛出异常
        if (result.getData()==null) {
            //throw new UsernameNotFoundException("用户不存在");
            return null;
        }
        //3.获取用户的密码 然后交给框架本身 他自己进行校验
        String password = result.getData().getPassword();//加密过的！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！

        //return new UserDto(username, passwordEncoder.encode("szitheima"),

        //AuthorityUtils.commaSeparatedStringToAuthorityList(permission));

       /* List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        list.add(new SimpleGrantedAuthority("ROLE_USER"));*/
        return new User(username, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(permission));
    }
}
