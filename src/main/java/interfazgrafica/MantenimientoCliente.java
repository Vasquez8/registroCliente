package interfazgrafica;

import entidadesdenegocio.Cliente;
import logicadenegocio.ClienteBL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MantenimientoCliente {
    private JPanel jpPrincipal;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JButton btnSalir;
    private JRadioButton rdbId;
    private JRadioButton rdbApellido;
    private JRadioButton rdbCorreo;
    private JTextField txtCriterio;
    private JButton btnBuscar;
    private JTable jtClientes;
    private ButtonGroup listaBuscar;

    // instancias propias
    ArrayList<Cliente> listaClientes;
    Cliente cliente;
    ClienteBL clienteBL = new ClienteBL();

    public static void main(String[] args) throws SQLException {
        new MantenimientoCliente();
    }

    public MantenimientoCliente() throws SQLException {
        inicializar();
        actualizarForm();
        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                txtCorreo.setEnabled(true);
                txtDireccion.setEnabled(true);
                txtTelefono.setEnabled(true);
                txtNombre.grabFocus();
                btnGuardar.setEnabled(true);
                btnNuevo.setEnabled(false);
                btnCancelar.setEnabled(true);
            }
        });
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cliente = new Cliente();
                cliente.setNombre(txtNombre.getText());
                cliente.setApellido(txtApellido.getText());
                cliente.setCorreo(txtCorreo.getText());
                cliente.setDireccion(txtDireccion.getText());
                cliente.setTelefono(txtTelefono.getText());
                try {
                    clienteBL.guardar(cliente);
                    JOptionPane.showMessageDialog(null, "Se guardó con éxito");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cliente = new Cliente();
                cliente.setId(Integer.parseInt(txtId.getText()));
                cliente.setNombre(txtNombre.getText());
                cliente.setApellido(txtApellido.getText());
                cliente.setCorreo(txtCorreo.getText());
                cliente.setDireccion(txtDireccion.getText());
                cliente.setTelefono(txtTelefono.getText());
                try {
                    clienteBL.modificar(cliente);
                    JOptionPane.showMessageDialog(null, "Se modificó con éxito");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jtClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = jtClientes.getSelectedRow();
                txtId.setText(jtClientes.getValueAt(fila, 0).toString());
                txtNombre.setText(jtClientes.getValueAt(fila, 1).toString());
                txtApellido.setText(jtClientes.getValueAt(fila, 2).toString());
                txtCorreo.setText(jtClientes.getValueAt(fila, 3).toString());
                txtDireccion.setText(jtClientes.getValueAt(fila, 4).toString());
                txtTelefono.setText(jtClientes.getValueAt(fila, 5).toString());

                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                txtCorreo.setEnabled(true);
                txtDireccion.setEnabled(true);
                txtTelefono.setEnabled(true);
                txtNombre.grabFocus();

                btnNuevo.setEnabled(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnCancelar.setEnabled(true);
            }
        });
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cliente = new Cliente();
                cliente.setId(Integer.parseInt(txtId.getText()));
                try {
                    clienteBL.eliminar(cliente);
                    JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtCriterio.getText().equals("") || (!rdbId.isSelected() &&
                        !rdbApellido.isSelected() && !rdbCorreo.isSelected())) {
                    JOptionPane.showMessageDialog(null,
                            "Seleccione un criterio de búsqueda o escriba el valor a buscar");
                }

                cliente = new Cliente();

                if (rdbId.isSelected()) {
                    cliente.setId(Integer.parseInt(txtCriterio.getText()));
                    try {
                        llenarTabla(clienteBL.obtenerDatosFiltrados(cliente));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (rdbApellido.isSelected()) {
                    cliente.setApellido(txtCriterio.getText());
                    try {
                        llenarTabla(clienteBL.obtenerDatosFiltrados(cliente));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (rdbCorreo.isSelected()) {
                    cliente.setCorreo(txtCriterio.getText());
                    try {
                        llenarTabla(clienteBL.obtenerDatosFiltrados(cliente));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    void inicializar() {
        JFrame frame = new JFrame("Mantenimiento de Clientes");
        frame.setContentPane(this.jpPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        listaBuscar = new ButtonGroup();
        listaBuscar.add(rdbId);
        listaBuscar.add(rdbApellido);
        listaBuscar.add(rdbCorreo);
    }

    void llenarTabla(ArrayList<Cliente> clientes) {
        Object[] objects = new Object[6];
        listaClientes = new ArrayList<>();
        String[] encabezado = {"ID", "NOMBRE", "APELLIDO", "CORREO", "DIRECCION", "TELEFONO"};
        DefaultTableModel tm = new DefaultTableModel(null, encabezado);
        listaClientes.addAll(clientes);
        for (Cliente item : listaClientes) {
            objects[0] = item.getId();
            objects[1] = item.getNombre();
            objects[2] = item.getApellido();
            objects[3] = item.getCorreo();
            objects[4] = item.getDireccion();
            objects[5] = item.getTelefono();
            tm.addRow(objects);
        }
        jtClientes.setModel(tm);
    }

    void actualizarForm() throws SQLException {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");

        txtId.setEnabled(false);
        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtTelefono.setEnabled(false);

        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);

        txtCriterio.setText("");
        listaBuscar.clearSelection();

        llenarTabla(clienteBL.obtenerTodos());
    }
}
