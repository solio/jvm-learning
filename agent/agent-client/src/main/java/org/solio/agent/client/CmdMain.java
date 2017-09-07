package org.solio.agent.client;

import java.io.IOException;

import org.solio.agent.client.attach.ProcessAttacher;
import org.solio.agent.client.socket.ObClientCnxn;

public class CmdMain
{
	private ProcessAttacher attacher = new ProcessAttacher();
	
	private ObClientCnxn cnxn;
	
	public void main(String[] args)
	{
		String pid = args[0];
		String agentArgs = "";

        System.out.println("agent args : " + agentArgs);
        
		attacher.attach(pid, agentArgs);
		
		try
		{
			cnxn = new ObClientCnxn(Config.getInstance().getHost(), Config.getInstance().getPort());
			
			Thread.sleep(20000);
			cnxn.start();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
