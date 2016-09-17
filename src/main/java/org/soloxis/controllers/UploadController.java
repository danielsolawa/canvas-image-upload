package org.soloxis.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadController {
	
	@RequestMapping(value ="/upload")
	public Map<String, Object> uploadImage(@RequestBody Map<String, Object> data){
		
		return null;
	}

}
