package me.emiya.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import com.spire.presentation.ISlide;
import javafx.fxml.FXML;
import me.emiya.AppRun;
import me.emiya.gui.main.MainController;
import me.emiya.mapping.Loader;

import java.lang.reflect.Method;

@SuppressWarnings("all")
public class ShowController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    public ShowController() {
        judge();
    }

    @FXML
    private void show() {
        int choice = toolbarPopupList.getSelectionModel().getSelectedIndex();

        if (choice == Function.ShowFromStart.ordinal()) {
            showFromStart();
        } else if (choice == Function.ShowFromCurrent.ordinal()) {
            showFromCurrent();
        }
    }

    @FXML
    private void judge() {
        // disable at showing stage
        AppRun.stage.fullScreenProperty().addListener(observable -> {
            toolbarPopupList.setDisable(AppRun.stage.isFullScreen());
        });
    }

    private void showFromStart() {
        AppRun.stage.setFullScreen(true);

        // ppt重置至初始第1页
        Loader.slideIndex = -1;
        scrollAction(-1);

        MainController.drawer.setOnScroll(event -> {
            if (AppRun.stage.isFullScreen()) {
                scrollAction(event.getDeltaY());
            }
        });
    }

    private void showFromCurrent() {
        AppRun.stage.setFullScreen(true);

        scrollAction(-1);
        scrollAction(1);

        MainController.drawer.setOnScroll(event -> {
            if (AppRun.stage.isFullScreen()) {
                scrollAction(event.getDeltaY());
            }
        });
    }

    private void scrollAction(double type) {
        if (type > 0) {
            // 向上回滚
            try {
                Class<?> aClass = Class.forName("me.emiya.mapping.Loader");
                Object o = aClass.newInstance();
                Method method = aClass.getDeclaredMethod("setContent", ISlide.class);
                method.setAccessible(true);

                // 至少第二页ppt时才回滚
                if (Loader.slideIndex != 0) {
                    method.invoke(o, Loader.slideList.get(--Loader.slideIndex));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type < 0) {
            // 向下放映
            try {
                Class<?> aClass = Class.forName("me.emiya.mapping.Loader");
                Object o = aClass.newInstance();
                Method method = aClass.getDeclaredMethod("setContent", ISlide.class);
                method.setAccessible(true);
                if (Loader.slideIndex < Loader.slideList.size() - 1) {
                    method.invoke(o, Loader.slideList.get(++Loader.slideIndex));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    enum Function {
        // for easy reading
        ShowFromStart, ShowFromCurrent
    }
}
