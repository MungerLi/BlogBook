package com.munger.blogbook.task;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

@Component
public class DataPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> s = resultItems.getAll();
        System.out.println("123");
    }
}
