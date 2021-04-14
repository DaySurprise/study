package com.daysurprise.study.leetCode;

/**
 * @Class: com.daysurprise.study.leetCode.TwoNumAdd
 * @Author: daysurprise
 * @Date: 2021/3/23
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 两数相加
 */
public class TwoNumAdd {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(2);
        list1.setNext(new ListNode(4));
        list1.next.setNext(new ListNode(3));

        ListNode list2 = new ListNode(2);
        list1.setNext(new ListNode(4));
        list1.next.setNext(new ListNode(3));

    }

    public static ListNode twoNumsAdd(ListNode list1, ListNode list2){
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;
        while(list1 != null || list2 != null){
            int n1 = list1 == null ? 0 : list1.value;
            int n2 = list2 == null ? 0 : list2.value;
            int sum = n1 + n2 + carry;
            if (head == null){
                head = tail = new ListNode(sum % 10);
            }else{
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (list1 != null){
                list1 = list1.next;
            }
            if (list2 != null){
                list2 = list2.next;
            }
        }
        if (carry > 0){
            tail.next = new ListNode(carry);
        }
        return head;
    }

    static class ListNode{
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }
}
