package coe628.lab9;

import java.util.logging.Level;
import java.util.logging.Logger;

public class fork {
    
     Semaphore sem = new Semaphore(1);
     int id; 
    
     public fork(int id){
         this.id = id;
     }
     
     int getId(){
         return (id+1);
     }
    
        void get_fork(){
        try {
            sem.down(); 
        } catch (InterruptedException ex) {
            ex.printStackTrace(System.out);
        }
         }

        void put_fork(){
            sem.up();
        }
        
        boolean isFree(){
            return sem.getvalue()> 0;
        }
    
    
}