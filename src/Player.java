public class Player {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.money = 100;
    }
    public Player(String username, String password,int money) {
        this.username = username;
        this.password = password;
        this.money = money;
    }

    int money;

    public String toString(){
        return username + "/" + password + "/" + money;
    }

}
