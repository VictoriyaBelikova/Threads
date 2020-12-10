package sber.bank.toy;

public class Request {
    private String clientName;
    private long amount;
    private RequestType type;

    public Request(String clientName) {

    }

    public Request(String clientName, long amount, RequestType type) {
        this.clientName = clientName;
        this.amount = amount;
        this.type = type;
    }

    public String getClientName() {
        return clientName;
    }

    public long getAmount() {
        return amount;
    }

    public RequestType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Заявка{" +
                "clientName='" + clientName + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}
