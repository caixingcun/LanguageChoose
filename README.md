# LanguageChoose
国际化简单封装


1.添加依赖
Android Studio 导入
  Project下build.gradle
  allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
  
  app 下build.gradle
  
  	dependencies {
	        implementation 'com.github.caixingcun:LanguageChoose:1.0'
	}
  
2. 创建Application 继承  LanguageBaseApp
  
  在Application 下 实现静态代码块，初始化 自己资源包中创建的几个 values包 
  
    public static String[] languages = {"CN", "HK", "TW", "EN"};
    public static Locale[] locates = {Locale.CHINA, Locale.TRADITIONAL_CHINESE, Locale.TAIWAN, Locale.ENGLISH};

    static {
        setLanguage(Arrays.asList(languages));
        setmLocates(Arrays.asList(locates));
    }
    
3.BaseActivity 中重写 attachBaseContext 方法
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageUtil.upDataLocate(newBase));
    }
 
 4.切换语言 代码
    LanguageUtil.switchLanguage(mActivity, App.languages[0]);  //这里选择了 Application 初始化的第一种语言
    
    因为需要重新渲染页面，需要刷新当前界面
      startActivity(new Intent(mActivity,MainActivity.class));
      finish();

  
  
  
