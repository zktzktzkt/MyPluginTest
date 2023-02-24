package me.zkt.asm;

import java.util.ArrayList;
import java.util.List;

public class MethodExtension {
    public String name;
    public String replaceType = "all";
    public List<String> include = new ArrayList<>();
    public List<String> exclude = new ArrayList<>();

    public MethodExtension() {
    }
}