package live.autu.ctcms.common.kit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Ret;

public class VideoFrameUtil {

	private static String rootPath = PathKit.getWebRootPath() + "/video/img";// 服务器路径

	private static final String IMAGEMAT = "png"; // 图片格式
	private static final String ROTATE = "rotate";
	public static final int MOD = 1;// 第一帧

	public static Ret getVideoFirstFrame(String videoPath, String imageName) {
		return getVideoFrame(videoPath, imageName, MOD);
	}

	/**
	 * 获取视频缩略图
	 * 
	 * @param lpath
	 * @param       filePath：视频路径
	 * @param       mod：视频长度/mod获取第几帧
	 * @throws Exception
	 */
	public static Ret getVideoFrame(String filePath, String targetImageName, int mod) {

		// 根据视频路径生成缩略图存放路径
		String targetFilePath = null;

		try (FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);) {

			ff.start();

			String rotate = ff.getVideoMetadata(ROTATE);
			int ffLength = ff.getLengthInFrames();
			Frame f = null;
			int i = 0;
			int index = mod;

			if (i >= ffLength) {
				return Ret.fail();
			}

			for (int j = 0; j <= index; j++) {
				f = ff.grabImage();
			}

			if (null != rotate && rotate.length() > 1) {
				OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
				IplImage src = converter.convert(f);
				f = converter.convert(rotate(src, Integer.valueOf(rotate))); // 旋转图片
			}

			targetFilePath = doExecuteFrame(f, targetImageName);
			ff.stop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Ret.ok("targetFilePath",targetFilePath)
				.set("relativeFilePath", "/video/img/"+targetImageName); // 返回的是视频第一帧
	}

	/**
	 * 旋转图片
	 * 
	 * @param src
	 * @param angle
	 * @return
	 */
	public static IplImage rotate(IplImage src, int angle) {
		IplImage img = IplImage.create(src.height(), src.width(), src.depth(), src.nChannels());
		opencv_core.cvTranspose(src, img);
		opencv_core.cvFlip(img, img, angle);
		return img;
	}

	/**
	 * 根据视频路径生成缩略图存放路径
	 * 
	 * @param filePath：视频路径
	 * @param index：第几帧
	 * @return：缩略图的存放路径
	 */
	private static String getImagePath(String fileName) {
		return rootPath+"/" + fileName;
	}

	/**
	 * 截取缩略图
	 * 
	 * @param f
	 * @param   targerFilePath:封面图片
	 * @throws IOException
	 */
	public static String doExecuteFrame(Frame f, String lpath) throws IOException {
		
		if(!new File(rootPath).exists()) {
			new File(rootPath).mkdirs();
		}
		
		String targerFilePath = getImagePath(lpath);
		if (null == f || null == f.image) {
			return null;
		}
		Java2DFrameConverter converter = new Java2DFrameConverter();
		BufferedImage bi = converter.getBufferedImage(f);
		File output = new File(targerFilePath);

		output.createNewFile();
		ImageIO.write(bi, IMAGEMAT, output);

		return targerFilePath;
	}

	/**
	 * 根据视频长度随机生成随机数集合
	 * 
	 * @param baseNum:基础数字，此处为视频长度
	 * @param length：随机数集合长度
	 * @return:随机数集合
	 */
	public static List<Integer> random(int baseNum, int length) {
		List<Integer> list = new ArrayList<Integer>(length);
		while (list.size() < length) {
			Integer next = (int) (Math.random() * baseNum);
			if (list.contains(next)) {
				continue;
			}
			list.add(next);
		}
		Collections.sort(list);
		return list;
	}

}
