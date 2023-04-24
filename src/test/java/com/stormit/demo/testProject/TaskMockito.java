package com.stormit.demo.testProject;

import com.stormit.demo.common.ControllerUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
// 9M. Lekcja 4:  Automatyczne mockowanie z Mockito
public class TaskMockito {

    @Test
    void simpleInvocation() {

        //given
        List<String> list = Mockito.mock(List.class);

        //when
        list.size();

        //then
        Mockito.verify(list).size();
    }

    @Test
    void noInteractions() {

        //given
        List<String> list = Mockito.mock(List.class);

        //when
        /** nothing **/

        //then
        Mockito.verifyNoInteractions(list);
        Mockito.verify(list,Mockito.times(0)).size();
        Mockito.verify(list, Mockito.never()).size();
    }

    @Test
    void amountOfInteractions() {
        //given
        List<String> list = Mockito.mock(List.class);

        //when
        list.size();
        list.size();

        //then
        Mockito.verify(list, Mockito.times(2)).size();

        Mockito.verify(list, Mockito.atLeast(1)).size();
        Mockito.verify(list, Mockito.atLeastOnce()).size();

        Mockito.verify(list, Mockito.atMost(5)).size();
    }

    @Test
    void shouldGeneratePageNumbers() {

        //given
        Page page = Mockito.mock(Page.class);
        Mockito.when(page.getTotalPages()).thenReturn(3);

        Model model = Mockito.mock(Model.class);

        //when
        ControllerUtils.paging(model, page);  //czyli zapisuje w atrybucie modelu: 1.) nazwe ("pageNumbers") i 2.) Liste (1,2,3)

        //then
        Mockito.verify(model).addAttribute(Mockito.any(), Mockito.any());
        Mockito.verify(model).addAttribute(Mockito.anyString(), Mockito.any());
        Mockito.verify(model).addAttribute(Mockito.eq("pageNumbers"), Mockito.eq(Arrays.asList(1,2,3)));
    }

}
