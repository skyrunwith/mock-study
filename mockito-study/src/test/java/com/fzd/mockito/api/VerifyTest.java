package com.fzd.mockito.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
}
