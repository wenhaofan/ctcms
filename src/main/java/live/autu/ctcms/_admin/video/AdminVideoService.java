package live.autu.ctcms._admin.video;

import com.jfinal.plugin.activerecord.Page;

import live.autu.ctcms.common.model.Video;

public class AdminVideoService {

	private static Video dao=new Video().dao();
	
	public Page<Video> page(Integer pageNum,Integer pageSize){
		return dao.paginate(pageNum, pageSize, "select * from video","");
	}
	
	public Video get(Integer videoId) {
		return dao.findById(videoId);
	}
	
	public boolean save(Video video) {
		return video.save();
	}
}
