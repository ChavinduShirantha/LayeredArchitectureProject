package lk.ijse.institute.bo;

import lk.ijse.institute.bo.custom.impl.*;

/**
 * @author : Chavindu
 * created : 1/19/2023-9:17 AM
 **/
public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        STUDENT, SUBJECT, EXAM, TEACHER, BATCH, CREATE_NEW_ACCOUNT, COURSE, PAYMENT, STUDENT_ATTENDANCE, STUDENT_MARKS, SALARY
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImpl();
            case SUBJECT:
                return new SubjectBOImpl();
            case EXAM:
                return new ExamBOImpl();
            case TEACHER:
                return new TeacherBOImpl();
            case BATCH:
                return new BatchBOImpl();
            case CREATE_NEW_ACCOUNT:
                return new CreateNewAccountBOImpl();
            case COURSE:
                return new CourseBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case STUDENT_ATTENDANCE:
                return new StudentAttendanceBOImpl();
            case STUDENT_MARKS:
                return new StudentMarksBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            default:
                return null;
        }
    }

}
