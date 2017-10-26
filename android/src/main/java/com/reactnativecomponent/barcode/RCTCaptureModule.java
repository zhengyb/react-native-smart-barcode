package com.reactnativecomponent.barcode;

import android.os.Environment;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.zxing.BarcodeFormat;
import com.reactnativecomponent.barcode.decoding.DecodeUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class RCTCaptureModule extends ReactContextBaseJavaModule {
    public static final int RCT_CAMERA_TORCH_MODE_OFF = 0;
    public static final int RCT_CAMERA_TORCH_MODE_ON = 1;
    public static final int RCT_CAMERA_TORCH_MODE_AUTO = 2;

    private ReactApplicationContext mContext;
    RCTCaptureManager captureManager;


    public RCTCaptureModule(ReactApplicationContext reactContext, RCTCaptureManager captureManager) {
        super(reactContext);
        mContext = reactContext;

        this.captureManager = captureManager;

    }

    @Override
    public String getName() {
        return "CaptureModule";
    }

//    public void sendMsgToRn(String msg) {
//        //将消息msg发送给RN侧
//        mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("AndroidToRNMessage", msg);
//
//    }



    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() {
            {
                put("barCodeTypes", getBarCodeTypes());
                put("torchMode", getTorchMode());
            }
            private Map<String, Object> getBarCodeTypes() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("upce", BarcodeFormat.UPC_E.toString());
                        put("code39", BarcodeFormat.CODE_39.toString());
//                        put("code39mod43",BarcodeFormat. );
                        put("ean13",BarcodeFormat.EAN_13.toString() );
                        put("ean8",BarcodeFormat.EAN_8.toString() );
                        put("code93", BarcodeFormat.CODE_93.toString());
                        put("code128", BarcodeFormat.CODE_128.toString());
                        put("pdf417",BarcodeFormat.PDF_417.toString() );
                        put("qr",BarcodeFormat.QR_CODE.toString() );
                        put("aztec", BarcodeFormat.AZTEC.toString());
//                        put("interleaved2of5", BarcodeFormat.);
                        put("itf14",BarcodeFormat.ITF.toString());
                        put("datamatrix", BarcodeFormat.DATA_MATRIX.toString());
                    }


                });
            }

            private Map<String, Object> getTorchMode() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("off", RCT_CAMERA_TORCH_MODE_OFF);
                        put("on", RCT_CAMERA_TORCH_MODE_ON);
                        put("auto", RCT_CAMERA_TORCH_MODE_AUTO);
                    }
                });
            }

        });
    }



    @ReactMethod
    public void startSession() {

        if (captureManager.cap != null) {
            getCurrentActivity().runOnUiThread(new Runnable() {
                public void run() {
                    captureManager.cap.startQR();
                    captureManager.cap.OpenFlash();
//                    captureManager.cap.startScan();
//                    Toast.makeText(getCurrentActivity(), "startScan", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @ReactMethod
    public void stopSession() {
        if (captureManager.cap != null) {
            getCurrentActivity().runOnUiThread(new Runnable() {
                public void run() {
//                    captureManager.cap.stopQR();
                    captureManager.cap.stopScan();
                    captureManager.cap.CloseFlash();
//                    Toast.makeText(getCurrentActivity(), "stopScan", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @ReactMethod
    public void stopFlash() {
        if (captureManager.cap != null) {
            getCurrentActivity().runOnUiThread(new Runnable() {
                public void run() {
                    captureManager.cap.CloseFlash();
//                    Toast.makeText(getCurrentActivity(), "stopFlash", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @ReactMethod
    public void startFlash() {
        if (captureManager.cap != null) {
            getCurrentActivity().runOnUiThread(new Runnable() {
                public void run() {
                    captureManager.cap.OpenFlash();
//                    Toast.makeText(getCurrentActivity(), "startFlash", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @ReactMethod
    public void DecodeFromPath(final String path,
                              final Callback errorCallback,
                               final Callback successCallback) {

            new Thread(new Runnable() {
                public void run() {
                    try {
                    String s = Environment.getExternalStorageDirectory()
                            .getAbsolutePath() + "/" + "IMG_20161011_170552.jpg";
                    //不加这个分号则不能自动添加代码

                    String ResultStr = DecodeUtil.getStringFromQRCode(s);
                        successCallback.invoke(ResultStr);

                    } catch (Exception e) {
                        e.printStackTrace();
                        errorCallback.invoke(e.getMessage());
                    }
                }
            }).start();
//        Toast.makeText(getCurrentActivity(), "DecodeFromPath:"+path, Toast.LENGTH_SHORT).show();

    }


/*
    @ReactMethod
    public void changeWidthHeight(final int width,final int height) {

        if (captureManager.cap != null) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    captureManager.cap.setCHANGE_WIDTH(width, height);
                }
                });
        }
    }*/
}
