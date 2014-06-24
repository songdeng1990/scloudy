package com.redis.monitor;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redis.monitor.redis.BasicRedisCacheServer;

public class RedisConfigInit
{

    public static final Logger logger = LoggerFactory.getLogger(RedisConfigInit.class);
    
    private String confFile;

    @PostConstruct
    public void init()
    {
        resolveConfig();
    }

    /**
     * 解析RedisServerConfig配置
     */
    private void resolveConfig()
    {

        ObjectMapper mapper = new ObjectMapper();

        List<RedisServer> listRS = null;
        
        try
        {
            listRS = mapper.readValue(new FileInputStream(confFile), new TypeReference<List<RedisServer>>(){});            
            RedisJedisPool.initRedisJedisPool(listRS);
            logger.info("redis manager build success!");
        }
        catch (Exception e)
        {
            logger.error("redis manager build error!");
            e.printStackTrace();
        }
    }

    /**
     * 服务器停止,或者定时将新加的redis配置写入文件
     */
    public static void rewriteConfigXml()
    {
        Collection<Redis> rList = RedisJedisPool.getAllRedis();
        if (rList != null && rList.size() > 0)
        {
            // TODO 如果有新的服务加入
            if (rList.size() > RedisJedisPool.LOAD_SIZE || rList.size() < RedisJedisPool.LOAD_SIZE)
            {
                logger.info("has new server,begin buid Redis-Server-Config.xml");
            }
        }
    }

    public static void main(String[] args)
    {
        new RedisConfigInit().resolveConfig();
        BasicRedisCacheServer brc = RedisJedisPool.getRedisCacheServer("0101");
        System.out.println(brc.configGetAll());
        System.out.println(2 % 2);
    }

    public String getConfFile()
    {
        return confFile;
    }

    public void setConfFile(String confFile)
    {
        this.confFile = confFile;
    }

}
