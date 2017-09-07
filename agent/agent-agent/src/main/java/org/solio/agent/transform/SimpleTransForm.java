package org.solio.agent.transform;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class SimpleTransForm implements ClassFileTransformer
{
    private String clazz;
    
    private byte[] code;
    
    public SimpleTransForm(String clazz, byte[] code)
    {
        this.clazz = clazz;
        this.code = code;
    }

    @Override
    public byte[] transform(ClassLoader classLoader, String cname, Class<?> classBeingRedefined, 
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException
    {
        String className = cname.replace('/', '.');        
        
        if (clazz.equals(className))
        {
            System.out.println(clazz + " equals " + className + " in " + classLoader.getResource("").toString());
            return code;
        }
        return null;
    }

}
