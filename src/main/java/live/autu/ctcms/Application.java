package live.autu.ctcms;

import com.jfinal.server.undertow.UndertowServer;

import live.autu.ctcms.common.CtCMSConfig;

public class Application {

	public static void main(String[] args) {
		UndertowServer.start(CtCMSConfig.class);
	}
	
}
