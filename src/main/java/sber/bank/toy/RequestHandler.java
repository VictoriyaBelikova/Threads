package sber.bank.toy;

public class RequestHandler extends Thread {

    private final FrontalSystem frontend;
    private final Backend backend;

    public RequestHandler(String handlerName, FrontalSystem frontend, Backend backend) {
        setName(handlerName);
        setDaemon(true);
        this.frontend = frontend;
        this.backend = backend;
    }

    public void outputState(Request request){
        String resultString = currentThread().getName() +
                ": " +
                " получена заявка на обработку по клиенту" +
                request.getClientName();
        System.out.println(resultString);
    }

    @Override
    public void run() {
        while (true){
            Request request = frontend.receive();
            outputState(request);
            backend.handleRequest(request);
        }
    }

}
