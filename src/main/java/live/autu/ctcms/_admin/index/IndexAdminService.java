

package live.autu.ctcms._admin.index;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;

/**
 * 首页业务
 */
public class IndexAdminService {

	public Ret getAccountProfile() {
		Ret ret = Ret.by("title", "账户总数");
		Integer total = Db.queryInt("select count(*) from account");
		ret.set("total", total);
		return ret;
	}
 
	public Ret getPermissionProfile() {
		Ret ret = Ret.by("title", "权限总数");
		Integer total = Db.queryInt("select count(*) from permission");
		ret.set("total", total);
		return ret;
	}
}