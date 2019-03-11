

package live.autu.ctcms.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * SEO 搜索引擎优化基础拦截器
 */
public abstract class BaseSeoInterceptor implements Interceptor {

	public static final String SEO_TITLE = "seoTitle";
	public static final String SEO_KEYWORDS = "seoKeywords";
	public static final String SEO_DESCR = "seoDescr";

	protected void setSeoTitle(Controller c, String seoTitle) {
		c.setAttr(SEO_TITLE, seoTitle);
	}

	protected void setSeoKeywords(Controller c, String seoKeywords) {
		c.setAttr(SEO_KEYWORDS, seoKeywords);
	}

	protected void setSeoDescr(Controller c, String seoDescr) {
		c.setAttr(SEO_DESCR, seoDescr);
	}

	public final void intercept(Invocation inv) {
		inv.invoke();

		Controller c = inv.getController();
		String method = inv.getMethodName();
		if (method.equals("index")) {
			if (c.getPara() == null) {
				indexSeo(c);
			} else {
				detailSeo(c);
			}
		} else if (method.equals("detail")) {
			detailSeo(c);
		} else {
			othersSeo(c, method);
		}
	}

	// 对 index() action 进行 seo
	public abstract void indexSeo(Controller c) ;

	// 对 detail() action 进行 seo
	public abstract void detailSeo(Controller c) ;

	// 对其它方法进行 seo，只需在该方法的实现类中判断一下 method 参数名
	// 并调用前面的 seoTitle、setSeoKeywords、setSeoDescr 三个工具方法即可完成 seo
	public abstract void othersSeo(Controller c, String method);
}



