package org.intissar.jasper4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Clase principal de la aplicación JavaFX que se encarga de iniciar y configurar la interfaz gráfica de usuario.
 */
public class LanzarInforme extends Application {

    /**
     * Metodo principal para configurar y mostrar la ventana principal de la aplicación.
     * Este metodo se ejecuta automáticamente al iniciar la aplicación JavaFX.
     *
     * @param stage La ventana principal (Stage) proporcionada por JavaFX, que actúa como contenedor raiz de la escena.
     * @throws IOException Si ocurre un error al intentar cargar el archivo FXML que define la interfaz.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LanzarInforme.class.getResource("/org/intissar/jasper4/interfaz.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Formulario medico");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metodo principal de la aplicación.
     * Inicia la aplicación JavaFX invocando el metodo {@code launch}, que a su vez llama al metodo {@code start}.
     *
     * @param args Argumentos de la línea de comandos. En esta aplicación no se utilizan.
     */
    public static void main(String[] args) {
        launch();
    }
}
