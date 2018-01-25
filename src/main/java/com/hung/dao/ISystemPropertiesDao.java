package com.hung.dao;

import java.util.List;

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
public interface ISystemPropertiesDao {

    List<SystemPropertiesDto> getSystemProperties();

    SystemPropertiesDto getSystemPropertie(String systemName);
}
