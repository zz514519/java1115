/**
 * Author : Zhang Zhe
 */
class ListNode{
    public int data;
    public ListNode next;
    public  ListNode(int data){
        this.data=data;
        this.next=null;
    }
}
class List{
    public ListNode head;
    public List(){
        this.head=null;
    }
    //头插法
    public void addFirst(int data) {
        ListNode node = new ListNode(data);
        if (this.head == null) {
            this.head = node;
        } else {
            node.next = this.head;
            this.head = node;
        }
    }

    //尾插法
    public void addLast(int data) {
        ListNode node = new ListNode(data);
        ListNode cur = this.head;
        if (this.head == null) {
            this.head = node;
        } else {
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
    }

    //打印单列表
    public void display() {
        ListNode cur = this.head;
        if (this.head == null) {
            return;
        } else {
            while (cur != null) {
                System.out.print(cur.data + "  ");
                cur = cur.next;
            }
            System.out.println();
        }
    }
    //插入到index位置
    //任意位置插入，第一个数据节点为0号下标
    //获取长度。
    public int getLength(){
        ListNode cur = this.head;
        int count = 0;
        while (cur!=null){
            count++;
            cur=cur.next;
        }
        return count;
    }
    public boolean addIndex(int index , int data){
        if(index<0||index>getLength()){
            return false;
        }
        if(index==0){
            addFirst(data);
            return true;
        }else{
            ListNode node = new ListNode(data);
            ListNode cur = this.head;
            int count = 0;
            while(count < index - 1){
                cur=cur.next;
                count++;
            }
            node.next = cur.next;
            cur.next = node;
        }
        return true;
    }
    //查询是否含有key
    public boolean contains(int key) {
        ListNode cur = this.head;
        while(cur!=null){
            if(cur.data==key){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }
    //删除key节点
    //先找前驱
    public ListNode seachPrev(int key){
        ListNode cur = this.head;
        while (cur.next!=null){
            if (cur.next.data==key){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }
    public void remove(int key){
        ListNode cur = seachPrev(key);
        if(cur == null){
            System.out.println("没有要删除的节点！");
            return ;
        }
        if(this.head.data==key){
            this.head=this.head.next;
        }
        ListNode del = cur.next;
        cur.next = del.next;
    }

    //删除所有的key节点
    public void removeAll(int key){
        ListNode cur = this.head;
        ListNode prev = this.head.next;
        while (prev !=null){
            if(cur.next.data==key){
                cur.next=prev.next;
                prev=prev.next;
            }else{
                cur = prev;
                prev = prev.next;
            }
        }
        if(this.head.data==key){
            this.head=this.head.next;
        }
    }
    //逆序单列表
    public ListNode reverseList() {
        ListNode cur = this.head;
        ListNode prev = null;
        ListNode newHead = null;
        while (cur != null) {
            ListNode curNext = cur.next;
            if (curNext == null) {
                newHead = cur;
            }
            cur.next = prev;
            prev = cur;
            cur = curNext;
        }
        return newHead;
    }
    //打印逆序单列表
    public void play(){
        ListNode cur = reverseList();
        if(cur==null){
            return ;
        }
        while (cur!=null){
            System.out.println(cur.data+"  ");
            cur = cur.next;
        }
        System.out.println();
    }
    //返回中间值
    public ListNode middleNode(){
        ListNode fast = this.head;
        ListNode slow = this.head;
        while (fast!=null&&fast!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    //寻找倒数第K个节点
    public ListNode findKthToTail(int k){
        ListNode fast = this.head;
        ListNode slow = this.head;
        while (k<0){
            fast = fast.next;
            k--;
        }
        while (fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    //以X为基准分割链表
    public ListNode partition(int x){
        ListNode bs = null;
        ListNode be = null;
        ListNode as = null;
        ListNode ae = null;

        ListNode cur = this.head;
        while(cur != null){
            if(cur.data < x){
                //是不是第一次插入
                if(bs == null){
                    bs = cur;
                    be = bs;
                }else{
                    be.next = cur;
                    be = be.next;
                }
            }else {
                //是不是第一次插入
                if(as == null){
                    as = cur;
                    ae = as;
                }else{
                    ae.next = cur;
                    ae = ae.next;
                }
            }
            cur = cur.next;
        }
        if(bs == null){
            return as;
        }
        be.next = as;
        if(as!=null){
            ae.next = null;
        }
        return bs;
    }
    //删除重复的节点
    public ListNode deleteDuplication(){
        if(this.head==null){
            return null;
        }
        ListNode cur = this.head;
        ListNode newHead = new ListNode(-1);
        ListNode tmp = newHead;
        while(cur!=null){
            if(cur.next!=null&&cur.data==cur.next.data){
                while (cur.next!=null&&cur.data==cur.next.data) {
                    cur = cur.next;
                }
                cur = cur.next;
            }else{
                tmp.next = cur;
                tmp = tmp.next;
                cur = cur.next;
            }
        }
        tmp.next = null;
        return newHead.next;
    }
    //回文结构列表
    public boolean chkPalindrome(){
        if(this.head == null){
            return false;
        }
        if(this.head.next == null){
            return true;
        }
        //1.找单列表的中间节点
        ListNode fast = this.head;
        ListNode slow = this.head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //2.反转单列表
        ListNode cur = slow.next;
        while (cur != null){
            ListNode curNext = cur.next;
            cur.next = slow;
            slow = cur;
            cur = curNext;
        }
        //3.fast/slow往前head往后
        while(slow != this.head){
            if(slow.data == this.head.data){
                if(this.head.next == slow){
                    return true;
                }
                slow = slow.next;
                this.head = this.head.next;
            }else {
                return false;
            }
        }
        return true;
    }
    //自创环
    public void creteLoop(){
        ListNode cur = this.head;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = this.head.next.next;
    }
    //判断是否有环--------环在后面
    public boolean hasCycle(){
        ListNode fast = this.head;
        ListNode slow = this.head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                break;
            }
        }
        if(fast == null || fast.next == null){
            return false;
        }
        return true;
    }
    //开始入环的第一个节点
    public ListNode detectCycle(){
        ListNode fast = this.head;
        ListNode slow = this.head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                break;
            }
        }
        if(fast == null || fast.next == null){
            return null;
        }
        slow = this.head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}