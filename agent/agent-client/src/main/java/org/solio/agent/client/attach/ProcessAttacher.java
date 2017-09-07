package org.solio.agent.client.attach;

import java.io.IOException;
import java.net.URL;

import org.solio.agent.client.Main;

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
    
    private static String getAgentPath()
    {
        if (null == ProcessAttacher.class.getClassLoader())
        {
            System.out.println("PathUtils.class.getClassLoader() is null");
        }
        
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        if (null == url)
        {
            System.out.println("getClassLoader().getResource(\"\") is null");
        }
        String resource = url.toString();
        
        resource = resource.substring(resource.indexOf("file:") + "file:".length());
        System.out.println("agent resource is " + resource);
        return resource + "org-solio-agent-1.0.0-SNAPSHOT.jar";
    }
}
