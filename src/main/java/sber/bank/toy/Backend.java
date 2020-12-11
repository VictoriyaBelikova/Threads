package sber.bank.toy;

public class Backend {

    private final String SUCCESS_OPERATION = " УСПЕШНО ВЫПОЛНЕНА;";
    private final String UNSUCCESS_OPERATION = " НЕ ВЫПОЛНЕНА;";
    private final String FAIL_CREDIT = "Баланс банка меньше указанной суммы";

    private long bankAmount = 0;
    private String handler;

    public void outputInfo(Request request, String message){
        StringBuilder resultString = new StringBuilder();

        resultString.append("Бэк-система: ").append(request).append(message);
        System.out.println(resultString.toString());
    }

    public void handleRequest(Request request) {

        long requestAmount = request.getAmount();
        this.handler = request.getHandlerName();
        String helpString = "";

        switch (request.getType()) {
            case CREDIT:
                helpString = giveCredit(requestAmount);
                break;
            case REPAYMENT:
                helpString = getRepayment(requestAmount);
                break;
        }

        outputInfo(request, helpString);
    }

    private synchronized String giveCredit(long amount) {
        StringBuilder resultString = new StringBuilder();
        boolean flag = false;

        if (amount < bankAmount) {
            flag = true;
            bankAmount -= amount;
        }

        resultString.append(flag ? SUCCESS_OPERATION : UNSUCCESS_OPERATION);
        resultString.append(" получена от ").append(this.handler);
        if (!flag) {
            resultString.append(". ").append(FAIL_CREDIT);
        }
        resultString.append(". Баланс банка: ").append(bankAmount);

        return resultString.toString();
    }

    private synchronized String getRepayment(long amount) {
        StringBuilder resultString = new StringBuilder();

        bankAmount += amount;

        resultString.append(SUCCESS_OPERATION)
                    .append(" получена от ").append(this.handler)
                    .append(". Баланс банка: ").append(bankAmount);

        return resultString.toString();
    }
}
