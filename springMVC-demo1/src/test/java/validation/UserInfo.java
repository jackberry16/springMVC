package validation;

import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;

/**
 * 待验证对象
 */
public class UserInfo {
    //登录场景，验证登陆组时不会验证标注为其他场景的参数以及没有组的参数
    public interface LoginGroup {
    }

    //注册场景
    public interface RegisterGroup {
    }

    //组排序场景
    @GroupSequence({
            //优先校验登录场景
            LoginGroup.class,
            RegisterGroup.class,
            //未添加组的属于默认组
            Default.class
    })
    public interface Group{}

    @NotNull(message = "用户id不能为空", groups = LoginGroup.class)
    private String userId;
    //@NotEmpty不去空格，空格也能通过检验
    @NotEmpty(message = "用户名称不能为空")
    private String userName;
    //@NotBlank会去掉字符串前后空格验证是否为空
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度介于6到16位之间")
    private String password;
    @NotNull(message = "邮箱不能为空", groups = RegisterGroup.class)
    @Email(message = "请输入有效邮箱")
    private String email;
    @Phone(message = "手机号要以158开头")
    private String phone;
    @Min(value = 18, message = "不得小于18岁")
    @Max(value = 60, message = "不得大于60岁")
    private Integer age;
    @Past(message = "生日不能为未来时间")
    private Date birthday;
    @Size(min = 1, message = "不能少于1个好友")
    private List<@Valid UserInfo> friends;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<UserInfo> getFriends() {
        return friends;
    }

    public void setFriends(List<UserInfo> friends) {
        this.friends = friends;
    }

    //完成验证的步骤：
    //1.约束注解的定义
    //2.约束验证规则
    //3.约束注解声明
    //4.约束注解流程
}
