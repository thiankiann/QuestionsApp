package com.stormit.demo.common;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ControllerUtils {

    public static void paging(Model model, Page page) {
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)  //equivalent of for(i=1; i <totalPages; i++)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            //czyli zapisuje w modelu: 1.) nazwe ("pageNumbers") i 2.) Liste (1,2,..,totalPages)
        }
    }
}
