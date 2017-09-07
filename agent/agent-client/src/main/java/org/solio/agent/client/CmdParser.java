package org.solio.agent.client;

import java.util.HashMap;
import java.util.Map;

import org.solio.agent.client.entity.Command;
import org.solio.agent.client.entity.ReplaceClassCommand;
import org.solio.agent.util.CommonUtils;

public abstract class CmdParser
{
	private static Map<String, Command> cmdMaps = new HashMap<String, Command>();
	
	static
	{
		new ReplaceClassCommand().addToMaps(cmdMaps);
	}
	
	public static Command parse(String[] args)
	{
		if (CommonUtils.isEmpty(args))
		{
			throw new IllegalStateException("arguments is empty");
		}
		
		Command cmd = cmdMaps.get(args[0]);
		if (cmd == null)
		{
			usage();
		}
		return cmd;
	}
	
	public static void usage()
	{
		System.out.println("usage:");
	}
}
