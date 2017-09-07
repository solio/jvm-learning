package org.solio.agent;

import java.lang.instrument.Instrumentation;

import org.solio.agent.client.Config;
import org.solio.agent.observer.CmdObserver;

public class AgentMain
{
	private CmdObserver obServer = new CmdObserver();
	
    public static void premain(String args, Instrumentation inst) {
        main(args, inst);
    }

    public static void agentmain(String args, Instrumentation inst) {
        main(args, inst);
    }
    
    public static void main(String argsstr, Instrumentation inst)
    {
        System.out.println("start agent " + argsstr);
        
        Config.getInstance().setInst(inst);
        
        AgentMain main = new AgentMain();
        main.start();
    }
    
    public void start()
    {
    	obServer.start();
    }
}