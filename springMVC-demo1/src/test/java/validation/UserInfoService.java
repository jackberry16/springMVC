package validation;

import javax.validation.Valid;

/**
 * 用户信息服务类
 */
public class UserInfoService {
    /**
     * userInfo作为输入参数
     * @param userInfo 用户信息类
     */
    public void setUserInfo(@Valid UserInfo userInfo){

    }

    /**
     * userInfo作为输出参数
     */
    public @Valid UserInfo getUserInfo(){
        return new UserInfo();
    }

    /**
     * 默认构造函数
     */
    public UserInfoService() {
    }

    /**
     * 接受UserInfo作为参数的构造函数
     * @param userInfo 用户信息类
     */
    public UserInfoService(@Valid UserInfo userInfo){}
}
