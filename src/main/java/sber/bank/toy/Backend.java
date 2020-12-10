package sber.bank.toy;

public class Backend {

    private final String SUCCESS_OPERATION = " УСПЕШНО ВЫПОЛНЕНА;";
    private final String UNSUCCESS_OPERATION = " НЕ ВЫПОЛНЕНА;";
    private final String FAIL_CREDIT = "";

    private long bankAmount = 0;

    public void outputInfo(Request request, String message){
        StringBuilder resultString = new StringBuilder();
        resultString.append(request);
        resultString.append(message);
        System.out.println(resultString.toString());
    }

    public void handleRequest(Request request) {

        long requestAmount = request.getAmount();

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

        resultString.append("Бэк-система: ");

        if (amount < bankAmount) {
            flag = true;
            bankAmount -= amount;
        }

        resultString.append(flag ? SUCCESS_OPERATION : UNSUCCESS_OPERATION);
        resultString.append(" получена от ");
        resultString.append(Thread.currentThread().getName());
        if (!flag) {
            resultString.append(FAIL_CREDIT);
        }
        resultString.append(". Баланс банка: ");
        resultString.append(bankAmount);

        return resultString.toString();
    }

    private synchronized String getRepayment(long amount) {
        StringBuilder resultString = new StringBuilder();

        bankAmount += amount;

        resultString.append(SUCCESS_OPERATION);
        resultString.append(" получена от ");
        resultString.append(Thread.currentThread().getName());
        resultString.append(". Баланс банка: ");
        resultString.append(bankAmount);

        return resultString.toString();
    }
}
