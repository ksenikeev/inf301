package ru.itis.inf301.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) throws IOException, TemplateException, URISyntaxException {
        Reg reg = new Reg();
        reg.setId(10923451001L);

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

        cfg.setDirectoryForTemplateLoading(new File(Main.class.getClassLoader().getResource("templates").toURI()));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());

        Map<String, Object> root = new HashMap<>();
        root.put("reg", reg);
        Template temp = cfg.getTemplate("index.ftl");
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);

    }

}
