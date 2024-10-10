package calculator;

public class StringCalculator {
    public static int calculate(String inputFormular) {
        Formular formular = new Formular(inputFormular);
        int cnt = 0;
        int result = 0;
        while (formular.hasOperand()) {
            OperatorEnum operatorEnum = OperatorEnum.getEnumByValue(formular.getNextOperator().getValue());

            result = operatorEnum.calculate(cnt == 0 ? formular.getNextOperand() : new Operand(String.valueOf(result)), formular.getNextOperand());
            cnt++;
        }
        return result;
    }


}

