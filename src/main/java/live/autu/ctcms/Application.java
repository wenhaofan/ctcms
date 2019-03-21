package live.autu.ctcms;

import com.jfinal.server.undertow.UndertowServer;

import live.autu.ctcms.common.CTCMSConfig;

public class Application {

	public static void main(String[] args) {
		UndertowServer.start(CTCMSConfig.class);
	}
	
}
