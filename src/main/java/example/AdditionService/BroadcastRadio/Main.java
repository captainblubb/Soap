package example.AdditionService.BroadcastRadio;

public class Main {

    public static void main(String args[]){
        System.out.println("Implement me");


        //Start Radiothread
        Thread threadRadio = new Thread(new Radio());
        threadRadio.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Start RadioClient
        Thread threadRadioClient = new Thread(new RadioClient());
        threadRadioClient.start();

        while (true);

    }
}
