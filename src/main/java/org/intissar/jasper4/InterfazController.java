package org.intissar.jasper4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class InterfazController {

    @FXML
    private Button btGenerar;

    @FXML
    private Button btLimpiar;

    @FXML
    private Button btSalir;

    @FXML
    private TextArea txaTratamiento;

    @FXML
    private TextField txtCodMedico;

    @FXML
    private TextField txtDirPaciente;

    @FXML
    private TextField txtEspMedico;

    @FXML
    private TextField txtNomMedico;

    @FXML
    private TextField txtNomPaciente;

    @FXML
    private TextField txtNumPaciente;

    /**
     * Maneja la acción del botón "Generar Informe".
     * Valida los datos del formulario y genera un informe Jasper si los campos son válidos.
     * Si hay errores, muestra un mensaje con los problemas detectados.
     *
     * @param event evento que dispara esta acción.
     */
    @FXML
    void generarInforme(ActionEvent event) {
        StringBuilder errores = new StringBuilder();

        // Validación de campos obligatorios y numéricos
        if (!esNumeroEntero(txtNumPaciente.getText())) {
            errores.append("El 'Número de paciente' debe ser un número entero.\n");
        }

        if (!esNumeroEntero(txtCodMedico.getText())) {
            errores.append("El 'Código del médico' debe ser un número entero.\n");
        }

        if (txtNomPaciente.getText().trim().isEmpty()) {
            errores.append("El 'Nombre del paciente' no puede estar vacío.\n");
        }

        if (txtNomMedico.getText().trim().isEmpty()) {
            errores.append("El 'Nombre del médico' no puede estar vacío.\n");
        }

        if (txtEspMedico.getText().trim().isEmpty()) {
            errores.append("La 'Especialidad del médico' no puede estar vacía.\n");
        }

        if (txaTratamiento.getText().trim().isEmpty()) {
            errores.append("El 'Tratamiento' no puede estar vacío.\n");
        }

        // Mostrar errores o proceder a generar el informe
        if (errores.length() > 0) {
            mostrarError("Errores en el formulario", errores.toString());
        } else {
            // Configurar los parámetros del informe
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("NUM_PACIENTE", Integer.parseInt(txtNumPaciente.getText()));
            parameters.put("NOM_PACIENTE", txtNomPaciente.getText());
            parameters.put("DIR_PACIENTE", txtDirPaciente.getText());
            parameters.put("COD_MEDICO", Integer.parseInt(txtCodMedico.getText()));
            parameters.put("NOM_MEDICO", txtNomMedico.getText());
            parameters.put("ESP_MEDICO", txtEspMedico.getText());
            parameters.put("TRATAMIENTO", txaTratamiento.getText());
            parameters.put("IMAGE_PATH", getClass().getResource("/img/").toString());

            generarReporte("/formulario.jasper", parameters);
        }
    }

    /**
     * Genera y muestra un informe Jasper utilizando un archivo de plantilla y un conjunto de parámetros.
     *
     * @param reportePath la ruta relativa al archivo de plantilla Jasper (.jasper).
     * @param parameters  el mapa de parámetros que se pasará al informe.
     */
    private void generarReporte(String reportePath, Map<String, Object> parameters) {
        try {
            InputStream reportStream = getClass().getResourceAsStream(reportePath);

            // Verificar si el archivo de plantilla existe
            if (reportStream == null) {
                System.out.println("El archivo no se encontró en: " + reportePath);
                return;
            }

            // Cargar, llenar y visualizar el informe Jasper
            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
            mostrarError("Error al generar el informe", "No se pudo generar el informe. Por favor, intente nuevamente.");
        }
    }

    /**
     * Valida si un texto dado es un número entero.
     *
     * @param texto el texto que se desea validar.
     * @return true si el texto es un número entero válido; false en caso contrario.
     */
    private boolean esNumeroEntero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Muestra una alerta de error con el título y mensaje especificados.
     *
     * @param titulo  el título de la alerta.
     * @param mensaje el mensaje que describe el error.
     */
    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Maneja la acción del botón "Limpiar". Resetea los valores de todos los campos del formulario.
     *
     * @param event evento que dispara esta acción.
     */
    @FXML
    void limpiarFormulario(ActionEvent event) {
        txtNumPaciente.clear();
        txtNomPaciente.clear();
        txtDirPaciente.clear();
        txtCodMedico.clear();
        txtNomMedico.clear();
        txtEspMedico.clear();
        txaTratamiento.clear();
    }

    /**
     * Maneja la acción del botón "Salir". Finaliza la ejecución de la aplicación.
     *
     * @param event evento que dispara esta acción.
     */
    @FXML
    void salir(ActionEvent event) {
        System.exit(0);
    }
}
