package Model;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class FileChoose {

    public String ShowFileChooser(TextField btn) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");
        File file = fileChooser.showOpenDialog(btn.getScene().getWindow());

        if (file != null) {
            ArrayList<String> list = new ArrayList<>();
            list.add(file.getAbsolutePath()); // Ruta original del archivo
            list.add(file.getName());         // Nombre del archivo
            return MoveFile(list);
        }

        return null;
    }

    public String EscojerRuta(Button btn, String NombreArchivo) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo");

        // Establecer el nombre predeterminado del archivo
        fileChooser.setInitialFileName(NombreArchivo+".pdf");

        // Agregar filtros de extensión (opcional)
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos PDF (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrar el diálogo de guardar archivo
        File selectedFile = fileChooser.showSaveDialog(btn.getScene().getWindow());

        // Verificar si el usuario seleccionó un archivo
        if (selectedFile != null) {
            // Obtener la ruta del archivo seleccionado
            System.out.println("El usuario seleccionó el archivo: " + selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();

            // Aquí puedes guardar el archivo en la ruta seleccionada
            // Por ejemplo: guardarPDF(filePath);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al guardar el archivo");
            alert.setContentText("No se seleccionó Ningun Destino");
            alert.showAndWait();
        }
        return null;
    }

    public String EscojerRuta(TextField btn, String NombreArchivo) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo");

        // Establecer el nombre predeterminado del archivo
        fileChooser.setInitialFileName(NombreArchivo+".pdf");

        // Agregar filtros de extensión (opcional)
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos PDF (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrar el diálogo de guardar archivo
        File selectedFile = fileChooser.showSaveDialog(btn.getScene().getWindow());

        // Verificar si el usuario seleccionó un archivo
        if (selectedFile != null) {
            // Obtener la ruta del archivo seleccionado
            System.out.println("El usuario seleccionó el archivo: " + selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();

            // Aquí puedes guardar el archivo en la ruta seleccionada
            // Por ejemplo: guardarPDF(filePath);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al guardar el archivo");
            alert.setContentText("No se seleccionó Ningun Destino");
            alert.showAndWait();
        }
        return null;
    }

    public String MoveFile(ArrayList<String> path) {
        File file = new File(path.get(0)); // Ruta original del archivo
        String fileName = path.get(1);     // Nombre del archivo

        // Ruta de destino dentro del proyecto (src/main/resources/Insumos)
        String destinationPath = "src/main/resources/Insumos/" + fileName;

        try {
            // Crear el directorio de destino si no existe
            Files.createDirectories(Paths.get("src/main/resources/Insumos"));

            // Mover el archivo usando Java NIO
            java.nio.file.Path source = file.toPath();
            java.nio.file.Path destination = Paths.get(destinationPath);
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

            return destinationPath; // Devolver la ruta de destino
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de errores
            return null;
        }
    }
}
