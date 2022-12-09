package com.liu;

import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticatorOptions;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class TestSelenium {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\18086\\Desktop\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        			//设置执行命令
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("-lang=en");
////			"--headless",
        options.addArguments("--disable-gpu", "--window-size=1920,1080", "--ignore-certificate-errors");

        ChromeDriver chromeDriver = new ChromeDriver(options);


        try {
            fun(chromeDriver);
        }catch (Exception e){
            chromeDriver.close();
        }

/*        while (true){
            Scanner sc = new Scanner(System.in);
            String next = sc.next();
            if (next.equals("ok")){
                break;
            }
            long star = System.currentTimeMillis();
            fun(chromeDriver);

            System.out.println(System.currentTimeMillis()-star);
        }*/







    }

    public static void fun(ChromeDriver chromeDriver) throws InterruptedException {
        chromeDriver.get("http://jwgl.just.edu.cn:8080/sso.jsp");
        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;

        WebElement button_network = new WebDriverWait(chromeDriver, Duration.ofMillis(10000)).until(ExpectedConditions.presenceOfElementLocated(By.id("button_network")));
        button_network.click();

        WebElement username = new WebDriverWait(chromeDriver, Duration.ofMillis(10000)).until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        WebElement password = chromeDriver.findElement(By.name("password"));
        WebElement submit = chromeDriver.findElement(By.name("submit"));
//        username.sendKeys("162211403107"); // 输入内容
        username.sendKeys("192211403127"); // 输入内容
//        password.sendKeys("Fguochao123");
        password.sendKeys("long161087.");
        submit.submit();

        WebElement jw = new WebDriverWait(chromeDriver, Duration.ofMillis(10000)).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("教务系统")));
        jw.click();
//        WebDriverWait wait = new WebDriverWait(chromeDriver,10000);
//        WebElement 教务系统 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div/div/ul")));
//        教务系统.click();

/*        JavascriptExecutor js =(JavascriptExecutor) chromeDriver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");*/

        List<String> windowHandles = new ArrayList<>(chromeDriver.getWindowHandles());
        int j = -1;
        while (j<windowHandles.size()){
            j++;
            Integer o = 0;
            try{
//                o = (Integer) js.executeScript("return document.querySelector('.wap').children.length");
                js.executeScript("$('div:contains(\"授课计划查询\")')[1].click()");
                if (o==null) continue;
            }catch (Exception ee){
                chromeDriver.switchTo().window(windowHandles.get(j));
                Thread.sleep(500);
                continue;
            }
        }



        WebElement 学期理论课表 = new WebDriverWait(chromeDriver, Duration.ofMillis(1000)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("学期理论课表")));
        学期理论课表.click();


        WebElement tempZC = chromeDriver.findElements(By.id("zc")).get(0);
        WebElement tempXQ = chromeDriver.findElement(By.name("xnxq01id"));
        Select selectZC = new Select(tempZC); // 周次选择
        Select selectXQ = new Select(tempXQ);// 学期选择


        List<WebElement> zc = selectZC.getOptions();
        for (WebElement webElement : zc) {
            System.out.println(webElement.getText()+":"+webElement.isDisplayed());
        }

        List<WebElement> xq = selectXQ.getOptions();
        for (WebElement webElement : xq) {
            System.out.println(webElement.getText()+":"+webElement.isDisplayed());
        }

        js.executeScript("document.getElementById('zc').onchange = false");
        js.executeScript("document.getElementById('xnxq01id').onchange = false");

        selectXQ.selectByValue("2022-2023-1");
        selectZC.selectByValue("1");

        js.executeScript("document.getElementById('Form1').submit();");



        WebElement kbTable = chromeDriver.findElements(By.id("kbtable")).get(0);
        List<WebElement> tr = kbTable.findElements(By.tagName("tr")); // 课表的行
        Map<String,Object> map = new HashMap<>(); // 最终解析数据

        WebElement time = tr.get(0); // 首行为周一  周二  ... 周日
        List<WebElement> th = time.findElements(By.tagName("th"));
        List<String> tempWeekList = new ArrayList<>();
        for (WebElement element : th) {
            tempWeekList.add(element.getText());
        }
        map.put("time",tempWeekList);

        Map<String,List<Map<String,String>>> yipai = new HashMap<>();

        for (int i=1;i<tr.size();i++){ // 其他行 为课程
            WebElement course = tr.get(i);
            String key = course.findElement(By.tagName("th")).getText(); // 第几节课做 key

            List<WebElement> td = course.findElements(By.tagName("td")); // 一行的所有课程
            List<Map<String,String>> tempCourseList = new ArrayList<>();// 保存一行课程信息  key为第几节课  value为课程信息
            for (WebElement webElement : td) {
//                WebElement element = webElement.findElement(By.xpath("./div[2]"));
                Map<String,String> courseDetailTemp = new HashMap<>();

                String[] split = webElement.getText().split("\n");
                String[] withSplit = {"courseId","courseName","teacher","week","classRoom"};
                for (int tep = 0 ;tep<split.length;tep++){
                    courseDetailTemp.put(withSplit[tep],split[tep]);
                }

                tempCourseList.add(courseDetailTemp);
            }
            yipai.put(key,tempCourseList);
        }
        map.put("course",yipai);



        System.out.println(JSONObject.toJSONString(map));





/*        WebElement element = chromeDriver.findElement(By.xpath   ("/html/body/div/div[2]/div[2]/div/div/div/div/div/div/ul"));
        List<WebElement> elementDiv1 = element.findElements(By.xpath("./li/div/div/div[1]"));
        List<WebElement> elementDiv2 = element.findElements(By.xpath("./li/div/div/div[2]"));

        for (int i=0;i<elementDiv1.size() ;i++){
            System.out.println(elementDiv2.get(i).getText());
            System.out.println(elementDiv2.get(i).getAttribute("innerHTML"));
        }*/

/*        for (WebElement webElement : elements) {
            String innerHTML = webElement.getAttribute("innerHTML");
            System.out.println(innerHTML);
            System.out.println(webElement.isDisplayed()+":"+webElement.getAttribute("data-url"));
            System.out.println("-----------------------------------------------------");
        }*/





//        chromeDriver.close();
    }


}
