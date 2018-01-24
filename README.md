
<h1 style="text-align:center">Android SDK 接入指南</h1>

### 1、集成前准备
<a href="https://www.baidu.com">添加新应用，获得Apikey.</a>
### 2、添加依赖
```java
compile ("com.igeshui.sdk.tax:taxsdklibrary:0.0.9", {
    exclude group: "com.android.support"
})
```
### 3、加入权限
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
```


### 4、使用
##### 4.1 在Application 的onCreate 方法中进行初始化
```java
TaxLib.getInstance().init(this, new MsgCallback() {
    @Override
    public void loginSuccess(String msg) {
		//个税账户登陆成功返回信息
    }

    @Override
    public void failed(String msg) {
		//发生错误返回信息
    }
});
```
##### 4.2 进入sdk前进行授权
apiKey：sdk授权码<br/>
userId：用户唯一标识
```java
TaxLib.getInstance().authorization(apiKey, userId, new AuthorizationCallback() {
    @Override
    public void success() {
        //授权成功回调
    }

    @Override
    public void failed(String msg) {
        //授权失败回调
    }
});
```
##### 4.3进入sdk页面


SDK有授权和查询两种模式
###### 授权
每次进入SDK都是个税登录页
###### 查询
若当前用户登录过个税账户则进入个税账户详情页，若未登录过则进入个税登录也

```java
TaxLib.getInstance().enterTaxSDK(activity, null, null);
```
    或者
```java
TaxLib.getInstance().enterTaxSDK(activity, "厦门", "350200");
```
enterTaxSDK方法参数：activity，城市名称，城市ID

##### 4.4自定义颜色
 res下colors文件添加
```xml
 <color name="TaxSdkColor_Primary">#06CFC5</color>
 <color name="TaxSdkColor_Background">#e9e9e9</color>
```
TaxSdkColor_Primary为主题延时<br/>
TaxSdkColor_Background为背景颜色

##### 4.5自定义图片资源
 在External Libraries下找到taxsdkLibrary-0.0.4替换res下mipmap中的图片资源<br>
<img src="mipmap.png"></img>
### 5、APP打包混淆
```java
-keep class com.igeshui.sdk.tax.** {*;}
```
### 6、FAQ
##### 6.1、com.android.dex.DexIndexOverflowException: method ID not in [0, 0xffff]: 65536

###### 6.1.1multiDexEnabled true
```gradle
defaultConfig {  
      applicationId "com.xxx.xxx"  
      minSdkVersion 18  
      targetSdkVersion 25  
      versionCode 1  
      versionName "1.0"  
      multiDexEnabled true  
  } 
 ```
###### 6.1.2添加依赖
```
 dependencies {  
    compile 'com.android.support:multidex:1.0.1'  
}  
``` 
###### 6.1.3
让应用的中自己定义的Application继承MultiDexApplication
```java
public class MyApplication extends MultiDexApplication{  
……  
}  
```
或者重写Application 的attachBaseContext方法，这个方法是在onCreate之前执行的
```java
public class MyApplication extends Application{  
  
@Override  
    protected void attachBaseContext(Context base) {  
        super.attachBaseContext(base);  
        MultiDex.install(this);  
    }  
}  
```

