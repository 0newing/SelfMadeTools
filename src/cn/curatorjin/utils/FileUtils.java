/*
 *
 * 文件名: FileUtils.java
 * 描述: 关于文件处理的工具类
 * 创建人: 0newing
 * 时间: 2018/12/5  18:43
 *
 */
package cn.curatorjin.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 文件工具
 *
 * @author : 0newing
 * @version : 1.0
 */
public final class FileUtils
{

    private FileUtils()
    {
    }

    /**
     * 获取目录中包含指定字符串的文件名
     *
     * @param file   检测文件夹
     * @param suffix 文件后缀名
     * @param regex  需检测的字符串
     * @return 包含指定字符串的文件名集合
     * @throws IOException 文件错误
     */
    public static List<String> checkDirContains(File file, String suffix, String regex)
        throws IOException
    {
        if (file == null || !file.isDirectory())
        {
            return null;
        }
        List<String> resultList = new ArrayList<>();
        File[] files = file.listFiles();
        if (files != null)
        {
            for (File f : files)
            {
                if (checkFileContains(f, suffix, regex))
                {
                    resultList.add(f.getName());
                }
            }
        }
        return resultList;
    }

    /**
     * 判断文件中是否包含指定的字符串
     *
     * @param file   检测文件
     * @param suffix 文件后缀名
     * @param regex  需检测的字符串
     * @return 是否包含字符串
     * @throws IOException 文件错误
     */
    public static boolean checkFileContains(File file, String suffix, String regex)
        throws IOException
    {
        String fileName = file.getName();
        if (fileName.endsWith(suffix))
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null)
            {
                if (line.contains(regex))
                {
                    CloseUtil.closeStream(br);
                    return true;
                }
            }
        }

        return false;
    }

}
