package com.hung.service;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hung.common.utils.CommonObjectUtils;
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
        doUpload(userDto.getImgFile(), userDto);
        userInterface.storeUser(userDto);
    }

    @Override
    public SystemPropertiesDto getSystemPropertie(String systemName) {
        return systemPropertiesInterface.getSystemPropertie(systemName);
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
