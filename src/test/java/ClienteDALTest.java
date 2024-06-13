import accesoadatos.ClienteDAL;
import entidadesdenegocio.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ClienteDALTest {
    @Test
    public void guardarTest() throws SQLException {
        Cliente client = new Cliente();
        client.setNombre("Juan Carlos");
        client.setApellido("Pérez López");
        client.setCorreo("Juan@gmail.com");
        client.setDireccion("Calle sin fin");
        client.setTelefono("7889-0989");

        int esperado = 1;
        int actual = ClienteDAL.guardar(client);
        Assertions.assertEquals(esperado, actual);
    }
    @Test
    public void modificarTest() throws SQLException{
        Cliente client = new Cliente();
        client.setId(2);
        client.setNombre("Carlos Antonio");
        client.setApellido("Pérez López");
        client.setCorreo("Antonio@gmail.com");
        client.setDireccion("Calle sin fin");
        client.setTelefono("7889-0989");

        int esperado = 1;
        int actual = ClienteDAL.modificar(client);
        Assertions.assertEquals(esperado, actual);
    }
    @Test
    public void eliminarTest() throws SQLException{
        Cliente student = new Cliente();
        student.setId(3);

        int esperado = 1;
        int actual = ClienteDAL.eliminar(student);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void obtenerTodosTest() throws SQLException{
        int actual = ClienteDAL.obtenerTodos().size();
        Assertions.assertNotEquals(0, actual);
    }

    @Test
    public void obtenerDatosFiltradosTest() throws SQLException{
        Cliente student = new Cliente();
        student.setApellido("Perez");
        student.setId(0);
        student.setCorreo("");

        int actual = ClienteDAL.obtenerDatosFiltrados(student).size();
        Assertions.assertNotEquals(0, actual);
    }
}
