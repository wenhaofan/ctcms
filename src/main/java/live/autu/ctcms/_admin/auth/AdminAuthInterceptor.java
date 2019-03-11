

package live.autu.ctcms._admin.auth;

import com.jfinal.aop.Inject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.Ret;

import live.autu.ctcms.common.model.Account;
import live.autu.ctcms.login.LoginService;

/**
 * 后台管理员授权拦截器
 */
public class AdminAuthInterceptor implements Interceptor {

	@Inject
	AdminAuthService srv;
	
	/**
	 * 用于 sharedObject、sharedMethod 扩展中使用
	 */
	private static final ThreadLocal<Account> threadLocal = new ThreadLocal<Account>();
	
	public static Account getThreadLocalAccount() {
		return threadLocal.get();
	}

	public void intercept(Invocation inv) {
		Account loginAccount = inv.getController().getAttr(LoginService.loginAccountCacheName);
		if (loginAccount != null && loginAccount.isStatusOk()) {
			// 传递给 sharedObject、sharedMethod 扩展使用
			threadLocal.set(loginAccount);
			
			// 如果是超级管理员或者拥有对当前 action 的访问权限则放行
			if (	srv.isSuperAdmin(loginAccount.getId()) ||
					srv.hasPermission(loginAccount.getId(), inv.getActionKey())) {
				inv.invoke();
				return ;
			}
		}

		// renderError(404) 避免暴露后台管理 url，增加安全性
		if (loginAccount == null || inv.getActionKey().equals("/admin")) {
			inv.getController().renderError(404);
		}
		// renderJson 提示没有操作权限，提升用户体验
		else {
			inv.getController().renderJson(Ret.fail("msg", "没有操作权限"));
		}
	}
}

