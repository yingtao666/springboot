package com.how2java.springboot.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GetPicByPhantomjs {
    public static String getPicByPhantomjs(Map<String ,String> map){
        System.out.println("使用phantomjs截图链接:"+map.get("url"));
        File sf = new File("D:/test1/1.jpg");//创建一个文件对象
        String pic=System.currentTimeMillis()+".png";
        //定义图片存储路径
        DesiredCapabilities dcaps = null;
        PhantomJSDriver driver = null;
        String picName = null;
        try {
            //设置必要参数
            dcaps =  new DesiredCapabilities();
            //ssl证书支持
            dcaps.setCapability("acceptSslCerts", true);
            //截屏支持
            dcaps.setCapability("takesScreenshot", true);
            //css搜索支持
            dcaps.setCapability("cssSelectorsEnabled", true);
            //js支持
            dcaps.setJavascriptEnabled(true);
            //驱动支持（第二参数表明的是你的phantomjs引擎所在的路径）
            dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    "D:/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/bin/phantomjs.exe");
//                "F:/test/phantomjs-2.1.1-windows/bin/phantomjs.exe");
            //创建无界面浏览器对象
            driver = new PhantomJSDriver(dcaps);
            long start = System.currentTimeMillis();
//         driver.get(url);
            driver.get(map.get("url"));
            //设置隐性等待（作用于全局）
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            Thread.sleep(5* 1000);
            if(!"".equals(map.get("str")) && map.get("str")!=null){
                /*         		if(ElementExist(driver,By.className(map.get("str")))){*/
                WebElement inputBox = driver.findElement(By.className(map.get("str")));
                Actions action = new Actions(driver);
                action.click(inputBox).build().perform();
                //元素点击 后等待加载
                Thread.sleep(2 * 1000);
                /*               }*/
            }
            JavascriptExecutor js = driver;
            //页面下滑10次,每次下滑加载2s
            for (int i = 0; i < 10; i++) {
                js.executeScript("window.scrollBy(0,1000)");
                //睡眠2s等js加载完成
                Thread.sleep(2 * 1000);
            }
            //指定了OutputType.FILE做为参数传递给getScreenshotAs()方法，其含义是将截取的屏幕以文件形式返回。
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Thread.sleep(2000);
            //利用FileUtils工具类的copyFile()方法保存getScreenshotAs()返回的文件对象
            FileUtils.copyFile(srcFile, new File(sf.getPath()+File.separator+pic));
            Thread.sleep(2000);
            Thumbnails.of(sf.getPath()+File.separator+pic).scale(1.0f).toFile(sf.getPath()+File.separator+pic);
            driver.close();
            driver.quit();
            picName = pic;
            System.out.println("本次截图耗时：" + (System.currentTimeMillis() - start) + " 毫秒");
//         System.out.println("转换后的链接:"+map.get("url"));
        } catch (Exception e) {
            driver.close();
            driver.quit();
            picName = "";
            /*		     logger.warn("使用phantomjs截图时："+e.toString());*/
        }
        return picName;
    }
}
