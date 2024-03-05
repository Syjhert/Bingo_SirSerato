package Bingo;

import java.util.ArrayList;
import java.util.List;

public abstract class BingoPattern implements Runnable{
    static boolean done = false;
    protected List<BingoChecker> bingoCheckers;
    private BingoCard card;

    public BingoPattern(BingoCard card) {
        bingoCheckers = new ArrayList<>();
        this.card = card;
    }
    @Override
    public void run() {
        List<Thread> threads = new ArrayList<>();
        for(BingoChecker checker : bingoCheckers) {
            threads.add(new Thread(checker));
        }
        for(Thread thrd : threads){
            thrd.start();
        }
        for(Thread thrd : threads){
            try {
                thrd.join();
            } catch (InterruptedException e) {

            }
        }

        if(BingoGame.bingo != true){
            System.out.println("Card " + card + " completes pattern!");
            card.print();
            BingoGame.bingo = true;
        }
    }
}