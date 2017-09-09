package org.solio.agent.client.attach;

import java.io.IOException;

import org.solio.agent.client.Config;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

public class ProcessAttacher
{
	private VirtualMachine vm;
	
	public void attach(String pid, String agentArgs)
	{
        try
        {
            vm = VirtualMachine.attach(pid);
            
            vm.loadAgent(getAgentPath(), agentArgs);
        }
        catch (AttachNotSupportedException | IOException | AgentLoadException | AgentInitializationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
    
    private static String getAgentPath() throws IOException
    {
        if (null == ProcessAttacher.class.getClassLoader())
        {
            System.out.println("PathUtils.class.getClassLoader() is null");
        }        
        
        return Config.getInstance().getLibPath() + "org-solio-agent-1.0.0-SNAPSHOT.jar";
    }
}
