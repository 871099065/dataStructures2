package com.example.demo.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode h1=new HeroNode( 1,"宋江","及时雨" );
        HeroNode h2=new HeroNode( 2,"卢俊义","王麒麟" );
        HeroNode h3=new HeroNode( 3,"吴用","智多星" );
        HeroNode h4=new HeroNode( 4,"林冲","豹子头" );

        //创建单向链表
        SingleLinkedList singleLinkedList=new SingleLinkedList();
        //加入
   /*     singleLinkedList.add( h1 );
        singleLinkedList.add( h2 );
        singleLinkedList.add( h3 );
        singleLinkedList.add( h4 );*/
            //加入按照编号的顺序
        singleLinkedList.addByOrder( h1 );
        singleLinkedList.addByOrder( h4 );
        singleLinkedList.addByOrder( h3 );
        singleLinkedList.addByOrder( h2 );

        //显示一把
        singleLinkedList.list();
        //测试修改节点
        HeroNode newHeroNode=new HeroNode( 2,"小🦌","玉麒麟~~~" );
        singleLinkedList.update( newHeroNode );
        System.out.println("修改后显示:");
        singleLinkedList.list();
        //删除节点
        singleLinkedList.del( 1 );
        System.out.println("删除后显示:");
        singleLinkedList.list();

        //测试一下单链表中的有效节点的个数
        System.out.println("有效的节点的个数是："+getLeagth( singleLinkedList.getHead()));

        //测试一下倒数第k的节点是
        System.out.println("倒数第k的节点是:"+ findLastIndexNode( singleLinkedList.getHead(),4));

        System.out.println("反转后显示：");
        rerversetList( singleLinkedList.getHead() );
        singleLinkedList.list();
        System.out.println("测试~~~~~~~~~~~~~~~~");
    }

    /**
     *  单链表反转，【腾讯面试题】
     */
    public static  void  rerversetList(HeroNode herd){
        //如果当前链表为空，或者只要一个节点，无需反转，直接返回
        if(herd.next==null||herd.next.next==null){
            return ;
        }
        //定义一个辅助的指针（变量），帮助我们遍历原来的链表
        //先暂时保存当前节点的下一个节点，因为后面需要使用
        HeroNode cur=herd.next;
        //指向当前节点[cur]的下一个节点
        HeroNode next=null;
        HeroNode rerverseHead= new HeroNode( 0,"","" );
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表rerverseHead的最前端
        while (cur!=null){
        //先暂时保存当前节点的下一个节点，因为后面需要使用
            next=cur.next;
            //将cur下一个节点指向新链表的最前端
            cur.next=rerverseHead.next;
            //将cur连接到新链表上
            rerverseHead.next=cur;
            //cur后移
            cur=next;

        }
        //将head.next指向rerverseHead.next,实现链表的反转
        herd.next=rerverseHead.next;
    }

    /**
     *  方法：获取到单链表的节点的个数（如果是带头节点的链表，需求不统计头节点
     */
        public  static  int getLeagth(HeroNode heroNode){
            if(heroNode.next==null){
                //空链表
                return 0;
            }
    int length=0;
            HeroNode  cur=heroNode.next;
            while (cur!=null){
                length++;
                cur=cur.next;
            }
return length;

        }

    /**
     * 查找单链表中的倒数第k个节点，【新浪面试题]
     * 思路
     * 1.编写一个方法，接收head节点，同时接收一个index
     *2.index表示倒数第k个节点
     *3.先把链表从头到尾遍历，的到链表的长度length
     * 4.得到size后，我们从链表的第一个开始遍历（size-index)个，就可以得到
     *5.如果找到了，则返回该节点，否则放回null
     *
     */
    public  static  HeroNode findLastIndexNode(HeroNode head,int index){
            if (head.next==null){
                //链表为空
                return  null;
            }
            //第一个b遍历得到链表长度（节点个数）
        int size=getLeagth( head );
            //第二遍遍历 size-index位置，就是我们倒数第k给节点
        if(index<=0||index>size){
            return null;
        }
        //定义辅助变量,for循环定位
        //3//3-1=2
        HeroNode cur=head.next;
        for (int i=0;i<size-index;i++){
            cur=cur.next;
        }
        return cur;
    }

}

