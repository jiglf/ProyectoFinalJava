package principal.controlador;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControladorPortada {

    @FXML
    private ProgressBar barraProgreso;

    @FXML
    private Pane panelPortada;

    @FXML
    private TextField tCargando;

    @FXML
    void accederPrincipal(ActionEvent event) {
        barraProgreso.setVisible(true);
        tCargando.setVisible(true);

        /** creo un hilo para generar la carga de una barra de progreso, para asi simular la carga de los datos */
        Task<Void> hiloBarra = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 100; i++) {

                    int aumento = i;
                    Platform.runLater(() -> {
                        barraProgreso.setProgress(aumento / 100.0);
                        tCargando.setText("Cargando datos ... " + aumento + " %");
                    });
                    Thread.sleep(30);
                }

                /** Una vez que la barra de progreso llega al 100%, carga la ventanaPrincipal y cierra la actual */
                Platform.runLater(() -> {
                    cargarVentanaPrincipal();
                    ((Stage) barraProgreso.getScene().getWindow()).close();
                });

                return null;
            }
        };

        new Thread(hiloBarra).start();

    }


    /** metodo usado para llamar a la ventana principal una vez se carga la barra de progeso */
    public void cargarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/principal/vista/Principal.fxml"));
            Parent raizPrincipal = loader.load();
            Scene escenaPrincipal = new Scene(raizPrincipal);
            Stage ventanaPrincipal = new Stage();

            //establezco los valores para que no se pueda modificar el tamaño de la ventana
            ventanaPrincipal.setMaxHeight(510);
            ventanaPrincipal.setMinHeight(510);
            ventanaPrincipal.setMaxWidth(620);
            ventanaPrincipal.setMinWidth(620);

            ventanaPrincipal.setTitle("La batalla de la tierra media");
            ventanaPrincipal.setScene(escenaPrincipal);
            ventanaPrincipal.show();

        } catch (Exception e) {
            Alert alertaPrincipal = new Alert(Alert.AlertType.ERROR);
            alertaPrincipal.setTitle("Error en la carga de la página");
            alertaPrincipal.showAndWait();
        }
    }
}





