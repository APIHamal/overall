package com.lizhengpeng.overall.boot.autoconfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

public class ImportHttpInfoLoggerSelector implements ImportSelector{

    /**
     * 加载指定的对象
     * @param annotationMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        System.out.println("方法被调用");
        List<String> nameList = SpringFactoriesLoader.loadFactoryNames(EnableHttpInfoLogger.class,ImportHttpInfoLoggerSelector.class.getClassLoader());
        return nameList.toArray(new String[0]);
    }

}
