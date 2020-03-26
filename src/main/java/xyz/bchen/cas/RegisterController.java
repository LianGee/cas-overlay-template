package xyz.bchen.cas;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RegisterController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/get/questions")
    public List getQuestions() {
        return jdbcTemplate.query("select id, question from question", new BeanPropertyRowMapper(Question.class));
    }

    @PostMapping("/user/register")
    public String register(@RequestBody Register register) {
        Long currentTimestamp = System.currentTimeMillis() / 1000;
        String userSql = String.format("insert into `user` " +
                        "(`name`, `email`, `phone`, `password`, `created_at`, `updated_at`) " +
                        "values ('%s', '%s', '%s', '%s', %d, %d)",
                register.getName(),
                register.getEmail(),
                register.getPhone(),
                DigestUtils.md5Hex(register.getPassword()),
                currentTimestamp,
                currentTimestamp
        );
        String firstQuesSql = String.format("insert into `user_question`" +
                        "(`user_name`, `answer`, `question`, `created_at`, `updated_at`)" +
                        "values ('%s', '%s', '%s', %d, %d)",
                register.getName(),
                register.getFirstAnswer(),
                register.getFirstQuestion(),
                currentTimestamp,
                currentTimestamp
        );
        String secondQuesSql = String.format("insert into `user_question`" +
                        "(`user_name`, `answer`, `question`, `created_at`, `updated_at`)" +
                        "values ('%s', '%s', '%s', %d, %d)",
                register.getName(),
                register.getSecondAnswer(),
                register.getSecondQuestion(),
                currentTimestamp,
                currentTimestamp
        );
        String thirdQuesSql = String.format("insert into `user_question`" +
                        "(`user_name`, `answer`, `question`, `created_at`, `updated_at`)" +
                        "values ('%s', '%s', '%s', %d, %d)",
                register.getName(),
                register.getThirdAnswer(),
                register.getThirdQuestion(),
                currentTimestamp,
                currentTimestamp
        );
        String existSql = String.format("select * from user where " +
                        "name = '%s' or email = '%s' or phone = '%s' limit 1",
                register.getName(),
                register.getEmail(),
                register.getPhone()
        );
        System.out.println(existSql);
        System.out.println(userSql);
        System.out.println(firstQuesSql);
        System.out.println(secondQuesSql);
        System.out.println(thirdQuesSql);
        try {
            Map<String, Object> user = jdbcTemplate.queryForMap(existSql);
            if (user != null) {
                if (user.get("name").equals(register.getName())) {
                    return "用户名已经存在";
                }
                if (user.get("email").equals(register.getEmail())) {
                    return "该邮箱已注册";
                }
                if (user.get("phone").equals(register.getPhone())) {
                    return "该手机已注册";
                }
                System.out.println(user);
                return "已经存在";
            }
        } catch (Exception ex) {
            System.out.println("数据正常，可以创建");
        }
        try {
            jdbcTemplate.update(userSql);
            jdbcTemplate.update(secondQuesSql);
            jdbcTemplate.update(firstQuesSql);
            jdbcTemplate.update(thirdQuesSql);
            return "success";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
