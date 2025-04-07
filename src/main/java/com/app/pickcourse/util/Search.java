package com.app.pickcourse.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
public class Search {

    String type;
    String isAct;
    String keyWord;

    public List<String> getTypes(){
        return new ArrayList<>(Arrays.asList(type.split("")));
    }
}
