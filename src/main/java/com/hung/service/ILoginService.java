package com.hung.service;

import java.util.List;

import com.hung.dto.UserDto;

public interface ILoginService {

    public UserDto getUser(String userName);

    // [USER,AMIN,..]
    public List<String> getUserRoles(String userName);
}
