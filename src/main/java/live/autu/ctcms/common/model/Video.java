

package live.autu.ctcms.common.model;

import live.autu.ctcms.common.model.base.BaseVideo;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Video extends BaseVideo<Video> {
	
	public String getFullName() {
		return getPath()+getFileName();
	}
	
}
