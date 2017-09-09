package org.solio.agent.util;

import java.io.Closeable;
import java.io.IOException;

public final class CommonUtils
{
	private CommonUtils()
	{
		
	}
	
	public static boolean isEmpty(String str)
	{
		return str == null || str.length() == 0;
	}
	
	public static boolean isNotEmpty(String str)
	{
		return !isEmpty(str);
	}
	
	public static boolean isEmpty(Object[] array)
	{
		return array == null || array.length == 0;
	}
	
	public static void closeQuitely(Closeable closeable)
	{
	    try
        {
            closeable.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
	}
}
