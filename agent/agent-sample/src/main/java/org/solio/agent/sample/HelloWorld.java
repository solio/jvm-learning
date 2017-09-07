package org.solio.agent.sample;

public class HelloWorld
{

    public static void main(String[] args)
    {
        while (true)
        {
            Sayhello hello = new Sayhello();
            
            System.out.println(hello.say());
            
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
