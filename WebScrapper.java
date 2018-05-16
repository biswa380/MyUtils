package org.sdrc.biharinfo.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;


public class WebScrapper{

	public static void main(String[] args) {
	try {
//	Document doc = Jsoup.connect("http://sbm.gov.in/sbmReport/Report/Physical/SBM_TargetVsAchievement.aspx").get();
	Document doc =	Jsoup.connect("https://www.w3schools.com/html/html_tables.asp").get();
//	Elements div = doc.getElementsByClass("report-formatting");
	Elements div=doc.select("div");
	doc.select("table");
	Element section = div.get(0);
//	List<Node> secNodes = section.childNodes();
	Document secDoc = Jsoup.parse(String.valueOf(section));
	Document tab = Jsoup.parse(String.valueOf(secDoc.select(".table-responsive")));
	
	Element t2 = doc.select("table").first();
	Iterator<Element> iterator = t2.select("td").iterator();
	List<Company> list = new ArrayList<Company>();
    while(iterator.hasNext()){
    	Company obj = new Company();
//        System.out.println("text : "+iterator.next().text()); //kolom -1
//        System.out.println("text : "+iterator.next().text()); //kolom -2
//        System.out.println("text : "+iterator.next().text()); //kolom -3
        
        obj.setCompany(iterator.next().text());
        obj.setContact(iterator.next().text());
        obj.setLocation(iterator.next().text());
        System.out.println(obj.toString());
        list.add(obj);
    }
    
//	Element table = doc.select("table").get(0); //select the first table.
//	Elements rows = table.select("tr");

	for (Element table : doc.select(".table-rpt-report")) {
	     for (Element row : table.select("tr:gt(2)")) {
	        Elements tds = row.select("td:not([rowspan])");
	        System.out.println(tds.get(0).text() + "->" + tds.get(1).text());
	     }
	}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
		HtmlButton htmlButton;
		WebClient client = new WebClient(BrowserVersion.CHROME);
		try {
		  String searchUrl = "http://sbm.gov.in/sbmReport/Report/Physical/SBM_TargetVsAchievement.aspx";
		  HtmlPage page = client.getPage(searchUrl);
		  htmlButton = (HtmlButton) page.getHtmlElementById("ctl00_icon_excel");
	      page = (HtmlPage)htmlButton.click();
		}catch(Exception e){
		  e.printStackTrace();
		}
	}
}
