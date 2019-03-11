

package live.autu.ctcms._admin.index;

import com.jfinal.aop.Inject;

import live.autu.ctcms.common.controller.BaseController;

/**
 * 后台管理首页
 */
public class IndexAdminController extends BaseController {

	@Inject
	IndexAdminService srv;

	public void index() {
		setAttr("accountProfile", srv.getAccountProfile());
 
		setAttr("permissionProfile", srv.getPermissionProfile());

		render("index.html");
	}
}
