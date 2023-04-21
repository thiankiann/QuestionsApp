package com.stormit.demo;

import com.stormit.demo.common.ControllerUtils;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import java.util.*;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskManualMocks {

    @Test
    void shouldGeneratePageNumbers() {

        //given
        Page page = new PageMock(2, 3, Arrays.asList("a", "b", "c", "d", "e", "f", "g"));
        // [[a, b, c], [d, e, f], [g]]
        Model model = new ModelMock();

        //when
        ControllerUtils.paging(model, page );
        
        //then
        assertThat(model.asMap()).containsOnlyKeys("pageNumbers");
        assertThat((List<Integer>) model.asMap().get("pageNumbers")).containsExactly(1,2,3);
    }

    private class PageMock implements Page {

        private final int page;
        private final int pageSize;
        private final List records;

        public <T> PageMock(int page, int pageSize, List<T> records) {
            this.page = page;
            this.pageSize = pageSize;
            this.records = records;
        }

        @Override
        public int getTotalPages() {
            return (int) Math.ceil((double) records.size() / pageSize);
        }

        @Override
        public long getTotalElements() {
            return records.size();
        }

        @Override
        public Page map(Function converter) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getNumber() {
            return page;
        }

        @Override
        public int getSize() {
            return pageSize;
        }

        @Override
        public int getNumberOfElements() {
            throw new UnsupportedOperationException();
        }

        @Override
        public List getContent() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasContent() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Sort getSort() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isFirst() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isLast() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Pageable nextPageable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Pageable previousPageable() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Iterator iterator() {
            throw new UnsupportedOperationException();
        }

    }

    private class ModelMock implements Model {

        private Map<String, Object> map = new HashMap<>();

        @Override
        public Model addAttribute(String attributeName, Object attributeValue) {
            map.put(attributeName, attributeValue);
            return this;
        }

        @Override
        public Map<String, Object> asMap() {
            return map;
        }

        @Override
        public Model addAttribute(Object attributeValue) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Model addAllAttributes(Collection<?> attributeValues) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Model addAllAttributes(Map<String, ?> attributes) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Model mergeAttributes(Map<String, ?> attributes) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsAttribute(String attributeName) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object getAttribute(String attributeName) {
            throw new UnsupportedOperationException();
        }

    }
}