package live.autu.ctcms._admin.video;

import com.jfinal.aop.Inject;
import com.jfinal.kit.Ret;

import live.autu.ctcms.common.controller.BaseController;
import live.autu.ctcms.common.model.Video;

public class AdminVideoApi extends BaseController {

	@Inject
	private AdminVideoService adminVideoService;
	
	/**
	 * 获取视频列表
	 */
	public void list() {
		renderJson(adminVideoService.page(getParaToInt("pageNum"), getParaToInt("pageSize")));
	}
	
	public void get() {
		renderJson(adminVideoService.get(getParaToInt()));
	}
	
	public void save() {
		Video video = getModel(Video.class,"",true);
		renderJson(adminVideoService.save(video)?Ret.ok():Ret.fail());
	}
	
}
