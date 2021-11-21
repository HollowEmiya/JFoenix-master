package me.emiya.gui.main.topmenu;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.net.URI;
import java.sql.*;

@SuppressWarnings("all")
public class AboutController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    private static final String FX_LABEL_FLOAT_TRUE = "-fx-label-float:true;";
    private static final String ERROR = "error";

    @FXML
    private void submit() {
        int choice = toolbarPopupList.getSelectionModel().getSelectedIndex();

        if (choice == Function.Contact.ordinal()) {
            try {
                Desktop.getDesktop().browse(new URI("https://space.bilibili.com/672353429?from=search&seid=6608951036427725100&spm_id_from=333.337.0.0"));
            } catch (Exception e) {
                e.printStackTrace();

            }
        }else if (choice == Function.Exit.ordinal()) {
            Platform.exit();
        }
    }

    enum Function {
        // for easy reading
        Contact, Exit
    }
}
