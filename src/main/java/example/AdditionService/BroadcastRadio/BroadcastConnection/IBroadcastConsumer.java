package example.AdditionService.BroadcastRadio.BroadcastConnection;

import example.AdditionService.BroadcastRadio.SOAPRegisterService.IRegisterListener;

public interface IBroadcastConsumer {

    public void notifyListener(String message);
    public void addIBroadcastListener(IBroadcastListener broadcastListener);
}
