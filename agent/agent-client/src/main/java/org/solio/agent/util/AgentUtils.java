package org.solio.agent.util;

import org.objectweb.asm.ClassReader;

public abstract class AgentUtils
{
    public static String getClassName(byte[] code)
    {
        ClassReader classReader = new ClassReader(code);
        
        System.out.println("retransform class " + classReader.getClassName());
        
        return classReader.getClassName();
    }
}
