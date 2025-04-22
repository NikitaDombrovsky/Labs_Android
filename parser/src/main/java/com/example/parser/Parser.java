package com.example.parser;

import com.example.parser.Models.Root;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public abstract class Parser {

    public abstract JSONObject toObject(Root root1Object) ;

    public abstract Root toModel(String string);

    public abstract JSONArray toArray(List<Root> roots);

    public abstract List<Root> toListModel(String string);
}
