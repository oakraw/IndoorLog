package com.oakraw.indoorlog.model;


/**
 * Created by Rawipol on 3/20/15 AD.
 */
public class Node {
    String nid;
    String node;

    public Node(String nid, String node) {
        this.nid = nid;
        this.node = node;
    }

    public String getNid() {
        return nid;
    }

    public String getNode() {
        return node;
    }
}
