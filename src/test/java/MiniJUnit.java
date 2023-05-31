import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MiniJUnit {
    public static void main(String[] args) throws Exception {
        Method methodBeforeEach = null;
        Method methofAfterEach = null;
        Method methofBeforeAll = null;
        Method methofAfterAll = null;
        CityLinkTests instance = CityLinkTests.class.getConstructor().newInstance();
        Method[] methods = CityLinkTests.class.getDeclaredMethods();
        for(Method method : methods) {
            BeforeEach annotationBeforeEach = method.getAnnotation(BeforeEach.class);
            AfterEach annotationAfterEach = method.getAnnotation(AfterEach.class);
            BeforeAll annotationBeforeAll = method.getAnnotation(BeforeAll.class);
            AfterAll annotationAfterAll = method.getAnnotation(AfterAll.class);
            if (annotationBeforeEach != null) {
                methodBeforeEach = method;
            }
            if (annotationAfterEach != null) {
                methofAfterEach = method;
            }
            if (annotationBeforeAll != null) {
                methofBeforeAll = method;
            }
            if (annotationAfterAll != null) {
                methofAfterAll = method;
            }
        }

        try {
            methofBeforeAll.invoke(instance);;
        } catch (Exception e) {
            System.out.println("TEST BROKEN!!!");
        }

        for (Method method : methods) {
            Test annotationTest = method.getAnnotation(Test.class);
            if (annotationTest != null) {
                try {
                    methodBeforeEach.invoke(instance);
                    method.invoke(instance);
                    methofAfterEach.invoke(instance);
                } catch (Exception e) {
                    System.out.println("TEST BROKEN!!!");
                    return;
                }
                System.out.println("TEST PASSED!!!");
            }
        }

        try {
            methofAfterAll.invoke(instance);;
        } catch (Exception e) {
            System.out.println("TEST BROKEN!!!");
            return;
        }
    }
}