package admintechinventory.Controllers.repairs;

import admintechinventory.Dao.repair.RepairDAO;
import admintechinventory.Models.Repair;

public class RepairController {

    private RepairDAO repairDAO = new RepairDAO();

    public boolean saveRepair(Repair repair) {
        return repairDAO.insertRepair(repair);
    }
}
