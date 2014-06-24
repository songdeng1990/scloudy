package com.redis.monitor;

public class RedisServer
{
    private String uuid;
    
    private String host;
    
    private int port;
    
    private String service;
    
    private String slaveof;

    public RedisServer()
    {
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public String getSlaveof()
    {
        return slaveof;
    }

    public void setSlaveof(String slaveof)
    {
        this.slaveof = slaveof;
    }

}
