package com.example.task4;

import com.example.task4.model.ConcreteAggregate;
import com.example.task4.model.Iterator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public ConcreteAggregate conaggr = new ConcreteAggregate("img");
    public Iterator iter = conaggr.getIterator();
    public Timeline time = new Timeline();
    public ImageView screen;
    public TextField DelayField;

    public int i = 1000;

    public void NextBtn(ActionEvent event) {
        screen.setImage((Image) iter.next());
    }

    @FXML
    public void PrevBtn(ActionEvent event) {
        screen.setImage((Image) iter.preview());
    }

    public void StartBtn(ActionEvent event) {
        time.play();
    }

    public void PauseBtn(ActionEvent event) {
        time.stop();
    }

    public void StopBtn(ActionEvent event) {
        time.pause();
    }

    public void SetBtn(ActionEvent event) {
        i = Integer.parseInt(DelayField.getText());;
        time.setCycleCount(i);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        time.setCycleCount(Timeline.INDEFINITE); //кол-во повторов
        time.getKeyFrames().add(new KeyFrame(Duration.millis(i), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (iter.hasNext(1))
                    screen.setImage((Image)
                            iter.next());
            }
        }));
    }
}