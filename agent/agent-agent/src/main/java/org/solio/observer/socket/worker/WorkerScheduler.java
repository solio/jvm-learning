package org.solio.observer.socket.worker;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.LinkedList;

public final class WorkerScheduler
{
	private static int poolsize = 10;
	
	private static LinkedList<Worker> workersPool = new LinkedList<Worker>();
	
	static {
		for (int i = 0; i < poolsize; i++) {
			workersPool.add(new CmdWorker());
		}
	}

	public static void schedule(SelectionKey key) throws IOException
	{
		Worker worker = workersPool.poll();		

		if (key.isAcceptable()) {
			worker.handleAccept(key);
		}
		if (key.isReadable()) {
			worker.handleRead(key);
		}
		if (key.isWritable()) {
			worker.handleWrite(key);
		}
	}
}
