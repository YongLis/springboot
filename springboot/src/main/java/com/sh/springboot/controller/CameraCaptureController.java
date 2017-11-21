package com.sh.springboot.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sh.springboot.capture.CameraCapture;

@RestController
@RequestMapping("/capture")
public class CameraCaptureController {
	private static Logger logger = Logger.getLogger(CameraCaptureController.class);
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(){
		logger.info("test start..........");
		return "test success";
	}
	
	@RequestMapping(value="/cameraCapture", method=RequestMethod.GET)
	public String cameraCapture(){
		logger.info("CameraCaptureController start..........");
		try {
			System.setProperty("java.awt.headless","true");
			CameraCapture cameraCapture = new CameraCapture();
		} catch (Exception e) {
			logger.error("摄像头启动错误",e);
		}
		logger.info("CameraCaptureController end..........");
		return "success";
	}
}
