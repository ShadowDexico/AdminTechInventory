package admintechinventory.Controllers.client;

import admintechinventory.Dao.Client.GetClientDAO;
import admintechinventory.Models.Client;

    public class GetClientController {

        private GetClientDAO clientDAO;

        public GetClientController(GetClientDAO clientDAO) {
            this.clientDAO = clientDAO;
        }

        public boolean addClient(Client client) {
            return clientDAO.addClient(client);
        }
    }
