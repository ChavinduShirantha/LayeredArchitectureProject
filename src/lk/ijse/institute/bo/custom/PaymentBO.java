package lk.ijse.institute.bo.custom;

import lk.ijse.institute.bo.SuperBO;
import lk.ijse.institute.dto.PaymentDTO;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:45 AM
 **/
public interface PaymentBO extends SuperBO {
    boolean addPayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    boolean deletePayment(String id) throws SQLException, ClassNotFoundException;

    String generateNewPaymentID() throws SQLException, ClassNotFoundException;

    PaymentDTO searchPayment(String id) throws SQLException, ClassNotFoundException;

}
