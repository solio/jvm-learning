package org.solio.observer.action;

import org.solio.agent.client.entity.Command;

public interface Action
{
	void recieve(Command cmd);
	
	void doAction();
}
