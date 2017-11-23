package com.cae.ds.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.http.AipRequest;
import com.cae.ds.entity.User;
@Controller
public class FaceRegister {
	@RequestMapping("/face_add")
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file ,User user) throws IOException {

		
		// 初始化一个FaceClient
		AipFace face = new AipFace("10415883", "ASO8Z2Q0ViKoARtkB9NXDM5p", "zjQefzc1Mr3cmigbiKot7EjinzD3ViGD");
		// 可选：设置网络连接参数
		face.setConnectionTimeoutInMillis(60000);
		face.setSocketTimeoutInMillis(60000);
		// 调用API
				HashMap map = new HashMap();
				//
				map.put("action_type","replace");
				
				AipRequest aipRequest = new AipRequest();
				aipRequest.setBody(map);
				String uid=user.getTelnumber();//用户ID
				String user_info=user.toString();//用户基本信息
				List<String> group_id=new LinkedList<String>();//分组信息，可以将一个用户插入多个用户组
				group_id.add("test");
				//将上传的文件流进行检测
				JSONObject result = face.addUser(uid, user_info, group_id, file.getBytes(), map);
				return result.toString();
			}
		
		
   
}