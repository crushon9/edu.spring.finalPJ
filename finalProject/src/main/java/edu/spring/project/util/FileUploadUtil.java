package edu.spring.project.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class FileUploadUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

	public static String saveUploadedFile(String uploadPath, String fileName, byte[] data) throws IOException {
		UUID uuid = UUID.randomUUID();
		String saveName = uuid.toString() + "_" + fileName;
		makeDir(uploadPath);
		File target = new File(uploadPath, saveName);
		FileCopyUtils.copy(data, target);
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (MediaUtil.geMediaType(extension) != null) {
			createThumbnail(uploadPath, saveName);
		} else {
			createIcon(uploadPath, saveName);
		}
		return "/" + saveName;
	}

	private static void makeDir(String uploadPath) {
		File dirPath = new File(uploadPath);
		if (!dirPath.exists()) {
			dirPath.mkdir();
			logger.info(dirPath.getPath() + " successfully created.");
		} else {
			logger.info(dirPath.getPath() + " already exists.");
		}
	}

	private static String createThumbnail(String uploadPath, String fileName) throws IOException {

		BufferedImage source = ImageIO.read(new File(uploadPath, fileName));
		BufferedImage destination = Scalr.resize(source, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		String thumbnailName = uploadPath + File.separator + "thumbnail_" + fileName;
		File thumbnail = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);

		boolean result = ImageIO.write(destination, formatName, thumbnail);
		logger.info("create thumbnail result: " + result);

		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	private static String createIcon(String uploadPath, String fileName) {
		String iconName = uploadPath + File.separator + fileName;

		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
}
