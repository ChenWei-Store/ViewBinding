package com.viewBinding;

import com.google.auto.service.AutoService;
import com.viewBinding.model.BindingInfo;
import com.viewBinding.model.OnClickInfo;
import com.viewBinding.model.ViewBindInfo;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

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
        //获取OnClick注解的相关信息
        for(Element element : elements){
            BindingInfo bindingInfo = createBindingInfo(element);
            OnClick onClick = element.getAnnotation(OnClick.class);
            int viewId = onClick.viewId();
            ExecutableElement executableElement = (ExecutableElement)element;
            String name = executableElement.getSimpleName().toString();
            OnClickInfo onClickInfo = new OnClickInfo(viewId, name);
            bindingInfo.addOnClickInfo(onClickInfo);
            if(!checkModifier(element.getModifiers())){
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        name + " method can not use private modifier");
            }
        }

        Set<? extends Element> elements2 = roundEnv.getElementsAnnotatedWith(BindView.class);
        //获取bindView注解的相关信息
        for(Element element : elements2){
            BindingInfo bindingInfo = createBindingInfo(element);
            BindView bindView = element.getAnnotation(BindView.class);
            int viewId = bindView.viewId();
            VariableElement variableElement = (VariableElement)element;
            String fieldName = variableElement.getSimpleName().toString();
            String viewQualifiedType = variableElement.asType().toString();
            ViewBindInfo viewBindInfo = new ViewBindInfo();
            viewBindInfo.setViewId(viewId);
            viewBindInfo.setViewQualifiedType(viewQualifiedType);
            viewBindInfo.setFieldName(fieldName);
            bindingInfo.addViewBindInfo(viewBindInfo);
            if(!checkModifier(element.getModifiers())){
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        fieldName + " variable can not use private modifier");
            }

        }

        //生成代码
        for(String key : bindingInfoMap.keySet()){
            BindingInfo bindingInfo = bindingInfoMap.get(key);
            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(
                        bindingInfo.getNewClassName(),
                        bindingInfo.getTypeElement());
                Writer writer = jfo.openWriter();
                writer.write(bindingInfo.generateClass());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        "unable to create " + bindingInfo.getNewClassName() + "class");
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "because " +
                       e.getMessage());
            }

        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "end to process");

        return true;
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

    private BindingInfo createBindingInfo(Element element){
        TypeElement typeElement = (TypeElement)element.getEnclosingElement();

        //获取类名
        String clsName = typeElement.getSimpleName().toString();
        BindingInfo bindingInfo = bindingInfoMap.get(clsName);
        if(bindingInfo == null){
            //获取包名
            String packageName =  processingEnv.getElementUtils().getPackageOf(typeElement).toString();
            bindingInfo = new BindingInfo(clsName, packageName, typeElement) ;
            bindingInfo.setClassQualifiedName(typeElement.getQualifiedName().toString());
            bindingInfoMap.put(clsName, bindingInfo);
        }
        return bindingInfo;

    }

    /**
     * 检查被@BindView和@OnClick注解声明的成员变量或者方法的修饰符是不是private
     * @param modifiers
     * @return
     */
    private boolean checkModifier(Set<Modifier> modifiers){
        for(Modifier modifier : modifiers){
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "modifier " +
                    modifier.toString());
            if(modifier.equals(Modifier.PRIVATE)){
                return false;
            }
        }
        return true;
    }

}
