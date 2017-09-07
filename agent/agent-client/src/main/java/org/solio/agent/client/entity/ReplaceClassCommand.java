package org.solio.agent.client.entity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.solio.agent.util.AgentUtils;

public class ReplaceClassCommand implements Command
{
	private String fileFullPath = "";
	
	private String classFullName = ""; 
	
	@Override
	public String getStatus()
	{
		return "finish";
	}

	@Override
	public String getType()
	{
		return "ReplaceClass";
	}

	@Override
	public void addToMaps(Map<String, Command> cmdMaps)
	{
		cmdMaps.put("rc", this);
	}

	@Override
	public void inputArguments(String[] args)
	{
		fileFullPath = args[1];        
        File file = new File(fileFullPath);
        byte[] code = null;
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                ByteArrayOutputStream bos = new ByteArrayOutputStream((int)file.length());)
        {
            int readed = 0;
            byte[] buffer = new byte[1024];
            while((readed = in.read(buffer)) > -1)
            {
                bos.write(buffer, 0 ,readed);
            }
            code = bos.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        if (code == null)
        {
            System.err.println(fileFullPath + " code is null ");
            return;
        }
        
        classFullName = AgentUtils.getClassName(code).replace('/', '.');
	}

	public String getFileFullPath()
	{
		return fileFullPath;
	}

	public void setFileFullPath(String fileFullPath)
	{
		this.fileFullPath = fileFullPath;
	}

	public String getClassFullName()
	{
		return classFullName;
	}

	public void setClassFullName(String classFullName)
	{
		this.classFullName = classFullName;
	}
}
