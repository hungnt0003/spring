package com.hung.service;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hung.common.ISelectListCreator;
import com.hung.common.exception.StoreException;
import com.hung.common.utils.CommonListUtils;
import com.hung.common.utils.CommonObjectUtils;
import com.hung.dto.RoleDto;
import com.hung.dto.SystemPropertiesDto;
import com.hung.dto.UserDto;
import com.hung.dto.UserRoleDto;
import com.hung.inf.IRoleInterface;
import com.hung.inf.ISystemPropertiesInterface;
import com.hung.inf.IUserInterface;
import com.hung.inf.IUserRoleInterface;

import javassist.NotFoundException;

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
public class UserService implements IUserService {

    /** UserInterface. */
    @Autowired
    private IUserInterface userInterface;
    /** RoleInterface. */
    @Autowired
    private IRoleInterface roleInterface;
    /** SelectListCreator. */
    @Autowired
    private ISelectListCreator selectListCreator;
    /** UserRoleInterface. */
    @Autowired
    private IUserRoleInterface userRoleInterface;
    /** SystemPropertiesInterface. */
    @Autowired
    ISystemPropertiesInterface systemPropertiesInterface;

    @Override
    public List<UserDto> search() {
        return userInterface.getUsers();
    }

    @Override
    public UserDto getUser(String userName) throws NotFoundException {
        UserDto user = userInterface.getUser(userName);
        if (CommonObjectUtils.isNullOrEmpty(user)) {
            throw new NotFoundException("UserService#getFullUser UserDto not found");
        }
        UserRoleDto userRoleDto = userRoleInterface.getMaxRole(userName);
        List<UserRoleDto> userRoleDtoList = userRoleInterface.getUserRoles(userName);

        List<RoleDto> roleDtoList = new ArrayList<>();
        if (CommonListUtils.isNotNullOrEmpty(userRoleDtoList)) {
            for (UserRoleDto dto : userRoleDtoList) {
                roleDtoList.add(dto.convertRoleDto());
            }
        }
        user.setRoles(roleDtoList);
        user.setRole(userRoleDto.getRoleId());
        user.setRoleList(selectListCreator.getRoleList());
        user.setSexList(selectListCreator.getSexList());
        return user;
    }

    @Override
    public void store(UserDto dto) throws StoreException, Exception {
        doUpload(dto.getImgFile(), dto);

        userInterface.storeUser(dto);
        userRoleInterface.deleteUserRole(dto.getUserName());
        List<RoleDto> roleDtoList = roleInterface.getRoles();
        int maxRole = Integer.parseInt(dto.getRole());
        for (int i = 0; i < maxRole; i++) {
            if (i <= maxRole && isContain(roleDtoList, i)) {
                UserRoleDto userRoleDto = new UserRoleDto(String.valueOf(i), dto.getUserName(), "");
                userRoleInterface.storeUserRole(userRoleDto);
            }
        }
    }

    /**
     * 
     * Check role has contain list?
     * 
     * @param roleDtoList
     * @param role
     * @return true:has contain, false: not contain
     */
    private boolean isContain(List<RoleDto> roleDtoList, int role) {
        for (RoleDto dto : roleDtoList) {
            if (String.valueOf(role).equals(dto.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(UserDto dto) {
        userInterface.delete(dto);
    }

    @Override
    public void initDataList(UserDto dto) {
        dto.setRoleList(selectListCreator.getRoleList());
        dto.setSexList(selectListCreator.getSexList());
    }

    private void doUpload(MultipartFile fileData, UserDto userDto) {

        if (CommonObjectUtils.isNullOrEmpty(fileData)) {
            return;
        }

        // Root Dir upload.
        SystemPropertiesDto uploadRootPath = systemPropertiesInterface.getSystemPropertie("IMG_ROOT_AVARTA_PATH");
        SystemPropertiesDto uploadRootThumbPath = systemPropertiesInterface
                .getSystemPropertie("IMG_ROOT_AVARTA_THUMB_PATH");

        File uploadRootDir = new File(uploadRootPath.getSysValue());
        // If folder not exist, create.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        // Root Dir upload thumb.
        File uploadThumbNailRootDir = new File(uploadRootThumbPath.getSysValue());
        // If folder not exist, create.
        if (!uploadThumbNailRootDir.exists()) {
            uploadThumbNailRootDir.mkdirs();
        }

        // File name of file upload.
        String name = fileData.getOriginalFilename();
        System.out.println("Client File Name = " + name);

        if (name != null && name.length() > 0) {
            try {
                String[] contentType = fileData.getContentType().split("/");
                String tail = "." + contentType[contentType.length - 1].toString();

                name = "avarta" + getPrisent() + tail;
                // Tạo file tại Server.
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileData.getBytes());
                stream.close();
                // http://www.javaroots.com/2013/09/how-to-create-thumbnail-images-in-java.html

                // Create thumbNail
                BufferedImage img = ImageIO.read(serverFile); // load image
                BufferedImage thumbImg = Scalr.resize(img, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, 50, 50,
                        Scalr.OP_ANTIALIAS);

                name = "thumb" + getPrisent() + ".jpg";
                File thumbFile = new File(uploadThumbNailRootDir.getAbsolutePath() + File.separator + name);
                FileOutputStream os = new FileOutputStream(thumbFile);
                ImageIO.write(thumbImg, ".jpg", os);

                userDto.setAvartaPath(serverFile.getAbsolutePath());
                userDto.setThumbPath(thumbFile.getAbsolutePath());
            } catch (Exception e) {
                return;
            }
        }
        return;
    }

    private String getPrisent() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
