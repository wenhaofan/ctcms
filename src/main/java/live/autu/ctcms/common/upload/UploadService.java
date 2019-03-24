

package live.autu.ctcms.common.upload;

import java.io.File;
import java.util.Date;

import org.joda.time.LocalDateTime;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;

import live.autu.ctcms.common.kit.ImageKit;
import live.autu.ctcms.common.kit.VideoFrameUtil;
import live.autu.ctcms.common.model.Account;
import live.autu.ctcms.common.model.Video;

/**
 * 上传业务
 * 1：不同模块分别保存到不同子目录
 * 2：每个目录下文件数达到 5000 时创建新的子目录，upload_counter 用于记录每个模块文件上传总数
 *    用于创建子目录
 */
public class UploadService {

	public static final UploadService me = new UploadService();

 

	/**
	 * 上传视频临时目录，相对于 baseUploadPath 的路径，是否以 "/" 并无影响
	 * 本项目的 baseUploadLoad 为 /var/www/project_name/upload
	 */
	public static final String uploadTempPath = "/video/temp";

	/**
	 * 相对于 webRootPath 之后的目录，与"/upload" 是与 baseUploadPath 重合的部分
	 */
	private static final String basePath = "/upload/video/";

	/**
	 * 每个子目录允许存 5000 个文件
 	 */
	public static final int FILES_PER_SUB_DIR = 5000;

	/**
	 *  上传业务方法
	 */
	public Ret upload(Account account, String uploadType, UploadFile uf,Date createDate) {
 
		if(createDate==null) {
			createDate=new Date();
		}
		
		String fileSize = uf.getFile().length() + "";
		String extName = "." + ImageKit.getExtName(uf.getFileName());

		// 相对路径 + 文件名：用于返回 ueditor 要求的 url 字段值，形如："/upload/img/project/0/123.jpg
		String[] relativePathFileName = new String[1];
		// 绝对路径 + 文件名：用于保存到文件系统
		String[] absolutePathFileName = new String[1];
		// 生成的文件名
		String[] fileName = new String[1];
		buildPathAndFileName(uploadType, account.getId(), extName, relativePathFileName, absolutePathFileName, fileName,createDate);
		saveOriginalFileToTargetFile(uf.getFile(), absolutePathFileName[0]);

		// 更新 upload_counter 表的 counter 字段值
		updateUploadCounter(uploadType);

	 
		Ret videoFrameResult=VideoFrameUtil.getVideoFirstFrame(absolutePathFileName[0], fileName[0]+".jpg");
 
		if(StrKit.isBlank(videoFrameResult.getStr("relativeFilePath"))) {
			return Ret.fail("msg", "截图保存失败！");
		}
		String url=relativePathFileName[0].replace(fileName[0], "");
		Video video=new Video();
		
		video.setCreateDate(createDate);
		video.setDescr(fileName[0]);
		video.setFileName(fileName[0]);
		video.setFileType(extName);
		video.setDownloadCount(0);
		video.setIsShow(1);
		video.setPath(url);
		video.setSize(fileSize);
		video.setPreview(videoFrameResult.getStr("relativeFilePath"));
		video.save();
		
		return Ret.ok("url",relativePathFileName[0])
				.set("title", fileName[0])
				.set("original", uf.getOriginalFileName())
				.set("type", extName)
				.set("size", fileSize);
	}

	/**
	 * 生成规范的文件名
	 * accountId_年月日时分秒
	 * 包含 accountId  ，方便清除恶意上传
	 * 目录中已经包含了模块名了，这里的 meta 只需要体现 accountId 与时间就可以了
	 */
	private String generateFileName(Date createDate,Integer accountId, String extName) {
		LocalDateTime dt = LocalDateTime.fromDateFields(createDate);
		return accountId + "_" + dt.toString("yyyy年MM月dd日HH时mm分ss秒") + extName+Math.random()*1000;
	}

	/**
	 * 根据上传类型生成完整的文件保存路径
	 * @param uploadType 上传类型，目前支持四种：project, share, feedback, document
	 */
	private void buildPathAndFileName(
			String uploadType,
			Integer accountId,
			String extName,
			String[] relativePathFileName,
			String[] absolutePathFileName,
			String[] fileName,Date createDate) {

		Integer counter = Db.queryInt("select counter from upload_counter where uploadType=? limit 1", uploadType);
		if (counter == null) {
			throw new IllegalArgumentException("uploadType 不正确");
		}

		String relativePath = "/" + (counter / FILES_PER_SUB_DIR) + "/";    // 生成相对对路径
		relativePath = basePath + uploadType + relativePath;

		fileName[0] = generateFileName(createDate,accountId, extName);
		relativePathFileName[0] =  relativePath + fileName[0];

		String absolutePath = PathKit.getWebRootPath() + relativePath;   // webRootPath 将来要根据 baseUploadPath 调整，改代码，暂时选先这样用着，着急上线
		File temp = new File(absolutePath);
		if (!temp.exists()) {
			temp.mkdirs();  // 如果目录不存在则创建
		}
		absolutePathFileName[0] = absolutePath + fileName[0];
	}

	/**
	 * 上传完成后更新 upload_counter 表
	 */
	private void updateUploadCounter(String uploadType) {
		Db.update("update upload_counter set counter = counter + 1 where uploadType=? limit 1", uploadType);
	}

	/**
	 * 目前使用 File.renameTo(targetFileName) 的方式保存到目标文件，
	 * 如果 linux 下不支持，或者将来在 linux 下要跨磁盘保存，则需要
	 * 改成 copy 文件内容的方式并删除原来文件的方式来保存
	 */
	private void saveOriginalFileToTargetFile(File originalFile, String targetFile) {
		originalFile.renameTo(new File(targetFile));
	}

 
}
