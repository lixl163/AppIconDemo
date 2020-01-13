# AppIconDemo
Android 应用图标动态改变，兼容targetAPI29


步骤：

### 1. 在AndroidManifest.xml中添加<activity-alias>标签，代码如下所示：
~~~  
<application
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:supportsRtl="true"
    android:theme="@style/AppTheme">
    <activity android:name=".MainActivity">
        <intent-filter>
            <action android:name="android.intent.action.MAIN"/>
            <category android:name="android.intent.category.LAUNCHER"/>
        </intent-filter>
    </activity>
    <activity-alias
        android:name="RoundActivity"
        android:enabled="false"
        android:icon="@mipmap/ic_launcher_round"
        android:targetActivity=".MainActivity">
        <intent-filter>
            <action android:name="android.intent.action.MAIN"/>
            <category android:name="android.intent.category.LAUNCHER"/>
        </intent-filter>
    </activity-alias>
</application>  
~~~


这个<activity-alias>标签需要注意的地方如下： 
（1）android:name属性可以随意起。 
（2）android:enabled属性要设为false，否则桌面会存在多个APP图标。 
（3）android:icon属性设置为不同的图标。 
（4）android:targetActivity属性要设为启动的Activity。 
（5）添加<intent-filter>那部分来使其作为启动的Activity。 
 
 
### 2. 我们在布局文件中创建两个按钮，用来切换不同的图标，代码如下：
~~~
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <Button
        android:id="@+id/btn_round_icon"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="切换圆形图标"/>
    <Button
        android:id="@+id/btn_primitive_icon"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="切换原始图标"/>
</LinearLayout> 
~~~
 

### 3. 在Java代码中切换<activity>和<activity-alias>的使能状态，代码如下：
~~~
private void setRoundIcon() {
    PackageManager packageManager = getPackageManager();
    packageManager.setComponentEnabledSetting(new ComponentName(this, getPackageName() +
            ".MainActivity"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager
            .DONT_KILL_APP);
    packageManager.setComponentEnabledSetting(new ComponentName(this, getPackageName() +
            ".RoundActivity"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager
            .DONT_KILL_APP);
}
private void setPrimitiveIcon() {
    PackageManager packageManager = getPackageManager();
    packageManager.setComponentEnabledSetting(new ComponentName(this, getPackageName() +
            ".RoundActivity"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP);
    packageManager.setComponentEnabledSetting(new ComponentName(this, getPackageName() +
            ".MainActivity"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager
            .DONT_KILL_APP);
}
~~~
注意setComponentEnabledSetting()方法的第3个参数有两个值供选择：1（也就是PackageManager.DONT_KILL_APP）和0。
这两种参数对应两种效果：
  当设为1时，当切换APP图标时，会有几秒钟的延迟，并且在延迟期间不能点击图标进入APP；
  当设为0时，当切换APP图标时，会立刻更换，但是应用会被强制退出并被清理掉。
