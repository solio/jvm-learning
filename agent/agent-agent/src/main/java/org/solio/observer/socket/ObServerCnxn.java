package org.solio.observer.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ObServerCnxn
{
	private static final Logger LOG = LogManager.getLogger(ObServerCnxn.class);
	
	private ServerSocketChannel ssc;
	
	private AcceptThread acceptThread;
	
	public ObServerCnxn(int port) throws IOException
	{
		SocketAddress addr = new InetSocketAddress(port);
        ssc = ServerSocketChannel.open();
        ssc.socket().setReuseAddress(true);
        LOG.info("binding to port " + port);
        ssc.socket().bind(addr);
        ssc.configureBlocking(false);
        acceptThread = new AcceptThread(ssc);
	}
	
	public void start()
	{
		LOG.debug("ObCnxn start");
        // ensure thread is started once and only once
        if (acceptThread.getState() == Thread.State.NEW) {
            acceptThread.start();
        }
	}
}
