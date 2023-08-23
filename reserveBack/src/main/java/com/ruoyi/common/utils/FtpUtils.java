package com.ruoyi.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FtpUtils {

	private static final Logger Logger = LoggerFactory.getLogger(FtpUtils.class);
	
	private FTPClient ftpClient;
//	private static String ip = "47.88.152.173"; // 服务器IP地址
//	private static String userName = "uftp"; // 用户名
//	private static String userPwd = "jiujiushengtai123"; // 密码
//	private static int port = 21; // 端口号
//	private static String path = "/var/www/html/recharge_log/"; // 读取文件的存放目录
//	private static String pathBack = "/var/www/html/recharge_log/back/"; //文件存放备份目录
	private static String remoteDir = "/";

	/**
	* @param ip
	* @param port
	* @param userName
	* @param userPwd
	* @param path
	* @throws SocketException
	* @throws IOException
	*             function:连接到服务器
	*/
	public void connectServer(String ip, int port, String userName, String userPwd) {
		//String ip, int port, String userName,String userPwd
		ftpClient = new FTPClient();
		try {
			// 连接
			ftpClient.connect(ip, port);
			// 登录
			ftpClient.login(userName, userPwd);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	* @throws IOException
	*             function:关闭连接
	*/
	public void closeServer() {
		if (ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	* 获取以当前时间命名的文件夹名字
	*/
	public String getTimeFilename() {
		Calendar c = Calendar.getInstance();
		Integer year = c.get(Calendar.YEAR);
		Integer month = c.get(Calendar.MONTH)+1;
		Integer data = c.get(Calendar.DATE);
		String paths;
		if (month.toString().length() == 1) {
			return paths = Integer.toString(year)+"0"+Integer.toString(month)+Integer.toString(data);
		} else {
			return paths = Integer.toString(year)+Integer.toString(month)+Integer.toString(data);
		}
	}
	/**
	* @param path
	* @return function:读取指定目录下的文件名
	* @throws IOException
	*/
	public List<String> getFileList(String path,String filenamen) {

		List<String> fileLists = new ArrayList<String>();
		// 获得指定目录下所有文件名
		FTPFile[] ftpFiles = null;
		try {
//				ftpClient.enterLocalActiveMode();    //主动模式
				ftpClient.enterLocalPassiveMode(); //被动模式
				ftpClient.changeWorkingDirectory(path+filenamen);
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftpFiles = ftpClient.listFiles(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
			FTPFile file = ftpFiles[i];
			if (file.isFile()) {
				fileLists.add(file.getName());
			}
		}
		return fileLists;
	}
	/*
	* 取得第一层文件,目录移除
	*/
	public List<String> getFileLists(String path) {
		//String path

		List<String> fileLists = new ArrayList<String>();
		// 获得指定目录下所有文件名
		FTPFile[] ftpFiles = null;
		try {
			Logger.info("获取文件中。。。");
//			ftpClient.enterLocalActiveMode();    //主动模式
			ftpClient.enterLocalPassiveMode(); //被动模式
//			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			Logger.info("执行获取文件操作。。。");
			Logger.info("路径下"+path+"文件数：" +String.valueOf(ftpClient.listNames(path).length));
			ftpFiles = ftpClient.listFiles(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
			FTPFile file = ftpFiles[i];
			Logger.info(file.getName());
			if(file.isFile()){
				fileLists.add(file.getName());
			}
		}

		return fileLists;
	}
	/*
	* 取得单个文件文件名
	*/
	/**
	* 写入文件，简单实现
	*/
	public boolean setFile(String test){
		InputStream inputStream = new ByteArrayInputStream(test.getBytes());
		try {
			ftpClient.storeFile("ceshi.txt", inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	/**
	* @param fileName
	* @return function:从服务器上读取指定的文件
	* @throws ParseException
	* @throws IOException
	*/
	public String readFile(String path, String filename) throws ParseException {
		InputStream ins = null;
		StringBuilder builder = null;
		try {
//			ftpClient.enterLocalActiveMode();    //主动模式
			ftpClient.enterLocalPassiveMode(); //被动模式
			ftpClient.changeWorkingDirectory(path);//进入当前目录
			ins = ftpClient.retrieveFileStream(filename);//读取文件
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(ins, "UTF-8"));
			String line;
			builder = new StringBuilder(1024);
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			reader.close();
			if (ins != null) {
				ins.close();
			}
			//去除null值
			ftpClient.getReply();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder != null ? builder.toString() : null;
	}
	
	/**
	 * 文件备份，将文件移除到指定路劲
	 * @param path 文件路径
	 * @param pathBack 文件备份路径
	 * @param filename 文件名
	 * @param isDel 是否删除原有文件
	 */
	public void backUpFile(String path, String pathBack, String filename, boolean isDel){
		//备份文件
		String backFilePath = pathBack + remoteDir + filename;
		//创建文件
		try {
//			ftpClient.enterLocalActiveMode();    //主动模式
			ftpClient.enterLocalPassiveMode(); //被动模式
			ftpClient.changeWorkingDirectory(path);//进入当前目录
			ftpClient.rename(filename,backFilePath);//备份文件
			if(isDel) ftpClient.removeDirectory(filename);//删除文件
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	* @param args
	* @throws ParseException
	*/
	public static void main(String[] args) {
		
		//连接服务器
//		FtpUtils ftpUtils = new FtpUtils();
//		ftpUtils.connectServer();
//		//ip, port, userName, userPwd
//		String path = "/var/www/html/recharge_log/";
//		List<String> files = ftpUtils.getFileLists(path);//path
//		
//		if(files != null && files.size() > 0){
//			for(String file : files){
//				try {
//					String content = ftpUtils.readFile(path, file);
//					if(!StringUtils.isEmpty(content)){
//						JSONArray list = JSONArray.parseArray(content);
//						for(int i=0; i<list.size(); i++){
//							Logger.info("获取的对象：" + list.get(0));
//						}
//					}
//					System.out.println("文件名：" + file + "  文件内容：" + content);
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		//关闭FTP
//		ftpUtils.closeServer();
		
	}

}
