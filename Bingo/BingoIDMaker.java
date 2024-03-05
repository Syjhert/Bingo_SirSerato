package Bingo;

public class BingoIDMaker {
    private static int id = 1;
    public BingoIDMaker(){

    }

    public static int getNewID(){
        id++;
        return id-1;
    }
}
