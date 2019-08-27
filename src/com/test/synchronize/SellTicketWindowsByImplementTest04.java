package com.test.synchronize;

/**
 * 使用实现方式模拟火车站售票窗口，开启窗口售票，总票数为100张
 * java实现线程的同步机制：
 * 	（2）、同步方法,放在方法声明中，表示整个个方法为同步方法
 * 		public synchronized void show () {
 *
 * 		}
 *
 * 		    将操作共享数据的方法声明为synchronized，即此方法为同步方法，能够保证当其中一个线程再执行此方法时，
 * 		其他线程在外等待直至此线程执行完成此方法，同步方法的锁：this，当前对象。
 *
 * 		  (注:在实现方式中可以使用同步方法，但是在继承方式中由于声明了多个对象，所有不能使用直接使用synchronized声明)
 *
 * @author Anna.
 * @date 2019-08-26
 */
public class SellTicketWindowsByImplementTest04 {
    public static void main(String[] args) {
        // 创建共享数据
        Windows2 windows2 = new Windows2(100);
        // 3、创建实现Runnable接口的子类对象
        SellTicketThread2 scn = new SellTicketThread2(windows2);
        // 4、创建一个Thread对象
        Thread th = new Thread(scn);
        th.setName("窗口1");
        Thread th2 = new Thread(scn);
        th2.setName("窗口2");
        // 5、调用线程的start()方法：启动此线程，调用相应的run()方法
        th.start();
        th2.start();
    }
}

/**
 * 创建共享数据
 */
class Windows2 {
    private int ticket;

    public Windows2(int ticket) {
        this.ticket = ticket;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public synchronized void sell() {
        if (ticket > 0) {
            ticket--;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":售出一张票，还剩" + ticket + "张票");
        }
    }
}

/**
 * 1、创建一个实现Runnable接口的子类
 */
class SellTicketThread2 implements Runnable {
    private Windows2 windows2;

    public SellTicketThread2(Windows2 windows2) {
        this.windows2 = windows2;
    }

    public Windows2 getWindows2() {
        return windows2;
    }

    public void setWindows2(Windows2 windows2) {
        this.windows2 = windows2;
    }

    /**
     * 2、实现重写Thread类的run()方法,实现子线程要完成的功能
     */
    @Override
    public void run() {
        // 子线程完成的功能
        // 同步代码块
        while (windows2.getTicket() > 0) {
            windows2.sell();
        }
    }
}