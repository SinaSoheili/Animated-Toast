# Animated-Toast
Animate Toast crated to show a toast with animation and avatar

you can see a sample of this library 

![here](https://github.com/SINAsoheili/Animated-Toast/blob/master/sample.gif)

**INSTALL :** 

*Step1 :*
Add it in your root build.gradle at the end of repositories:

```
  allprojects {
      repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    }
```    
    
*Step 2 :*
Add the dependency

```
    dependencies {
             implementation 'com.github.SINAsoheili:Animated-Toast:V1.0'
    }
```    
    
**USAGE :**

use this is very simple . you can use this looklike a Toast .

for example :

        AnimatedToast t = new AnimatedToast(this , "Animated Toast" , R.drawable.home); // you must insert your context instade of "this" and image address instade of "R.drawable.home"
        t.setGravity(Gravity.CENTER_VERTICAL , 0 , 0 );
        t.show();
                

**Contribute :**

you can contribute in this project and help me to improve this .

for contribute :

1. fork project in your repository.
2. clone project in your system.
3. create your branch and switch in that.
4. make your change .
5. commit your changes and send pull request to me. :wink:
