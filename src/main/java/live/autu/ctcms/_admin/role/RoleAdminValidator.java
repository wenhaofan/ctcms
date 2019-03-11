

package live.autu.ctcms._admin.role;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * AccountUpdateValidator 验证角色修改功能表单
 */
public class RoleAdminValidator extends Validator {
	
	protected void validate(Controller c) {
		setShortCircuit(true);

		validateRequiredString("role.name", "msg", "角色名称不能为空");
	}

	protected void handleError(Controller c) {
		c.setAttr("state", "fail");
		c.renderJson();
	}
}

