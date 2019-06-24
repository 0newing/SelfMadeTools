/*
 *
 * 文件名: FileUtils.java
 * 描述: 一句话描述
 * 创建人: curatorjin
 * 时间: 2019/5/12  15:30
 *
 */
package io.github.curatorjin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 文件工具
 *
 * @author : curatorjin
 * @version : 1.0
 */
public final class FileUtils
{
    /**
     * 日志输出类
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils()
    {
    }

    /**
     * 删除整个目录或文件
     *
     * @param file 待删除的目录或文件
     */
    public static void deleteAll(File file)
    {
        if (null != file)
        {
            if (file.isDirectory())
            {
                File[] files = file.listFiles();
                if (files == null)
                {
                    return;
                }
                for (File f : files)
                {
                    deleteAll(f);
                }
                if (file.delete())
                {
                    LOGGER.info("delete success!");
                }
            }
            else
            {
                if (file.delete())
                {
                    LOGGER.info("delete success!");
                }
            }
        }
    }

    /**
     * 获取文件特征码
     *
     * @param file 目标文件
     * @param type 加密类型
     * @return 文件特征码
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 无加密算法异常
     */
    private static byte[] getFileCode(File file, String type)
        throws IOException, NoSuchAlgorithmException
    {
        InputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance(type);
        int numRead;

        do
        {
            numRead = fis.read(buffer);
            if (numRead > 0)
            {
                complete.update(buffer, 0, numRead);
            }
        }
        while (numRead != -1);
        CloseUtil.closeStream(fis);
        return complete.digest();
    }

    /**
     * 获取文件MD5
     *
     * @param file 目标文件
     * @return 文件MD5
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 无加密算法异常
     */
    public static String getMD5(File file)
        throws IOException, NoSuchAlgorithmException
    {
        byte[] b = getFileCode(file, "MD5");
        StringBuilder result = new StringBuilder();
        for (byte aB : b)
        {
            result.append(Integer.toString((aB & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

    /**
     * 获取文件SHA1
     *
     * @param file 目标文件
     * @return 文件SHA1
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 无加密算法异常
     */
    public static String getSHA1(File file)
        throws IOException, NoSuchAlgorithmException
    {
        byte[] b = getFileCode(file, "SHA-1");
        StringBuilder result = new StringBuilder();
        for (byte aB : b)
        {
            result.append(Integer.toString((aB & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

    /**
     * 获取文件SHA256
     *
     * @param file 目标文件
     * @return 文件SHA256
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 无加密算法异常
     */
    public static String getSHA256(File file)
        throws IOException, NoSuchAlgorithmException
    {
        byte[] b = getFileCode(file, "SHA-256");
        StringBuilder result = new StringBuilder();
        for (byte aB : b)
        {
            result.append(Integer.toString((aB & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

}
