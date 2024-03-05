package Bingo;
public class BingoPatternPlus extends BingoPattern{
    public BingoPatternPlus(BingoCard card) {
        super(card);
        bingoCheckers.add(new BingoColumnChecker(card, 3));
        bingoCheckers.add(new BingoRowChecker(card, 3));
    }
}