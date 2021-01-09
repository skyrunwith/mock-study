package com.fzd.mockito.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    /**
     * 保证交互操作不会发生在mock上
     */
    @Test
    public void interactionNeverHappenOnMock(){
        LinkedList<String> mockOne = mock(LinkedList.class);
        // mockOne 进行交互操作
        mockOne.add("one");
        verify(mockOne).add("one");
        // 验证该方法从没被mock调用过
        verify(mockOne, never()).add("two");
        // 验证其他mock没有交互操作
        LinkedList<String> mockTwo = mock(LinkedList.class);
        LinkedList<String> mockThree = mock(LinkedList.class);
        verifyNoInteractions(mockTwo, mockThree);
    }

    /**
     * 查找冗余调用，不建议每个测试里面都调用verifyNoMoreInteractions方法
     */
    @Test
    public void findRedundantInvocation(){
        LinkedList<String> mockedList = mock(LinkedList.class);
        mockedList.add("one");
        // 该操作会发生冗余调用
        mockedList.add("two");

        verify(mockedList).add("one");
        // 以下验证会失败
        verifyNoMoreInteractions(mockedList);
    }

    @Mock
    private LinkedList<String> mockAnnotationList;
    /**
     * 简写 mock 创建，使用 mock 注解
     */
    @Test
    public void mockAnnotation(){
        // 重要！这需要在基类中或测试运行器中：
//        MockitoAnnotations.openMocks(testClass);
    }
}
