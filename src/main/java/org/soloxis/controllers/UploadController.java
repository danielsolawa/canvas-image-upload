package org.soloxis.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
	private static final String SAVE_DIR = "D:/";
	
	@RequestMapping(value ="/upload")
	public Map<String, Object> uploadImage(@RequestBody Map<String, Object> data) throws IOException{
		
		String image64Base = (String)data.get("imgBase64");
		String fileName = generateFileName();
		
		LOGGER.debug("file name " + fileName);
		
		
		byte[] imageData = decodeDataURL(image64Base);

		BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));
		if (image == null) {
			LOGGER.error("Buffered Image is null");
		} else {
			LOGGER.debug("Starting upload to server ");

		}
		File outputfile = new File(SAVE_DIR + fileName);

		ImageIO.write(image, "png", outputfile);
		
		
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("name", fileName);
		return result;
	}

	private String generateFileName() {
		Random rand = new Random();
		String fileName = "";
		for(int i = 0; i< 10; i++){
			char gen = (char)  (rand.nextInt(25)+ 65);
			fileName += gen;
		}
		String finalName = fileName.toLowerCase();
		finalName += ".png";
		return finalName;
	}
	
	private byte[] decodeDataURL(String imageBase64) {

		String decoded = null;
		try {
			decoded = URLDecoder.decode(imageBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		String normalizeString = decoded.replaceAll("data:image/png;base64,", "");
		String finalString = normalizeString.replaceAll(" ", "+");
		byte[] imageData = Base64.decodeBase64(finalString);

	

		return imageData;
	}

}
