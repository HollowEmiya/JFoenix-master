package me.emiya.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import me.emiya.AppRun;
import me.emiya.draw.stage.Shape;

/**
 * @author Yuanhao
 */
public class EditorController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    public EditorController() {
        judge();
    }

    @FXML
    private void edit() {
        int choice = toolbarPopupList.getSelectionModel().getSelectedIndex();

        if (choice == Function.Draw.ordinal()) {
            Shape.toolName = "PEN";
        } else if (choice == Function.Eraser.ordinal()) {
            Shape.toolName = "ERASER";
        }
    }

    @FXML
    private void judge() {
        // disable at start
        if (toolbarPopupList != null) {
            toolbarPopupList.setDisable(!AppRun.stage.isFullScreen());
        }

        // disable at start
        AppRun.stage.fullScreenProperty().addListener(observable -> {
            toolbarPopupList.setDisable(!AppRun.stage.isFullScreen());
        });
    }

    enum Function {
        // for easy reading
        Draw, Eraser
    }
}
