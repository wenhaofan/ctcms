

package live.autu.ctcms._admin.video;

import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;

import live.autu.ctcms.common.model.Account;
import live.autu.ctcms.common.model.Video;

import com.jfinal.kit.Ret;

import java.util.Date;
import java.util.List;

/**
 * 下载业务
 */
public class AdminDownloadService {

	public static final AdminDownloadService me = new AdminDownloadService();

	private Video dao = new Video().dao();

	/**
	 * 用于在首页显示的下载列表
	 */
	public List<Video> getDownloadList() {
		return dao.findByCache("download", "downloadList", "select * from download where isShow = 1");
	}

	public void clearCache() {
		CacheKit.remove("download", "downloadList");
	}

	public Ret download(Account loginAccount, Integer id, String ip) {
		Video download = dao.findById(id);
		if (download != null) {
			try {
				processDownloadCount(loginAccount, download, ip);
			} catch (Exception e) {
				e.printStackTrace();
				LogKit.error(e.getMessage(), e);
			}
			return Ret.ok("fullFileName", download.getPath() + download.getFileName());
		} else {
			return Ret.fail("msg", "文件未找到：" + id);
		}
	}

	/**
	 * 每个 ip 每天对于每个文件只统计一次下载量
	 */
	private void processDownloadCount(Account loginAccount, Video download, String ip) {
	 
		Db.update("update video set downloadCount = downloadCount + 1 where id = ?", download.getId());
		Record downloadLog = new Record().set("ip", ip).set("fileName", download.getFileName()).set("downloadDate", new Date());
		downloadLog.set("accountId", loginAccount.getId());
		Db.save("download_log", downloadLog);
		 
	}
}