/**
 * 定义SingleLinkedList，管理我们的英雄
 */
class  SingleLinkedList{
      /**
       *  先初始化一个头节点，头节点不要动，不存放具体数据
       */
        private  HeroNode head=new HeroNode( 0,"","" );

        public  HeroNode getHead(){
            return  head;
        }

        //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1.找到链表的最后节点
    //2.将最后这个节点的next指向新的节点

    public  void  add(HeroNode heroNode){
        //因为head节点不能动。因此我们需要一个辅助变量temp
        HeroNode temp=head;
        //遍历链表，找到最后
        while (true){
            //找到链表最后
            if(temp.next==null){
                break;
            }
            //如果没有找到最后,就将temp后移
            temp=temp.next;
        }
        //当退出while循环时候，temp就指向了链表的最后
        //将最后这个节点的next 指向新的节点
        temp.next=heroNode;
    }
    /**
     *  第二种方式添加英雄时候，根据排名将英雄插如到指定位置
     *  （如果有这个排名，则添加失败，并给出提示）
     */
        public  void  addByOrder(HeroNode heroNode){
            //因为head节点不能动。因此我们需要一个辅助变量temp来帮助我们早到添加位置
            //因为是单链表，因为我们找的temp是位于添加位置的前一个节点，否则插入不了
            HeroNode temp=head;
            //flag标志添加编号是否存在，默认为false
            boolean flag=false;
            while (true){
                if (temp.next==null){
                    //说明temp已经到最后
                    break;
                }
           if (temp.next.no>heroNode.no){
               //位置找到，就在temp后面插入
                break;
           }else if(temp.next.no==heroNode.no){
               //说明希望添加的heroNode的编号已经存在
               flag=true;
               break;
           }
                //后移，遍历temp
                temp=temp.next;
            }
                //判断flag的值
            if(flag){
                System.out.println("说明插入的编号"+heroNode.no+"已经存在,不能加入");
            }else {
            //加入到链表中，temp的后面
            heroNode.next=temp.next;
            temp.next=heroNode;
            }



        }
    /**
     * 修改节点信息,根据no账号来修改,即no编号不能修改
     */
    public void  update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改i的节点，根据no编写
        HeroNode temp=head.next;
        boolean flag=false;
        while (true){
            if(temp==null){
                break;//表示链表已经遍历结束
            }
            if(temp.no==newHeroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //根据flag判断是否找到需要修改的节点
        if(flag){
        temp.name=newHeroNode.name;
        temp.nickname=newHeroNode.nickname;
        }else {
            System.out.println("没有扎到编号"+newHeroNode.no+"的节点，不能修改");
        }

    }

    /**
     *  删除节点信息
     *  思路：
     *  1.head不能动，因此我们需要一个temp辅助节点找到删除节点的前一个节点
     *  2.说明我们在比较时候,是temp.next.no和需要的删除的节点no比较
     */
    public  void  del(int no){
            HeroNode temp=head;
            boolean flag=false;
            while (true){
         if(temp.next==null){
             //已经到链表最后
             break;
         }
        if(temp.next.no==no){
            //找到删除节点的前一个节点
            flag=true;
            break;
        }
        temp=temp.next;
            }
            if(flag){
            temp.next=temp.next.next;

            }else {
                System.out.println("要删除的节点"+no+"不存在");
            }
    }
    /**
     *  显示链表【遍历】
     */
public  void  list(){

        //判断链表是否为空
    if(head.next==null){
        System.out.println("链表为空!!");
        return ;
    }
    //因为头节点，不能动，所以需要一个辅助变量来遍历
    HeroNode temp=head.next;
    while (true){
        //判断是否到链表最后
        if(temp==null){
            break;
        }
        //输出节点信息
        System.out.println(temp);
        //将temp后移，一定小心
        temp=temp.next;
    }

}




}
/**
 * 定义heroNodE,每个heroNode就是一个节点
 */
class  HeroNode{
    public  int no;
    public  String name;
    public  String nickname;
    public  HeroNode next;
    //构造器
    public  HeroNode(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    /**
     * 为了显示方法。我们重写toString()方法
     * @return s
     */
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}