package validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
    //结果集合
    private Set<ConstraintViolation<UserInfoService>> otherSet;

    //初始化操作
    @Before
    public void init() {
        //初始化验证器
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        //初始化待验证对象 - 用户信息
        userInfo = new UserInfo();
        userInfo.setUserId("1");
        userInfo.setUserName("chengh");
        userInfo.setPassword("123456");
        userInfo.setEmail("s@c.com");
        userInfo.setAge(20);
        userInfo.setPhone("15800000000");
        Calendar calendar = Calendar.getInstance();
//        calendar.set(2025,1,1);
        calendar.set(2021, 1, 1);
        userInfo.setBirthday(calendar.getTime());

        UserInfo friend = new UserInfo();
        friend.setUserId("2");
        friend.setUserName("chengh");
        friend.setPassword("123456");
        friend.setEmail("s@c.com");
        friend.setPhone("15800000000");
        List<UserInfo> list = new ArrayList<>();
        list.add(friend);
        userInfo.setFriends(list);
    }

    //结果打印
    @After
    public void print() {
        set.forEach(a -> {
            System.out.println(a.getMessage());
        });
//        otherSet.forEach(a -> {
//            System.out.println(a.getMessage());
//        });
    }

    @Test
    public void nullValidation() {
        set = validator.validate(userInfo);
    }

    //级联验证测试
    @Test
    public void graphValidation() {
        set = validator.validate(userInfo);
    }

    //分组校验测试
    @Test
    public void groupValidation() {
        //可以通过追加组，同时检验多个组
        set = validator.validate(userInfo,
                UserInfo.RegisterGroup.class,
                UserInfo.LoginGroup.class);
    }

    //组排序测试方法
    @Test
    public void groupSequenceValidation() {
        //大组设置验证组的顺序，顺序靠前的验证不通不继续进行
        set = validator.validate(userInfo, UserInfo.Group.class);
    }

    //对方法输入参数进行约束注解
    @Test
    public void paramValidation() throws NoSuchMethodException {
        //获取验证执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        //待验证对象
        UserInfoService userInfoService = new UserInfoService();
        //待验证方法
        Method method = userInfoService.getClass().getMethod("setUserInfo", UserInfo.class);
        //方法输入参数
        Object[] paramObj = new Object[]{new UserInfo()};
        //对方法的输入参数进行校验
        otherSet = executableValidator.validateParameters(userInfoService,method,paramObj);
    }

    //对方法返回值进行约束注解
    @Test
    public void returnValueValidation() throws Exception {
        //获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        //构造要验证的方法对象
        UserInfoService userInfoService = new UserInfoService();
        Method method = userInfoService.getClass().getMethod("getUserInfo");
        //调用方法得到返回值
        Object returnValue = method.invoke(userInfoService);
        //校验返回值
        otherSet = executableValidator.validateReturnValue(userInfoService,method,returnValue);
    }

    //对构造函数入参进行校验
    @Test
    public void constructorValidation() throws NoSuchMethodException {
        //获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        //获取构造函数
        Constructor constructor = UserInfoService.class.getConstructor(UserInfo.class);
        Object[] paramObj = new Object[]{new UserInfo()};
        //校验构造函数
        otherSet = executableValidator.validateConstructorParameters(constructor,paramObj);
    }
}
