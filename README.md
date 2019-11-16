# Gamiphy SDK

[Gamiphy SDK Jitpack Link](https://jitpack.io/#gamiphy/android-sdk)

## Inroduction 

Gamibot, is the loyality program that provide gamified user journey, with rewarding system, where users can get points by doing certine actions. and they 
can be rewarded for doing these actions. 

## Requirements

- Android Studio 3.1+
- Java 1.8
- Androidx

## Installation

gamiphy is available through [JitPack](https://jitpack.io/#gamiphy/android-sdk). To install
it, simply add the dependency for the Gamiphy SDK in your module (app-level) Gradle file (usually app/build.gradle):

```gradle
       implementation 'com.github.gamiphy:android-sdk:1.0.1'
```

and make sure you have jitpack in your root-level (project-level) Gradle file (build.gradle), 
```gradle
   allprojects {
    repositories {
        google()
        jcenter()
        // add jitpack if it's not added
        maven { url 'https://jitpack.io' }
    }
}
```

## Getting started

Gamiphy SDK needs to be initialized in Application class, you can do that by calling the init methid as shown below, and pass some required data / parameters that 
you can get after you signup for an account at Gamiphy. kinldy note the initilize method below. 

```kotlin
      GamiphySDK.getInstance().init(botId)
```
And you can set Debug mode.
```kotlin
      GamiphySDK.getInstance().setDebug(true)
```

## Showing the bot within your application

Gamibot can be triggered and shown in two methods. 

- If you are interested to use the widget that Gamiphy SDK provides, this widget will handle opening the bot within the web view.
you just need to add the widget button to your xml layout

```xml  
        <com.gamiphy.library.utils.GamiphyBotButton
        android:layout_width="70dp"
        android:layout_height="70dp" />
```

- If you are interested on having your own widget/button that will be repsonsible to open the bot, or you want to open the bot after a certin action. you can do so by calling the following method: 

```Kotlin
    GamiphySDK.getInstance().open(context)
```

## Bot visitor flow 

Gamibot support the ability for the end users to navigate the different features available, without even being logged in. but whenever 
the users trying to perform the tasks / actions so they can get the points, Gamibot will encourage them to either login or signup to the application. 

You need to specify the Activity where the users can login / register in your application. you should implement OnAuthTrigger by doing as the following: 

```Kotlin
GamiphySDK.getInstance().registerGamiphyOnAuthTrigger(object : OnAuthTrigger {
            override fun onAuthTrigger(signUp: Boolean) {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }
        }
        )
```
OnAuthTrigger method called when click signUp/login in the bot. isSignUp true for signup redirection, isSignUp false for login redirection.

In login activity, after the user logged in, set user name and email and start gamiphy view
```kotlin
 GamiphySDK.getInstance().login(User(email,name,hash))
        GamiphySDK.getInstance()..open(this)
```

Gamiphy SDK Listeners:

- OnTaskTrigger: this listener has onTaskTrigger method,this method called when click get on custom tasks.
```kotlin
 gamiBot.registerGamiphyOnTaskTrigger(object : OnTaskTrigger {
            override fun onTaskTrigger(actionName: String) {
                Log.d(MainActivity::class.java.simpleName, "here is action name $actionName")
            }
        })
```

- OnAuthTrigger: this listener has OnAuthTrigger method, this method called when the bot requires login/signup for the user or the login button inside bot clicked.
 isSignUp true for signup redirection, isSignUp false for login redirection.
```kotlin
GamiBot.getInstance().registerGamiphyOnAuthTrigger(object : OnAuthTrigger {
            override fun onAuthTrigger(signUp: Boolean) {
             //make your action here, you may start login activity
                if (!signUp) {
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                }
            }
        }
        )
```

- OnRedeemTrigger: this listener has onRedeemTrigger method, this method Called when redeem clicked in the bot. 
rewardId is the reward object want to redeem.
```kotlin
 gamiBot.registerGamiphyOnRedeemTrigger(object : OnRedeemTrigger {
            override fun onRedeemTrigger(rewardId: String) {
                Log.d(MainActivity::class.java.simpleName, "here is reward Id  $rewardId")
                GamiBot.getInstance().markRedeemDone(rewardId)
            }
        })
```

## Registering the users

As Gamibot is a loyality program that should be able to give points for the users, you can simply register your users for our SDK by calling this method. 

```kotlin
   GamiBot.getInstance().login(User(email,name,hash))
```

you need to call this method in both cases the login / signup if you do instant login of your users after they login/signup. 


## Creating the tasks: 


You need to send the custom event actions whenever its done using the method markTaskDone shown below.
This method take the event name label and mark it as done.

```kotlin
GamiBot.getInstance().markTaskDone(actionName)
```

