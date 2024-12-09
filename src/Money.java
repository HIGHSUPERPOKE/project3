public class Money {
    private int totalProfit;

    public Money(){}

    public int getTotalProfit() {
        return totalProfit;
    }

    public void wonBet(int totalBet){
        totalProfit += totalBet;
    }

    public void lostBet(int totalBet){
        totalProfit -= totalBet;
    }
}
