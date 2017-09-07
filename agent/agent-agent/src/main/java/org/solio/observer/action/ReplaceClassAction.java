package org.solio.observer.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.ArrayList;
import java.util.List;

import org.solio.agent.client.Config;
import org.solio.agent.client.entity.Command;
import org.solio.agent.client.entity.ReplaceClassCommand;
import org.solio.agent.transform.SimpleTransForm;

public class ReplaceClassAction implements Action
{
	private ReplaceClassCommand cmd;

	@Override
	public void recieve(Command cmd)
	{
		
	}

	@Override
	public void doAction()
	{
		Instrumentation inst = Config.getInstance().getInst();
        String reloadClassFile = cmd.getFileFullPath();
        String className = cmd.getClassFullName();
        
        File file = new File(reloadClassFile);
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        if (code == null)
        {
            System.err.println(reloadClassFile + " code is null ");
            return;
        }
        
        //Class<?> clazz = null;
        //AgentClassLoader loader = new AgentClassLoader(AgentMain.class.getClassLoader());
        //clazz = loader.loadClass(className, code);
        List<Class<?>> classes = getTargetClasses(inst, className);
        
        inst.addTransformer(new SimpleTransForm(className, code), true);
        try
        {
            for (Class<?> clazz : classes)
            {
                inst.retransformClasses(clazz);
            }
        }
        catch (UnmodifiableClassException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

    
    private static List<Class<?>> getTargetClasses(Instrumentation inst, String className)
    {        
        Class[] classes = inst.getAllLoadedClasses();
        List<Class<?>> result = new ArrayList<Class<?>>();
        
        for (Class clazz : classes)
        {
            //System.out.println(clazz.getCanonicalName());
            if (className.equals(clazz.getCanonicalName()))
            {
                System.out.println("class resource: " + clazz.getResource("/").toString());
                result.add(clazz);
            }
        }
        
        return result;
    }
}
