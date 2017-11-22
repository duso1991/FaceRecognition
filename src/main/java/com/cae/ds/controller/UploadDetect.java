package com.cae.ds.controller;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.http.AipRequest;

/**   
  * @Title: UploadDetect.java
  * @Description:
  * @Company  电子科技大学自动化研究所
  * @author  杜松   
  * @date 2017年11月22日 下午8:41:21
  * @version V1.0   
*/
@Controller
public class UploadDetect {
	/**
	 * 
	  *@Description:上传图片识别人脸
	  *@param file  上传的图片文件
	  *@return   返回识别的结果
	  *@throws IOException
	  *@author  杜松   
	  *@date 2017年11月22日 下午8:41:32
	 */
	@RequestMapping("/upRecong")
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

		// 初始化一个FaceClient
		AipFace face = new AipFace("10415883", "ASO8Z2Q0ViKoARtkB9NXDM5p", "zjQefzc1Mr3cmigbiKot7EjinzD3ViGD");
		// 可选：设置网络连接参数
		face.setConnectionTimeoutInMillis(60000);
		face.setSocketTimeoutInMillis(60000);
		// 调用API
		HashMap map = new HashMap();
		//
		map.put("face_fields", "age,beauty,expression,gender,glasses,race,qualities");
		/*
		 * String path = "C:/Users/DUS/Desktop/face/face3.jpg";
		 */AipRequest aipRequest = new AipRequest();
		aipRequest.setBody(map);
		JSONObject result = face.detect(file.getBytes(), map);
		return result.toString();
	}
}
