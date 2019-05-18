package example.AdditionService.BroadcastRadio.SOAPRegisterService;

public interface IRegisterProvider {
    public void notifyListener(String serviceTyp,String url);
    public void addIRegisterListener(IRegisterListener iRegisterListener);
}
