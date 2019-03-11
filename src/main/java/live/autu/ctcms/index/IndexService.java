

package live.autu.ctcms.index;

import com.jfinal.plugin.ehcache.CacheKit;

/**
 * 首页业务，主要为了方便做缓存，以及排序逻辑
 */
public class IndexService {

	public static final IndexService me = new IndexService();
	private final String indexCacheName = "index";
	 
	public void clearCache() {
		CacheKit.removeAll(indexCacheName);
	}
}



