import java.util.LinkedList;

public class MyService implements Service {
    private final int numOfThreads;
    private final Thread[] threads;
    private final LinkedList<Runnable> taskQueue;
    private boolean shutDown = false;
    private boolean inProcess = true;

    public MyService(int num) {
        this.numOfThreads = num;
        this.threads = new Thread[num];
        this.taskQueue = new LinkedList<>();

        for(int i = 0; i < numOfThreads; i++) {
            this.threads[i] = new Thread(() -> {
                while (!shutDown) {
                    Runnable program;
                    boolean lastTask = false;
                    synchronized (taskQueue) {
                        while (taskQueue.isEmpty() && !shutDown) {
                            try {
                                taskQueue.wait();
                            } catch (InterruptedException e) {
                                // Catch for errors while queue is waiting for tasks
                            }
                        }
                        program = taskQueue.poll();
                        if(taskQueue.size() == 0) {
                            lastTask = true;
                        }
                    }
                    if(program != null) {
                        program.run();
                    }
                    if(lastTask) {
                        this.inProcess = false;
                    }
                }
            });
            this.threads[i].start();
        }
    }

    public void execute(Runnable r) throws InterruptedException {
        if(shutDown) {
            throw new InterruptedException("All the threads are shut down");
        }
        synchronized (taskQueue) {
            taskQueue.add(r);
            taskQueue.notify();
        }
    }

    public void awaitTermination() throws InterruptedException {
        while (this.inProcess) {
            Thread.sleep(1000);
        }
    }

    public void shutdown() {
        this.shutDown = true;
    }

    public void shutdownNow() {
        this.shutDown = true;
        for(int i = 0; i < numOfThreads; i++) {
            try {
                threads[i].interrupt();
                threads[i] = null;
            }
            catch (Exception e) {
                // DO NOTHING (Catch if the thread is null)
            }
        }
    }

    public boolean isShutdown() {
        for(int i = 0; i < this.numOfThreads; i++) {
            try {
                if (threads[i].isInterrupted()) {
                    return false;
                }
            } catch (Exception e) {
                // DO NOTHING (Catch if the thread is null)
            }
        }
        return true;
    }

    public static void main( String[] args ) throws InterruptedException
    {
        Service s = new MyService( 5 ); // create a pool of 5 threads
        for( int j = 0; j < 30; j++ ) // generate 30 Runnables
        {
            s.execute( new Runnable()
            {
                public void run()
                {
                    long id = Thread.currentThread().getId();
                    System.out.println( "Thread: " + id + " task: " + this );
                    for( int i = 0; i < 100000000; i++ ) // take some time
                    {
                        double d = Math.sin((double) i);
                    }
                    System.out.println( "Thread: " + id + " end " );
                }
            } );
        }
        s.awaitTermination(); // only if implemented
        System.out.println( "job done" );
        s.shutdown();
        System.out.println( "isShutdown() = " + s.isShutdown() );

        s.shutdownNow();
        System.out.println( "Terminated" );
    }

}


