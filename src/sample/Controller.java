package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.ENTER;

public class Controller implements Initializable, ChangeListener {

    @FXML
    TextField addressBar;

    @FXML
    WebView web;

    public void onForward() {
        System.out.println("forward");
    }
    public void onBack(){
        System.out.println("back");
    }
    public void onGo(){
        String url = addressBar.getText();
        if (!url.startsWith("http")){
            url = "http://" + url;
        }
        web.getEngine().load(url);
    }
    public void onKeyPressed(KeyEvent event){
        if (event.getCode() == ENTER) {
            onGo();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        web.getEngine().getLoadWorker().stateProperty().addListener(this);
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        String url = web.getEngine().getLocation();
        addressBar.setText(url);

    }
}