package com.hung.common;

import java.util.List;

import com.hung.dto.common.SelectListDto;

public interface ISelectListCreator {

    List<SelectListDto> getSexList();

    List<SelectListDto> getRoleList();
}
