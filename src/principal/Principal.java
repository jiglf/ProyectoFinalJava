package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Objects;


public class Principal extends Application {

    @Override
    public void start(Stage ventanaPortada) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/principal/vista/Portada.fxml"));
            Parent raizPortada = loader.load();
            Scene escenaPortada = new Scene(raizPortada);
            escenaPortada.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/principal/recursos/Estilos.css")).toExternalForm());
            ventanaPortada.setScene(escenaPortada);

            //determino los valores para que no se pueda modificar el tamaño de la ventana
            ventanaPortada.setMaxHeight(450);
            ventanaPortada.setMinHeight(450);
            ventanaPortada.setMaxWidth(620);
            ventanaPortada.setMinWidth(620);
            ventanaPortada.setTitle("Bienvenido");
            ventanaPortada.show();

        } catch(Exception e) {
            // creo una alerta para que salte en el caso de que no se pueda cargar la vista correcta
            Alert alertaPortada = new Alert(Alert.AlertType.ERROR);
            alertaPortada.setTitle("Error en la carga de la página");
            alertaPortada.showAndWait();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

