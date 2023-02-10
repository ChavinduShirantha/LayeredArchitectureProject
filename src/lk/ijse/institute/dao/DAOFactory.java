package lk.ijse.institute.dao;

import lk.ijse.institute.dao.custom.impl.*;

/**
 * @author : Chavindu
 * created : 1/19/2023-9:21 AM
 **/
public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENT, SUBJECT, EXAM, TEACHER, BATCH, CREATE_NEW_ACCOUNT, COURSE, PAYMENT, STUDENT_ATTENDANCE, STUDENT_MARKS, SALARY, FUND
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case SUBJECT:
                return new SubjectDAOImpl();
            case EXAM:
                return new ExamDAOImpl();
            case TEACHER:
                return new TeacherDAOImpl();
            case BATCH:
                return new BatchDAOImpl();
            case CREATE_NEW_ACCOUNT:
                return new UserDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case STUDENT_ATTENDANCE:
                return new StudentAttendanceDAOImpl();
            case STUDENT_MARKS:
                return new StudentMarksDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case FUND:
                return new FundDAOImpl();
            default:
                return null;
        }
    }

}
