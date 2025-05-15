package admintechinventory.Controllers.client;

import admintechinventory.Dao.Client.ClientDAO;
import admintechinventory.Models.Client;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class ClientController {
    private ClientDAO clientDAO;

    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public DefaultTableModel getClientsTableModel() {
        return clientDAO.ObtainClients();
    }
}
