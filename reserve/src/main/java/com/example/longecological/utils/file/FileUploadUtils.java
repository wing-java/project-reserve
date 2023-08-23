package com.example.longecological.utils.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.example.longecological.config.RuoYiConfig;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.encryption.md5.MD5Utils;
import com.example.longecological.utils.exception.file.FileNameLengthLimitExceededException;
import com.example.longecological.utils.exception.file.FileSizeLimitExceededException;
import com.example.longecological.utils.time.DateUtils;

/**
 * 文件上传工具类
 * 
 * @author ruoyi
 */
public class FileUploadUtils
{
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = RuoYiConfig.getProfile();

    /**
     * 默认文件类型jpg
     */
    public static final String IMAGE_JPG_EXTENSION = ".jpg";

    private static int counter = 0;

    
    /**
     * 设置默认上传的地址
     * @param defaultBaseDir
     */
    public static void setDefaultBaseDir(String defaultBaseDir)
    {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    
    /**
     * 获取默认上传的地址
     * @return
     */
    public static String getDefaultBaseDir()
    {
        return defaultBaseDir;
    }
    

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException
    {
        try
        {
            return upload(getDefaultBaseDir(), "", file, FileUploadUtils.IMAGE_JPG_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(ExceptionUtil.getExceptionAllinformation(e), e);
        }
    }

    
    
    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, String secondDir, MultipartFile file) throws IOException
    {
        try
        {
            return upload(baseDir, secondDir, file, FileUploadUtils.IMAGE_JPG_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(ExceptionUtil.getExceptionAllinformation(e), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @param extension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException 比如读写文件出错时
     */
    public static final String upload(String baseDir, String secondDir, MultipartFile file, String extension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException
    {
    	//获取文件名的原始长度
        int fileNamelength = file.getOriginalFilename().length();
        //如果原始文件名称长度>默认的文件名最大长度 100，抛出文件名称超长限制异常类
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        //文件大小校验
        assertAllowed(file);

        //文件名称处理
        String fileName = secondDir + extractFilename(file, extension);

        //创建对应的文件
        File desc = getAbsoluteFile(baseDir, baseDir + fileName);
        
        //文件转换
        file.transferTo(desc);
        
        return SysParamConstant.RESOURCE_PREFIX+"/"+fileName;
    }

    
    /**
     * 获得处理过得文件名称
     * @param file：文件对象
     * @param extension：上传文件类型
     * @return
     */
    public static final String extractFilename(MultipartFile file, String extension)
    {
    	//文件原始名称
        String filename = file.getOriginalFilename();
        //拼接文件名称：2018/08/08+加密处理过的文件名称+文件类型
        filename = DateUtils.datePath() + "/" + encodingFilename(filename) + extension;
        return filename;
    }
    
    
    /**
     * 文件名称重命名
     * @param fileName
     * @return
     */
    public static String renameToUUID(String fileName) {
		return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
	}

    
    /**
     * 得到文件的绝对路径
     * @param uploadDir：相对应用的基目录
     * @param filename：文件名称
     * @return
     * @throws IOException
     */
    private static final File getAbsoluteFile(String uploadDir, String filename) throws IOException
    {
    	//创建一个文件夹（File.separator：/字符转义）
        File desc = new File(File.separator + filename);
        //如果他的父文件夹不存在，则创建父文件夹
        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        //如果文件不存在，创建一个文件
        if (!desc.exists())
        {
            desc.createNewFile();
        }
        return desc;
    }

    
   
    /**
     * 编码文件名
     * @param filename：文件名称
     * @return
     */
    private static final String encodingFilename(String filename)
    {
        filename = filename.replace("_", " ");//去除下划线，替换为空格
        //文件名称加密
        filename = MD5Utils.hash(filename + System.nanoTime() + counter++);
        return filename;
    }

    
    
    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     */
    public static final void assertAllowed(MultipartFile file) throws FileSizeLimitExceededException
    {
    	//获取文件大小
        long size = file.getSize();
        //设置了默认大小，并且文件大小大于默认大小，抛出文件名大小限制异常
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE)
        {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }
    }
}
