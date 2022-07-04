# JDK 工具使用总结

## jps 虚拟机进程状态工具
jps（JVM Process Status Tool），它的功能也和ps命令类似：可以列出正在运行的虚拟机进程，并显示虚拟机执行主类（Main Class,main()函数所在的类）名称以及这些进程的本地虚拟机唯一ID（Local Virtual Machine Identifier,LVMID）。

    jps命令格式：
    jps [options] [hostid]
jps可以通过RMI协议查询开启了RMI服务的远程虚拟机进程状态，hostid为RMI注册表中注册的主机名。jps的其他常用选项如下：

    -q 只输出LVMID 省略主类的名称
    -m 输出虚拟机进程启动时传递给主类main()函数的参数。
    -l 输出主类的全名，如果进程执行的是jar包，输出jar路径。
    -v 输出虚拟机进程启动时的JVM参数

对于本地虚拟机进程来说，LVMID与操作系统的进程ID（Process Identifier,PID）是一致的。

## jstat 虚拟机统计信息监视工具
jstat（JVM Statistics Monitoring Tool）用于监视虚拟机各种运行状态信息。可以显示本地或者远程虚拟机进程中的类装载、内存、垃圾收集、JIT编译等运行数据。

在没有GUI图形界面，只提供了纯文本控制台环境的服务器上，它将是运行期定位虚拟机性能问题的首选工具

    jstat命令格式：
    jstat [option vmid [interval [s|ms][count]]]
选项option代表着用户希望查询的虚拟机信息，主要分为3类：类装载、垃圾收集、运行期编译状况，具体选项及作用如下：

    -class 显示ClassLoad的相关信息；监视类装载，卸载数量，总空间 所耗费时间等信息。
    -compiler 显示JIT编译的相关信息；
    -gc 显示和gc相关的堆信息；监视java堆状况，eden区，survivor区 老年代永久代等容量
    -gccapacity 　　 显示各个代的容量以及使用情况；各个区域使用到的最大最小空间。
    -gcmetacapacity 显示metaspace的大小
    -gcnew 显示新生代信息；
    -gcnewcapacity 显示新生代大小和使用情况；
    -gcold 显示老年代和永久代的信息；
    -gcoldcapacity 显示老年代的大小；
    -gcutil　　 显示垃圾收集信息；
    -gccause 显示垃圾回收的相关信息（通-gcutil）,同时显示最后一次或当前正在发生的垃圾回收的诱因；
    -printcompilation 输出JIT编译的方法信息

## jinfo：Java配置信息工具（命令行工具）
jinfo（Configuration Info for Java）的作用是实时地查看和调整虚拟机各项参数。使用jps命令的-v参数可以查看虚拟机启动时显式指定的参数列表，但如果想知道未被显式指定的参数的系统默认值，除了去找资料外，就只能使用jinfo的-flag选项进行查询了。

    jinfo命令格式：
    jinfo [option] pid

## jmap：Java内存映像工具（命令行工具）
jmap（Memory Map for Java）命令用于生成堆转储快照（一般称为heapdump或dump文件）。jmap的作用并不仅仅是为了获取dump文件，它还可以查询finalize执行队列、Java堆和永久代的详细信息，如空间使用率、当前用的是哪种收集器等。

    jmap命令格式
    jmap [option] vmid
option选项的合法值与具体含义如下：
    
    option： 选项参数。
    pid： 需要打印配置信息的进程ID。
    executable： 产生核心dump的Java可执行文件。
    core： 需要打印配置信息的核心文件。
    server-id 可选的唯一id，如果相同的远程主机上运行了多台调试服务器，用此选项参数标识服务器。
    remote server IP or hostname 远程调试服务器的IP地址或主机名。

option

    no option： 查看进程的内存映像信息,类似 Solaris pmap 命令。
    heap： 显示Java堆详细信息
    histo[:live]： 显示堆中对象的统计信息
    clstats：打印类加载器信息
    finalizerinfo： 显示在F-Queue队列等待Finalizer线程执行finalizer方法的对象
    dump:<dump-options>：生成堆转储快照
    F： 当-dump没有响应时，使用-dump或者-histo参数. 在这个模式下,live子参数无效.
    help：打印帮助信息
    J<flag>：指定传递给运行jmap的JVM的参数

## jhat：虚拟机堆转储快照分析工具（命令行工具）
jhat（JVM Heap Analysis Tool）命令与jmap搭配使用，来分析jmap生成的堆转储快照。jhat内置了一个微型的HTTP/HTML服务器，生成dump文件的分析结果后，可以在浏览器中查看。

