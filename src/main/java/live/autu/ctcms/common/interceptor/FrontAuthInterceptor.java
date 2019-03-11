

package live.autu.ctcms.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.StrKit;

import live.autu.ctcms.login.LoginService;

/**
 * 需要登录才能授权的操作，例如文件下载
 * 
 * 未登录将被重定向到登录界面，登录成功后又会再返回到原来想跳去的 url
 * 注意在登录表单中有 returnUrl 变量的传递才能跳到原来想跳去的 url
 * 详见登录页面表单传参
 */
public class FrontAuthInterceptor implements Interceptor {
	public void intercept(Invocation inv) {
		if (inv.getController().getAttr(LoginService.loginAccountCacheName) != null) {
			inv.invoke();
		} else {
			String queryString = inv.getController().getRequest().getQueryString();
			if (StrKit.isBlank(queryString)) {
				inv.getController().redirect("/login?returnUrl=" + inv.getActionKey());
			} else {
				inv.getController().redirect("/login?returnUrl=" + inv.getActionKey() + "?" + queryString);
			}
		}
	}
}

