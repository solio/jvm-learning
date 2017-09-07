package org.solio.observer.socket.worker;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import org.solio.agent.observer.ActionFactory;
import org.solio.observer.action.Action;

public class CmdWorker implements Worker
{
	private int bufferSize = 1024;
	
	public void handleAccept(SelectionKey key) throws IOException {

		ServerSocketChannel channel = (ServerSocketChannel) key.channel();
		
		SocketChannel sc = channel.accept();
		
		sc.configureBlocking(false);
		
		sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
	}

	@Override
	public void handleRead(SelectionKey key) throws IOException
	{
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
		
		byteBuffer.clear();
		long readed = channel.read(byteBuffer);
		if (-1 == readed) {
			
		} else {
			byteBuffer.flip();
			
			Action action = ActionFactory.getAction(byteBuffer.array());
			action.doAction();
		}
	}

	@Override
	public void handleWrite(SelectionKey key) throws IOException
	{
		// TODO Auto-generated method stub
		
	}
}
