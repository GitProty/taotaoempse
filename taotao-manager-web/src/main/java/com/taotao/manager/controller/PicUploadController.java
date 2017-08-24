package com.taotao.manager.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.pojo.PicUploadResult;

@Controller
@RequestMapping("/pic/upload")
public class PicUploadController {

	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	// 文件格式
	private static String[] TYPE = { ".jpg", ".jpeg", ".png", ".bmp", ".gif" };

	//使用jackjson工具类把对象转换json格式
	private static final ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 图片上传
	 */
	//富文本上传文件兼容性不是很好,火狐不能兼容,不能使用json响应,需要响应文本
	//produces = MediaType.TEXT_HTML_VALUE
	@RequestMapping(method = RequestMethod.POST,produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String upload(MultipartFile uploadFile) throws Exception {

		// 初始化返回的数据,默认失败
		PicUploadResult picUploadResult = new PicUploadResult();
		picUploadResult.setError(1);

		// 声明标志的位置
		boolean flag = false;

		for (String type : TYPE) {
			// 获取前端传输过来文件的名称
			String filename = uploadFile.getOriginalFilename();

			// 如果是要求的格式结尾,设置true,跳出循环
			if (StringUtils.endsWithIgnoreCase(filename, type)) {
				flag = true;
				break;
			}
		}

		// 如果后缀正确,获取文件内容
		if (flag) {
			// 重置验证标识
			flag = false;

			try {
				BufferedImage image = ImageIO.read(uploadFile.getInputStream());
				if (image != null) {
					
					flag = true;
					// 保存图片的宽高
					picUploadResult.setHeight(image.getHeight() + "");
					picUploadResult.setWidth(image.getWidth() + "");
				}
			} catch (Exception e) {
			}
		}

		// 图片验证成功
		if (flag) {
			// 1、创建tracker.conf配置文件，内容就是tracker服务的地址。配置文件内容：tracker_server=192.168.37.161:22122，然后加载配置文件(ClientGlobal.init方法加载)
			ClientGlobal.init(System.getProperty("user.dir") + "/src/main/resources/tracker.conf");

			// 2、创建一个TrackerClient对象。直接new一个。
			TrackerClient trackerClient = new TrackerClient();

			// 3、使用TrackerClient对象创建连接，getConnection获得一个TrackerServer对象。
			TrackerServer trackerServer = trackerClient.getConnection();

			// 4、创建一个StorageServer的引用，值为null，为接下来创建StorageClient使用
			StorageServer storageServer = null;

			// 5、创建一个StorageClient对象，直接new一个，需要两个参数TrackerServer对象、StorageServer的引用
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);

			// 6、使用StorageClient对象upload_file方法上传图片。
			// 获取上传文件的后缀名
			String file_ext_name = StringUtils.substringAfterLast(uploadFile.getOriginalFilename(), ".");

			String[] file = storageClient.upload_file(uploadFile.getBytes(), file_ext_name, null);
			// 7、返回数组。包含组名和图片的路径，打印结果。
			picUploadResult.setUrl(this.IMAGE_SERVER_URL + "/"+ file[0] + "/" + file[1]);
			
			//设置图片上传成功
			picUploadResult.setError(0);
		}
		
		// 使用Jackson工具类把对象转为接送数据
		String str = mapper.writeValueAsString(picUploadResult);
		
		return str;
	}
}
