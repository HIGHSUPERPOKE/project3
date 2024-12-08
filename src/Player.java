public class Player {
    private int wins;
    private String name;


    public Player(String name){
        this.name = name;
        wins = 0;
    }


    public String getName() {
        return name;
    }


    public int getWins(){
        return wins;
    }


    public void incrementScore() {
        wins++;
    }
}

