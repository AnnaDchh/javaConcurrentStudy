package com.test;

/**
 * 使用继承方式模拟火车站售票窗口，开启窗口售票，总票数为100张
 * 创建方式（注：暂不涉及线程安全问题，进简单创建一个线程）：
 * // 1、创建一个继承Thread类的子类
 * class SubClassName extends Thread {
 *      // 2、实现重写Thread类的run()方法,实现子线程要完成的功能
 *     public void run() {
 *          // TODO 子线程完成的功能
 *     }
 * }
 *
 * public static void main(String[] args) {
 *      // 3、创建子类对象
 *      SubClassName scn = new SubClassName();
 *      // 4、调用线程的start()方法：启动此线程，调用相应的run()方法
 *      scn.start();
 * }
 *
 * @author Anna.
 * @date 2019-08-26
 */
public class SellTicketWindowsByExtendsTest01 {
    public static void main(String[] args) {
        // 创建共享数据
        Windows windows = new Windows(100);
        // 3、创建子类对象
        SellTicketThread scn = new SellTicketThread(windows);
        // 4、调用线程的start()方法：启动此线程，调用相应的run()方法
        scn.setName("窗口1");
        scn.start();
    }
}

/** 创建共享数据 */
class Windows {
    private int ticket;

    public Windows(int ticket) {
        this.ticket = ticket;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public void sell(){
        ticket--;
        System.out.println(Thread.currentThread().getName() + ":售出一张票，还剩"  + ticket + "张票");
    }
}
/** 1、创建一个继承Thread类的子类*/
class SellTicketThread extends  Thread {
    private Windows windows;

    public SellTicketThread(Windows windows){
        this.windows = windows;
    }

    public Windows getWindows() {
        return windows;
    }

    public void setWindows(Windows windows) {
        this.windows = windows;
    }

    /** 2、实现重写Thread类的run()方法,实现子线程要完成的功能*/
    @Override
    public void run() {
        // 子线程完成的功能
        while (windows.getTicket() > 0) {
            windows.sell();
        }
    }
}
