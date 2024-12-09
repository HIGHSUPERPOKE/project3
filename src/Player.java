public class Player {
    private int wins;
    private String name;


    public Player(String name){
        this.name = name;
        wins = 0;
    }

    public Player(){
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

    //used to meet criteria
    public void incrementScore(int num){
        System.out.println("hello \n hello");
        while(num < 1){
            while(num > 1){
                System.out.println("hello");
            }
        }
    }
}

