
package live.autu.ctcms._admin.account;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

import live.autu.ctcms.common.kit.SensitiveWordsKit;

/**
 * AccountUpdateValidator 验证账号修改功能表单
 */
public class AccountUpdateValidator extends Validator {
	
	protected void validate(Controller c) {
		setShortCircuit(true);

	 
		validateRequired("account.nickName", "msg", "昵称不能为空");
		validateString("account.nickName", 1, 19, "msg", "昵称不能超过19个字");

		String nickName = c.getPara("account.nickName").trim();
		if (nickName.contains("@") || nickName.contains("＠")) { // 全角半角都要判断
			addError("msg", "昵称不能包含 \"@\" 字符");
		}
		if (nickName.contains(" ") || nickName.contains("　")) {
			addError("msg", "昵称不能包含空格");
		}
	 

		/**
		 * 验证 userName
		 */
		validateRequired("account.userName", "msg", "邮箱不能为空");
		validateEmail("account.userName", "msg", "邮箱格式不正确");
	}

	protected void handleError(Controller c) {
		c.setAttr("state", "fail");
		c.renderJson();
	}
}

