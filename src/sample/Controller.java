package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;


public class Controller {
    @FXML
    public Button btn1;
    @FXML
    public Button btn2;
    @FXML
    public Button btn3;
    @FXML
    public Button btn4;
    @FXML
    public Button btn5;
    @FXML
    public Button btn6;
    @FXML
    public Button btn7;
    @FXML
    public Button btn8;
    @FXML
    public Button btn9;
    @FXML
    public Button btn0;
    @FXML
    public Button btnComma;
    @FXML
    public Button btnPlus;
    @FXML
    public Button btnMinus;
    @FXML
    public Button btnMultiply;
    @FXML
    public Button btnDivision;
    @FXML
    public Button btnSQRT;
    @FXML
    public Button btnEquation;
    @FXML
    public Button btnClear;
    @FXML
    public TextField textField;
    @FXML
    public Button btnChangeSign;
    ArrayList <Double> numbers = new ArrayList<>();
    ArrayList<String> opers = new ArrayList<>();
    double result;


    public void getSQRT(ActionEvent actionEvent) {
        getResult();
        if (result >= 0) {
            result = Math.sqrt(result);
            textField.setText(Double.toString(result));
        } else textField.setText("Корень из отр. числа не существует!");

    }

    public void getResult() {

        String[] operands = textField.getText().split("[-+X//]");
        System.out.println(Arrays.toString(operands));
        if (textField.getText().charAt(0)=='-') operands[1] = "-" + operands[1];
        for (String s : operands) {
            try {
                numbers.add(Double.parseDouble(s));
            } catch (NumberFormatException e) {
                System.out.println("перед первой цифрой стоит оператор");;
            }
        }
        System.out.println(numbers);

        String[] operators = textField.getText().split("\\d+");
        System.out.println(Arrays.toString(operators));
        if (operators.length == 0) result = numbers.get(0);
        else {
            opers.addAll(Arrays.asList(operators));
            opers.remove(0);
            opers.removeIf(x -> x.equals("."));
        }
        System.out.println(opers);

        result = numbers.get(0);
        for (int i = 0; i < opers.size(); i++) {
            result = equation(opers.get(i), result, i);
        }
        opers.clear();
        numbers.clear();
        textField.setText(Double.toString(result));
    }

    public double equation(String sign, double result, int index) {
        switch (sign) {
            case "+": result += numbers.get(index+1);
                break;
            case "-": result -= numbers.get(index+1);
                break;
            case "X": result *= numbers.get(index+1);
                break;
            case "/": result /= numbers.get(index+1);


        }
        return result;
    }

    public void addComma(ActionEvent actionEvent) {
        String num = textField.getText();

        textField.appendText(btnComma.getText());
    }


    public void clearTextArea(ActionEvent actionEvent) {
        textField.clear();
    }

    public void exitClick(ActionEvent actionEvent) {
        Stage stage = (Stage) textField.getScene().getWindow();
        stage.close();
    }

    public void unsupported(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Внимание!");
        alert.setHeaderText("Сообщение системы: ");
        alert.setContentText("К сожалению, функция пока не работает!");

        alert.showAndWait();
    }

    public void addText(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        textField.appendText(btn.getText());
    }

    public void changeSign(ActionEvent actionEvent) {
        getResult();
        result = result*(-1);
        textField.setText(Double.toString(result));
    }

    public void copyText(ActionEvent actionEvent) {
        textField.copy();
    }

    public void cutText(ActionEvent actionEvent) {
        textField.cut();
    }

    public void pasteText(ActionEvent actionEvent) {
        textField.paste();
    }
}
