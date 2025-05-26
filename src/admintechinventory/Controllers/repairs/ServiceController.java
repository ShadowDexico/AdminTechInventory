package admintechinventory.Controllers.repairs;

import admintechinventory.Dao.repair.ServiceDAO;
import java.util.List;

public class ServiceController {

    private ServiceDAO serviceDAO = new ServiceDAO();

    public boolean addService(String service) {
        return serviceDAO.insertService(service);
    }

    public List<String> getServices() {
        return serviceDAO.getServices();
    }
}
