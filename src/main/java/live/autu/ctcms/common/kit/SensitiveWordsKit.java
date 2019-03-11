

package live.autu.ctcms.common.kit;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词检测
 */
public class SensitiveWordsKit {

	private static final List<String> sensitiveWords = build();

	private static List<String> build() {
		ArrayList<String> ret = new ArrayList<String>();
		List<Record> list = Db.find("select * from sensitive_words where status = 1");
		for (Record r : list) {
			ret.add(r.getStr("word"));
		}
		return ret;
	}

	/**
	 * 在 MyProjectValidator 中checkSensitiveWords(c.getPara("project.content"),....) 这行调用
	 * 在第一次调用时传入 null 时 target 为 String[1]对象，而里面的内容为 null，第二次调用 target 则为 null
	 */
	public static String checkSensitiveWord(String... target) {
		if (target != null) {
			for (String s : target) {
				if (s != null) {
					for (String sensitiveWord : sensitiveWords) {
						if (s.indexOf(sensitiveWord) >= 0) {
							return sensitiveWord;
						}
					}
				}
			}
		}
		return null;
	}
}
