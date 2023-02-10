package lk.ijse.institute.bo.custom.impl;

import lk.ijse.institute.bo.custom.PaymentBO;
import lk.ijse.institute.dao.DAOFactory;
import lk.ijse.institute.dao.custom.FundDAO;
import lk.ijse.institute.dao.custom.PaymentDAO;
import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.dto.PaymentDTO;
import lk.ijse.institute.entity.Fund;
import lk.ijse.institute.entity.Payment;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:52 AM
 **/
public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    FundDAO fundDAO = (FundDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FUND);

    public boolean addPayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean add = paymentDAO.add(new Payment(dto.getPayment_id(), dto.getStd_id(), dto.getStd_name(), dto.getBatch_id(), dto.getCourse_name(), dto.getAmount(), dto.getDate(), dto.getTime()));
        if (add) {
            Payment search = paymentDAO.search(dto.getPayment_id());
            double amount = search.getAmount();
            boolean b = fundDAO.AddPayment(new Fund(amount));
            if (b) {
                connection.commit();
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPayment_id(), dto.getStd_id(), dto.getStd_name(), dto.getBatch_id(), dto.getCourse_name(), dto.getAmount(), dto.getDate(), dto.getTime()));
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public String generateNewPaymentID() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewID();
    }

    @Override
    public PaymentDTO searchPayment(String id) throws SQLException, ClassNotFoundException {
        Payment p = paymentDAO.search(id);
        return new PaymentDTO(p.getPayment_id(), p.getStd_id(), p.getStd_name(), p.getBatch_id(), p.getCourse_name(), p.getAmount(), p.getDate(), p.getTime());
    }
}
