package validation;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * 待验证对象
 */
public class UserInfo {
    @NotNull(message = "用户id不能为空")
    private String userId;
    //@NotEmpty不去空格，空格也能通过检验
    @NotEmpty(message = "用户名称不能为空")
    private String userName;
    //@NotBlank会去掉字符串前后空格验证是否为空
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6,max = 16,message = "密码长度介于6到16位之间")
    private String password;
    @Email(message = "请输入有效邮箱")
    private String email;
    private String phone;
    @Min(value = 18,message = "不得小于18岁")
    @Max(value = 60,message = "不得大于60岁")
    private Integer age;
    @Past(message = "生日不能为未来时间")
    private Date birthday;
    @Size(min = 1,message = "不能少于1个好友")
    private List<UserInfo> friends;

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
}
