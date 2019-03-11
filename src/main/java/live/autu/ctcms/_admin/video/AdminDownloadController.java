

package live.autu.ctcms._admin.video;

import com.jfinal.aop.Before;
import com.jfinal.kit.Ret;

import live.autu.ctcms.common.controller.BaseController;
import live.autu.ctcms.common.interceptor.AuthCacheClearInterceptor;
import live.autu.ctcms.common.interceptor.FrontAuthInterceptor;
import live.autu.ctcms.common.kit.IpKit;
import live.autu.ctcms.common.model.Account;

/**
 * 下载控制器
 */
@Before(FrontAuthInterceptor.class)
public class AdminDownloadController extends BaseController {

	AdminDownloadService srv = AdminDownloadService.me;

	/**
	 * 下载
	 */
	public void index() {
		Account loginAccount = getLoginAccount();
		String ip = IpKit.getRealIp(getRequest());
		Ret ret = srv.download(loginAccount, getPara("file"), ip);
		if (ret.isOk()) {
			String fullFileName = ret.getAs("fullFileName");
			renderFile(fullFileName);
		} else {
			renderError(404);
		}
	}

	/**
	 * 清缓存
	 */
	@Before(AuthCacheClearInterceptor.class)
	public void clear() {
		srv.clearCache();
		redirect("/");
	}
}
