package validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义手机号约束注解
 */
//注解的作用域
@Target({ElementType.FIELD})
//注解的保留策略
@Retention(RetentionPolicy.RUNTIME)
//与注解关联的验证器
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {
    //注解校验输出信息
    String message() default "手机号校验错误";
    //所属组别
    Class<?>[] groups() default {};
    //约束注解的有效负载
    Class<? extends Payload>[] payload() default {};
}
