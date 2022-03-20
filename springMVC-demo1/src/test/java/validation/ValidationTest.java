package validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

/**
 * 验证测试类
 */
public class ValidationTest {
    //验证器对象
    private Validator validator;
    //待验证对象
    private UserInfo userInfo;
    //结果集合
    private Set<ConstraintViolation<UserInfo>> set;

    //初始化操作
    @Before
    public void init(){
        //初始化验证器
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        //初始化待验证对象 - 用户信息
        userInfo = new UserInfo();
        userInfo.setUserId("1");
        userInfo.setUserName("chengh");
        userInfo.setPassword("123456");
        userInfo.setEmail("s@c.com");
        userInfo.setAge(20);
        Calendar calendar = Calendar.getInstance();
//        calendar.set(2025,1,1);
        calendar.set(2021,1,1);
        userInfo.setBirthday(calendar.getTime());
        List<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo());
        userInfo.setFriends(list);
    }

    //结果打印
    @After
    public void print(){
        set.forEach(a->{
            System.out.println(a.getMessage());
        });
    }

    @Test
    public void nullValidation(){
        set = validator.validate(userInfo);
    }
}
