package Bingo;

public class BingoColumnChecker extends BingoChecker{
    private int column;

    public BingoColumnChecker(BingoCard card, int column) {
        super(card);
        this.column = column-1;
    }

    @Override
    public void run() {
        boolean done = false;
        for(int i=0; i<5; i++){
            int num = card.getNumbers()[i][column];
            if(num == 0) continue;

            while(!BingoGame.results[num] && !BingoGame.bingo){
                try {
                    synchronized (BingoGame.results){
                        BingoGame.results.wait();
                    }
                } catch (InterruptedException e) {

                }
            }
            if(BingoGame.bingo) break;
            if(i==4) done = true;
        }
        if(done) System.out.println("Card" + card + " - completes column " + (column+1));
    }
}