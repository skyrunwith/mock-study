package com.fzd.mockito.api;

import com.fzd.mockito.dos.Foo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @Description: Mockito demo 11-15
 * @Author: fuzude
 * @Date: 2021-01-10
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class VerifyTest_11_15 {

    /**
     * 用回调做测试桩，使用 answer
     */
    @Test
    public void stubbingAnswer(){
        LinkedList<String> mockList = mock(LinkedList.class);
        when(mockList.get(0)).thenAnswer(invocation -> {
            Object[] arguments = invocation.getArguments();
            Object mock =invocation.getMock();
            return "call with arguments: " + Arrays.toString(arguments);
        });
        log.info(mockList.get(0));
    }

    /**
     * 为 void 方法打桩
     * doThrow, doReturn, doAnswer, doNothing, doCallRealMethod
     */
    @Test(expected = RuntimeException.class)
    public void stubVoidMethod(){
        LinkedList<String> mockList = mock(LinkedList.class);
        doThrow(new RuntimeException()).when(mockList).clear();
        mockList.clear();
    }

    /**
     * 监控真实对象
     * 可以真实调用对象方法，也可以打桩
     * 有点监控方法只能使用：doReturn|Answer|Throw() 打桩
     */
    @Test
    public void spyOnRealObject(){
        LinkedList<String> mockList = new LinkedList<>();
        List<String> spy = spy(mockList);
        // 使用 spy 真实的方法调用
        spy.add("one");
        spy.add("two");
        // 为size方法打桩
        when(spy.size()).thenReturn(100);
        // 输出 one
        log.info(spy.get(0));
        // 输出 stubbed：100
        log.info("spy size: {}", spy.size());

        // 验证mock调用
        verify(spy).add("one");
        verify(spy).add("two");

        LinkedList list = new LinkedList();
        List spy2 = spy(list);
        // 会抛出 IndexOutOfBoundsException 异常
//        when(spy2.get(0)).thenReturn("foo");
        // 使用 doReturn 打桩
        doReturn("foo").when(spy2).get(0);
    }

    /**
     * 改变未stubbed方法的默认返回值
     */
    @Test
    public void changeDefaultReturn(){
        Foo foo = mock(Foo.class, RETURNS_SMART_NULLS);
        Foo fooTwo = mock(Foo.class, invocation -> "");
    }
}
