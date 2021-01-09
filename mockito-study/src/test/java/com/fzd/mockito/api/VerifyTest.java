package com.fzd.mockito.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * @Description: verify demo
 * @Author: fuzude
 * @Date: 2021-01-08
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class VerifyTest {
    /**
     * 模拟对象，使用模拟对象，验证使用模拟对象情况
     */
    @Test
    public void verifySomeAction() {
        //创建 mock 对象
        List<String> list = mock(List.class);

        //使用 mock 对象
        list.add("one");
        list.clear();

        //验证
        verify(list).add("one");
        verify(list).clear();

        //异常：Wanted but not invoked
//        verify(list).size();
    }

    /**
     * 怎么stubbing？
     * when.xxx
     */
    @Test
    public void stubbing(){
        //mock 具体的类，不仅仅是 接口
        LinkedList<String> mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //打印 first
        log.info(mockedList.get(0));
        //抛出 RuntimeException
//        log.info(mockedList.get(1));
        //打印 null，因为 get(999) 没有stubbing
        log.info(mockedList.get(999));
        log.info(mockedList.get(0));
        verify(mockedList).get(0);
    }

    /**
     * 参数匹配器
     */
    @Test
    public void argumentsMatcher(){
        LinkedList<String> mockedList = mock(LinkedList.class);
        // stubbing 使用内置 anyInt 参数匹配器
        when(mockedList.get(anyInt())).thenReturn("element");
        //打印 "element"
        log.info(mockedList.get(999));
        // 验证使用参数匹配器
        verify(mockedList).get(anyInt());
    }
}
