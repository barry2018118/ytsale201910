package com.yuntu.sale.base;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.coolshow.util.BaseUtil;
import com.coolshow.util.DateUtil;
import com.yuntu.sale.base.vo.OperatorFailureVo;
import org.apache.commons.io.FileUtils;

import com.yuntu.sale.base.servlet.PropertiesInitServlet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * @Summary 风控系统 文件上传辅助类
 * @Author snps
 * @Detail
 * @Nonuse
 */
public class FileUploadAider {

	/**
	 * 得到“网站信息”文件文件目录
	 * @return String
	 */
	public static String getWebSiteUploadPath() {
		return PropertiesInitServlet.getPropContent(GrobalSystemConstant.PROP_RESOURCES).get("root.path");
	}
	
	/**
	 * 通过路径生成新的空文件
	 * @param psFilename
	 * @return new File()
	 */
	public static File getFile(String psFilename) {
		if (psFilename == null)
			return null;

		return new File(psFilename);
	}
	
	/**
	 * 上传文件（拷贝客户端上传的文件到服务器指定路径）
	 * @param inputStream
	 * @param sRelativePath 服务器端要存放文件的相对路径（通常由具体业务确定）
	 * @param sFilename 上传文件名
	 * @return Map<String, Object>
	 * @throws IOException
	 */
	public static Map<String, String> uploadFile(InputStream inputStream, String sRelativePath, String sFilename,String path) throws IOException{
		// 得到上传资源根目录
		String sUploadRoot = getWebSiteUploadPath();
		
		// 生成相对文件路径（不包括上传根目录）
		String websitePath = PropertiesInitServlet.getPropContent(GrobalSystemConstant.PROP_RESOURCES).get(path);
		StringBuffer sbuFilePath = new StringBuffer(websitePath).append(sRelativePath);
		if(!sRelativePath.endsWith(GrobalSystemConstant.S_SLASH)) {
			sbuFilePath.append(GrobalSystemConstant.S_SLASH);
		}
		File file = new File(sUploadRoot + sbuFilePath.toString()) ;
		if (!file.exists()) {
			file.mkdirs();
		}

		// 拷贝文件
		sbuFilePath.append(sFilename);
		File fileUpload = getFile(sUploadRoot + sbuFilePath.toString()) ;
		FileUtils.copyInputStreamToFile(inputStream, fileUpload) ;
		
		Map<String, String> mapReturn = new HashMap<String, String>() ;
		mapReturn.put("uploadPath", sbuFilePath.toString()) ;
		return mapReturn ;
	}

	/**
	 * 上传文件至服务器
	 * @param path
	 * @param file
	 * @return
	 */
	public static String  uploadFileToServer(String path, MultipartFile file) {
		String message = "";
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			is = file.getInputStream();
			// 如果服务器已经存在和上传文件同名的文件,则删除
			File tempFile = new File(path);
			if (tempFile.exists()) {
				tempFile.delete();
			}
			if (tempFile != null) {
				fos = new FileOutputStream(tempFile);
				byte[] buffer = new byte[8192]; // 每次读8K字节
				int count = 0;
				// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count); // 向服务端文件写入字节流
				}
			}
		} catch (FileNotFoundException e) {
			message = "文件读取异常了请联系管理员!";
			return message;
		} catch (IOException e) {
			message ="文件读取异常了请联系管理员!";
			return message;
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (is != null)
					is.close();
			} catch (IOException e2) {
				message = "文件流关闭错误!";
				return message;
			}
		}
		return "SUCCESS";
	}

	/**
	 * 上传图片
	 * @param request 页面请求
	 * @param id 当前企业id
	 * @param path 上传的文件路径 @ 例:
	 *        path.put("windows","/product/pic/");     //服务目录
	 *	      path.put("linux","project.product.path");//资源目录
	 * @param file 上传的文件
	 */
	public static Map<String, String>  uploadPic(HttpServletRequest request,Long id,Map<String, String> path,MultipartFile file) throws IOException{
		// 处理图片上传 linux
		String businessPath = new StringBuffer(GrobalSystemConstant.S_SLASH).append(id).append(GrobalSystemConstant.S_SLASH).toString();
		Map<String, String> mapUploadInfo = null;
		if (!BaseUtil.isEmpty(file)) {
			String extendFileName= com.coolshow.util.FileUtils.getExtendFileName(file.getOriginalFilename()) ;
			// 生成实时文件名
			String time = DateUtil.getCurrentTimestamp().toString();
			String filename = time + "-" + RandomStringUtils.getStringRandom(10000, 4) + extendFileName ;
			mapUploadInfo = uploadFile(file.getInputStream(), businessPath, filename,path.get("linux"));
		}
		if(path.containsKey("windows")) {
			// 处理文件上传 windows
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String contentType = file.getContentType();
			String suffixName = contentType.substring(contentType.indexOf("/") + 1);
			String filename = uuid + "." + suffixName;
			String newURL = request.getSession().getServletContext().getRealPath("/").replaceAll("\\\\", "/");
			String fullFilename = newURL + "WEB-INF/static" + path.get("windows") + filename;//工程根目录
			File fileLocation = new File(newURL + "WEB-INF/static" + path.get("windows")); //此处也可以在应用根目录手动建立目标上传目录
			if (!fileLocation.exists()) {
				boolean isCreated = fileLocation.mkdirs();
				if (!isCreated) {
					mapUploadInfo.put("fail", "目录创建失败!");
					return mapUploadInfo;
				}
			}
			if (file.getSize() > 2000000) {
				mapUploadInfo.put("fail", "上传文件最大为2M！");
				return mapUploadInfo;
			}
			System.out.println("审核图片名 : " + fullFilename);
			String va = uploadFileToServer(fullFilename, file);
			if (va != "SUCCESS") {
				mapUploadInfo.put("fail", va);
				return mapUploadInfo;
			}
			System.out.println("审核图片结果 : " + va);
			mapUploadInfo.put("filename", path.get("windows") + filename);
		}
		return mapUploadInfo;
	}
}	