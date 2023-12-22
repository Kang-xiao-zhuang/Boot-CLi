package com.zhuang.aspect.thread;

public class Gate {

    private int count = 0;
    private String name = "nobody";
    private String address = "Nowhere";

    public synchronized void pass(String name, String address) {
        this.count++;
        this.name = name;
        this.address = address;
        check();
    }

    @Override
    public synchronized String toString() {
        return "Gate{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("BROKEN ------------" + toString());
        }
    }


}
