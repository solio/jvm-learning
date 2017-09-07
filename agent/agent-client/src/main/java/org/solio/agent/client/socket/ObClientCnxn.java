package org.solio.agent.client.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import org.solio.agent.client.entity.Command;

public class ObClientCnxn
{
	private SocketChannel sc;
	
	private SenderThread senderThread;
	
	public ObClientCnxn(String ip, int port) throws IOException
	{
		sc = SocketChannel.open(new InetSocketAddress(ip, port));
		
		sc.configureBlocking(false);
		
		senderThread = new SenderThread(sc);
	}
	
	public void start()
	{
		if (senderThread.getState() == Thread.State.NEW)
		{
			senderThread.start();
		}
	}
	
	public void processCmd(Command cmd)
	{
		senderThread.addCommand(cmd);
	}
	
	public void commandInput()
	{
		senderThread.CommandInput();
	}
}
