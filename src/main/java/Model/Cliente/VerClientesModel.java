package Model.Cliente;

import Utils.ConectBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VerClientesModel {
    ConectBD Conect = new ConectBD();


    public void cargarClientesTabla(TableColumn<Cliente,String> NombreClienteColumn, TableColumn<Cliente, String> ApellidoClienteColumn, TableColumn<Cliente, String> CedulaClienteColumn, TableColumn<Cliente, String> TelefonoClienteColumn, TableColumn<Cliente, String> DireccionClienteColumn, TableColumn<Cliente, String> CorreoClienteColumn1, TableView<Cliente> TablaClientes) {
        // Configurar las columnas
        NombreClienteColumn.setCellValueFactory(new PropertyValueFactory<>("NombreCliente"));
        ApellidoClienteColumn.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        CedulaClienteColumn.setCellValueFactory(new PropertyValueFactory<>("Cedula"));
        TelefonoClienteColumn.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        DireccionClienteColumn.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        CorreoClienteColumn1.setCellValueFactory(new PropertyValueFactory<>("Correo"));

        // Cargar los datos del vehículo
        CargarClientes(TablaClientes);
    }

    public void CargarClientes(TableView<Cliente> TablaInventario) {
        ArrayList<String> Clientes = BuscarClientes();
        ObservableList<Cliente> datos = FXCollections.observableArrayList();

        for (int i = 0 ; i < Clientes.size(); i += 7) {

            Cliente cliente = new Cliente(
                    Clientes.get(i),
                    Clientes.get(i + 1),
                    Clientes.get(i + 2),
                    Clientes.get(i + 3),
                    Clientes.get(i + 4),
                    Clientes.get(i + 5)
            );
            datos.add(cliente);
        }
        TablaInventario.setItems(datos);
    }



    public ArrayList<String> BuscarClientes () {
        ArrayList<String> Clientes = new ArrayList<>();

        try (Connection connection = Conect.Conect();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Clientes.Cliente")
        ) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Clientes.add(resultSet.getString("NOMBRE"));
                Clientes.add(resultSet.getString("APELLIDO"));
                Clientes.add(resultSet.getString("CEDULA"));
                Clientes.add(resultSet.getString("TELEFONO"));
                Clientes.add(resultSet.getString("DIRECCION"));
                Clientes.add(resultSet.getString("CORREO"));
            }
            return Clientes;
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
