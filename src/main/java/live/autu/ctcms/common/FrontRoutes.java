 

package live.autu.ctcms.common;

import com.jfinal.config.Routes;

import live.autu.ctcms.common.upload.UploadController;
import live.autu.ctcms.index.IndexController;
import live.autu.ctcms.login.LoginController;

/**
 * 前台路由
 */
public class FrontRoutes extends Routes {

	public void config() {
		setBaseViewPath("/_view");
		
		add("/", IndexController.class, "/index");
 
		add("/login", LoginController.class);
		
		add("/api/upload", UploadController.class);
  
	}
}
