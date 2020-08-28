/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author connormcdonough
 */
public class Runner extends Application{
    
    private static Scene sceneOne,sceneTwo,sceneThree,sceneFour;
    
    private static Stage stage;//required for setScene
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage s) throws Exception {
        stage=s;
        
        Parent screenOne=(FXMLLoader.load(getClass().getResource("StartScreenFXML.fxml")));
        Parent screenTwo=(FXMLLoader.load(getClass().getResource("GameScreenFXML.fxml")));
        Parent screenThree=(FXMLLoader.load(getClass().getResource("AboutScreenFXML.fxml")));
        
        sceneOne=new Scene(screenOne);
        sceneTwo=new Scene((screenTwo));
        sceneThree=new Scene(screenThree);
        stage.setTitle("Connect 4");
        System.out.println("In main");
        stage.setScene(sceneOne);
        stage.setResizable(false);
        stage.show();

    }
    
    public static void setScene(int index) { 
        if(index==0) {
            stage.setScene(sceneOne);
        } else if(index == 1) {
            stage.setScene(sceneTwo);
        } else if(index==2) {
            stage.setScene(sceneThree);
        } else {
            stage.setScene(sceneFour);
        }
    }
    
}


