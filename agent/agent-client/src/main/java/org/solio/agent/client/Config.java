package org.solio.agent.client;

import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.util.Properties;

public final class Config
{
	private static final Config INSTANCE = new Config();
	
	public static final String AGENT_HOST = "agent.host";
	public static final String AGENT_PORT = "agent.port";
	
	private Properties properties = new Properties();
	
	private Instrumentation inst;
	
	private Config()
	{
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

	private void initProperties()
	{
		InputStream in = Config.class.getResourceAsStream("/agent.properties");
		if (null == in)
		{
			throw new IllegalStateException("agent.properties not found");
		}
		
		try
		{
			properties.load(in);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
