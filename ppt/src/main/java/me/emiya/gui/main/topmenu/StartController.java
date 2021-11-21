package me.emiya.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import me.emiya.mapping.Loader;

public class StartController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    @FXML
    private void start() {
        int choice = toolbarPopupList.getSelectionModel().getSelectedIndex();

        if(choice == 0) {
            try {
                Loader.ppt.getSlides().append();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
