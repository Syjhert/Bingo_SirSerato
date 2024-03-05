package Bingo;

public abstract class BingoChecker implements Runnable {
    protected BingoCard card;
    protected BingoGame game;
    public BingoChecker(BingoCard card){
        this.card = card;
    }
}