

package live.autu.ctcms.common.loginlog;

/**
 * 用户登录时做日志，便于统计活跃用户
 * 用缓存缓冲一下，不要每次都写库
 *
 * 例如缓存设置为
 * map {accountId, date, times}
 *
 * 用户的 session 过期时间设置为 30 分钟，如果过期就会触发登录操作
 * 另外注意，登录操作可能是利用 cookie 值自动实现的，但是这个自动实现也
 * 算做是一次登录，相当于只要  ehcache 中没有用户 session，建立这个 session就算做是一次登录
 *
 * 集群部署时需要考虑 ehcache 同步问题
 */
public class LoginLogService {

	public static final LoginLogService me = new LoginLogService();

}