## jstack：Java堆栈跟踪工具（命令行工具）
jstack（Stack Trace for Java）命令用于生成虚拟机当前时刻的线程快照（一般称为threaddump或者javacore文件）。线程快照就是当前虚拟机内每一条线程正在执行的方法堆栈的集合，生成线程快照的主要目的是定位线程出现长时间停顿的原因，如线程间死锁、死循环、请求外部资源导致的长时间等待等都是导致线程长时间停顿的常见原因。线程出现停顿的时候通过jstack来查看各个线程的调用堆栈，就可以知道没有响应的线程到底在后台做些什么事情，或者等待着什么资源。

jstack命令格式：
    
    jstack [option] vmid
option选项的合法值与具体含义如下：
    -F 当正常输出的请求不被响应时，强制输出线程堆栈
    -l 除堆栈外，显示关于锁的附加信息
    -m 如果调用到本地方法的话 可以显示c/c++ 的堆栈。


## JConsole：Java监视与管理控制台（可视化工具）
JConsole（Java Monitoring and Management Console）是一种基于JMX的可视化监视、管理工具。它管理部分的功能是针对JMX MBean进行管理，由于MBean可以使用代码、中间件服务器的管理控制台或者所有符合JMX规范的软件进行访问，所以本节将会着重介绍JConsole监视部分的功能。

JConsole主界面，可以看到主界面里共包括“概述”、“内存”、“线程”、“类”、“VM摘要”、"MBean"6个页签；

## javap
javap是JDK自带的反汇编器，可以查看java编译器为我们生成的字节码。通过它，我们可以对照源代码和字节码，从而了解很多编译器内部的工作。
语法：
　　javap [ 命令选项 ] class. . .
　　javap 命令用于解析类文件。其输出取决于所用的选项。若没有使用选项，javap 将输出传递给它的类的 public 域及方法。javap 将其输出到标准输出设备上。
命令选项
　　-help 输出 javap 的帮助信息。
　　-l 输出行及局部变量表。
　　-b 确保与 JDK 1.1 javap 的向后兼容性。
　　-public 只显示 public 类及成员。
　　-protected 只显示 protected 和 public 类及成员。
　　-package 只显示包、protected 和 public 类及成员。这是缺省设置。
　　-private 显示所有类和成员。
　　-J[flag] 直接将 flag 传给运行时系统。
　　-s 输出内部类型签名。
　　-c 输出类中各方法的未解析的代码，即构成 Java 字节码的指令。
　　-verbose 输出堆栈大小、各方法的 locals 及 args 数,以及class文件的编译版本
　　-classpath[路径] 指定 javap 用来查找类的路径。如果设置了该选项，则它将覆盖缺省值或 CLASSPATH 环境变量。目录用冒号分隔。
 　 -bootclasspath[路径] 指定加载自举类所用的路径。缺省情况下，自举类是实现核心 Java 平台的类，位于 jrelib下面。
　　-extdirs[dirs] 覆盖搜索安装方式扩展的位置。扩展的缺省位置是 jrelibext。

## jcmd
jcmd。他是一个多功能的工具，可以用它来导出堆、查看Java进程、导出线程信息、执行GC、还可以进行采样分析（jmc 工具的飞行记录器）

    jcmd <pid | main class> <command ... | PerfCounter.print | -f  file>
    jcmd -l
    jcmd -h

pid：接收诊断命令请求的进程ID。
 main class ：接收诊断命令请求的进程的main类。匹配进程时，main类名称中包含指定子字符串的任何进程均是匹配的。如果多个正在运行的Java进程共享同一个main类，诊断命令请求将会发送到所有的这些进程中。
 command：接收诊断命令请求的进程的main类。匹配进程时，main类名称中包含指定子字符串的任何进程均是匹配的。如果多个正在运行的Java进程共享同一个main类，诊断命令请求将会发送到所有的这些进程中。
 注意: 如果任何参数含有空格，你必须使用英文的单引号或双引号将其包围起来。 此外，你必须使用转义字符来转移参数中的单引号或双引号，以阻止操作系统shell处理这些引用标记。当然，你也可以在参数两侧加上单引号，然后在参数内使用双引号(或者，在参数两侧加上双引号，在参数中使用单引号)。
 
Perfcounter.print：打印目标Java进程上可用的性能计数器。性能计数器的列表可能会随着Java进程的不同而产生变化。
-f file：从文件file中读取命令，然后在目标Java进程上调用这些命令。在file中，每个命令必须写在单独的一行。以"#"开头的行会被忽略。当所有行的命令被调用完毕后，或者读取到含有stop关键字的命令，将会终止对file的处理。
-l：查看所有的进程列表信息。
-h：查看帮助信息。（同 -help）
