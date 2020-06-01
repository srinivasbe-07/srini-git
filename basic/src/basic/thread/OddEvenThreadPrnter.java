package basic.thread;

public class OddEvenThreadPrnter {
    private final int max;
    private int count = 1;
    private boolean isOddTurn = true;

    public OddEvenThreadPrnter(int max) {
        this.max = max;
    }

    public void   printOddNumber(){
            synchronized (this){

                while(count < max)
                {
                    while (!isOddTurn){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                        System.out.println(count++);
                        isOddTurn = false;
                        notify();
                }
            }
        }

        public void printEvenNumber(){
            synchronized (this){
                while(count < max)
                {
                    while (isOddTurn){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                        System.out.println(count++);
                        isOddTurn = true;
                        notify();
                }
            }

        }

    public static void main(String[] args) {
        OddEvenThreadPrnter oddEvenThreadPrnter = new OddEvenThreadPrnter(10);
        Thread oddThread = new Thread(()->oddEvenThreadPrnter.printOddNumber());
        Thread eventThread = new  Thread(()->oddEvenThreadPrnter.printEvenNumber());
        oddThread.start();
        eventThread.start();
        try {
            oddThread.join();
            eventThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
