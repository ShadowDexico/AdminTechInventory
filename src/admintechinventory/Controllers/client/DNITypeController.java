package admintechinventory.Controllers.client;

import admintechinventory.Dao.Client.DNITypeDAO;
import admintechinventory.Models.DNIType;
import java.util.List;

import java.util.List;

public class DNITypeController {

    private DNITypeDAO dniTypeDAO;

    public DNITypeController(DNITypeDAO dniTypeDAO) {
        this.dniTypeDAO = dniTypeDAO;
    }

    public List<String> getDNITypes() {
        return dniTypeDAO.getDNITypes();
    }
}
