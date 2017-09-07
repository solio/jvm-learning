package org.solio.agent.client.entity;

import java.util.Map;

public interface Command
{
	void addToMaps(Map<String, Command> cmdMaps);
	
	void inputArguments(String[] args);
	
	String getType();
	
	String getStatus();
}
