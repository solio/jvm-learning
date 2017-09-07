package org.solio.observer.socket;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solio.observer.socket.worker.WorkerScheduler;

/**
 * 1 socketchannel => 1 selector 模式
 * @author solio-thunder
 *
 * since org-solio-agent 2017年7月25日
 */
public class AcceptThread extends Thread
{
	private static final Logger LOG = LogManager.getLogger(AcceptThread.class);
	
	private ServerSocketChannel ssc;
	
	private Selector selector;
	
	public AcceptThread(ServerSocketChannel ssc) throws IOException
	{
		this.ssc = ssc;
		
		this.selector = Selector.open();
		
		ssc.register(selector, SelectionKey.OP_ACCEPT);
	}
	
	@Override
	public void run() {
		while (!ssc.socket().isClosed()) {
			try
			{
				selector.select();
				
				Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
				while (keys.hasNext()) {
					SelectionKey key = keys.next();
					keys.remove();
					
					if (!key.isValid()) {
						LOG.info("invalid key ignore" + key.toString());
						continue;
					}
					
					WorkerScheduler.schedule(key);
				}
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
