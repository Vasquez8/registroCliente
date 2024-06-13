package logicadenegocio;

import accesoadatos.ClienteDAL;
import entidadesdenegocio.Cliente;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteBL {
    public int guardar(Cliente cliente) throws SQLException {
        return ClienteDAL.guardar(cliente);
    }

    public int modificar(Cliente cliente) throws SQLException{
        return ClienteDAL.modificar(cliente);
    }

    public int eliminar(Cliente cliente) throws SQLException{
        return ClienteDAL.eliminar(cliente);
    }

    public ArrayList<Cliente> obtenerTodos() throws SQLException{
        return ClienteDAL.obtenerTodos();
    }

    public ArrayList<Cliente> obtenerDatosFiltrados(Cliente cliente) throws SQLException{
        return ClienteDAL.obtenerDatosFiltrados(cliente);
    }
}