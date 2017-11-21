package com.sh.springboot.capture;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

/**
 * 摄像头抓图类
 */
public class CameraCapture extends JFrame {
	private static Logger logger = Logger.getLogger(CameraCapture.class);

	private static final long serialVersionUID = -250082071618972548L;

	
	public CameraCapture() throws Exception{
		logger.info("capture start");
		// 启动摄像头
		initCamera();
		
		// 设置窗体属性
		this.setTitle("camera1");
		this.setBounds(500, 100, 800, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	// 初始化摄像头
	private void initCamera() throws Exception{
		// 获取所有的音频视频设备
		Vector<CaptureDeviceInfo> deviceInfoList = CaptureDeviceManager.getDeviceList(null);
		
		CaptureDeviceInfo cameraDeviceInfo = null;
		
		if(deviceInfoList == null){
			logger.info("音频视频设备为空");
			throw new Exception("音频视频设备为空");
		}
		for(CaptureDeviceInfo tmp : deviceInfoList){
			if(tmp.getName().startsWith("vfm")){
				cameraDeviceInfo = tmp;
				break;
			}
		}
		
		if(cameraDeviceInfo == null){
			logger.info("未找到视频设备");
			throw new Exception("未找到视频设备");
		}
		
		// 创建视频播放器
		MediaLocator mediaLocator = cameraDeviceInfo.getLocator();
		Player player = Manager.createRealizedPlayer(mediaLocator);
		
		if(player == null){
			logger.info("创建视频播放器失败");
			throw new Exception("创建视频播放器失败");
		}
		
		// 播放视频
		player.start();
		
		// 将播放器加入到窗体
		Component component = player.getControlPanelComponent();
		if(component != null){
			add(component, BorderLayout.CENTER);
		}
	}
}
