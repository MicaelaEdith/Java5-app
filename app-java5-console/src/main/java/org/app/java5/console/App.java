package org.app.java5.console;

import org.app.java5.services.ServicesDB;


public class App 
{
    public static void main( String[] args )
    {
    	ServicesDB serviceDB = new ServicesDB();
     
        try {
            serviceDB.pruebaConectividad();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
