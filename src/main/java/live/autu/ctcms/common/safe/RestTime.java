

package live.autu.ctcms.common.safe;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import live.autu.ctcms.common.model.Account;
import live.autu.ctcms.login.LoginService;

import org.joda.time.LocalTime;

/**
 * 休息时间对待内容提交的策略
 */
public class RestTime implements Interceptor {

	// 动态加载在休息时间可允许的最大的id 值
	private static final int restTimeMaxId = 44079;   //PropKit.getInt("restTimeMaxId");

	private static final LocalTime workTime = new LocalTime(8, 30);     // LocalTime.parse("08:30");
	private static final LocalTime restTime = new LocalTime(22, 0);       // LocalTime.parse("22:00");

	public void intercept(Invocation inv) {
		Account loginAccount = inv.getController().getAttr(LoginService.loginAccountCacheName);
		String msg = checkRestTime(loginAccount);
		if (msg != null) {
			inv.getController().renderJson("msg", msg);
		} else {
			inv.invoke();
		}
	}

	/**
	 * 如果是休息时间则返回提示信息，否则返回 null
	 */
	public static String checkRestTime(Account loginAccount) {
		// 如果账号 id 小于restTimeMaxId 则放行，允许任何时候发布内容
		if (loginAccount.getId() <= restTimeMaxId) {
			return null;
		}

		LocalTime now = LocalTime.now();
		if (now.isBefore(workTime)) {
			return "太早了点吧，赶紧吃早饭去，吃完后再来发哈！";
		} else if (now.isAfter(restTime)) {
			return "夜深了，早点休息，明天再来发哈！";
		} else {
			return null;
		}
	}
}
