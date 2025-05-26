package admintechinventory.Controllers.repairs;

import admintechinventory.Dao.repair.PaymentMethodDAO;
import java.util.List;

public class PaymentMethodController {

    private PaymentMethodDAO paymentmethodDAO = new PaymentMethodDAO();

    public boolean addPaymentMethod(String paymentMethod) {
        return paymentmethodDAO.insertPaymentMethodDAO(paymentMethod);
    }

    public List<String> getPaymentMethod() {
        return paymentmethodDAO.getPaymentMethodDAO();
    }
}
