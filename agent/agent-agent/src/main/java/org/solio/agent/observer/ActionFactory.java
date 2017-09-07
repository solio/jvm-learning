package org.solio.agent.observer;

import java.nio.charset.StandardCharsets;

import org.solio.agent.client.entity.Command;
import org.solio.agent.client.entity.CommandRes;
import org.solio.observer.action.Action;

import com.google.gson.Gson;

public final class ActionFactory
{
	private static final String CMD_NAMESPACE = "org.solio.observer.entity.";

	private static final String ACTION_NAMESPACE = "org.solio.observer.action.";
	
	public static Action getAction(byte[] data)
	{
		if (null == data || data.length > 0)
		{
			throw new IllegalArgumentException("no data input");
		}
		
		String cmdStr = new String(data, StandardCharsets.UTF_8);
		Gson gson = new Gson();
		CommandRes res = gson.fromJson(cmdStr, CommandRes.class);
		
		try
		{
			Class cmdClass = Class.forName(CMD_NAMESPACE + res.getType());
			
			Command cmd = (Command) gson.fromJson(res.getCmdString(), cmdClass.getDeclaringClass());

			Class actionClass = Class.forName(ACTION_NAMESPACE + res.getType() + "Action");
			
			Action action = (Action) gson.fromJson(res.getCmdString(), cmdClass.getDeclaringClass());
			action.recieve(cmd);
			
			return action;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
