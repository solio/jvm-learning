package org.solio.observer.socket.worker;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface Worker
{
	void handleAccept(SelectionKey key) throws IOException;

	void handleRead(SelectionKey key) throws IOException;

	void handleWrite(SelectionKey key) throws IOException;
}
