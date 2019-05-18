package Main;

import example.AdditionService.Addition;
import example.HelloWorldService.HelloWorld;

public class Main {
    public static void main(String args[]){


        Addition.startService();
        HelloWorld.startService();
        helloCli helloClix = new helloCli("127.0.0.1");
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
}
