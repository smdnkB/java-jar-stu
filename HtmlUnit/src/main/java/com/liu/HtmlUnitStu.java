package com.liu;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.List;

public class HtmlUnitStu {
    public static void main(String[] args) throws Exception {
//        getItems();
        getPage();
    }
    public static void getItems() throws Exception {
        WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);


        HtmlPage page = webClient.getPage("http://localhost:8080/#/classTableCopy");
        List<HtmlDivision> divs = (List) page.getByXPath("//*[@id=\"app\"]/div[2]/div/div/table/tbody/tr[1]/td[2]/div");
        for(HtmlDivision  div :divs) {
            DomNodeList<DomNode> childs = div.getChildNodes();
            String name = "";
            String price = "";
            String comments = "";
            for(DomNode dn : childs) {
                NamedNodeMap map = dn.getAttributes();
                Node node = map.getNamedItem("class");
                if(node != null) {
                    String value = node.getNodeValue();
                    if(value.contains("p-name")) {
                        name = dn.asText();
                    } else if(value.contains("p-price")) {
                        price = dn.asText();
                    } else if(value.contains("p-commit")) {
                        comments = dn.asText();
                    }
                }
            }
            System.out.println(name+"//"+price+"//"+comments);
        }
    }

    public static void getPage() throws Exception{
//        WebClient webClient = new WebClient();//
        WebClient webClient = new WebClient(BrowserVersion.CHROME);// 使用模拟浏览器
//        WebClient webClient = new WebClient(BrowserVersion.CHROME,"127.0.0.1",81); // 使用代理ip
        webClient.getOptions().setUseInsecureSSL(true); // 忽略 ssh安全
        setConf(webClient);
        HtmlPage htmlPage = webClient.getPage("https://vpn2.just.edu.cn");

        HtmlForm fm1 =(HtmlForm) htmlPage.getElementById("fm1");
        HtmlTextInput username = fm1.getInputByName("username");
        HtmlPasswordInput password = fm1.getInputByName("password");
        HtmlSubmitInput submit = fm1.getInputByName("submit");
        username.setValueAttribute("162211403107");
        password.setValueAttribute("Fguochao123");
        HtmlPage click = submit.click();

        Thread.sleep(5000);


        System.out.println(click.asXml());
        webClient.close();
    }

    /**
     * 添加头消息
     */
    public static void setConf(WebClient webClient){
        webClient.getOptions().setCssEnabled(false); // 关闭解析css
        webClient.getOptions().setJavaScriptEnabled(false); // 开启js解析支持
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.waitForBackgroundJavaScript(600*1000);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
    }

    public static void getPageContent(HtmlPage htmlPage){
        DomElement byId = htmlPage.getElementById("app"); // 获取指定id的标签内容

        DomNodeList<DomElement> a = htmlPage.getElementsByTagName("a"); // 获取全部的a标签内容

        List<Object> byXPath = htmlPage.getByXPath("//*[@id='app']/div[2]/div/div/table/tbody/tr[1]/td[2]/div"); // 根据XPath
    }

}
