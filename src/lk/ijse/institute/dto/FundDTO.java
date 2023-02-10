package lk.ijse.institute.dto;

public class FundDTO {
    double money;

    public FundDTO(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "money=" + money +
                '}';
    }
}
