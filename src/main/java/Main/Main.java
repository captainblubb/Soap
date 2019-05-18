package Main;

import example.AdditionService.Addition;
import example.HelloWorldService.HelloWorld;

public class Main {
    public static void main(String args[]){


      penAdditionService();

    }

    public static void testServices(){
        Addition.startService();
        HelloWorld.startService();
        helloCli helloClix = new helloCli("127.0.0.1");
        int counter = 0;
        while (true){

            try{
                try {
                    Thread.sleep(1000);
                }catch (Exception exp){

                }
                helloClix.askService();

            }catch (Exception e){
                System.out.println("Exception in askServcie "+e);
            }

        }
    }

    public static void penAdditionService(){
        Addition.startService();
        // HelloWorld.startService();
        //  helloCli helloClix = new helloCli("127.0.0.1");
        int counter = 0;
        while (true){

            try{
                try {
                    Thread.sleep(100+counter);
                }catch (Exception exp){

                }

                if (counter % 25 == 0)
                System.out.println("Threads "+ counter);

                counter++;
                System.out.println(counter);
                Thread thread = new Thread(new PenAddition());
                thread.start();
                // helloClix.askService();

            }catch (Exception e){
                System.out.println("Exception in askServcie "+e);
            }

        }
    }
}
