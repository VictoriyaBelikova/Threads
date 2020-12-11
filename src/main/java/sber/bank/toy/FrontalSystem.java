package sber.bank.toy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FrontalSystem {

    private final int REQUESTS_COUNT = 2;

    private final BlockingQueue<Request> requests = new ArrayBlockingQueue<Request>(REQUESTS_COUNT);

    public void sendRequest(Request request){
        try{
            requests.put(request);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted ");
            e.printStackTrace();
        }
    }

    public Request receive(){
        Request request = null;
        try {
            request = requests.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted ");
            e.printStackTrace();
        }
        return request;
    }
}
