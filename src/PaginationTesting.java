import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PaginationTesting {

    public static void main(String[] args){
//        System.out.println("Say hEY");
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeAutomation\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        webDriver.get("https://addisfortune.net/");

        WebElement lists = webDriver.findElement(By.xpath("//*[@id=\"addisfortune-main\"]/div/div[1]/div[2]/div"));


        List<WebElement> newsArtice= lists.findElements(By.tagName("h3"));
        List<WebElement> newsBody = lists.findElements(By.className("span4"));
        List<WebElement> newsImage = lists.findElements(By.className("span2"));
//        System.out.println(x.getSize());
        File htmlTemplateFile = new File("C:\\Users\\Abel Tilahun\\IdeaProjects\\SelenuimPagination\\HTML\\home.html");
        File newHtmlFile = new File("C:\\Users\\Abel Tilahun\\IdeaProjects\\SelenuimPagination\\HTML\\home.html");
        String htmlString = null;
        try {
            htmlString = FileUtils.readFileToString(htmlTemplateFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println("The size is:- " + newsArtice.size());

        for (int i =0 ; i <newsArtice.size() ;i++){
            if(i==newsArtice.size()){
                break;
            }
//            System.out.println(newsArtice.get(i).getText());

            assert htmlString != null;
//            htmlString = htmlString.replace("$articleHeader",  newsArtice.get(i).getText()+"<br>"+"\n$articleHeader");
            htmlString = htmlString.replace("$articleHeader", newsArtice.get(i).getAttribute("innerHTML").concat("<br>$aritcleImage"));
            htmlString = htmlString.replace( "$aritcleImage", newsBody.get(i).getAttribute("innerHTML").concat("<br>$imageView"));
            htmlString = htmlString.replace("$imageView", newsImage.get(i).getAttribute("innerHTML").concat("<br>$articleHeader"));
            try {
                FileUtils.writeStringToFile(newHtmlFile, htmlString);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }





        //This is the external webpage that the pagination occurs on
        webDriver.navigate().to("C:\\Users\\Abel Tilahun\\IdeaProjects\\SelenuimPagination\\HTML\\home.html");
// <div>
//                $articleHeader
//        $aritcleImage
//                $imageView
//
//        </div>

        try{
            Thread.sleep(3000);
        }catch (Exception e){}

        webDriver.close();
    }
}

