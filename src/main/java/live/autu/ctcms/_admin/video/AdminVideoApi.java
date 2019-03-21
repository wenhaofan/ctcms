package live.autu.ctcms._admin.video;

import java.util.Date;

import com.jfinal.aop.Inject;
import com.jfinal.kit.Ret;

import live.autu.ctcms._admin.video.entity.VideoQuery;
import live.autu.ctcms.common.controller.BaseController;
import live.autu.ctcms.common.model.Video;

public class AdminVideoApi extends BaseController {

	@Inject
	private AdminVideoService adminVideoService;
	
	/**
	 * 获取视频列表
	 */
	public void list() {
		Date start=getDate("start");
		Date end=getDate("end");
		 
		VideoQuery query=new VideoQuery();
		query.setEnd(end);
		query.setStart(start);
		
		renderJson(adminVideoService.page(getParaToInt("pageNumber",1), getParaToInt("pageSize",20),query));
	}
	
	public void get() {
		renderJson(adminVideoService.get(getParaToInt()));
	}
	
	public void save() {
		Video video = getModel(Video.class,"",true);
		renderJson(adminVideoService.save(video)?Ret.ok():Ret.fail());
	}
	
}
