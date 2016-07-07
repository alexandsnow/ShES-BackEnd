package com.GH.king.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * Created by alex on 2016/3/10.
 */
public class FreeMarkerService {

    private Configuration configuration;
    private Template template;

    public FreeMarkerService() {
    }

    public FreeMarkerService(Configuration configuration) {
        this.configuration = configuration;
    }

    public String  processTemplate(Map<String,String> map,String templateName){

        String content=null;
        try {
            this.template=configuration.getTemplate(templateName);
            //template.process(map,new OutputStreamWriter(System.out));
            content=FreeMarkerTemplateUtils.processTemplateIntoString(template,map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return content;
    }
}
