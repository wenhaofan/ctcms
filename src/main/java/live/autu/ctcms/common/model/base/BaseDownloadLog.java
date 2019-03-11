package live.autu.ctcms.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDownloadLog<M extends BaseDownloadLog<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setAccountId(java.lang.Integer accountId) {
		set("accountId", accountId);
	}
	
	public java.lang.Integer getAccountId() {
		return getInt("accountId");
	}

	public void setIp(java.lang.String ip) {
		set("ip", ip);
	}
	
	public java.lang.String getIp() {
		return getStr("ip");
	}

	public void setFileName(java.lang.String fileName) {
		set("fileName", fileName);
	}
	
	public java.lang.String getFileName() {
		return getStr("fileName");
	}

	public void setDownloadDate(java.util.Date downloadDate) {
		set("downloadDate", downloadDate);
	}
	
	public java.util.Date getDownloadDate() {
		return get("downloadDate");
	}

}
