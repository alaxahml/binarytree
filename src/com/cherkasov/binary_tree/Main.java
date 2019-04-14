package com.cherkasov.binary_tree;

import jdk.jfr.events.ExceptionThrownEvent;

public class Main {
    public static void main(String[] args) {
        Binary_Tree tree=new Binary_Tree(5);

        tree.insert(tree.getRoot(),4);
        tree.insert(tree.getRoot(),3);
        tree.insert(tree.getRoot(),2);
        tree.insert(tree.getRoot(),3);
        tree.insert(tree.getRoot(),6);
        tree.insert(tree.getRoot(),9);
        tree.insert(tree.getRoot(),7);
        tree.insert(tree.getRoot(),10);
        try {
            tree.delete(tree.getRoot(), null, 4);
        }catch(Exception e){
            System.out.println("Doesnt exist!");
        }

        System.out.println(tree.root.left.key);

        try {
            tree.delete(tree.getRoot(), null, 1);
        }catch(Exception e){
            System.out.println("Doesnt exist!");
        }

        try{
            tree.search(tree.getRoot(),4);
            System.out.println("mistake");
        }catch(Exception e){
            System.out.println("udolil");
        }

    }
}
