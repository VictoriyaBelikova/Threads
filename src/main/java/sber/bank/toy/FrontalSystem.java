package sber.bank.toy;

import java.util.Deque;
import java.util.ArrayDeque;

public class FrontalSystem {

    private final Deque<Request> requests = new ArrayDeque<Request>();

    public synchronized void sendRequest(Request request){
        while (requests.size() >= 2){
            try{
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted " + e);
            }
        }

        requests.add(request);
        notifyAll();
    }

    public synchronized Request receive(){
        while (requests.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted");
                e.printStackTrace();
            }
        }

        Request request = requests.poll();
        notifyAll();
        return request;
    }
}
