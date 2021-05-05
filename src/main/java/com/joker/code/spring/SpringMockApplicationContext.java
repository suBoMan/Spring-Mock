package com.joker.code.spring;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname SpringMockApplicationContext
 * @Created by wangkx
 * @Date 5/4/21 1:14 AM
 * @Description TODO
 */
public class SpringMockApplicationContext {

    private Class configClass;

    // 单例池
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();


    public SpringMockApplicationContext(Class configClass) {
        this.configClass = configClass;

        // 解析配置类,主要解析的是这个类及其方法是否有Spring自己的注解
        // 解析ComponentScan注解--》扫描路径--》扫描--》BeanDefinition--》BeanDefinitionMap
        scan(configClass);

        // 扫描完，获取了所有的beanDefinition。然后我们开始创建Bean
        for (Map.Entry<String, BeanDefinition> entry: beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            // 单例Bean
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }


    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            // 给Bean实例的属性进行赋值(依赖注入)
            // 遍历类的所有属性
            for (Field field : clazz.getDeclaredFields()) {
                // 对属性使用类@Autowried注解的属性进行注入
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object bean = getBean(field.getName());
                    field.setAccessible(true);
                    field.set(instance, bean);
                }
            }

            // 判断实例是否实现了BeanNameAware接口
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBeanName(beanName);
            }

            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    /***
     * 涉及到Java的类加载器：
     * BootStrap
     * Ext
     * APP
     * 这三种类加载器对应的扫描路径也不一样；
     * BootStrap -----> jre/lib
     * Ext ----->  jre/ext/lib
     * APP ----->  classpath
     */
    private void scan(Class configClass) {
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        // 获取扫描路径
        String path = componentScanAnnotation.value();

        path = path.replace(".", "/");
        // 根据扫描的路径获取路径下的所有类
        try {
            ClassLoader classLoader = SpringMockApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            // 得到一个目录
            File file = new File(resource.getFile());
            // 判断是否是目录
            if (file.isDirectory()) {
                // 获取文件列表
                File[] files = file.listFiles();
                for (File f : files) {
                    // 获取文件名称
                    String fileName = f.getAbsolutePath();
                    if (fileName.endsWith(".class")) {
                        // 截取文件路径并转换为需要的文件路径
                        String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                        className = className.replace("/", ".");
                        // 传入参数
                        try {
                            Class<?> clazz = classLoader.loadClass(className);

                            if (clazz.isAnnotationPresent(Component.class)) {
                                // 解析类，判断当前Bean是Singleton(单例)Bean还是prototype(原型)Bean

                                Component componentAnnotation = clazz.getDeclaredAnnotation(Component.class);
                                String beanName = componentAnnotation.value();

                                // 定义一个BeanDefinition类
                                BeanDefinition beanDefinition = new BeanDefinition();
                                beanDefinition.setClazz(clazz);
                                // 判断是否配置了作用域
                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    Scope scopeAnnotation = clazz.getDeclaredAnnotation(Scope.class);
                                    beanDefinition.setScope(scopeAnnotation.value());

                                } else {
                                    beanDefinition.setScope("singleton");
                                }

                                // 存储BeanDefinition
                                beanDefinitionMap.put(beanName, beanDefinition);

                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String beanName) {
        // 判断是否存在该Bean
        if (beanDefinitionMap.containsKey(beanName)){
            // 获取该Bean的定义
            BeanDefinition beanDefinition =  beanDefinitionMap.get(beanName);
           // 判断Bean是不是单例的，如果是则直接通过单例池中获取
            if (beanDefinition.getScope().equals("singleton")) {
                Object o = singletonObjects.get(beanName);
                return o;
            } else {
                // 如果不是单例Bean，需要我们创建该Bean对象
                Object bean = createBean(beanName, beanDefinition);
                return bean;
            }

        } else{
            // 不存在对应的Bean
            throw new NullPointerException();
        }
    }
}
