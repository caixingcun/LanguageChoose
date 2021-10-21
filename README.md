# LanguageChoose 国际化简单封装


## ------------------------------- version 1.0 . without application extends

# 2.1 dependencies
~~~
   project build.gradle 
   
    allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
	
	app  build.gradle
  
  	dependencies {
	        implementation 'com.github.caixingcun:LanguageChoose:v2.0'
	}
~~~

# 2.2 init in Application
~~~
    	
    // define your language resource package
    
    public static String[] languages = {"CN", "HK", "TW", "EN"};
    public static Locale[] locates = {Locale.CHINA, Locale.TRADITIONAL_CHINESE, Locale.TAIWAN, Locale.ENGLISH};

    // oncreate init languages
    @Override
    public void onCreate() {
        super.onCreate();
        LanguageDelegate.getInstance().init(Arrays.asList(languages),Arrays.asList(locates));
    }
    
    
    @Override
    protected void attachBaseContext(Context base) {
        LanguageDelegate.getInstance().upDataLocate(base);
        super.attachBaseContext(base);
    }
    
    // when config changed ，update language 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LanguageDelegate.getInstance().upDataLocate(this);
    }
~~~

# 2.3 switch language
~~~
   // update config in switch page 
     @Override
    protected void attachBaseContext(Context newBase) {
        LanguageDelegate.getInstance().upDataLocate(newBase)
        super.attachBaseContext(newBase);
    }
   
    // switch 
    LanguageDelegate.getInstance().switchLanguage(mActivity, App.languages[3]);
~~~



##---------------------------- version 1.0 . need extends application

# 1.添加依赖  Android Studio 导入
~~~
  Project下build.gradle
  allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
  
  app build.gradle
  
  	dependencies {
	        implementation 'com.github.caixingcun:LanguageChoose:1.0'
	}
~~~
  
# 2. create Application extends  LanguageBaseApp
  
~~~
    // create locates depend on your language resource package
    
    public static String[] languages = {"CN", "HK", "TW", "EN"};
    public static Locale[] locates = {Locale.CHINA, Locale.TRADITIONAL_CHINESE, Locale.TAIWAN, Locale.ENGLISH};

    static {
        setLanguage(Arrays.asList(languages));
        setmLocates(Arrays.asList(locates));
    }
    
~~~

# 3. in switch page ,override attachBaseContext 
~~~
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageUtil.upDataLocate(newBase));
    }
~~~
 
#  4.switch language
~~~
    // switch language 
    LanguageUtil.switchLanguage(mActivity, App.languages[0]); 
    
    // restart your page to reconfig your resource
      startActivity(new Intent(mActivity,MainActivity.class));
      finish();
~~~

  
  






  
