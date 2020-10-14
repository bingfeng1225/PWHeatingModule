package cn.haier.bio.medical.heating;

/***
 * 超低温变频、T系列、双系统主控板通讯
 *
 */
public class HeatingManager {
    private HeatingSerialPort serialPort;
    private static HeatingManager manager;

    public static HeatingManager getInstance() {
        if (manager == null) {
            synchronized (HeatingManager.class) {
                if (manager == null)
                    manager = new HeatingManager();
            }
        }
        return manager;
    }

    private HeatingManager() {

    }

    public void init(String path) {
        if(this.serialPort == null){
            this.serialPort = new HeatingSerialPort();
            this.serialPort.init(path);
        }
    }

    public void enable() {
        if(null != this.serialPort){
            this.serialPort.enable();
        }
    }

    public void disable() {
        if(null != this.serialPort){
            this.serialPort.disable();
        }
    }

    public void release() {
        if(null != this.serialPort){
            this.serialPort.release();
            this.serialPort = null;
        }
    }

    public void changeListener(IHeatingListener listener) {
        if(null != this.serialPort){
            this.serialPort.changeListener(listener);
        }
    }
}

