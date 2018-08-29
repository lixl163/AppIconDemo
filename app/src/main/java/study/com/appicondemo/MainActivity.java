package study.com.appicondemo;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 中国的传统节日有：春节，元宵节，清明节，端午节，七夕，中元节，中秋节，重阳节，冬至，腊八节。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //要跟manifest的activity-alias 的name保持一致
    private String tag0 = "study.com.appicondemo.MainActivity";  //默认应用界面入口
    private String tag1 = "study.com.appicondemo.icon_tag_1";
    private String tag2 = "study.com.appicondemo.icon_tag_2";
    private String tag3 = "study.com.appicondemo.icon_tag_3";
    private String tag4 = "study.com.appicondemo.icon_tag_4";
    private String tag5 = "study.com.appicondemo.icon_tag_5";
    private String tag6 = "study.com.appicondemo.icon_tag_6";
    private String tag7 = "study.com.appicondemo.icon_tag_7";
    private String tag8 = "study.com.appicondemo.icon_tag_8";
    private String tag9 = "study.com.appicondemo.icon_tag_9";
    private String tag10 = "study.com.appicondemo.icon_tag_10";

    private ImageView iv;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        httpGetIcon();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv9 = (TextView) findViewById(R.id.tv9);
        tv10 = (TextView) findViewById(R.id.tv10);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
        tv10.setOnClickListener(this);
    }

    /**
     * 网络请求，获取节日类型，显示对应的icon
     */
    private void httpGetIcon(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL("http://testapp.guoss.cn/gssapi/server/api.do?method=get_icon_gss_api");
                    URLConnection conn = url.openConnection();
                    conn.setConnectTimeout(5 * 1000);
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    final StringBuffer resultBuffer = new StringBuffer();
                    String tempLine = null;
                    while ((tempLine = reader.readLine()) != null) {
                        resultBuffer.append(tempLine);
                    }
                    Log.e("httpGetIcon", resultBuffer.toString());

                    final String result = resultBuffer.toString();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject object = new JSONObject(result);
                                String data = object.optString("data");
                                if (data.equals("default")){  //默认图片
                                    switchIcon(0);
                                } else if (data.equals("中秋节")){
                                    switchIcon(6);
                                } else if (data.equals("国庆节")){
                                    switchIcon(8);
                                }
                                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv1:
                switchIcon(1);
                iv.setBackgroundResource(R.mipmap.grade1);
                Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv2:
                switchIcon(2);
                iv.setBackgroundResource(R.mipmap.grade2);
                Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv3:
                switchIcon(3);
                iv.setBackgroundResource(R.mipmap.grade3);
                Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv4:
                switchIcon(4);
                iv.setBackgroundResource(R.mipmap.grade4);
                Toast.makeText(MainActivity.this, "4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv5:
                switchIcon(5);
                iv.setBackgroundResource(R.mipmap.grade5);
                Toast.makeText(MainActivity.this, "5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv6:
                switchIcon(6);
                iv.setBackgroundResource(R.mipmap.grade6);
                Toast.makeText(MainActivity.this, "6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv7:
                switchIcon(7);
                iv.setBackgroundResource(R.mipmap.grade7);
                Toast.makeText(MainActivity.this, "7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv8:
                switchIcon(8);
                iv.setBackgroundResource(R.mipmap.grade8);
                Toast.makeText(MainActivity.this, "8", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv9:
                switchIcon(9);
                iv.setBackgroundResource(R.mipmap.grade9);
                Toast.makeText(MainActivity.this, "9", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv10:
                switchIcon(10);
                iv.setBackgroundResource(R.mipmap.grade10);
                Toast.makeText(MainActivity.this, "10", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * @param useCode 哪个节日
     */
    private void switchIcon(int useCode) {
        //做一个控制，服务器端设置一个开关，
        //当请求到要更改桌面图标时，我们就可以通过 PackageManager 对象提供的 setComponentEnabledSetting()方法关闭当前 Component 组件，
        //并启动别名对应的 Component 组件即可，为了使得图标能够快速更换，我们可以加上重启Luncher应用代码，name是自己定义个类名，记住一定要传全路径
        //①获取包资源管理器
        PackageManager pm = getPackageManager();
        //②关闭当前的组件（不显示）
        pm.setComponentEnabledSetting(getComponentName(),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        //③获取组件名称，根据清单中的name字段
        ComponentName normalComponentName = null;
        //④正常图标新状态，此处使用用来修改清单文件中activity-alias下的android:enable的值
        int normalNewState = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
        switch (useCode){
            case 1:
                normalComponentName = new ComponentName(getBaseContext(), tag1);
                break;
            case 2:
                normalComponentName = new ComponentName(getBaseContext(), tag2);
                break;
            case 3:
                normalComponentName = new ComponentName(getBaseContext(), tag3);
                break;
            case 4:
                normalComponentName = new ComponentName(getBaseContext(), tag4);
                break;
            case 5:
                normalComponentName = new ComponentName(getBaseContext(), tag5);
                break;
            case 6:
                normalComponentName = new ComponentName(getBaseContext(), tag6);
                break;
            case 7:
                normalComponentName = new ComponentName(getBaseContext(), tag7);
                break;
            case 8:
                normalComponentName = new ComponentName(getBaseContext(), tag8);
                break;
            case 9:
                normalComponentName = new ComponentName(getBaseContext(), tag9);
                break;
            case 10:
                normalComponentName = new ComponentName(getBaseContext(), tag10);
                break;
            default:
                normalComponentName = new ComponentName(getBaseContext(), tag0);
                break;
        }

        //⑤新状态跟当前状态不一样才执行
        if (pm.getComponentEnabledSetting(normalComponentName) != normalNewState) {
            //PackageManager.DONT_KILL_APP表示执行此方法时不杀死当前的APP进程
            pm.setComponentEnabledSetting(
                    normalComponentName,
                    normalNewState,
                    PackageManager.DONT_KILL_APP);
        }
    }
}
