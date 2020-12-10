package sber.bank.toy;

public class Client extends Thread {

    private final FrontalSystem frontend;
    private Request request;

    public Client(String clientName, FrontalSystem frontend) {
        setName(clientName);
        this.frontend = frontend;
    }

    public void outputState() {
        String resultString = currentThread().getName() +
                ": " +
                this.request.toString() +
                " отправлена в банк";
        System.out.println(resultString);
    }

    public void createRequest(long amount, RequestType type) {
        this.request = new Request(getName(), amount, type);
    }

    @Override
    public void run() {
        outputState();
        frontend.sendRequest(this.request);
    }
}
