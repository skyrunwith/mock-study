package com.fzd.mockito.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.LinkedList;

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
}
