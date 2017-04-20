package com.viewBinding;

import com.google.auto.service.AutoService;
import com.viewBinding.model.BindingInfo;
import com.viewBinding.model.OnClickInfo;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * 1.包名
 * 2.类名
 * 3. 如果是BindView注解获得id值，字段类型
 * 4. 如果是OnClick注解获得方法名(Element.getSimpleName)和id值.()
 */
@AutoService(Processor.class)
public class ViewBindingProcess extends AbstractProcessor {
    private Map<String, BindingInfo> bindingInfoMap;  // key className value: BindingInfo
    /**
     *
     * @param annotations 根据注解处理器中支持的所有注解类型生成的TypeElement类
     * @param roundEnv 可通过该参数查找要处理的注解信息
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if(bindingInfoMap == null){
            bindingInfoMap = new HashMap();
        }
        if(bindingInfoMap.size() > 0){
            bindingInfoMap.clear();
        }

        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "start to process");
        //获取给定的注解类型的Element集合，Element中保存了声明该注解的元素相关信息
        //对方法声明的注解会返回ExecutableElement，对成员变量声明的注解会返回VariableElement
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(OnClick.class);

        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "===================================");

        for(Element element : elements){
            TypeElement enclosedElement = (TypeElement)element.getEnclosingElement();
            String clsName = enclosedElement.getQualifiedName().toString();
            BindingInfo bindingInfo = bindingInfoMap.get(clsName);
            if(bindingInfo == null){
                String packageName =  processingEnv.getElementUtils().getPackageOf(enclosedElement).toString();
                bindingInfo = new BindingInfo(clsName, packageName) ;
                bindingInfoMap.put(clsName, bindingInfo);
            }
        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "===================================");

        Set<? extends Element> elements2 = roundEnv.getElementsAnnotatedWith(BindView.class);
        for(Element element : elements2){
            TypeElement enclosedElement = (TypeElement)element.getEnclosingElement();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "hashcode: " +
                    enclosedElement.hashCode());
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


    public void printfInterface(Object element){
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
