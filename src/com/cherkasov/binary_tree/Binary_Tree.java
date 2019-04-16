package com.cherkasov.binary_tree;


public class Binary_Tree {
    Node root;
    public class Node{
        Node right;
        Node left;
        int key,rights,lefts,value;
        Node(int key){
            this.key=key;
            value=key*key;
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
                ++h.lefts;
                return;
            }
            insert(h.left,key);
            ++h.lefts;
        }
        else{
            if(h.right==null){
                h.right=new Node(key);
                ++h.rights;
                return;
            }
            insert(h.right,key);
            ++h.rights;
        }
    }
    public void insertroot(Node h,Node parent,Node inserted) throws Exception{
        if(h==null){
            throw new Exception();
        }

        if(inserted.key<h.key){
            insertroot(h.left,h,inserted);
            if(h==root) {
                h = rotateR(h);
                root=h;
            }
            else{
                h = rotateR(h);
                parent.left=h;
            }
        }
        if(inserted.key>h.key){
            insertroot(h.right,h,inserted);
            if(h==root) {
                h = rotateL(h);
                root=h;
            }
            else{
                h = rotateL(h);
                parent.right=h;
            }
        }
    }
    public void insert_minimal(Node h,Node parent,int order){
        int lchildren=h.lefts+1;
        if(lchildren>order){
            insert_minimal(h.left,h,order);
            if(h==root) {
                h=rotateR(h);
                root=h;
            }
            else {
                h=rotateR(h);
                parent.left = h;
            }

        }
        if(lchildren<order){
            insert_minimal(h.right,h,order-lchildren);
            if(h==root){
                h=rotateL(h);
                root=h;
            }
            else {
                h=rotateL(h);
                parent.right = h;
            }
        }
    }
    public Node rotateL(Node node){
        /*Node temp=node.right.left;
        node.right.left=node;
        node.left=temp;*/
        Node temp=node;
        node=node.right;
        Node temp2=node.left;
        node.left=temp;
        node.left.right=temp2;
        node.left.rights=node.left.rights-1-node.rights;
        node.lefts=node.lefts+node.left.lefts+1;
        return node;
    }
    public Node rotateR(Node node){
        /*Node temp=node.left.right;
        node.left.right=node;
        node.right=temp;*/
        Node temp=node;
        node=node.left;
        Node temp2=node.right;
        node.right=temp;
        node.right.left=temp2;
        node.right.lefts=node.right.lefts-1-node.lefts;
        node.rights=node.rights+node.right.rights+1;
        return node;
    }
    public Node search(Node h,int key)throws Exception {
        if(h==null){
            throw new Exception();
        }
        if(h.key==key){
            System.out.println(h.value);
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
            --h.lefts;
        }
        if(key>h.key){
            delete(h.right,h,key);
            --h.rights;
        }
    }
    public Node joinLR(Node a,Node b){
        if(b==null){
            return a;
        }
       insert_minimal(b,null,1);
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
