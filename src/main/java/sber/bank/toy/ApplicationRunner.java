package sber.bank.toy;

import sber.bank.toy.RequestType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationRunner {

    public final static int CLIENTS_COUNT = 5;
    public final static int OPERATORS_COUNT = 2;

    private final static ExecutorService bankClients = Executors.newFixedThreadPool(CLIENTS_COUNT);
    private final static ExecutorService operators = Executors.newFixedThreadPool(OPERATORS_COUNT);

    public static void main(String[] args) {

        FrontalSystem frontend = new FrontalSystem();
        Backend backend = new Backend();

        for (int i = 0; i < CLIENTS_COUNT; i++) {
            String name = "Клиент №" + (i + 1);
            Client client = new Client(name, frontend);
            client.createRandomRequest();
            bankClients.execute(client);
        }

        for (int i = 0; i < OPERATORS_COUNT; i++) {
            String name = "Обработчик заявок №" + (i + 1);
            RequestHandler requestHandler = new RequestHandler(name, frontend, backend);
            operators.execute(requestHandler);
        }
    }
}
