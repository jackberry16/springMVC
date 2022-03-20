package validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号注解关联验证器
 */
public class PhoneValidator implements ConstraintValidator<Phone,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String check = "158\\d{8}";
        Pattern regex = Pattern.compile(check);
        //空值处理
        String phone = Optional.ofNullable(s).orElse("");
        Matcher matcher = regex.matcher(phone);
        return matcher.matches();
    }
}
