package cn.haier.bio.medical.heating;

public interface IHeatingListener {
    void onHeatingReady();
    void onHeatingConnected();
    void onHeatingSwitchWriteModel();
    void onHeatingSwitchReadModel();
    void onHeatingPrint(String message);
    void onHeatingSystemChanged(int type);
    void onHeatingDataChanged(byte[] data);
    byte[] packageHeatingResponse(int type);
    void onHeatingException(Throwable throwable);
}
