package com.example.demo;

import com.example.demo.jmx.QueueSampler;
import com.example.demo.jmx.TodoList;
import com.example.demo.jmx.TodoListMBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("mike.example:type=TodoList");
        TodoListMBean mbean = new TodoList();
        mbs.registerMBean(mbean, name);

        ObjectName mxbeanName = new ObjectName("mike.example:type=QueueSampler");

        Queue<String> queue = new ArrayBlockingQueue<String>(10);
        queue.add("Request-1");
        queue.add("Request-2");
        queue.add("Request-3");
        QueueSampler mxbean = new QueueSampler(queue);

        mbs.registerMBean(mxbean, mxbeanName);

        SpringApplication.run(DemoApplication.class, args);
    }

}
