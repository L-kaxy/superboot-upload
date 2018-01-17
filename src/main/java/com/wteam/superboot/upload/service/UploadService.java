/**
 * Copyright (c) 2007-2017 Wteam.  All rights reserved. 网维网络技术创业团队 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.upload.service;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import com.wteam.superboot.core.enums.ResultEnum;
import com.wteam.superboot.core.exception.SuperException;
import com.wteam.superboot.core.helper.ResultHelper;
import com.wteam.superboot.core.result.ResultMessage;

/**
 * 上传文件 Service.
 * 
 * @authod 罗佳欣
 * 
 */
@Service
public class UploadService {

	public ResultMessage addUploadFile(String fileName, InputStream fileInputStream, String saveFilePath) throws Exception {
		if (fileName == null) {
            throw new SuperException(ResultEnum.PARAM_ERROR);
        }
        if (saveFilePath == null) {
        	throw new SuperException(ResultEnum.PARAM_ERROR);
        }

        String extension = FilenameUtils.getExtension(fileName);
        String saveFileName = UUID.randomUUID() + "." + extension;
        File copyFile = new File(new File(saveFilePath), saveFileName);
        FileUtils.copyInputStreamToFile(fileInputStream, copyFile);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fileName", saveFileName);
        
        ResultMessage rs = ResultHelper.result(ResultEnum.UPLOAD_SUCCESS, map);
		
		return rs;
	}

}
