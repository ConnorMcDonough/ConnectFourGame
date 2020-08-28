package ConnectFour;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author connormcdonough
 */
public class GameScreenFXMLController implements Initializable {

    ArrayList<Circle> circles = new ArrayList<Circle>();// to organize all the checkers
    private final int row = 6;
    private final int col = 7;
    int matrix[][] = new int[row][col];

    private boolean player = true;//true is blue player | false is red player

    private int condition; //which win condition | 0=both lose, 1=blue, 1=red

    //---- Auto Gen
    @FXML
    private Circle checker00, checker10, checker20, checker30, checker40, checker50, checker01, checker11, checker21, checker31, checker41, checker51,
            checker02, checker12, checker22, checker32, checker42, checker52, checker03, checker13, checker23, checker33, checker43, checker53, checker04,
            checker14, checker24, checker34, checker44, checker54, checker05, checker15, checker25, checker35, checker45, checker55, checker06, checker16,
            checker26, checker36, checker46, checker56;

    @FXML
    private Button colBut0, colBut1, colBut2, colBut3, colBut4, colBut5, colBut6;
    @FXML
    private Label turnText;
    @FXML
    private Text clickHere;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Collections.addAll(circles,
                 checker00, checker10, checker20, checker30, checker40, checker50,//5
                 checker01, checker11, checker21, checker31, checker41, checker51,//11
                 checker02, checker12, checker22, checker32, checker42, checker52,//17
                 checker03, checker13, checker23, checker33, checker43, checker53,//23
                 checker04, checker14, checker24, checker34, checker44, checker54,//29
                 checker05, checker15, checker25, checker35, checker45, checker55,//35
                 checker06, checker16, checker26, checker36, checker46, checker56);//41

        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 7; y++) {
                matrix[x][y] = 0;
            }
        }

        for (int x = 0; x < circles.size(); x++) {//size is 42 elements
            circles.get(x).setFill(Color.YELLOW);//sets checkers to background color
        }

    }

    @FXML
    private void col0(ActionEvent event) {
        buttonLogic(0, 0);
        checkWin();
    }

    @FXML
    private void col1(ActionEvent event) {
        buttonLogic(1, 6);
        checkWin();
    }

    @FXML
    private void col2(ActionEvent event) {
        buttonLogic(2, 12);
        checkWin();
    }

    @FXML
    private void col3(ActionEvent event) {
        buttonLogic(3, 18);
        checkWin();
    }

    @FXML
    private void col4(ActionEvent event) {
        buttonLogic(4, 24);
        checkWin();
    }

    @FXML
    private void col5(ActionEvent event) {
        buttonLogic(5, 30);
        checkWin();
    }

    @FXML
    private void col6(ActionEvent event) {
        buttonLogic(6, 36);
        checkWin();
    }

    private void buttonLogic(int col, int add) {
        if (matrix[5][col] == 0) {//[row][col]
            setCheckers(5, col, add);
        } else if (matrix[4][col] == 0) {
            setCheckers(4, col, add);
        } else if (matrix[3][col] == 0) {
            setCheckers(3, col, add);
        } else if (matrix[2][col] == 0) {
            setCheckers(2, col, add);
        } else if (matrix[1][col] == 0) {
            setCheckers(1, col, add);
        } else if (matrix[0][col] == 0) {
            setCheckers(0, col, add);
        }

        if (player == true) {
            turnText.setText("Turn: Blue");
        } else {
            turnText.setText("Turn: Red");
        }
    }

    public void setCheckers(int row, int col, int add) {
        circles.get(row + add).setVisible(true);
        if (player == true) {
            circles.get(row + add).setFill(Color.BLUE);
            matrix[row][col] = 1;
            player = false;
        } else {
            circles.get(row + add).setFill(Color.RED);
            matrix[row][col] = 2;
            player = true;
        }
    }

    public void checkWin() {
        checkHorizontalWin();
        checkVerticalWin();
        checkDiagonalPlusWin();
        checkDiagonalNegWin();
        isFull();
    }

    public void isFull() {
        int numOfZero = 0;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (matrix[x][y] == 0) {
                    numOfZero++;
                }
            }
        }
        if (numOfZero == 0) {
            condition = 0;
            gameOver();
        }
    }

    public void checkHorizontalWin() {
        for (int x = 0; x < row; x++) {

            for (int y = 0; y < this.col; y++) {
                if (matrix[x][y] == 1) {
                    horizontalStatements(x, y, 1);
                } else if (matrix[x][y] == 2) {
                    horizontalStatements(x, y, 2);
                }
            }
        }
    }

    public void horizontalStatements(int x, int y, int player) {
        if (y + 3 < col) {
            if (matrix[x][y + 1] == player && matrix[x][y + 2] == player && matrix[x][y + 3] == player) {
                condition = player;
                gameOver();
            }
        }
    }

    public void checkVerticalWin() {
        for (int x = 0; x < row; x++) {

            for (int y = 0; y < this.col; y++) {
                if (matrix[x][y] == 1) {
                    verticalStatements(x, y, 1);
                } else if (matrix[x][y] == 2) {
                    verticalStatements(x, y, 2);
                }
            }
        }
    }

    public void verticalStatements(int x, int y, int player) {
        if (x + 3 < row) {
            if (matrix[x + 1][y] == player && matrix[x + 2][y] == player && matrix[x + 3][y] == player) {
                condition = player;
                gameOver();
            }
        }
    }

    public void checkDiagonalPlusWin() {
        for (int x = 0; x < row; x++) {

            for (int y = 0; y < this.col; y++) {
                if (matrix[x][y] == 1) {
                    if (x - 3 >= 0 && x - 3 < row && y + 3 < col) {
                        diagonalPlusStatements(x, y, 1);
                    }
                } else if (matrix[x][y] == 2) {
                    if (x - 3 >= 0 && x - 3 < row && y + 3 < col) {
                        diagonalPlusStatements(x, y, 2);
                    }
                }
            }
        }
    }

    public void diagonalPlusStatements(int x, int y, int player) {
        if (matrix[x - 1][y + 1] == player && matrix[x - 2][y + 2] == player && matrix[x - 3][y + 3] == player) {
            condition = player;
            gameOver();
        }
    }

    public void checkDiagonalNegWin() {
        for (int x = 0; x < row; x++) {

            for (int y = 0; y < this.col; y++) {
                if (matrix[x][y] == 1) {
                    if (x + 3 < row && y + 3 < col) {
                        diagonalNegStatements(x, y, 2);
                    }
                } else if (matrix[x][y] == 2) {
                    if (x + 3 < row && y + 3 < col) {//row=6 col=7
                        diagonalNegStatements(x, y, 2);
                    }
                }
            }
        }
    }

    public void diagonalNegStatements(int x, int y, int player) {
        if (matrix[x + 1][y + 1] == player && matrix[x + 2][y + 2] == player && matrix[x + 3][y + 3] == player) {
            condition = player;
            gameOver();
        }
    }

    public void gameOver() {
        colBut0.setVisible(false);
        colBut1.setVisible(false);
        colBut2.setVisible(false);
        colBut3.setVisible(false);
        colBut4.setVisible(false);
        colBut5.setVisible(false);
        colBut6.setVisible(false);
        clickHere.setVisible(false);
        switch (condition) {
            case 0:
                turnText.setText("No Winners :/");
                break;
            case 1:
                turnText.setText("Blue won!");
                break;
            default:
                turnText.setText("Red won!");
                break;
        }

    }

}
