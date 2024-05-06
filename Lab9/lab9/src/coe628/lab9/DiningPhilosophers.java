package coe628.lab9;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiningPhilosophers extends Thread{
    
    int n;
    fork leftfork, rightfork; 
    static int finished = 0;
    
    public DiningPhilosophers(int num, fork left, fork right){
        this.n = num + 1;
        this.leftfork = left;
        this.rightfork = right;       
    }
    
    @Override
    public void run(){
        
        while(true){
            
          // think
           think();
            leftfork.get_fork(); 
            System.out.println(" Fork "+ leftfork.getId() + " taken by Philosopher " + n );    
            rightfork.get_fork();
            System.out.println(" Fork "+ rightfork.getId() + " taken by Philosopher " + n );    
            eat();
            System.out.println(" Philosopher " + n + " completed his dinner" );    
              System.out.println(" Philosopher " + n + " released fork " + leftfork.getId());  
            leftfork.put_fork();
             System.out.println(" Philosopher " + n + " released fork " +  rightfork.getId());  
            rightfork.put_fork();
            finished++;
            System.out.println("Till now num of philosophers completed dinner are " + finished ); 
            if(finished == 5){
                System.exit(0);
            }
             think();
        }
    }
    
    
    public void think(){
        try {
            Thread.sleep((long) (Math.random() * 1000));            
        } catch (InterruptedException ex) {
           ex.printStackTrace(System.out);
        }
    }
    
    public void eat(){
        try {
           Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException ex) {
           ex.printStackTrace(System.out);
        }
    }
}