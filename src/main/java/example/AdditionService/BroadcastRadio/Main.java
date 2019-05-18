package example.AdditionService.BroadcastRadio;

public class Main {

    public static void main(String args[]){
        System.out.println("Implement me");


        //Start Radiothread
        Thread threadRadio = new Thread(new Radio());
        threadRadio.start();

        //Start RadioClient
        Thread threadRadioClient = new Thread(new RadioClient());
        threadRadioClient.start();

    }
}
