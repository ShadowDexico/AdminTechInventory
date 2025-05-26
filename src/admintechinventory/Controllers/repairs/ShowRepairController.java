package admintechinventory.Controllers.repairs;

import admintechinventory.Dao.repair.ShowRepairDAO;
import javax.swing.table.DefaultTableModel;

public class ShowRepairController {

    private ShowRepairDAO showRepairDAO = new ShowRepairDAO();

    public DefaultTableModel getRepairsTableModel() {
        return showRepairDAO.ShowRepairs();
    }
}
