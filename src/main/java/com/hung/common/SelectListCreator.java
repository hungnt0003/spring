package com.hung.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hung.common.constants.CommonConstants;
import com.hung.common.utils.CommonListUtils;
import com.hung.common.utils.CommonObjectUtils;
import com.hung.dto.RoleDto;
import com.hung.dto.SystemPropertiesDto;
import com.hung.dto.common.SelectListDto;
import com.hung.inf.IRoleInterface;
import com.hung.inf.ISystemPropertiesInterface;

/**
 * 
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
public class SelectListCreator implements ISelectListCreator {

	/** SystemPropertiesInterface. */
	@Autowired
	ISystemPropertiesInterface systemPropertiesInterface;
	/** RoleInterface. */
	@Autowired
	IRoleInterface roleInterface;

	/**
	 * 
	 * @see com.hung.common.ISelectListCreator#getSexList()
	 * @return
	 */
	@Override
	public List<SelectListDto> getSexList() {
		List<SelectListDto> sexList = new ArrayList<>();
		SystemPropertiesDto man = systemPropertiesInterface.getSystemPropertie(CommonConstants.MAN);
		SystemPropertiesDto woman = systemPropertiesInterface.getSystemPropertie(CommonConstants.WOMAN);
		if (CommonObjectUtils.isNotNullOrEmpty(man) && CommonObjectUtils.isNotNullOrEmpty(woman)) {
			sexList.add(new SelectListDto(man.getSysValue(), man.getSysName()));
			sexList.add(new SelectListDto(woman.getSysValue(), woman.getSysName()));
		}
		return sexList;
	}

	/**
	 * 
	 * @see com.hung.common.ISelectListCreator#getRoleList()
	 * @return
	 */
	@Override
	public List<SelectListDto> getRoleList() {
		List<RoleDto> roleDtoList = roleInterface.getRoles();
		List<SelectListDto> roleList = new ArrayList<>();
		if (CommonListUtils.isNotNullOrEmpty(roleDtoList)) {
			for (RoleDto dto : roleDtoList) {
				roleList.add(new SelectListDto(String.valueOf(dto.getId()), dto.getName()));
			}
		}
		return roleList;
	}

}
