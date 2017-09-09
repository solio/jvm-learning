package org.solio.agent.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.util.Properties;

import org.solio.agent.util.CommonUtils;

public final class Config
{
	private static final Config INSTANCE = new Config();
	
	public static final String AGENT_HOST = "agent.host";
	public static final String AGENT_PORT = "agent.port";
	
	private Properties properties = new Properties();
	
	private String currentPath;
    
    private String libPath;
	
	private Instrumentation inst;
	
	private Config()
	{
	    initVariables();
		initProperties();
	}
	
	public static Config getInstance()
	{
		return INSTANCE;
	}
	
	public String get(String key)
	{
		return properties.getProperty(key);
	}
	
	public String getHost()
	{
		return properties.getProperty(AGENT_HOST);
	}
	
	public int getPort()
	{
		return Integer.parseInt(properties.getProperty(AGENT_PORT));
	}
	
	public Instrumentation getInst()
	{
		return inst;
	}

	public void setInst(Instrumentation inst)
	{
		this.inst = inst;
	}
	
	public String getCurrentPath()
    {
        return currentPath;
    }

    public String getLibPath()
    {
        return libPath;
    }

    private void initVariables()
	{
	    try
        {
            currentPath = (new File(".")).getCanonicalPath() + "/";
            libPath = currentPath + "lib/";
            
            System.out.println("currentPath is :" + currentPath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
	}

	private void initProperties()
	{
	    InputStream in = null;
		try
		{
		    in = new FileInputStream(new File(libPath + "agent.properties"));
			properties.load(in);
		}
		catch (IOException e)
		{
		    System.out.println("agent.properties not found");
			e.printStackTrace();
		}
		finally
		{
		    CommonUtils.closeQuitely(in);
		}
	}
}
