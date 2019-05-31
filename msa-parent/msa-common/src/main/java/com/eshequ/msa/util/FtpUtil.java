package com.eshequ.msa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eshequ.msa.exception.AppSysException;

/**
 * 使用时尽量一次下载多个文件，不要频繁开连接。暂时没有连接池的实现。
 * 多个下载线程并行时，每次new 单个实例保证线程安全。
 * @author david
 *
 */
public class FtpUtil {
	
    private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);
	private String charset = "GBK";	//默认GBK，修改的话自己set
	private boolean usePassiveMode = true;	//是否使用被动模式，默认true
	
    private FTPClient ftpClient;
    
    /**
     * 构造
     * @param host
     * @param port
     * @param username
     * @param pwd
     */
    public FtpUtil(String host, Integer port, String username, String pwd) {

		try {
			// 连接FTP服务器
			ftpClient = new FTPClient();
			ftpClient.setConnectTimeout(3000);
			ftpClient.connect(host, port);
			ftpClient.login(username, pwd);//登陆FTP服务器
			ftpClient.setControlEncoding(charset); // 中文支持
	        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
	        if (usePassiveMode) {
	        	ftpClient.enterLocalPassiveMode();
			}
	        int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				logger.error("未连接到FTP，用户名或密码错误。");
				ftpClient.disconnect();
			}
			logger.info("FTP连接成功。");
			ftpClient.changeWorkingDirectory("/");//切换到根目录
		} catch (Exception e) {
			throw new AppSysException(e);
		}
    }
    
    /**
     * ftp上传文件
     * @param f
     * @throws Exception
     */
    public void upload(File file) throws Exception{
        if (file.isDirectory()) {
        	ftpClient.makeDirectory(file.getName());
        	ftpClient.changeWorkingDirectory(file.getName());
            //返回目录名和文件名组成的字符串数组
            String[] files=file.list();
            for(String fstr : files){
                File file1=new File(file.getPath()+"/"+fstr);
                if (file1.isDirectory()) {
                    upload(file1);
                    ftpClient.changeToParentDirectory();
                } else {
                    File file2=new File(file.getPath()+"/"+fstr);
                    FileInputStream input=new FileInputStream(file2);
                    ftpClient.storeFile(file2.getName(),input);
                    input.close();
                }
            }
        }else{
            File file2=new File(file.getPath());
            FileInputStream input=new FileInputStream(file2);
            ftpClient.storeFile(file2.getName(),input);
            input.close();
        }
    }
    
    /** 
     * 
     * 下载FTP文件 
     * 当你需要下载FTP文件的时候，调用此方法 
     * 根据<b>获取的文件名，本地地址，远程地址</b>进行下载 
     * 
     * @param ftpFile 
     * @param relativeLocalPath 
     * @param relativeRemotePath 
     */ 
    public void download(FTPFile ftpFile, String relativeLocalPath,String relativeRemotePath) { 
        if (ftpFile.isFile()) {
        	OutputStream outputStream = null; 
            try { 
            	File entryDir = new File(relativeLocalPath);
				//如果文件夹路径不存在，则创建文件夹
				if (!entryDir.exists() || !entryDir.isDirectory())
				{
					entryDir.mkdirs();
				}
                File locaFile= new File(relativeLocalPath+ ftpFile.getName()); 
                //判断文件是否存在，存在则返回 
                if(locaFile.exists()){ 
                    return; 
                }else{ 
                    outputStream = new FileOutputStream(relativeLocalPath+ ftpFile.getName()); 
                    ftpClient.retrieveFile(ftpFile.getName(), outputStream); 
                    outputStream.flush(); 
                    outputStream.close(); 
                } 
            } catch (Exception e) {
            	logger.error(e.getMessage(), e);
            } finally { 
                try { 
                    if (outputStream != null){ 
                        outputStream.close();
                    }
                } catch (IOException e) { 
                   logger.error("输出文件流异常"); 
                } 
            }  
        }
    }
    
    /**
     * 根据条件列出文件
     * @param regx
     * @return
     */
    public FTPFile[] listFiles(String regx){
    	
    	FTPFileFilter filter = (file)->file.getName().contains(regx);
    	FTPFile[]ftpFiles = null;
    	try {
    		ftpFiles = ftpClient.listFiles("", filter);
    		
		} catch (IOException e) {
			logger.error("list ftp file failed !", e);
		}
    	return ftpFiles;
    }
    
    /**
     * 关闭ftp连接
     */
    public void closeFtp(){
        if (ftpClient!=null && ftpClient.isConnected()) {
            try {
            	ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
	
}
