/**
 * This class is the game logic
 *
 * @author Alex Liu
 */




import java.util.Scanner;




public class GameLogic {








    /**
     * The total sum of the dealer's hand
     */
    private int dealerTotal = 0;








    /**
     * The total sum of the player's hand
     */
    private int playerTotal = 0;








    private Scanner scan;
    private String name;








    /**
     * creates the object
     */
    public GameLogic() {
        scan = new Scanner(System.in);
    }








    /**
     * The total sum of the dealer's hand
     *
     * @return the dealer total
     */
    public int getDealerTotal() {
        return dealerTotal;
    }








    /**
     * The total sum of the player's hand
     *
     * @return the player total
     */
    public int getPlayerTotal() {
        return playerTotal;
    }








    public void stand() {
        int card = dealerDraw();
        if (dealerTotal > 21){
            System.out.println("Dealer draws a card value of " + card);
            System.out.println("BUST");
            System.out.println("Player wins!");
        }else{
            System.out.println("Dealer draws a card value of " + card);
        }
    }








    public void hit() {
        int card = playerDraw();
        if(playerTotal > 21){
            System.out.println("Player draws a card value of " + card);
            System.out.println("BUST");
            System.out.println("Dealer wins!");
        }else{
            System.out.println("Player draws a card value of " + card);
        }
    }




    public void rounds(){
        String playAgain = "yes";
        Player player = new Player(name);
        while(playAgain.equals("yes")){
            playerTotal = 0;
            dealerTotal = 0;
            System.out.println("Drawing two cards for dealer");
            dealerDraw();
            dealerDraw();
            System.out.println("Dealer hand value = " + getDealerTotal());
            System.out.println("Drawing two cards for the player");
            playerDraw();
            playerDraw();
            if (playerTotal == 21) {
                System.out.println("Player wins!");
                player.incrementScore();
            } else if (dealerTotal == 21) {
                System.out.println("Dealer wins!");
            } else {
                System.out.println("Player hand value = " + getPlayerTotal());
                System.out.print("Do you want to hit? ");
                String answer = scan.nextLine();
                while (!answer.equals("no")) {
                    hit();
                    if (getPlayerTotal() >= 21) {
                        break;
                    }
                    System.out.println("Player total after hitting is " + getPlayerTotal());
                    System.out.print("Do you want to hit again? ");
                    answer = scan.nextLine();
                }
                if (getPlayerTotal() == 21) {
                    System.out.println("Player wins");
                    player.incrementScore();
                }
                if (getPlayerTotal() < 21) {
                    System.out.print("Do you want to stand? ");
                    String answer2 = scan.nextLine();
                    if (answer2.equals("yes")) {
                        while (dealerTotal < 17) {
                            stand();
                        }
                        if (dealerTotal < 21) {
                            System.out.println("Dealer hand value is " + dealerTotal);
                            if (playerTotal > dealerTotal) {
                                System.out.println("Player Wins!");
                                player.incrementScore();
                            } else if (dealerTotal > playerTotal) {
                                System.out.println("Dealer Wins!");
                            } else {
                                System.out.println("TIE");
                            }
                        }
                    }
                }
            }
            System.out.println(player.getName() + " has " + player.getWins() + " number of wins");
            System.out.print("Do you want to play again? " );
            playAgain = scan.nextLine();
        }
    }








    /** starts the game */
    public void start() {
        System.out.println("Welcome to BlackJack!");
        System.out.print("Enter your name: ");
        name = scan.nextLine();


        rounds();
    }


    /** dealer draws a card */
    public int dealerDraw(){
        int card = drawCard();
        if(card == 1){
            if(dealerTotal + 11 < 21) {
                card = 11;
            }
        }
        dealerTotal += card;
        return card;
    }








    /** player draws a card */
    public int playerDraw(){
        int card = drawCard();
        if(card == 1){
            if(playerTotal + 11 < 21){
                card = 11;
            }
        }
        playerTotal += card;
        return card;
    }








    /**
     *  draws a random card out of a regular deck of cards
     *  @return the value of the random card selected
     */
    private int drawCard() {
        int num = 0;
        num = (int) (Math.random() * 10) + 1;
        if (num == 1) {
            System.out.println("Draws a Ace");
        }else if (num == 10) {
            int num2 = (int) (Math.random() * 4) + 1;
            if (num2 == 1) {
                System.out.println("Draws a 10");
            } else if (num2 == 2) {
                System.out.println("Draws a Jack");
            } else if (num2 == 3) {
                System.out.println("Draws a Queen");
            } else if (num2 == 4) {
                System.out.println("Draws a King");
            }
        }else{
            System.out.println("Draws a " + num);
        }
        return num;
    }
}

