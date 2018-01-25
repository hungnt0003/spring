package com.hung.inf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hung.dao.ISystemPropertiesDao;
import com.hung.dto.SystemPropertiesDto;

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
public class SystemPropertiesInterface implements ISystemPropertiesInterface {

    @Autowired
    ISystemPropertiesDao systemPropertiesDao;

    @Override
    public List<SystemPropertiesDto> getSystemProperties() {
        return systemPropertiesDao.getSystemProperties();
    }

    @Override
    public SystemPropertiesDto getSystemPropertie(String systemName) {
        // TODO Auto-Generated Method Stub
        return systemPropertiesDao.getSystemPropertie(systemName);
    }

}
