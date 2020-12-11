package sber.bank.toy;

public class RequestHandler extends Thread {

    private final FrontalSystem frontend;
    private final Backend backend;

    private String handlerName;

    public RequestHandler(String handlerName, FrontalSystem frontend, Backend backend) {
        setHandlerName(handlerName);
        setDaemon(true);
        this.frontend = frontend;
        this.backend = backend;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public void outputState(Request request){
        String resultString = getHandlerName() +
                ": " +
                "получена заявка на обработку по клиенту - " +
                request.getClientName();
        System.out.println(resultString);
    }

    @Override
    public void run() {
        while (true){
            Request request = frontend.receive();
            outputState(request);
            request.setHandlerName(handlerName);
            backend.handleRequest(request);
        }
    }

}
