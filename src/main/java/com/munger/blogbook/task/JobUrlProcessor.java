package com.munger.blogbook.task;

import com.munger.blogbook.utils.OutToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

@Component
public class JobUrlProcessor implements PageProcessor {
    @Autowired
    private DataPipeline dataPipeline;
//    @Resource
//    public OutToFile outToFile;
    private String url="https://blog.csdn.net/lk_blog/article/list/";
    public int i=0;
    @Override
    public void process(Page page) {
        String nextUrl="";
        for (int i = 1; i<=6; i++){
            nextUrl=url+i;
            page.addTargetRequest( nextUrl );
       }
        List<Selectable> list = page.getHtml().css( "div.article-list" ).links().nodes();
        if (list.size()==0){
            this.savaPage( page );
        }else {
            for (Selectable selectable:list){
                String url=selectable.toString();
                page.addTargetRequest( url );
            }
        }
    }
    private void savaPage(Page page){

        String article = page.getHtml().css( "div.htmledit_views" ).toString();
        String title=page.getHtml().css( "div.article-title-box h1.title-article" ,"text").toString();

        System.out.println(title);
//        System.out.println(article);
        OutToFile.toFile( title,article );
        System.out.println(i++ + "------------------------");
    }

    @Override
    public Site getSite() {
        return this.site;
    }
    private Site site=Site.me()
            .setCharset( "utf8" )
            .setTimeOut( 1*1000 )
            .setRetrySleepTime( 1000 )
            .setRetryTimes( 1 );

    // todo 定时任务注解
    /**
     * 定时任务启动
     */
    @Scheduled(initialDelay = 100,fixedDelay = 1000000*1000)
    public void process(){
        Spider.create( new JobUrlProcessor() )
                .addUrl( url )
                .setScheduler( new QueueScheduler().setDuplicateRemover( new BloomFilterDuplicateRemover( 10000 ) ) )
                // 多线程导致线程不安全？？
                .thread( 1 )
//                .addPipeline( dataPipeline )
                .run();
    }
//    public String nextUrl(String url){
//        url=url.substring( 0,url.length()-1 );
//        url.lastIndexOf(  )
//        String nextUrl="";

//        return nextUrl;
//    }

}
