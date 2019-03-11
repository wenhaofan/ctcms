

package live.autu.ctcms.common.handler;

import com.jfinal.core.Action;
import com.jfinal.core.JFinal;
import com.jfinal.handler.Handler;
import com.jfinal.kit.HandlerKit;
import com.jfinal.kit.StrKit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 只影响 index() 与 detail()，这两个 action 的 url 变得更短，有利于 SEO，规则是：
 * 1：actionMethod 为 "index" 时，判断是否有 urlPara，有的话直接将 target 转成 controllerKey + "detail" + urlPara
 * 2：actionMethod 为 "detail" 时，直接阻止请求，不允许这样的 url 出现
 * 3：其它情况直接放行
 *
 * 注意两点：
 * 1：由于对带有 urlPara 的 index() 请求，都被转成了 detail() 请求，所以这类应用场景需要将 index() 方法改名
 *    并利用 @ActionKey(controllerKey) 指定为自己想要的 url，具体可参考 "/my" 模块中的一些 Controller 用法
 * 
 * 2：由于 detail() 方法已被阻止，系统中所有 url 都不能添加 "detail" 为 actionMethod 部分，直接去掉这部分即可
 * 
 */
public class UrlSeoHandler extends Handler {

	private String detailMethodName;
	private String detailMethodWithSlash;

	public UrlSeoHandler() {
		detailMethodName = "detail";
		detailMethodWithSlash = "/" + detailMethodName + "/";
	}

	public UrlSeoHandler(String detailMethodName) {
		if (StrKit.isBlank(detailMethodName)) {
			throw new  IllegalArgumentException("detailMethodName can not be blank.");
		}
		this.detailMethodName = detailMethodName;
		this.detailMethodWithSlash = "/" + detailMethodName + "/";
	}

	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		// 静态请求直接跳出
		if (target.indexOf('.') != -1) {
			return ;
		}

		String[] urlPara = {null};
		Action action = JFinal.me().getAction(target, urlPara);
		if (action != null) {
			String methodName = action.getMethodName();
			if ("index".equals(methodName)) {
				if (StrKit.notBlank(urlPara[0])) {
					target = action.getControllerKey() + detailMethodWithSlash + urlPara[0];
				}
			}
			// 不允许 "detail" 出现在 url 中，映射到 detail() 方法的 url 仅使用 "/project/123" 这种格式
			else if (detailMethodName.equals(methodName)) {
				HandlerKit.renderError404(request, response, isHandled);
				return;
			}
		}

		next.handle(target, request, response, isHandled);
	}
}
