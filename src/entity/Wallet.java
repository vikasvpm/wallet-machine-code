package entity;

public class Wallet {
    private String  username;
    private Integer balanceAmount;
    public Integer increaseAmount(Integer byAmount) {
        this.balanceAmount += byAmount;
        return this.balanceAmount;
    }
    public Integer decreaseAmount(Integer byAmount) {

        this.balanceAmount -= byAmount;
        return this.balanceAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Integer balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
}
