package live.autu.ctcms._admin.video;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.SqlPara;

import live.autu.ctcms._admin.video.entity.VideoQuery;
import live.autu.ctcms.common.model.Video;

public class AdminVideoService {

	private static Video dao=new Video().dao();
	
	public Page<Video> page(Integer pageNum,Integer pageSize,VideoQuery query){
		SqlPara sql=dao.getSqlPara("admin.video.page", Kv.by("query", query));
		return dao.paginate(pageNum, pageSize,sql);
	}
	
	public Video get(Integer videoId) {
		return dao.findById(videoId);
	}
	
	public boolean save(Video video) {
		return video.save();
	}
}
