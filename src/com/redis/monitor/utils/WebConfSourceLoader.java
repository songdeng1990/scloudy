package com.redis.monitor.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author dengs
 * @date 2014年6月20日 company:步步高教育电子产品有限公司
 */
public class WebConfSourceLoader extends ClassLoader
{
    /**
     * @param name
     * @return
     */
    @Override
    public URL getResource(String name)
    {
        String webroot = System.getProperty("redisMonitor");
        URL url = null;
        
        try
        {
             url = new URL(webroot + "conf" +  File.separator + name);
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return url;
    }
}
