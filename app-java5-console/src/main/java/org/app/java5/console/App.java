package org.app.java5.console;

import org.app.java5.domain.Pokemon;
import org.app.java5.services.ServicesDB;


public class App 
{
    public static void main( String[] args )
    {
    	ServicesDB serviceDB = new ServicesDB();
    	Pokemon nuevo = new Pokemon(33,"Micaela");
        try {
            serviceDB.findAllPoke();
            
            //serviceDB.findById(96);
            //serviceDB.findByName("poke1");
            serviceDB.savePokemon(nuevo);

            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
