

package live.autu.ctcms.common.upload;

import java.util.Date;

import com.jfinal.kit.LogKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.render.JsonRender;
import com.jfinal.upload.UploadFile;

import live.autu.ctcms.common.controller.BaseController;

/**
 * UploadController 上传控制器，接管 ueditor 上传功能
 */
public class UploadController extends BaseController {

	UploadService srv = UploadService.me;

	/**
	 * 1：测试上传图片成功所返回的 json 数据格式如下：
	 *  {
	 *     "state": "SUCCESS",
	 *     "title": "1461249851191086496.png",
	 *     "original": "qr.png",
	 *     "type": ".png",
	 *     "url": "/ueditor/jsp/upload/image/20160421/1461249851191086496.png",
	 *     "size": "58640"
	 *  }
	 *
	 * 2：如果上传出现错误，直接响应如下的 json 即可：
	 *    {"state": "错误信息"}
	 *
	 */
	public void index() {
  
	 
		String uploadType = getPara("uploadType");
		if (StrKit.isBlank(uploadType)) {
			renderJson("state", "上传类型参数缺失");
			return ;
		}

		if (notLogin()) {
			renderJson("state", "只有登录用户才可以上传文件");
			return ;
		}

		UploadFile uploadFile = null;
		try {
			// "upfile" 来自 config.json 中的 imageFieldName 配置项
			uploadFile = getFile("file", UploadService.uploadTempPath,237490830);
			
			Date createDate=getDate("create");
			
			Ret ret = srv.upload(getLoginAccount(), uploadType, uploadFile,createDate);
			// renderJson(ret);
			render(new JsonRender(ret).forIE());	// 防止 IE 下出现文件下载现象
		} 
		catch(Exception e) {
			if (uploadFile != null) {
				uploadFile.getFile().delete();
			}
			
			renderJson("state", "上传出现未知异常，请告知管理员：" + e.getMessage());
			LogKit.error(e.getMessage(), e);
		}
	}

 
}
