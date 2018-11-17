package com.xym.entities;

import java.util.*;

/**
 * @ClassName AllCollectionType
 * @Description TODO
 * @Author ak
 * @Date 2018/11/14 上午11:53
 * @Version 1.0
 **/
public class AllCollectionType {
    private List<String> fruitList;
    private String[] fruitArray;
    private Set<String> fruitSet;
    private Map<String,String> fruitMap;
    private Properties fruitProps;

    @Override
    public String toString() {
        return "AllCollectionType{" +
                "fruitList=" + fruitList +
                ", fruitArray=" + (fruitArray == null ? null : Arrays.asList(fruitArray)) +
                ", fruitSet=" + fruitSet +
                ", fruitMap=" + fruitMap +
                ", fruitProps=" + fruitProps +
                '}';
    }

    public List<String> getFruitList() {
        return fruitList;
    }

    public void setFruitList(List<String> fruitList) {
        this.fruitList = fruitList;
    }

    public String[] getFruitArray() {
        return fruitArray;
    }

    public void setFruitArray(String[] fruitArray) {
        this.fruitArray = fruitArray;
    }

    public Set<String> getFruitSet() {
        return fruitSet;
    }

    public void setFruitSet(Set<String> fruitSet) {
        this.fruitSet = fruitSet;
    }

    public Map<String, String> getFruitMap() {
        return fruitMap;
    }

    public void setFruitMap(Map<String, String> fruitMap) {
        this.fruitMap = fruitMap;
    }

    public Properties getFruitProps() {
        return fruitProps;
    }

    public void setFruitProps(Properties fruitProps) {
        this.fruitProps = fruitProps;
    }
}