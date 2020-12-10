package sber.bank.toy;

import sber.bank.toy.RequestType;

public class ApplicationRunner {

    public static void main(String[] args) {

        FrontalSystem frontend = new FrontalSystem();
        Backend backend = new Backend();

        Client client1 = new Client("Клиент №1", frontend);
        Client client2 = new Client("Клиент №2", frontend);
        Client client3 = new Client("Клиент №3", frontend);
        Client client4 = new Client("Клиент №4", frontend);
        Client client5 = new Client("Клиент №5", frontend);

        client1.createRequest(5000L, RequestType.REPAYMENT);
        client2.createRequest(2000L, RequestType.CREDIT);
        client3.createRequest(13000L, RequestType.REPAYMENT);
        client4.createRequest(45000L, RequestType.REPAYMENT);
        client5.createRequest(125000L, RequestType.CREDIT);


        RequestHandler requestHandler1 = new RequestHandler("Обработчик заявок №1", frontend, backend);
        RequestHandler requestHandler2 = new RequestHandler("Обработчик заявок №2", frontend, backend);

        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();

        requestHandler1.start();
        requestHandler2.start();
    }

}
