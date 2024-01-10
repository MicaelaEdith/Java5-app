package org.app.java5.console;

import org.app.java5.domain.Pokemon;
import org.app.java5.services.PokeService;


public class App 
{
    public static void main( String[] args )
    {
    	
    	
    	PokeService serviceDB = new PokeService();
    	
    	Pokemon nuevo = new Pokemon(9,"nombre");
        try {
            serviceDB.findAll();
            
            serviceDB.findById(33);
            //serviceDB.findByName("poke1");
            //serviceDB.savePokemon(nuevo);

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
      
    }
    
}
