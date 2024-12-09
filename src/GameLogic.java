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
    private Money money;
    private int bet;








    /**
     * creates the object
     */
    public GameLogic() {
        scan = new Scanner(System.in);
        money = new Money();
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
            money.wonBet(bet);
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
            money.lostBet(bet);
        }else{
            System.out.println("Player draws a card value of " + card);
        }
    }


    /**
     * How many rounds are played
     * Where most of the game logic is
     */
    public void rounds(){
        int roundsPlayed = 0;
        bet = 0;
        String playAgain = "yes";

        Player player = new Player(name);

        while(playAgain.equals("yes")){
            System.out.print("How much do you wanna bet? ");
            bet = scan.nextInt();
            scan.nextLine();
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
                money.wonBet(bet);
            } else if (dealerTotal == 21) {
                System.out.println("Dealer wins!");
                money.lostBet(bet);
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
                    money.wonBet(bet);
                }
                if (getPlayerTotal() < 21) {
                    System.out.print("Do you want to stand? ");
                    String answer2 = scan.nextLine();
                    if (answer2.equals("yes")) {
                        while (dealerTotal < 17) {
                            stand();
                        }
                        if(dealerTotal == 21){
                            System.out.println("Dealer Wins!");
                            money.lostBet(bet);
                        }
                        if(dealerTotal > 21){
                            player.incrementScore();
                        }
                        if (dealerTotal < 21) {
                            System.out.println("Dealer hand value is " + dealerTotal);
                            if (playerTotal > dealerTotal) {
                                System.out.println("Player Wins!");
                                player.incrementScore();
                                money.wonBet(bet);
                            } else if (dealerTotal > playerTotal) {
                                System.out.println("Dealer Wins!");
                                money.lostBet(bet);
                            } else {
                                System.out.println("TIE");
                            }
                        }
                    }
                }
            }
            if(money.getTotalProfit() > 0) {
                System.out.println(player.getName() + " has " + player.getWins() + " number of wins and has won " + money.getTotalProfit() + " dollars");
            }else{
                System.out.println(player.getName() + " has " + player.getWins() + " number of wins and has lost " + money.getTotalProfit() + " dollars");
            }
            System.out.print("Do you want to play again? " );
            playAgain = scan.nextLine();
            roundsPlayed++;
        }
    }








    /** starts the game */
    public void start() {
        System.out.println("Welcome to BlackJack!");
        System.out.print("Enter your name: ");
        name = scan.nextLine();
        if(name.length() == 0){
            System.out.println("Please enter a name?");
            name = scan.nextLine();
        }


        rounds();

        System.out.println("Thank you for playing BLACKJACK!");
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

