package live.autu;

import org.junit.Test;

import com.jfinal.kit.PathKit;

import live.autu.ctcms.common.kit.VideoFrameUtil;

public class TestVideFrameUtils {

	@Test
	public void testGetFirstFrame() {
		String videoPath="L:/Eclipse/workspace/order/ctcms/src/main/webapp/1_20190322093924.mp4";
		String webRootPath="L:\\Eclipse\\workspace\\order\\ctcms\\src\\main\\webapp";
		PathKit.setWebRootPath(webRootPath);
		
		System.out.println(VideoFrameUtil.getVideoFirstFrame(videoPath,"test.jpg"));;
	}
	
}
