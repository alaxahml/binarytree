package com.cherkasov.binary_tree;


public class Binary_Tree {
    Node root;
    public class Node{
        Node right;
        Node left;
        int key,rights,lefts;
        Node(int key){
            this.key=key;
            right=null;
            left=null;
            rights=0;
            lefts=0;
        }
    }
    Binary_Tree(int key){
       root=new Node(key);
    }
    public Node getRoot(){
        return root;
    }
    public void insert(Node h,int key){

        if(key<h.key){
            if(h.left==null){
                h.left=new Node(key);
                return;
            }
            insert(h.left,key);
            ++h.lefts;
        }
        else{
            if(h.right==null){
                h.right=new Node(key);
                return;
            }
            insert(h.right,key);
            ++h.rights;
        }
    }
    public void insertroot(Node h,Node inserted){
        if(h==null){
            h=new Node(inserted.key);
        }
        if(inserted.key<h.key){
            insertroot(h.left,inserted);
            rotateR(h);
        }
        else{
            insertroot(h.right,inserted);
            rotateL(h);
        }
    }
    public void insert_minimal(Node h,int order){
        int lchildren=h.lefts+1;
        if(lchildren>order){
            insert_minimal(h.left,order);
            rotateR(h);
        }
        if(lchildren<order){
            insert_minimal(h.right,order-lchildren-1);
            rotateL(h);
        }
    }
    public void rotateL(Node node){
        Node temp=node.right.left;
        node.right.left=node;
        node.left=temp;
    }
    public void rotateR(Node node){
        Node temp=node.left.right;
        node.left.right=node;
        node.right=temp;
    }
    public Node search(Node h,int key)throws Exception {
        if(h==null){
            throw new Exception();
        }
        if(h.key==key){
            return h;
        }
        if(key>h.key){
          return search(h.right,key);
        }
        if(key<h.key){
            return search(h.left,key);
        }
        return null;
    }
    public void delete(Node h,Node parent,int key) throws Exception{
        if(h==null){
            throw new Exception();
        }
        if(key==h.key){
           if(h==root){
            h=joinLR(h.left,h.right);
            return;
            }
            if(parent.left==h){
            parent.left=joinLR(h.left,h.right);
            return;
            }
            else{
            parent.right=joinLR(h.left,h.right);
            return;
            }
        }
        if(key<h.key){
            delete(h.left,h,key);
        }
        if(key>h.key){
            delete(h.right,h,key);
        }
    }
    public Node joinLR(Node a,Node b){
        if(b==null){
            return a;
        }
       insert_minimal(b,1);
        b.left=a;
        return b;
    }


    /*public int minkey(Node h){
        if(h.left==null){
            return h.key;
        }
        else{
            return minkey(h.left);
        }
    }*/
}
