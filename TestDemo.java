/**
 * Author : Zhang Zhe
 */
public class TestDemo {
    //合并两个有序单列表
    public static ListNode mergeTwoLists(ListNode headA,ListNode headB){
        ListNode newHead = new ListNode(-1);
        ListNode tmp = newHead;
        while (headA != null && headB != null){
            if(headA.data < headB.data){
                tmp.next = headA;
                headA = headA.next;
                tmp = tmp.next;
            }else{
                tmp.next = headB;
                headB = headB.next;
                tmp = tmp.next;
            }
        }
        if(headA == null){
            tmp.next = headB;
        }else {
            tmp.next = headA;
        }
        return newHead.next;
    }
    //找相交节点
    public static ListNode getIntersectionlink(ListNode headA,ListNode headB){
        if(headA == null || headB == null){
            return null;
        }

        ListNode pL = headA;//永远指向长的单列表。
        ListNode pS = headB;//永远指向短的单列表。

        int lenA = 0;
        int lenB = 0;

        //求lenA与 lenB 的值。
        while(pL != null){
            lenA++;
            pL = pL.next;
        }
        while(pS != null){
            lenB++;
            pS = pS.next;
        }
        pL = headA;
        pS = headB;
        //差值-->最长的单列表先走len步
        int len = lenA - lenB;
        if(len<0){
            pL = headB;
            pS = headA;
            len = lenB - lenA;
        }
        //先让pL先走len步
        while (len > 0){
            pL = pL.next;
            len--;
        }
        //pL与pS一起走
        while (pL != pS){
            pL = pL.next;
            pS = pS.next;
        }
        return pL;
    }
    public static void main(String[] args) {
        List node = new List();
        node.addLast(1);
        node.addLast(4);
        node.addLast(6);
        node.addLast(15);
        node.addLast(267);
        node.display();
        List nodex = new List();
        nodex.addLast(12);
        nodex.addLast(14);
        nodex.addLast(16);
        nodex.addLast(25);
        nodex.addLast(27);
        nodex.display();
       ListNode x = mergeTwoLists(node.head,nodex.head);
       while (x != null) {
           System.out.print(x.data + "  ");
           x = x.next;
       }
        System.out.println();
    }
}