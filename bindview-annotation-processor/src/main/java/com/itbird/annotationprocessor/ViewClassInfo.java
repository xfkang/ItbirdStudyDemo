package com.itbird.annotationprocessor;

import com.itbird.bindview_annotation.ItbirdAopBinderView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;


/**
 * 解析注解，创建相关view信息类
 * Created by itbird on 2022/4/15
 */
public class ViewClassInfo {

    private static final int INVAILD = -1;

    public static final String BIND_METHOD_PARAMETER_NAME = "activity";
    /**
     * 类元素
     */
    private TypeElement typeElement;
    /**
     * 类的view集合
     */
    private List<VariableElement> elements;

    public String getClassFullName() {
        return typeElement != null ? typeElement.getQualifiedName().toString() : null;
    }

    public String getClassSimpleName() {
        return typeElement != null ? typeElement.getSimpleName().toString() : null;
    }

    public int getClassLayoutId() {
        if (typeElement == null) {
            return INVAILD;
        }

        //获取类注解
        ItbirdAopBinderView annotaion = typeElement.getAnnotation(ItbirdAopBinderView.class);

        if (annotaion == null) {
            return INVAILD;
        }
        //获取注解的value
        return annotaion.value();
    }

    public void addElements(VariableElement element) {
        if (elements == null) {
            elements = new ArrayList<>();
        }
        elements.add(element);
    }

    public void setTypeElement(TypeElement typeElement) {
        if (this.typeElement == null) {
            this.typeElement = typeElement;
        }
    }

    /**
     * 创建java代码
     *
     * @return
     */
    private CodeBlock generateJavaCode() {
        CodeBlock.Builder codeBlock = CodeBlock.builder();
        if (typeElement != null) {
            codeBlock.add(BIND_METHOD_PARAMETER_NAME + ".setContentView( $L );\n", getAnnotationValue(typeElement));
        }
        for (VariableElement element : elements) {
            codeBlock.add(BIND_METHOD_PARAMETER_NAME + "." + element.getSimpleName() + " = " + BIND_METHOD_PARAMETER_NAME + ".findViewById( $L );\n", getAnnotationValue(element));
        }
        return codeBlock.build();
    }

    private int getAnnotationValue(Element element) {
        ItbirdAopBinderView annotaion = element.getAnnotation(ItbirdAopBinderView.class);
        //TODO :这儿应该不会为空，为什么会空指针
        return annotaion == null ? INVAILD : annotaion.value();
    }

    public void createJavaCode(ProcessingEnvironment mProcessingEnvironment, Elements elementUtils) {
        //获取类信息
        ClassName className = ClassName.get(typeElement);
        //构建bind的入参
        ParameterSpec parameterSpec = ParameterSpec.builder(className, BIND_METHOD_PARAMETER_NAME).build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("bind")
                .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                .addParameter(parameterSpec)
                .addCode(generateJavaCode())
                .build();

        //构造类
        TypeSpec typeSpec = TypeSpec.classBuilder(getClassSimpleName() + "_ViewBinding")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodSpec)
                .build();
        //创建文件
        JavaFile javaFile = JavaFile.builder(getPackageName(elementUtils), typeSpec).build();

        //写入文件
        try {
            javaFile.writeTo(mProcessingEnvironment.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPackageName(Elements elementUtils) {
        if (typeElement == null) {
            return null;
        }
        PackageElement packageElement = elementUtils.getPackageOf(typeElement);
        return packageElement.getQualifiedName().toString();
    }
}
