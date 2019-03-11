

package live.autu.ctcms.index;

import com.jfinal.aop.Before;

import live.autu.ctcms.common.controller.BaseController;
import live.autu.ctcms.common.interceptor.AuthCacheClearInterceptor;

/**
 * 首页控制器
 */
public class IndexController extends BaseController {

	IndexService srv = IndexService.me;

	public void index() {
		redirect("/login");
	}

	@Before(AuthCacheClearInterceptor.class)
	public void clear() {
		srv.clearCache();
		redirect("/");
	}
}
