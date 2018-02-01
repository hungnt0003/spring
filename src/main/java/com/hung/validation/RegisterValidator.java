package com.hung.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hung.common.validation.CommonValidator;
import com.hung.dto.UserDto;

/**
 * クラスタイトル(ピリオド削除厳禁).
 *
 * <pre>
 * 内容, 使用例など
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
@Component
public class RegisterValidator extends CommonValidator implements Validator {

    @Override
    public boolean supports(Class<?> arg0) {
        return UserDto.class.equals(arg0);
    }

    @Override
    protected void simpleCheck(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        String userName = userDto.getUserName();
        // if (!isRequiredInput(errors, "userName", WordConstants.WRD_REGISTER_USERNAME, userDto.getUserName())) {
        // if (isLength(errors, "userName", WordConstants.WRD_REGISTER_USERNAME, userName,
        // CommonConstants.MAX_USERNAME)) {
        // isHalfAlpha(errors, "userName", userName);
        // }
        // }
        //
        // if (userDto.getImgFile() != null) {
        // if ("png".equalsIgnoreCase(userDto.getImgFile().getContentType())) {
        // if (userDto.getImgFile().getSize() > 0) {
        //
        // }
        // }
        // }
    }

    @Override
    protected void relationCheck(Object target, Errors errors) {
        // TODO Auto-Generated Method Stub

    }

}
