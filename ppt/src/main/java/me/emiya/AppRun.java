package me.emiya;

import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import me.emiya.gui.main.MainController;

import java.io.IOException;

public class AppRun extends Application {
    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(AppRun.class.getResourceAsStream("/fonts/icomoon.svg"),
                    "icomoon.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }).start();

        Flow flow = new Flow(MainController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", primaryStage);
        flow.createHandler(flowContext).start(container);

        // 不设置全屏button 将此显示做为放映模式
        JFXDecorator decorator = new JFXDecorator(primaryStage, container.getView(), false, true, true);
        decorator.setCustomMaximize(true);
        decorator.setGraphic(new SVGGlyph(""));

        primaryStage.setTitle("PoorPigTrick");

        double width = 1000;
        double height = 600;
        try {
            Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
            width = bounds.getWidth() / 1.31;
            height = bounds.getHeight() / 1.2;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(decorator, width, height);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
            JFoenixResources.load("css/jfoenix-design.css").toExternalForm(),
            AppRun.class.getResource("/css/jfoenix-main-demo.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
