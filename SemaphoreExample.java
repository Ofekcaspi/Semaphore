// Ofek caspi - 208895367
// Omri argaman - 314772351	

package pkg2;

import java.util.concurrent.*;

public class SemaphoreExample {
    static class SharedResource {
        private final Semaphore semaphore;

        public SharedResource(int permits) {
         semaphore=new Semaphore(permits,true);
          }

        public void accessResource() {
            try {
//יש להשלים את החסר כאן // Acquire a permit
            	semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " is accessing the resource.");
                // Simulating some work
                System.out.println(Thread.currentThread().getName() +"Working");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            	//יש להשלים את החסר כאן // Release the permit
            	semaphore.release();
            }
        }
    }

    static class Worker extends Thread {
        private final SharedResource sharedResource;

        public Worker(String name, SharedResource sharedResource) {
            super(name);
            this.sharedResource = sharedResource;
        }

        @Override
        public void run() {
     while(true) {
    	 sharedResource.accessResource();
     }
        	//יש להשלים את החסר כאן
        }
    }

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(2); //  permits available

        // Create multiple threads trying to access the resource
        Thread thread1 = new Worker("Thread 1", sharedResource);
        Thread thread2 = new Worker("Thread 2", sharedResource);
        Thread thread3 = new Worker("Thread 3", sharedResource);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
