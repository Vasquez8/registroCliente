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
        try {
            String sql = "INSERT INTO Cliente( Nombre, Apellido, Correo, Dirreccion, Telefono) VALUES(?, ?, ?, ?)";
            Connection conexion = ComunBD.obtenerConexion();
            PreparedStatement ps = ComunBD.crearPreparedStatement(conexion, sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    // método que permite modificar un registro existente
    public static int modificar(Cliente cliente) throws SQLException{
        int result = 0;
        try {
            String sql = "UPDATE Clientes SET Nombre = ?, Apellido = ?, Correo = ?, Direccion = ?, Telefono = ? WHERE Id = ?";
            Connection conexion = ComunBD.obtenerConexion();
            PreparedStatement ps = ComunBD.crearPreparedStatement(conexion, sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    // método que permite eliminar un registro existente
    public static int eliminar(Cliente cliente) throws SQLException{
        int result = 0;
        try {
            String sql = "DELETE FROM Cliente WHERE Id = ?";
            Connection conexion = ComunBD.obtenerConexion();
            PreparedStatement ps = ComunBD.crearPreparedStatement(conexion, sql);
            ps.setInt(1, cliente.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite modificar un registro existente
    public static int modificar(Cliente cliente) throws SQLException{
        int result = 0;
        try {
            String sql = "UPDATE Cliente SET Nombre = ?, Apellido = ?, Correo = ?, Direccion = ?, Telefono = ? WHERE Id = ?";
            Connection conexion = ComunBD.obtenerConexion();
            PreparedStatement ps = ComunBD.crearPreparedStatement(conexion, sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite eliminar un registro existente
    public static int eliminar(Cliente cliente) throws SQLException{
        int result = 0;
        try {
            String sql = "DELETE FROM Cliente WHERE Id = ?";
            Connection conexion = ComunBD.obtenerConexion();
            PreparedStatement ps = ComunBD.crearPreparedStatement(conexion, sql);
            ps.setInt(1, cliente.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    // método que muestra todos los registros de la tabla
    public static ArrayList<Cliente> obtenerTodos() throws SQLException {
        ArrayList<Cliente> lista = new ArrayList<>();
        Cliente cliente;
        try{
            String sql = "SELECT Id, Nombre, Apellido, Correo, Dirreccion, Telefono FROM Cliente";
            Connection conexion = ComunBD.obtenerConexion();
            PreparedStatement ps = ComunBD.crearPreparedStatement(conexion, sql);
            ResultSet rs = ComunBD.obtenerResultSet(ps);
            while (rs.next()){
                cliente = new Cliente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
                lista.add(cliente);
            }
            conexion.close();
            ps.close();
            rs.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }

    // método para consultar mediante criterios
    public static ArrayList<Cliente> obtenerDatosFiltrados(Cliente cliente) throws SQLException{
        ArrayList<Cliente> lista = new ArrayList<>();
        Cliente cl;
        try{
            String sql = "SELECT id, nombre, apellido, Correo, Dirrecion, Telefono FROM Cliente WHERE id = ? or apellido like'%" + cliente.getApellido() + "%' or carrera like'%" + cliente.getCorreo() + "%'";
            Connection connection = ComunBD.obtenerConexion();
            PreparedStatement ps = ComunBD.crearPreparedStatement(connection, sql);
            ps.setInt(1, cliente.getId());
            ResultSet rs = ComunBD.obtenerResultSet(ps);
            while (rs.next()){
                cl = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                lista.add(cl);
            }
            connection.close();
            ps.close();
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}