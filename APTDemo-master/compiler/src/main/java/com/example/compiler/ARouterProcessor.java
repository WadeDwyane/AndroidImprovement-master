package com.example.compiler;

import com.example.annonation.ARouter;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
@SupportedOptions("content")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("com.example.annonation.ARouter")
public class ARouterProcessor extends AbstractProcessor {

    // 操作element工具类
    private Elements elementUtils;
    // 操作类信息工具类
    private Types typesUtils;
    // 用来做log输出
    private Messager messager;
    // 操作文件,文件生成器
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        typesUtils = processingEnvironment.getTypeUtils();
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();

        String content = processingEnvironment.getOptions().get("content");
        messager.printMessage(Diagnostic.Kind.NOTE, content);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        // ------------ 以下这部分是auto-service方法实现动态注解的方法,是google提供的方法 ------------
//        if (set.isEmpty()) return false;
//        // 获取项目中所有使用
//        Set<? extends Element> sets = roundEnvironment.getElementsAnnotatedWith(ARouter.class);
//        for (Element element : sets) {
//            // 类节点之上就是包节点,获取包名
//            String packageName = elementUtils.getPackageOf(element).getQualifiedName().toString();
//            // 获取简单类名
//            String className = element.getSimpleName().toString();
//            messager.printMessage(Diagnostic.Kind.NOTE, "被注解的类有:" + className);
//            //最终要动态生成的类文件名称
//            String finalClassName = className + "$$ARouter";
//
//            try {
//                JavaFileObject object = filer.createSourceFile(packageName + "." + finalClassName);
//                Writer writer = object.openWriter();
//                //写包名
//                writer.write("package " + packageName + ";\n");
//                //写类名
//                writer.write("public class " + finalClassName + "{\n");
//
//                writer.write("public static Class<?> findTargetClass(String path) {\n");
//                ARouter aRouter = element.getAnnotation(ARouter.class);
//                writer.write("if (path.equalsIgnoreCase(\"" + aRouter.path() + "\")) {\n");
//                writer.write("return " + className + ".class; \n } \n");
//                writer.write("return null;\n");
//                writer.write("}\n}");
//
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return true;


        //------------- 以下这部分是javapoet方法实现的动态注解,是square开源方案 ---------
        if (set.isEmpty()) return false;
        Set<? extends Element> sets = roundEnvironment.getElementsAnnotatedWith(ARouter.class);
        for (Element element : sets) {
            // 获取包名
            String packageName = elementUtils.getPackageOf(element).getQualifiedName().toString();
            // 获取简单类名
            String className = element.getSimpleName().toString();
            // 打印出被注解的类名
            messager.printMessage(Diagnostic.Kind.NOTE, "被注解的类有:" + className);
            // 最终要生成的类名
            String finalClassName = className + "$$ARouter";

            ARouter aRouter = element.getAnnotation(ARouter.class);

            MethodSpec methodSpec = MethodSpec.methodBuilder("findTargetClass")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(Class.class)
                    .addParameter(String.class, "path")
                    .addStatement("return path.equalsIgnoreCase($S) ? $T.class : null",
                            aRouter.path(),
                            ClassName.get((TypeElement) element))
                    .build();

            TypeSpec typeSpec = TypeSpec.classBuilder(finalClassName)
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addMethod(methodSpec)
                    .build();

            JavaFile javaFile = JavaFile.builder(packageName, typeSpec)
                    .build();

            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
