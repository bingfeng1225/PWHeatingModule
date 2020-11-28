package cn.qd.peiwen.demo;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import cn.haier.bio.medical.heating.HeatingManager;
import cn.haier.bio.medical.heating.IHeatingListener;
import cn.qd.peiwen.serialport.PWSerialPort;

public class MainActivity extends AppCompatActivity implements IHeatingListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String path = "/dev/ttyS4";
        if ("magton".equals(Build.MODEL)) {
            path = "/dev/ttyS2";
        }
        HeatingManager.getInstance().init(path);
        HeatingManager.getInstance().changeListener(this);
        HeatingManager.getInstance().enable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HeatingManager.getInstance().disable();
        HeatingManager.getInstance().release();
    }

    @Override
    public void onHeatingReady() {
        Log.e("Main","Heating Ready");
    }

    @Override
    public void onHeatingConnected() {
        Log.e("Main","Heating Connected");
    }

    @Override
    public void onHeatingSwitchWriteModel() {
        Log.e("Main","Heating Switch Write Model");
        if (!"magton".equals(Build.MODEL)) {
            PWSerialPort.writeFile("/sys/class/gpio/gpio24/value", "0");
        } else {
            PWSerialPort.writeFile("/sys/class/misc/sunxi-acc/acc/sochip_acc", "1");
        }
    }

    @Override
    public void onHeatingSwitchReadModel() {
        Log.e("Main","Heating Switch Read Model");
        if (!"magton".equals(Build.MODEL)) {
            PWSerialPort.writeFile("/sys/class/gpio/gpio24/value", "1");
        } else {
            PWSerialPort.writeFile("/sys/class/misc/sunxi-acc/acc/sochip_acc", "0");
        }
    }

    @Override
    public void onHeatingPrint(String message) {
        Log.e("Main","" + message);
    }

    @Override
    public void onHeatingSystemChanged(int type) {
        Log.e("Main","Heating System Changed " + type);
    }

    @Override
    public void onHeatingDataChanged(byte[] data) {

    }

    @Override
    public byte[] packageHeatingResponse(int type) {
        return new byte[0];
    }

    @Override
    public void onHeatingException(Throwable throwable) {
        throwable.printStackTrace();
    }
}
