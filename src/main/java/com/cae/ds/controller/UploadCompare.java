package com.cae.ds.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.http.AipRequest;

/**
 * @Title: UploadCompare.java
 * @Description:
 * @Company 电子科技大学自动化研究所
 * @author 杜松
 * @date 2017年11月22日 下午9:25:35
 * @version V1.0
 */

@Controller
public class UploadCompare {

	/**
	 * 
	 * @Description:在线上传图片对比
	 * @param file
	 * @return
	 * @throws IOException
	 * @author 杜松
	 * @date 2017年11月22日 下午9:28:38
	 */
	@RequestMapping("/upcmp")
	@ResponseBody
	public String handleFileUpload(HttpServletRequest request) throws IOException {

		// 多文件上传
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

		// 初始化一个FaceClient
		AipFace face = new AipFace("10415883", "ASO8Z2Q0ViKoARtkB9NXDM5p", "zjQefzc1Mr3cmigbiKot7EjinzD3ViGD");
		// 可选：设置网络连接参数
		face.setConnectionTimeoutInMillis(60000);
		face.setSocketTimeoutInMillis(60000);
		// 调用API
		HashMap map = new HashMap();
		//
		map.put("face_fields", "age,beauty,expression,gender,glasses,race,qualities");

		AipRequest aipRequest = new AipRequest();
		aipRequest.setBody(map);

		// 将上传的两张图片进行对比
		byte[][] bytes = new byte[][] { files.get(0).getBytes(), files.get(0).getBytes() };
		JSONObject result = face.match(bytes, map);
		return result.toString();
	}
}
