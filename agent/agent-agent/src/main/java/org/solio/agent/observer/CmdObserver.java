package org.solio.agent.observer;

import java.io.IOException;

import org.solio.agent.client.Config;
import org.solio.observer.socket.ObServerCnxn;

public class CmdObserver
{
	public void start()
	{
		try
		{
			ObServerCnxn cnxn = new ObServerCnxn(Config.getInstance().getPort());
			
			cnxn.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
