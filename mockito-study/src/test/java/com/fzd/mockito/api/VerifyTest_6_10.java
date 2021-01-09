package com.fzd.mockito.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

/**
 * @Description: Mockito api 6-10
 * @Author: fuzude
 * @Date: 2021-01-09
 */
@RunWith(MockitoJUnitRunner.class)
public class VerifyTest_6_10 {

    /**
     * 验证执行顺序
     */
    @Test
    public void verifyInOrder(){
        // A.单个mock必须按照特别的顺序调用
        LinkedList<String> singleMock = mock(LinkedList.class);

        singleMock.add("was added first");
        singleMock.add("was added second");
        // 创建inOrder验证single mock
        InOrder inOrder = inOrder(singleMock);
        // 保证第一次add是"was added first"，第二次add是"was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");
        // B.多个mock必须按照特别的顺序调用
        LinkedList<String> firstMock = mock(LinkedList.class);
        LinkedList<String> secondMock = mock(LinkedList.class);

        firstMock.add("was added first");
        secondMock.add("was added second");
        // 创建InOrder对象，并传递需要排序的mock
        InOrder multiOrder = inOrder(firstMock, secondMock);
        // 以下保证先调用firstMock，再调用secondMock
        multiOrder.verify(firstMock).add("was added first");
        multiOrder.verify(secondMock).add("was added second");
        // A+B可以混合使用
    }
}
