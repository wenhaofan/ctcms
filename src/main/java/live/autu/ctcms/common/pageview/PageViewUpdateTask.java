

package live.autu.ctcms.common.pageview;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.cron4j.ITask;
import java.util.Date;

/**
 * 定时更新 project_page_view、share_page_view、feedback_page_view
 *
 * 目前暂定为每小时的 0 分这个时间点更新
 * cron 表达式为：0 * * * *
 */
public class PageViewUpdateTask implements ITask {

	public void run() {
		doUpdate();
	}

	public void stop() {
		doUpdate();
	}

	private void doUpdate() {
		PageViewService.me.updateToDataBase();

		// 每次调度启动时，向 task_run_log 写日志，用于检查调度的时间是否与预期的一致，避免出现 bug 却不知道
		Record taskRunLog = new Record().set("taskName", "PageViewUpdateTask").set("createAt", new Date());
		Db.save("task_run_log", taskRunLog);
	}
}
