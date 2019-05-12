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
                file.delete();
            }
            else
            {
                file.delete();
            }
        }
    }

}
