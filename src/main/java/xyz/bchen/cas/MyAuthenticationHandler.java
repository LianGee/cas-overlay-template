package xyz.bchen.cas;

import org.apache.commons.codec.digest.DigestUtils;
import org.apereo.cas.authentication.*;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("myAuthenticationHandler")
public class MyAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {
    public MyAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential, String originalPassword) throws GeneralSecurityException, PreventedException {
        System.out.println("authenticateUsernamePasswordInternal");
        String name = credential.getUsername();
        String password = credential.getPassword();
        String sql = String.format("SELECT * FROM user WHERE name = '%s'", name);

        JdbcTemplate template = new JdbcTemplate();
        DriverManagerDataSource d = new DriverManagerDataSource();
        d.setDriverClassName("com.mysql.jdbc.Driver");
        d.setUrl("jdbc:mysql://127.0.0.1:3306/cas");
        d.setUsername("root");
        d.setPassword("123456");
        template.setDataSource(d);
        Map<String, Object> user;
        try {
            user = template.queryForMap(sql);
        } catch (Exception e) {
            throw new AccountException("用户不存在!");
        }
        System.out.println("database username : " + user.get("name"));
        System.out.println("database password : " + user.get("password"));
        if (!DigestUtils.md5Hex(password).toLowerCase().equals(user.get("password").toString().toLowerCase())) {
            throw new FailedLoginException("密码错误!");
        } else {
            Map<String, Object> resultMap = new HashMap<>(1);
            resultMap.put("user", user);
            final List<MessageDescriptor> list = new ArrayList<>();
            return createHandlerResult(
                    credential,
                    this.principalFactory.createPrincipal(name, resultMap),
                    list
            );
        }
    }
}
