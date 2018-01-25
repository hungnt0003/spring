package com.hung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hung.dto.SystemPropertiesDto;
import com.hung.dto.UserDto;
import com.hung.inf.ISystemPropertiesInterface;
import com.hung.inf.IUserInterface;

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
@Service
public class RegisterService implements IRegisterService {

    @Autowired
    IUserInterface userInterface;

    @Autowired
    ISystemPropertiesInterface systemPropertiesInterface;

    @Override
    public void storeUser(UserDto userDto) {
        userDto.setImg("img.img");
        userInterface.storeUser(userDto);
    }

    @Override
    public SystemPropertiesDto getSystemPropertie(String systemName) {
        return systemPropertiesInterface.getSystemPropertie(systemName);
    }

}
