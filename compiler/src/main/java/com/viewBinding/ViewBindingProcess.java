package com.viewBinding;

import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class ViewBindingProcess extends AbstractProcessor {

    /**
     *
     * @param annotations
     * @param roundEnv 可通过该参数查找要处理的注解信息
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "start to process");
        //获取给定的注解类型的Element集合，Element中保存了声明该注解的元素相关信息
        //对方法声明的注解会返回ExecutableElement，对成员变量声明的注解会返回VariableElement
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(OnClick.class);
        for(Element element : elements){
            printClsAndSuperCls(element);
            printfInterface(element);
        }

        Set<? extends Element> elements2 = roundEnv.getElementsAnnotatedWith(BindView.class);
        for(Element element : elements2){
            printClsAndSuperCls(element);
            printfInterface(element);
        }

        return false;
    }

    /**
     * 支持的注解类型
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(OnClick.class.getCanonicalName());
        types.add(BindView.class.getCanonicalName());
        return types;
    }

    /**
     * 支持的源码版本
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void printClsAndSuperCls(Element element){
        Class cls = element.getClass();
        String str = "";
        do{

            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, cls.toString());
            cls = cls.getSuperclass();
        }while(cls != null);
    }

    public void printfInterface(Element element){
       Class[] classes =  element.getClass().getInterfaces();
        for(Class cls : classes){
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, cls.toString());

        }
    }

    /**
     * 生成文件
     */
    private void generateFiles(){

    }
}
