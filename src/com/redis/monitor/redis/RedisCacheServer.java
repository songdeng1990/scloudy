package com.redis.monitor.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.Slowlog;

import com.redis.monitor.FeJedisMonitor;
import com.redis.monitor.json.FastJson;

public class RedisCacheServer implements BasicRedisCacheServer
{

    private JedisPool jedisPool;

    public static final Integer DEFAULT_EXPIRE_TIME = 60 * 60 * 24;

    public RedisCacheServer(final String host, final int port, int maxActive, int maxIdle, int maxWait, boolean testOnBorrow)
    {
        JedisPoolConfig jpConfig = new JedisPoolConfig();
        if (maxActive == 0)
            maxActive = 10;
        jpConfig.setMaxActive(maxActive);
        if (maxIdle == 0)
            maxIdle = 10;
        jpConfig.setMaxIdle(maxIdle);
        if (maxWait == 0)
            maxWait = 10;
        jpConfig.setMaxWait(maxWait);
        jpConfig.setTestOnBorrow(testOnBorrow);
        jpConfig.setTestWhileIdle(true);
        jedisPool = new JedisPool(jpConfig, host, port);
    }

    public List<String> get(String key, String mapKey)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            List<String> list = jedis.hmget(key, mapKey);
            return list;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }

    }

    public List<byte[]> get(byte[] key, byte[] mapKey)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            List<byte[]> list = jedis.hmget(key, mapKey);
            return list;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }

    }

    public List<String> get(String key, String[] mapKeys)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            List<String> list = jedis.hmget(key, mapKeys);
            return list;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public String get(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            return str;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public <T> T get(String key, Class<T> clazz)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return FastJson.fromJson(jedis.get(key), clazz);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public Set<String> getMapKeys(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            Set<String> set = jedis.hkeys(key);
            return set;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public Set<String> getKeysByPattern(String patternKey)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            Set<String> set = jedis.keys(patternKey);
            return set;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public Map<String, String> getMap(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            Map<String, String> resMap = jedis.hgetAll(key);
            return resMap;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }

    }

    public List<String> getMapVal(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            List<String> list = jedis.hvals(key);
            return list;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public String getMapVal(String cacheKey, String mapKey)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            String mapVal = jedis.hget(cacheKey, mapKey);
            return mapVal;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public boolean isExists(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            boolean flag = jedis.exists(key);
            return flag;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public boolean isExists(byte[] byteKey)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            boolean flag = jedis.exists(byteKey);
            return flag;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    private byte[] getKey(String key)
    {
        return key.getBytes();
    }

    public Set<String> getSet(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            Set<String> str = jedis.smembers(key);
            return str;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public List<String> getList(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.lrange(key, 0, -1);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public String brpopDataQueue(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.brpop(0, key).get(1);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public String blpopDataQueue(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.blpop(0, key).get(1);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public long llenQueueSize(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.llen(key);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public String getRedisInfo()
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            // TODO 获取redis服务器信息
            Client client = jedis.getClient();
            client.info();
            return client.getBulkReply();
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public boolean isConnect()
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return true;
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public String ping()
    {

        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            // TODO 获取redis服务器信息
            Client client = jedis.getClient();
            client.ping();
            return client.getBulkReply();
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public String flushAll()
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            // TODO 获取redis服务器信息
            Client client = jedis.getClient();
            client.flushAll();
            return client.getBulkReply();
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public String flushDb()
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            // TODO 获取redis服务器信息
            Client client = jedis.getClient();
            client.flushDB();
            return client.getBulkReply();
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void monitor()
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            FeJedisMonitor jm = new FeJedisMonitor(jedis);
            Client client = jedis.getClient();
            client.monitor();
            jm.proceed(client);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public List<String> configGet(String pattern)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            // TODO 获取redis服务器信息
            Client client = jedis.getClient();
            client.configGet(pattern);
            return client.getMultiBulkReply();
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public List<String> configGetAll()
    {
        return configGet("*");
    }

    public String configSet(String key, String value)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            // TODO 配置redis服务信息
            Client client = jedis.getClient();
            client.configSet(key, value);
            return client.getBulkReply();
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public Long dbSize()
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            // TODO 配置redis服务信息
            Client client = jedis.getClient();
            client.dbSize();
            return client.getIntegerReply();
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void save(String key, String value)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void save(String key, String value, int expireTime)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            if (expireTime != 0)
            {
                jedis.expire(key, expireTime);
            }
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }

    }

    public void save(String key, Object obj)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.set(key, object2String(obj));
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void save(String key, Object obj, int expireTime)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.set(key, object2String(obj));
            if (expireTime != 0)
            {
                jedis.expire(key, expireTime);
            }
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void replace(String key, Map<String, String> map, int expireTime)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.del(key);
            save(key, map, expireTime);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void save(String key, Map<String, String> map)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            if (map.size() == 0)
            {
                map.put("", "");
            }
            jedis.hmset(key, map);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void save(String key, Map<String, String> map, int expireTime)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            if (map.size() == 0)
            {
                map.put("", "");
            }
            jedis.hmset(key, map);
            if (expireTime != 0)
            {
                jedis.expire(key, expireTime);
            }
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }

    }

    public void saveMapVal(String cacheKey, String mapKey, String mapVal)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.hset(cacheKey, mapKey, mapVal);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void saveMapVal(byte[] cacheKey, byte[] mapKey, byte[] mapVal)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.hset(cacheKey, mapKey, mapVal);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void del(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.del(key);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }

    }

    public void del(String... keys)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.del(keys);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void del(String key, String mapKey)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.hdel(key, mapKey);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    private String object2String(Object obj)
    {
        return FastJson.toJson(obj);
    }

    public void saveSet(String key, String str, int expireTime)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.sadd(key, str);
            if (expireTime != 0)
            {
                jedis.expire(key, expireTime);
            }
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void delFromSet(String key, String setValue)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.srem(key, setValue);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void saveList(String key, List<String> list, int expireTime)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            for (String str : list)
            {
                jedis.lpush(key, str);
            }
            if (expireTime != 0)
            {
                jedis.expire(key, expireTime);
            }
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void saveList(String key, List<String> list)
    {
        saveList(key, list, 0);
    }

    public void lpushQueue(String key, String data)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.lpush(key, data);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public void rpushQueue(String key, String data)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.rpush(key, data);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public List<Slowlog> slowlogs()
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.slowlogGet();
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public long ttl(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.ttl(key);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public String set(String key, String value)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        }
        catch (JedisConnectionException e)
        {
            jedisPool.returnBrokenResource(jedis);
            jedis = null;
            throw new RuntimeException(e);
        }
        finally
        {
            if (jedis != null)
                jedisPool.returnResource(jedis);
        }
    }

    public JedisPool getJedisPool()
    {
        return jedisPool;
    }

}
