/*
 *
 * 文件名: TextFileIterator.java
 * 描述: 一句话描述
 * 创建人: curatorjin
 * 时间: 2019/5/12  15:06
 *
 */
package io.github.curatorjin.utils.iterators;

import io.github.curatorjin.utils.CloseUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;


/**
 * 文本迭代器
 *
 * @author : curatorjin
 * @version : 1,0
 */
public class TextLineIterator implements Iterator
{

    /**
     * 内部实际迭代器
     */
    private Iterator<String> iterator;

    public TextLineIterator(File file)
    {
        List<String> list = new LinkedList<>();
        BufferedReader bufferedReader = null;
        try
        {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while (null != (line = bufferedReader.readLine()))
            {
                list.add(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        iterator = list.iterator();
        CloseUtil.closeStream(bufferedReader);
    }

    @Override
    public void remove()
    {
        iterator.remove();
    }

    @Override
    public void forEachRemaining(Consumer action)
    {
        iterator.forEachRemaining(action);
    }

    @Override
    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    @Override
    public String next()
    {
        return iterator.next();
    }

}
