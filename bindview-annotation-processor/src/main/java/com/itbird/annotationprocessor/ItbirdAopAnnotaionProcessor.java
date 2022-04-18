package com.itbird.annotationprocessor;

import com.google.auto.service.AutoService;
import com.itbird.bindview_annotation.ItbirdAopBinderView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * 自定义注解处理器，处理ItbirdRunningBinderView注解
 * Created by itbird on 2022/4/13
 */
//指定注解处理器，可以去处理的注解类型
@SupportedAnnotationTypes("com.itbird.bindview_annotation.ItbirdAopBinderView")
//指定支持的jdk版本
@SupportedSourceVersion(SourceVersion.RELEASE_6)
//自动生成META-INF/services/javax.annotation.processing.Processor文件，使javac可以发现当前自定义注解处理器
@AutoService(Processor.class)
public class ItbirdAopAnnotaionProcessor extends AbstractProcessor {
    //https://www.apiref.com/java11-zh/java.compiler/javax/annotation/processing/ProcessingEnvironment.html
    //处理器可以使用框架提供的工具来编写新文件，报告错误消息以及查找其他实用程序
    private ProcessingEnvironment mProcessingEnvironment;
    //返回一些用于操作元素的实用方法的实现
    private Elements mElementUtils;
    //用于报告错误，警告和其他通知的消息
    private Messager mMessager;

    /**
     * init：初始化。可以得到ProcessingEnviroment，ProcessingEnviroment提供很多有用的工具类Elements, Types 和 Filer
     * Elements	getElementUtils()	返回一些用于操作元素的实用方法的实现
     * Filer	getFiler()	返回用于创建新的源，类或辅助文件的文件管理器。
     * Locale	getLocale()	如果没有区域设置生效，则返回当前区域设置或 null 。
     * Messager	getMessager()	返回用于报告错误，警告和其他通知的消息。
     * Map<String,​String>	getOptions()	返回传递给注释处理工具的特定于处理器的选项。
     * SourceVersion	getSourceVersion()	返回任何生成的 source和 class文件应符合的源版本。
     * Types	getTypeUtils()	返回一些用于对类型进行操作的实用程序方法的实现。
     *
     * @param processingEnv
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mProcessingEnvironment = processingEnv;
        mMessager = mProcessingEnvironment.getMessager();
        mElementUtils = mProcessingEnvironment.getElementUtils();
    }

    private Map<String, ViewClassInfo> map = new HashMap<>();

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 日志打印，用于辅助process方法是否被执行
        mMessager.printMessage(Diagnostic.Kind.NOTE, "---------------ItbirdAopAnnotaionProcessor start------------");

        // 获取该注解声明的相关元素
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ItbirdAopBinderView.class);
        // 遍历拥有该注解的元素
        for (Element element : elements) {
            mMessager.printMessage(Diagnostic.Kind.NOTE, "element = " + element.toString());
            /**
             * 注: ---------------ItbirdAopAnnotaionProcessor start------------
             * 注: element = mImageView
             * 注: element = com.itbird.annotation.bindview.v2.BindViewTestV2Activity
             * 注: element = textView1
             * 注: element = textView2
             * 注: element = textView3
             * 注: element = textView4
             */
            if (element.getKind() == ElementKind.CLASS) {
                mMessager.printMessage(Diagnostic.Kind.NOTE, "process is class " + element.toString());
                //如果元素是一个类，则转换为类元素
                TypeElement typeElement = (TypeElement) element;
                ViewClassInfo viewClassInfo = getViewClassInfo(typeElement);
                //设置类信息
                viewClassInfo.setTypeElement(typeElement);
            } else if (element.getKind() == ElementKind.FIELD) {
                mMessager.printMessage(Diagnostic.Kind.NOTE, "process is field " + element.toString());

                //如果元素是一个变量，则转换为变量元素
                VariableElement variableElement = (VariableElement) element;
                //获取包裹此变量的类
                TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
                ViewClassInfo viewClassInfo = getViewClassInfo(typeElement);
                //设置类信息
                viewClassInfo.setTypeElement(typeElement);
                viewClassInfo.addElements(variableElement);
            }
        }

        //使用javapoet生成java文件
        createJavaFile();
        return roundEnvironment.processingOver();
    }

    private ViewClassInfo getViewClassInfo(TypeElement typeElement) {
        //获取class name
        String classname = typeElement.getSimpleName().toString();
        ViewClassInfo proxy = null;
        if (!map.containsKey(classname)) {
            proxy = new ViewClassInfo();
            map.put(classname, proxy);
        } else {
            proxy = map.get(classname);
        }
        return proxy;
    }

    /**
     * 使用javapoet，遍历map，生成java文件
     */
    private void createJavaFile() {
        mMessager.printMessage(Diagnostic.Kind.NOTE, "createJavaFile");
        mMessager.printMessage(Diagnostic.Kind.NOTE, "createJavaFile map = " + map.toString());

        for (String classname : map.keySet()) {
            ViewClassInfo proxy = map.get(classname);
            if (proxy != null) {
                mMessager.printMessage(Diagnostic.Kind.NOTE, "createJavaFile " + proxy.getClassFullName());
                proxy.createJavaCode(mProcessingEnvironment, mElementUtils);
            }
        }
    }
}
