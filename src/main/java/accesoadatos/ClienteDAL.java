package accesoadatos;

import entidadesdenegocio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAL {
    // método que permite guardar un nuevo registro
    public static int guardar(Cliente cliente) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO Cliente(Nombre, Apellido, Correo, Direccion, Telefono) VALUES(?, ?, ?, ?, ?)";
        try (Connection conexion = ComunBD.obtenerConexion();
             PreparedStatement ps = ComunBD.crearPreparedStatement(conexion, sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite modificar un registro existente
    public static int modificar(Cliente cliente) throws SQLException {
        int result = 0;
        String sql = "UPDATE Cliente SET Nombre = ?, Apellido = ?, Correo = ?, Direccion = ?, Telefono = ? WHERE Id = ?";
        try (Connection conexion = ComunBD.obtenerConexion();
             PreparedStatement ps = ComunBD.crearPreparedStatement(conexion, sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setInt(6, cliente.getId());
            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite eliminar un registro existente
    public static int eliminar(Cliente cliente) throws SQLException {
        int result = 0;
        String sql = "DELETE FROM Cliente WHERE Id = ?";
        try (Connection conexion = ComunBD.obtenerConexion();
             PreparedStatement ps = ComunBD.crearPreparedStatement(conexion, sql)) {
            ps.setInt(1, cliente.getId());
            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // método que muestra todos los registros de la tabla
    public static ArrayList<Cliente> obtenerTodos() throws SQLException {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT Id, Nombre, Apellido, Correo, Direccion, Telefono FROM Cliente";
        try (Connection conexion = ComunBD.obtenerConexion();
             PreparedStatement ps = ComunBD.crearPreparedStatement(conexion, sql);
             ResultSet rs = ComunBD.obtenerResultSet(ps)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                lista.add(cliente);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // método para consultar mediante criterios
    public static ArrayList<Cliente> obtenerDatosFiltrados(Cliente cliente) throws SQLException {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, Correo, Direccion, Telefono FROM Cliente WHERE id = ? OR apellido LIKE ? OR Correo LIKE ?";
        try (Connection conexion = ComunBD.obtenerConexion();
             PreparedStatement ps = ComunBD.crearPreparedStatement(conexion, sql)) {
            ps.setInt(1, cliente.getId());
            ps.setString(2, "%" + cliente.getApellido() + "%");
            ps.setString(3, "%" + cliente.getCorreo() + "%");
            try (ResultSet rs = ComunBD.obtenerResultSet(ps)) {
                while (rs.next()) {
                    Cliente cl = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                    lista.add(cl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}