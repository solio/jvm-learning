package org.solio.agent.client.socket;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.solio.agent.client.entity.Command;

public class SenderThread extends Thread
{
	private static final Logger LOGGER = LogManager.getLogger(SenderThread.class);
	
	private SocketChannel sc;
	
	private Selector selector;
	
	private Queue<Command> queue = new ConcurrentLinkedQueue<Command>();
	
	public SenderThread(SocketChannel sc) throws IOException
	{
		this.sc = sc;
		this.selector = Selector.open();
	}
	
	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				selector.select();
			}
			catch (IOException e)
			{
				LOGGER.error("error happen", e);
			}
		}
	}
	
	public void addCommand(Command cmd)
	{
		queue.add(cmd);
	}
	
	public void CommandInput()
	{
		selector.wakeup();
	}
}
