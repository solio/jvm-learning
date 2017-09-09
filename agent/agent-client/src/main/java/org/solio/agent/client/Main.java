package org.solio.agent.client;

import java.net.URL;

public class Main
{

    public static void main(String[] args)
    {
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        if (null == url)
        {
            System.out.println("getClassLoader().getResource(\"\") is " + url);
        }
        System.out.println("start client ,agent path ");
        CmdMain main = new CmdMain();
        main.main(args);
    }
}