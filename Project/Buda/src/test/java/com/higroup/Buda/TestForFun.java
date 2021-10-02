package com.higroup.Buda;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestForFun {
    // @Mock
    // List<String> mockList;

    // @Test
    // public void whenNotUseMockAnnotation_thenCorrect() {
        
        
    //     mockList.add("one");
    //     Mockito.verify(mockList).add("one");
    //     assertEquals(0, mockList.size());

    //     Mockito.when(mockList.size()).thenReturn(1);
    //     assertEquals(1, mockList.size());
    // }

    @Spy
    List<String> spiedList = new ArrayList<String>();
    
    @Test
    public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
        spiedList.add("one");
        spiedList.add("two");
    
        Mockito.verify(spiedList).add("one");
        Mockito.verify(spiedList).add("two");
    
        assertEquals(2, spiedList.size());
    
        Mockito.doReturn(100).when(spiedList).size();
        assertEquals(100, spiedList.size());
    }


    @Mock
    List<String> mockedList;

    @Captor
    ArgumentCaptor<String> argCaptor;

    @Test
    public void whenUseCaptorAnnotation_thenTheSam() {
        mockedList.add("one");
        Mockito.verify(mockedList).add(argCaptor.capture());

        assertEquals("one", argCaptor.getValue());
    }
}
