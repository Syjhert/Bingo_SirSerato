package Bingo;
public class BingoPatternHash extends BingoPattern{

    public BingoPatternHash(BingoCard card) {
        super(card);
        bingoCheckers.add(new BingoColumnChecker(card, 2));
        bingoCheckers.add(new BingoColumnChecker(card, 4));
        bingoCheckers.add(new BingoRowChecker(card, 2));
        bingoCheckers.add(new BingoRowChecker(card, 4));
    }
}