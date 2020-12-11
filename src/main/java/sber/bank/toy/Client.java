package sber.bank.toy;

public class Client extends Thread {

    private final FrontalSystem frontend;
    private Request request;

    private String clientName;

    public Client(String clientName, FrontalSystem frontend) {
        setClientName(clientName);
        setName(clientName);
        this.frontend = frontend;
        setDaemon(true);
    }

    public void setClientName(String name) {
        this.clientName = name;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void outputState() {
        String resultString = getClientName() +
                ": " +
                this.request.toString() +
                " отправлена в банк";
        System.out.println(resultString);
    }

    public void createRequest(long amount, RequestType type) {
        this.request = new Request(getClientName(), amount, type);
    }

    public void createRandomRequest() {
        int x = (int)(Math.random() * 2);
        RequestType type = RequestType.values()[x];

        long amount = (long)(Math.random() * 100) * 1000;

        this.request = new Request(getClientName(), amount, type);
    }

    @Override
    public void run() {
        outputState();
        frontend.sendRequest(this.request);
    }
}
