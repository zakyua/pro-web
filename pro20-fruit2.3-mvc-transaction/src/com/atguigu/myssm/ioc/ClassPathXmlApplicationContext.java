package com.atguigu.myssm.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen
 * @create 2022-04-12-20:25
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private Map<String,Object> beanMap = new HashMap<>();
    public ClassPathXmlApplicationContext(){
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //1.创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder() ;
            //3.创建Document对象
            Document document = documentBuilder.parse(inputStream);

            //4.获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
                for(int i = 0 ; i<beanNodeList.getLength() ; i++){
                    Node beanNode = beanNodeList.item(i);
                        if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                            Element beanElement = (Element)beanNode ;
                            String beanId =  beanElement.getAttribute("id");
                            String className = beanElement.getAttribute("class");
                            Class BeanClass = Class.forName(className);
                            //创建bean的实例
                            Object beanObj = BeanClass.getConstructor().newInstance() ;
                            //将bean实例对象保存到map容器当中
                            beanMap.put(beanId , beanObj) ;
                            //到目前位置只是用一个map将applicationContext.xml之间的所对应的类和地址保存下来,是让哪一个组件来响应哪一个请求
                       }
               }

//         <bean id="fruitService" class="com.atguigu.fruit.service.impl.FruitServiceImpl">
//         <!--property标签用来表示属性(FruitServiceImpl需要的属性 FruitDAOImpl)：name表示属性名 ref表示需要应用其他的bean的id值-->
//         <property name="fruitDAO" ref="fruitDAO"/>
//          </bean>

                //5.组装bean之间的依赖关系(是层于层之间的依赖关系)
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element)beanNode ;
                    String beanId =  beanElement.getAttribute("id");//fruitService
                    NodeList beanChildNodeList = beanElement.getChildNodes();
//                    System.out.println(childNodes.getLength());
                    //在这里将当前这个bean节点的所有的子节点取出来
                    for (int j = 0; j < beanChildNodeList.getLength(); j++) {
                        Node beanChildNode = beanChildNodeList.item(j);
                        //在这里把 <property name="fruitDAO" ref="fruitDAO"/> 取出来
                        if(beanChildNode.getNodeType() ==Node.ELEMENT_NODE  && "property".equals(beanChildNode.getNodeName())){
                            //将<property name="fruitDAO" ref="fruitDAO"/> 强转为元素节点
                            Element PropertyElement = (Element) beanChildNode;
                            String PropertyName = PropertyElement.getAttribute("name");//fruitDAO
//                            <bean id ="fruitDAO" class="com.atguigu.fruit.dao.impl.FruitDAOImpl"/>
                            String ProperRef = PropertyElement.getAttribute("ref");
                              //1) 找到properRef对应的实例
                            Object refobj = beanMap.get(ProperRef);//fruitDAoImpl这个类
 //        <bean id="fruitService" class="com.atguigu.fruit.service.impl.FruitServiceImpl">
//         <!--property标签用来表示属性(FruitServiceImpl需要的属性 FruitDAOImpl)：name表示属性名 ref表示需要应用其他的bean的id值-->
//         <property name="fruitDAO" ref="fruitDAO"/>
//          </bean>
                  //2) 将refobj(fruitDAoImpl)设置到当前这个bean(FruitServiceImpl)对应的实例的property属性上去
                        //获取这个FruitServiceImpl的Class对象
                      //(beanMap保存的就是具体类的对象)
                        Object beanObj = beanMap.get(beanId);
                        Class beanClazz = beanObj.getClass();//FruitServiceImpl的class对象
                        //要给这个beanClazz的属性赋值 这个属性是PropertyName(fruitDAO)
                        Field PropertyField = beanClazz.getDeclaredField(PropertyName);
                        PropertyField.setAccessible(true);
                        PropertyField.set(beanObj,refobj);


                        }
                    }






                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }



    public Object getBean(String id) {
        //fruit
        return beanMap.get(id);
    }
}
