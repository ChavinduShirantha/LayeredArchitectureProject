package lk.ijse.institute.entity;

/**
 * @author : Chavindu
 * created : 1/22/2023-3:32 PM
 **/
public class StudentAttendance {
    private String std_id;
    private String std_name;
    private String date;
    private String attendance;

    public StudentAttendance(String std_id, String std_name, String date, String attendance) {
        this.std_id = std_id;
        this.std_name = std_name;
        this.date = date;
        this.attendance = attendance;
    }

    public String getStd_id() {
        return std_id;
    }

    public void setStd_id(String std_id) {
        this.std_id = std_id;
    }

    public String getStd_name() {
        return std_name;
    }

    public void setStd_name(String std_name) {
        this.std_name = std_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "StudentAttendance{" +
                "std_id='" + std_id + '\'' +
                ", std_name='" + std_name + '\'' +
                ", date='" + date + '\'' +
                ", attendance='" + attendance + '\'' +
                '}';
    }
}
