package org.solio.agent.transform;

public class AgentClassLoader extends ClassLoader
{
    public AgentClassLoader(ClassLoader classLoader)
    {
        super(classLoader);
    }
    
    public Class<?> loadClass(String className, byte[] code) 
    {
        System.out.println("start to load " + className);
        if (null == className)
        {
            return null;
        }
        try
        {
            return this.loadClass(className);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(className + " is not loaded");
        }
        String packageName = "";
        int i = className.lastIndexOf('.');
        if (i != -1)
        {
            packageName = className.substring(0, i);
        }
        if (this.getPackage(packageName) == null)
        {
            definePackage(packageName);
        }
        return defineClass(className, code, 0, code.length);
    }

    private Package definePackage(String packageName)
    {
        return super.definePackage(packageName, "", "", "", "", "", "", null);
    }
}
