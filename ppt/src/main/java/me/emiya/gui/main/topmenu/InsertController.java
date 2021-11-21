package me.emiya.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import me.emiya.AppRun;
import me.emiya.mapping.Loader;

import java.io.File;

@SuppressWarnings("all")
public class InsertController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    public InsertController() {
        judge();
    }

    @FXML
    private void insert() {
        int choice = toolbarPopupList.getSelectionModel().getSelectedIndex();

        if (choice == Function.InsertImage.ordinal()) {
            insertImg();
        } else if (choice == Function.InsertText.ordinal()) {
            insertText();
        } else if (choice == Function.InsertShape.ordinal()) {
            insertShape();
        }
    }

    @FXML
    private void judge() {
        // disable at showing stage
        AppRun.stage.fullScreenProperty().addListener(observable -> {
            toolbarPopupList.setDisable(AppRun.stage.isFullScreen());
        });
    }

    private void insertImg() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image", "*.png", "*.jpeg", "*.jpg"));
        fileChooser.setTitle("打开");
        File img = fileChooser.showOpenDialog(null);

        if (img != null) {
            ImageView imageView = new ImageView("file:" + img.getPath());
            imageView.setFitHeight(300);
            imageView.setFitWidth(250);
            // 拖动图片移动操作
            imageView.setOnMousePressed(event -> {
                final double deltaX = event.getX() - imageView.getX();
                final double deltaY = event.getY() - imageView.getY();
                imageView.setOnMouseDragged(e -> {
                    imageView.setX(e.getX() - deltaX);
                    imageView.setY(e.getY() - deltaY);
                });
            });
            Loader.content.getChildren().add(imageView);
        }
    }

    private void insertText() {
        JFXTextArea jfxTextArea = new JFXTextArea();
        jfxTextArea.setLayoutX(300);
        jfxTextArea.setLayoutY(400);

        // 拖动文本框移动操作 但是不规范
        jfxTextArea.setOnMousePressed(event -> {
            final double deltaX = event.getX() - jfxTextArea.getLayoutX();
            final double deltaY = event.getY() - jfxTextArea.getLayoutY();
            jfxTextArea.setOnMouseDragged(e -> {
                jfxTextArea.setLayoutX(e.getX() - deltaX);
                jfxTextArea.setLayoutY(e.getY() - deltaY);
            });
        });

        Loader.content.getChildren().add(jfxTextArea);
    }

    private void insertShape() {

    }

    enum Function {
        // for easy reading
        InsertImage, InsertText, InsertShape
    }
}
