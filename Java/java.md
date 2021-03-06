# MS

*  **全局唯一有序 ID**

  * ==UUID==
    * 一组32位数的16进制数字所构成，字符过长，且一般是无序的
  * snowFlake雪花算法
    * 雪花ID生成的是一个==64位的二进制Long型正整数，按照时间自增排序，效率高==
  * redis
    * 单线程的Redis实现了一个原子操作==INCR和INCRBY==实现递增的操作

*  **冯诺依曼体系**

1. 计算机处理的数据和指令一律用二进制数表示
2. 顺序执行程序
   * 计算机运行过程中，把要执行的程序和处理的数据首先存入主存储器（内存），计算机执行程序时，将自动地并按顺序从主存储器中取出指令一条一条地执行，这一概念称作顺序执行程序
3. ==计算机硬件由运算器、控制器、存储器、输入设备和输出设备五大部分组成==

* Java 中应该使用什么数据类型来代表价格？

  * 如果不是特别关心内存和性能的话，使用BigDecimal，否则使用预定义精度的 double 类型。
* 怎么将 byte 转换为 String

  * 可以使用 String 接收 byte[] 参数的构造器来进行转换，需要注意的点是要使用的正确的编码

* **并发与并行**

  两个线程先后执行，即不支持并发也不支持并行

  ==两个线程能间断执行，这叫并发，单核==

  两个线程同时执行，这叫并行，多核的并发


* a++和++a
  * ==单独==使用时，功能一模一样，都是让a的值增加1；
  * 不同的是与赋值号“=”一起使用时，y=++a表示先将a的值增加1后，再把值赋给y；而y=a++表示先把a的值给y，a自己再增加1.
  * ==int a = 1; int b = a++;   a为2，b为1；==
  * int a = 1; int b = ++a;   === a为2，b为2
* ==a = a + b 与 a += b 的区别==(int a , char b)

  * 对于==同样类型==的a,b来说，结果确实没有什么区别
  * 对于不同类型的a,b来说，==类型的转换==

    * 运算过程中，低精度的类型自动向高精度类型转换；高精度的数值显示强制转换后赋值给低精度类型变量
  * +=运算中，`b + = a`结合了强制类型转换的功能，因此，不会出现编译错误
  * 而对于`b=a+b;`简单的运算，没有类型转换（`b = (char)(a+b)`），在编译过程中会报错
* Java 中的编译期常量是什么？使用它又什么风险？

  * 公共静态不可变（==public static final== ）变量也就是我们所说的编译期常量，这里的 public 可选的。实际上这些变量在编译时会被替换掉，因为编译器知道这些变量的值，并且知道这些变量在运行时不能改变。这种方式存在的一个问题是你使用了一个内部的或第三方库中的公有编译时常量，但是==这个值后面被其他人改变了==，但是你的客户端仍然在使用老的值，甚至你已经部署了一个新的jar。为了避免这种情况，当你在更新依赖 JAR 文件时，确保==重新编译==你的程序
* Java 中怎么打印数组

  * Arrays.toString() 和 Arrays.deepToString() 方法来打印数组
* try {return ...} finally {}
  * 当try语句退出时肯定会执行finally语句，这确保了即使==发了一个意想不到的异常也会执行finally语句块==
  * finally的用处不仅是用来处理异常——它可以让程序员不会因为return、continue、或者break语句而忽略了==清理代码==
  * 注意，当try或者catch的代码在运行的时候，==JVM退出==了。那么finally语句块就不会执行。同样，如果线程在运行try或者catch的代码时被中断了或者被杀死了(killed)，那么finally语句可能也不会执行了，即使整个运用还会继续执行。
  * ==使用return语句把控制权转移给其他的方法前会执行finally语句==。
  * ==如果try语句里有return==，那么代码的行为如下：
    * .如果有返回值，就把返回值保存到局部变量中
    * 执行jsr指令跳到finally语句里执行
    * 执行完finally语句后，返回之前保存在局部变量表里的值
  * ==规范规定了，当try和finally里都有return时，会忽略try的return，而使用finally的return==
* ==object有哪些方法-9==
  
  * clone() 、toString() 、equals(Object obj) 、hashCode() 
  * finalize() 当垃圾回收器确定不存在对该对象的更多引用时，由对象的垃圾回收器调用此方法
  * getClass() 返回此 Object 的运行时类
  * notify() 唤醒在此对象监视器上等待的单个线程
  * notifyAll() 唤醒在此对象监视器上等待的所有线程
  * wait() 在其他线程调用此对象的 notify() 方法或 notifyAll() 方法前，导致当前线程等待
  * wait(long timeout)….,超过指定的时间量前，导致当前线程等待
  * wait(long timeout, int nanos)或者其他某个线程中断当前线程，或者已超过某个实际时间量前，导致当前线程等待

# 经验

* 【强制】==线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式==，这样的处理⽅式让写的同学更加明确线程池的运行规则，规避资源耗尽的⻛风险
  * 说明：Executors 返回的线程池对象的弊端如下:
    * 1）FixedThreadPool 和 SingleThreadPool
      * 允许的==请求队列长度为 Integer.MAX_VALUE==，可能会堆积大量的请求，从而导致OOM。
    * 2）CachedThreadPool 和 ScheduledThreadPool：
      * 允许的==创建线程数量为 Integer.MAX_VALUE==，可能会创建⼤量的线程，从⽽导致OOM。

* 【强制】==对多个资源、数据库表、对象同时加锁时，需要保持一致的加锁顺序，否则可能会造成死锁==。

  * 说明：线程一需要对表 A、B、C 依次全部加锁后才可以进行更新操作，那么线程二的加锁顺序也必须是 A、B、C，否则可能出现死锁。
* 【强制】==并发修改同一记录时，避免更新丢失，需要加锁。要么在应用层加锁，要么在缓存加锁，要么在数据库层使用乐观锁，使用 version 作为更新依据。==

  * 说明：如果每次访问冲突概率⼩于 20%，推荐使用乐观锁，否则使用悲观锁。乐观锁的重试次数不得⼩于3次。
* 【强制】==多线程并行处理定时任务时，Timer 运行多个 TimeTask 时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用 ScheduledExecutorService 则没有这个问题==。
* 【参考】==volatile 解决多线程内存不可见问题。对于一写多读，是可以解决变量同步问题，但是如果多写，同样⽆法解决线程安全问题==。如果是 count++操作，使⽤用如下类实现： 
  AtomicInteger count = new AtomicInteger(); count.addAndGet(1);

   如果是 JDK8，推荐使⽤LongAdder 对象，⽐AtomicLong 性能更好(减少乐观锁的重试次数)。

* 【参考】 HashMap 在容量不够进行 resize 时由于高并发可能出现死链，导致 CPU 飙升，在开发过程中可以使⽤其它数据结构或加锁来规避此⻛险。

* 【参考】下列情形，需要进行参数校验：

  * 1) 调⽤频次低的方法。 
  * 2) 执行时间开销很大的⽅法。此情形中，参数校验时间几乎可以忽略不计，但如果因为参数错误导致中间执行回退，或者错误，那得不偿失。
  * 3) 需要极高稳定性和可用性的方法。 
  * 4) 对外提供的开放接口，不管是RPC/API/HTTP接⼝。 
  * 5) 敏感权限⼊口。

* 【参考】下列情形，不需要进行参数校验：

  * 1) 极有可能被循环调用的方法。但在方法说明里必须注明外部参数检查要求。
  * 2) 底层调用频度⽐较高的方法。毕竟是像纯净水过滤的最后一道，参数错误不不太可能到底层才会暴露问题。⼀般 DAO 层与 Service 层都在同一个应用中，部署在同一台服务器中，所以DAO的参数校验，可以省略。
  * 3) 被声明成private只会被⾃己代码所调用的方法，如果能够确定调用⽅法的代码传入参数已经做过检查或者肯定不会有问题，此时可以不校验参数。

* 【强制】在使⽤正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度。

  * 说明：不要在方法体内定义：Pattern pattern = Pattern.compile(“规则”);

* 【强制】==注意 Math.random() 这个⽅法返回是 double 类型，注意取值的范围 0≤x<1==(能够取到零值，注意除零异常)，如果想获取整数类型的随机数，不要将 x 放大10的若干倍然后取整，直接使⽤ Random 对象的 nextInt 或者 nextLong ⽅方法。

* 【强制】获取当前毫秒数 System.currentTimeMillis(); 而不是 new Date().getTime();

* 【强制】Java 类库中定义的可以通过预检查⽅式规避的 RuntimeException 异常不应该通过catch 的方式来处理，比如：NullPointerException，IndexOutOfBoundsException 等等。 

  * 说明：无法通过预检查的异常除外，比如，在解析字符串形式的数字时，不得不通过 catch NumberFormatException 来实现。
  * 正例：==if (obj != null) {…}==

* 【强制】==捕获异常是为了处理它，不要捕获了却什么都不处理而抛弃之，如果不想处理它，请将该异常抛给它的调用者。最外层的业务使用者，必须处理异常，将其转化为用户可以理理解的内容==

* 【强制】有 try 块放到了事务代码中，catch 异常后，如果需要回滚事务，⼀定要注意手动回滚事务

* 【推荐】防止 NPE，是程序员的基本修养，注意 NPE 产生的场景：

  * ==返回类型为基本数据类型，return 包装数据类型的对象时，自动拆箱有可能产⽣ NPE==。反例：public int f() { return Integer 对象}， 如果为 null，⾃动解箱抛 NPE。
  * 数据库的查询结果可能为null
  * 集合⾥的元素即使isNotEmpty，取出的数据元素也可能为null
  * ==远程调用返回对象时，一律要求进行空指针判断，防⽌NPE==
  * ==对于Session中获取的数据，建议NPE检查，避免空指针==
  * 级联调用obj.getA().getB().getC();一连串调用，易产生NPE
    * 正例：使用 JDK8 的 Optional 类来防止 NPE 问题

* ==对于公司外的 http/api 开放接口必须使用“错误码”；而应⽤内部推荐异常抛出； 跨应用间 RPC 调⽤优先考虑使用 Result 方式，封装 isSuccess()方法、“错误码”、“错误简 短信息”。== 

  * 说明：==关于RPC 方法返回方式使用 Result ⽅式的理由==：
    * 使用抛异常返回⽅式，调用⽅如果没有捕获到就会产⽣运行时错误
    * 如果不加栈信息，只是new自定义异常，加⼊⾃己的理解的error message，对于调⽤端解决问题的帮助不会太多。如果加了栈信息，在频繁调用出错的情况下，数据序列化和传输的性能损耗也是问题。

* 【强制】用户请求传入的任何参数必须做有效性验证。 
  * 说明：忽略参数校验可能导致
    * page size 过大导致内存溢出  
    * 恶意 order by 导致数据库慢查询  
    * 任意重定向  
    * SQL 注入 
    * 反序列化注⼊ 
    *  正则输入源串拒绝服务 ReDoS
  * 说明：Java 代码⽤正则来验证客户端的输入，有些正则写法验证普通用户输⼊没有问题， 但是如果攻击人员使用的是特殊构造的字符串来验证，有可能导致死循环的结果。

* 【强制】表单、A JAX提交必须执行CSRF安全验证。 

  * 说明：CSRF(Cross-site request forgery)跨站请求伪造是一类常⻅编程漏洞。对于存在 CSRF 漏洞的应用/网站，攻击者可以事先构造好URL，只要受害者⽤户一访问，后台便在用户不知情的情况下对数据库中⽤户参数进行相应修改
* 【强制】在使用平台资源，譬如短信、邮件、电话、下单、⽀付，必须实现正确的防重放的机制，如数量限制、疲劳度控制、验证码校验，避免被滥刷而导致资损。

  * 说明：如注册时发送验证码到⼿机，如果没有限制次数和频率，那么可以利⽤用此功能骚扰到其它用户，并造成短信平台资源浪费。
* 【推荐】发贴、评论、发送即时消息等用户生成内容的场景必须实现防刷、⽂本内容违禁词过滤等⻛控策略

# 类

## 基础

* 类中5个成员：成员变量、初始化块、构造器、方法、内部类

* byte 1个字节 、short 2个字节 、char 2个字节 、int 4个字节、 float 4个字节、long 8个字节 、 double 8个字节 boolean

  Integer：-128~127自动装箱(数组中缓存起来，不在这个范围内的，每次都是新建实例)

  Integer.valueof(6) -128~127会缓存该方法创建的对象，若new，则新对象

  3*0.1==0.3是true还是false，为什么？false，因为有些浮点数不能完全精确的表示出来

* 基本类型和字符串之间的转换

  包装类提供的 parseXxx(String str)`int i = Integer.parseInt("123");` `Integer integer = Integer.valueOf("123");`

   静态⽅法.Xxx(String str)构造器`Integer it = new Integer("123");`

  String.valueof(XXX xx) 、Xxx + "" =>基本类型转字符串

* 定义类的主要作⽤：定义变量、创建实例和作为⽗类被继承

* 面向对象三大特征：封装、继承、多态

* 变量：

  1. 成员变量：(实例变量、类变量）  =》  ⽆需显示初始化，具有默认初始化，堆内存中
  2. 局部变量：(形参、⽅法局部变量、代码块局部变量) => 除形参外，使用之前都必须显示初始化

* 面向对象编程中的基本目的：

  * 让代码只操纵对基类的引用。这样，如果要添加一个新类来扩展程序，就不会影响到原来的代码

* this总是指向调⽤该方法的对象

  1. 构造器中引用该构造器正在初始化的对象
  2. 在⽅法中引用调用该方法的对象

  最大作用：让类中的一个方法，访问该类里的另一个方法或实例变量

  允许对象的⼀个成员直接调用另一个成员，可以省略this前缀

  如果确实需要在静态方法中访问另一个普通⽅法，则只能重新创建一个对象去访问

* 形参个数可变的⽅法 test(int a, String ... books) books可以是数组

* 向下类型转型前，先instanceof（对象是不是某个特定类型的实例）

* 值传递，引用变量存放在栈内存里，指向真正存储在堆内存中的对象

  1. ==基本类型：传递值的副本==。封装类型（如Integer）：传递的也是值的副本，约定
  2. ==引用类型：传递内存空间地址==

  ==记住==

  ```java
  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
      //不是int类型
      Integer i1 = 1, i2 =2;
      //自动装箱  Integer a = 1 => Integer a = Integer.valueOf(1);
      // -128-127缓存，提前分配好地址
      System.out.println("Before: i1 = " + i1 + ", i2 = " + i2);
      swap(i1, i2);
      System.out.println("After: i1 = " + i1 + ", i2 = " + i2);
  }
  
  private static void swap(Integer i1, Integer i2) throws NoSuchFieldException, IllegalAccessException {
      Field field = Integer.class.getDeclaredField("value");
      field.setAccessible(true);  //绕过安全检查
      int tmp = i1.intValue();
      field.setInt(i1, i2.intValue());
      field.setInt(i2, tmp);
  }
  ```
  
* java和c++的区别

  1. 都是面向对象的语言，封装、继承、多态
  2. java中没有指针用来访问内存，更加安全
  3. java中有自动的内存回收机制
  4. java中的类是单继承，c++多继承，但是java可以通过接口实现多继承

## Class对象

==所有的类都是在对其第一次使用时，动态加载到JVM中==

* 当程序创建第一个对类的静态成员的引用时，就会加载这个类。
  * 这个证明构造器也是类的静态方法，即使在构造器之前并没有使用static关键字。因此，使用new操作符创建类的新对象也会被当作对类的静态成员的引用
* Java程序在它开始运行之前并非完全被加载，其各个部分是在必须时才加载的
* Class对象仅在需要的时候才被加载，static初始化是在类加载时进行的
* ==使用newInstance()来创建的类，必须带有默认的构造器==
* 获取class对象
  1. ==Class.forName()==不需要持有该类型的对象。`会自动地初始化该Class对象`
  2. ==obj.getClass()==返回该对象的实际类型的Class引用
  3. ==类字面量==Demo.class生成对Class对象的引用，编译时就会受到检查，并且根除了对forName()的调用，更高效。应用于普通的类、接口、数组、基本数据类型。`不会自动地初始化该Class对象`

## 类初始化

==编译期常量（static final int staticFinal = 47），不需要初始化类就可以读取，调用其他的需要先初始化类==

```java
//编译期常量，不需要初始化类就可以读取，调用其他的需要先初始化类
static final int staticFinal = 47;
//域设置成static final的，需要先初始化类，再调用
static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);  //A
static {  
    System.out.println("Initializing Initable!");  //B
}
//调用A时，先执行B，再执行A
```

## 对象

* ==Java创建（实例化）对象的五种方式==

  1. 用new语句创建对象，这是最常见的创建对象的方法
  2. 运用反射手段,调用java.lang.Class或者java.lang.reflect.Constructor类的newInstance()实例方法。如：Object obj = Class.forName("java.lang.Object").newInstance(); 
  3. 调用对象的clone()方法
  4. 通过工厂方法返回对象，如：String str = String.valueOf(23); 
  5. 通过I/O流（包括反序列化），如运用反序列化手段，调用java.io.ObjectInputStream对象的 readObject()方法

* Java创建一个对象，系统先为该对象的所有实际变量分配内存=》执行初始化(初始化块/指定初始值—>构造器器里指定的初始值)

* ==Java对象创建过程==

  1. 父类【静态成员】和【静态代码块】，按在代码中出现的顺序依次执行。
  2. 子类【静态成员】和【静态代码块】，按在代码中出现的顺序依次执行。
  3. 父类的【普通成员变量被普通成员方法赋值】和【普通代码块】，按在代码中出现的顺序依次执行。
  4. 执行父类的构造方法。
  5. 子类的【普通成员变量被普通成员方法赋值】和【普通代码块】，按在代码中出现的顺序依次执行。
  6. 执行子类的构造方法。
  7. 一般顺序：静态块（静态变量）——>成员变量——>构造方法——>静态方法， 静态方法需要调用才会执行

* 类成员不能访问实例成员：类成员的作用域比实例成员的作用域更大，完全可能出现类成员已经初始化完成，但实例成员还不曾初始化的情况

* 封装

  将对象状的态信息隐藏在对象内部，不允许外部程序直接访问对象内部信息，而是通过该类所提供的方法来实现对内部信息的操作和访问

  private - default - protected - public

  类 包 子类+包 所有

* 继承

  1. 实现代码复用的重要手段
  2. 一般和特殊的关系 is a关系，父类包含的范围总⽐子类大，单继承
  3. 子类扩展了父类，将可以获得父类的全部成员变量和方法，除了父类构造器，但是可以用super()调⽤
  4. 子类调⽤⽗类中被覆盖的方法：super.实例方法、⽗类名.类方法
  5. ==不管是否使用super调用来执行父类构造器的初始化代码，子类构造器总会调用⽗类构造器一次==
  6. ==子类不能继承父类的构造器(构造⽅法或者构造函数)，如果父类的构造器带有参数，则必须在子类的构造器中显式地通过 super 关键字调用父类的构造器并配以适当的参数列表==
  7. ==如果父类构造器没有参数，则在子类的构造器中不需要使用 super 关键字调⽤父类构造器，系统会自动调用⽗类的⽆参构造器==
  8. 创建任何对象总是从该类所在继承树最顶层类的构造器开始执行，然后依次向下执行，最后才执行本类的构造器
  9. 继承：is a关系，==严重地破坏了父类的封装性==
  10. 组合：has a关系，把旧类对象作为新类的成员变量组合进来

* 多态

  1. 可以使程序==有良好的扩展==，并可以对所有类的对象进行通用处理
  2. ==Java 引⽤变量有两种类型，不一致时产⽣多态==
     * 编译时类型：由声明该变量时使用的类型决定
     * 运⾏时类型：由实际赋给该变量的对象决定
  3. ==实际执行子类覆盖父类后的方法，不能调用子类特有的方法==
  4. ==对象的实例变量，则不具备多态性，实际访问编译时类型(父类)所定义的成员变量==
  5. 引用变量只能调用它编译时类型的方法，⽽不能调用它运行时类型的方法。即使它实际所引⽤的对象确实包含该方法，如果需要让这个引用变量调用它运行时类型的方法，则必须把它强制类型转换成运行时类型
  6. 多态实现方式：重写、接口、抽象类和抽象方法

* ==不可变对象==

  * ==一旦被创建，它们的状态就不能被改变的对象，每次对它们的改变都是产生了新的不可变的对象==
  * String和StringBuilder，String是immutable的，每次对于String对象的修改都将产生一个新的String对象，而原来的对象保持不变，而StringBuilder是mutable，因为每次对于它的对象的修改都作用于该对象本身，并没有产生新的对象
  * 实际上JDK本身就自带了一些immutable类，比如==String，Integer以及其他包装类==
  * 编写不可变类原则：
    1. 不可变类的所有的属性都应该是final的
    2. 不可变类对象的状态在创建之后就不能发生改变，任何对它的改变都应该产生一个新的对象
    3. 对象必须被正确的创建，比如：对象引用在对象创建过程中不能泄露(leak)
    4. 对象应该是final的，以此来限制子类继承父类，以避免子类改变了父类的不可变特性
    5. 如果类中包含可变类对象，那么返回给客户端的时候，返回该对象的一个拷贝，而不是该对象本身
  * ==为类、属性添加了final修饰==，从而避免因为继承和多态引起的不可变风险
  * 可以创建一个包含可变对象的不可变对象的，不要共享可变对象的引用，如果需要变化时，就返回原对象的一个拷贝。最常见的例子就是对象中包含一个日期对象的引用
  * 好处：
    * 不可变类对象是==线程安全==的、且可以被重复使用
  * 缺点就是会制造大量垃圾

## 序列化

* Java对象的序列化机制 => 可以==把内存中的Java对象转换成二进制字节流==(可以把Java对象存储到磁盘里，或者在⽹络上传输 Java 对象)，也是Java提供分布式编程的重要基础，使得对象可以脱离程序的运行而独立存在

  ```java
  ObjectOutputStream -> writeObject() 对象输出到输出流
  ObjectInputStream -> readObject() 读取流中的对象 -> 反序列化:⽆需通过构造器来初始化Java对象
  ```

* 浅克隆

  快速构造一个已有对象的副本

  Object-clone()

  ==实现Cloneable接口（标识）==

  1. 原对象和克隆对象中的引用类型属性是同一个，基本类型复制
  2. 原对象.clone();

* 深克隆

  1. 原对象和克隆对象中的引用类型属性不是同一个

  2. 方法1：引用类型属性也实现Cloneable接口

  3. 方法2：序列化和反序列化（实现Cloneable、Serialiable） public User deepClone(){...}

     ==ByteArrayInputStream、ByteArrayOutputStream、ObjectInputStream、ObjectOutputStream==

## 跨域

* 浏览器同源策略限制的一类请求场景

* 同源是指，域名、协议、端口均为相同

* 常见跨域场景：

  1. 主域不同、子域名不同、端口不同、协议不同

  2. localhost   调用 127.0.0.1 跨域

* 同源策略限制了以下行为：
  *  Cookie、LocalStorage 和 IndexDB 无法读取
  * DOM和JS对象无法获取
  * Ajax请求不能发送

[解决方法](<https://segmentfault.com/a/1190000011145364>)：

1. jsonp跨域

   * 在html页面中通过相应的标签从不同域名下加载静态资源文件是被浏览器允许的
* 可以通过动态创建script，再请求一个带参网址实现跨域通信
     * 原生
  * jquery ajax，`dataType: 'jsonp'`
  
* 最大的缺陷是，只能够实现get请求
  
2. document.domain + iframe跨域

   * 仅限主域相同，子域不同的跨域应用场景
   * 实现原理：两个页面都通过js强制设置document.domain为基础主域，就实现了同域

3. 跨域资源共享（CORS）

   * 普通跨域请求，只服务端设置Access-Control-Allow-Origin即可，前端无须设置

   * 若要带cookie请求：前后端都需要设置

     * 前端设置是否带cookie

     * 后端

       ```java
       // 允许跨域访问的域名：若有端口需写全（协议+域名+端口），若没有端口末尾不用加'/'
       response.setHeader("Access-Control-Allow-Origin", "http://www.domain1.com"); 
       // 允许前端带认证cookie：启用此项后，上面的域名不能为'*'，必须指定具体的域名，否则浏览器会提示
       response.setHeader("Access-Control-Allow-Credentials", "true"); 
       // 提示OPTIONS预检时，后端需要设置的两个常用自定义头
       response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
       ```

4. ==nginx代理跨域==

   * 浏览器跨域访问js、css、img等常规静态资源被同源策略许可，但iconfont字体文件(eot|otf|ttf|woff|svg)例外，可在nginx的静态资源服务器中加入以下配置

     ```json
     location / {
       add_header Access-Control-Allow-Origin *;
     }
     ```

   * ##### ==nginx反向代理接口跨域==

     * 跨域原理： 同源策略是浏览器的安全策略，不是HTTP协议的一部分。服务器端调用HTTP接口只是使用HTTP协议，不会执行JS脚本，不需要同源策略，也就不存在跨越问题

     * 实现思路：通过nginx配置一个代理服务器（域名与domain1相同，端口不同）做跳板机，反向代理访问domain2接口，并且可以顺便修改cookie中domain信息，方便当前域cookie写入，实现跨域登录

       ```shell
       #proxy服务器
       server {
           listen       81;
           server_name  www.domain1.com;
       
           location / {
               proxy_pass   http://www.domain2.com:8080;  #反向代理
               proxy_cookie_domain www.domain2.com www.domain1.com; #修改cookie里域名
               index  index.html index.htm;
       
               # 当用webpack-dev-server等中间件代理接口访问nignx时，此时无浏览器参与，故没有同源限制，下面的跨域配置可不启用
               add_header Access-Control-Allow-Origin http://www.domain1.com;  #当前端只跨域不带cookie时，可为*
               add_header Access-Control-Allow-Credentials true;
           }
       }
       ```

5. WebSocket协议跨域

   * WebSocket protocol是HTML5一种新的协议。它实现了浏览器与服务器全双工通信，同时允许跨域通讯

6. 跨域资源共享CORS

   * 目前主流的跨域解决方案，CORS支持所有类型的HTTP请求

   浏览器一旦发现AJAX请求跨源，就会自动添加一些附加的头信息，有时还会多出一次附加的请求，但用户不会有感觉。 因此，实现CORS通信的关键是服务器。

   * 简单请求

     1. ==请求方式为HEAD、POST 或者 GET==

     2. http头信息不超出以下字段：Accept、Accept-Language 、 Content-Language、 Last-Event-ID、 Content-Type(限于三个值：application/x-www-form-urlencoded、multipart/form-data、text/plain)

     ==浏览器在头信息之中，增加一个Origin字段用来说明，本次请求来自哪个源（协议 + 域名 + 端口）。服务器根据这个值，决定是否同意这次请求，只要服务器实现了CORS接口，就可以跨源通信==

     如果Origin指定的源，不在许可范围内，服务器会返回一个正常的HTTP回应。 浏览器发现，这个回应的头信息没有包含Access-Control-Allow-Origin字段，就知道出错了，从而抛出一个错误，被XMLHttpRequest的onerror回调函数捕获

     如果Origin指定的域名在许可范围内，服务器返回的响应，会多出几个头信息字段

     ```java
     Access-Control-Allow-Origin: http://api.bob.com //要么是请求时Origin字段的值，要么是一个
     Access-Control-Allow-Credentials: true  //否允许发送Cookie
     Access-Control-Expose-Headers: FooBar  //CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定
     Content-Type: text/html; charset=utf-8
     ```

   * 非简单请求

     对服务器有特殊要求的请求，比如==请求方法是PUT或DELETE，或者Content-Type字段的类型是application/json==

     在正式通信之前，增加一次HTTP查询请求，称为"预检"请求（preflight），=="预检"请求用的请求方法是`OPTIONS`，表示这个请求是用来询问的==。头信息里面，关键字段是`Origin`，表示请求来自哪个源。

     ==服务器收到"预检"请求以后，检查了`Origin`、`Access-Control-Request-Method`和`Access-Control-Request-Headers`字段以后，确认允许跨源请求，就可以做出回应==

     如果浏览器否定了"预检"请求，会返回一个正常的HTTP回应，但是没有任何CORS相关的头信息字段。这时，浏览器就会认定，服务器不同意预检请求，因此触发一个错误，被`XMLHttpRequest`对象的`onerror`回调函数捕获

     ==一旦服务器通过了"预检"请求，以后每次浏览器正常的CORS请求，就都跟简单请求一样，会有一个Origin头信息字段==。服务器的回应，也都会有一个Access-Control-Allow-Origin头信息字段

## ==泛型==

* Java泛型设计原则：只要在编译时期没有出现警告，那么运行时期就不会出现ClassCastException异常
* 泛型：==把类型明确的工作推迟到创建对象或调用方法的时候才去明确的特殊的类型==

* 向Class引用添加泛型语法的原因仅仅是为了提供编译期类型检查
* ==泛型用在编译期，编译过后泛型擦除（消失掉）。所以是可以通过反射越过泛型检查的==

* 泛型类：就是把泛型定义在类上，⽤户使⽤该类的时候，才把类型明确下来

  * 在类上定义的泛型，在类的方法中也可以使⽤

    ```java
    //1:把泛型定义在类上
    //2:类型变量定义在类上,⽅法中也可以使用
    public class ObjectTool<T> {
        private T obj;
        public T getObj() {
            return obj;
    	}
        public void setObj(T obj) {
            this.obj = obj;
    	} 
    }
    ```

* 仅仅在某一个⽅法上需要使用泛型....外界仅仅是关⼼该方法，不关心类其他的属性

  * 定义泛型⽅法....泛型是先定义后使用的

    ```java
    //定义泛型⽅法..
    public <T> void show(T t) {
        System.out.println(t);
    }
    ```

    ```java
    public static void main(String[] args) { //创建对象
        ObjectTool tool = new ObjectTool();
    //调⽤⽅法,传入的参数是什么类型,返回值就是什么类型 
    	tool.show("hello");
    	tool.show(12);
    	tool.show(12.5);
    }
    ```

* 泛型⼦类

  ```java
  //把泛型定义在接⼝上
  public interface Inter<T> {
      public abstract void show(T t);
  }
  
  //⼦类明确泛型类的类型参数变量:
  public class InterImpl implements Inter<String> {
      @Override
      public void show(String s) {
          System.out.println(s);
  	} 
  }
  
  //⼦类不明确泛型类的类型参数变量: 
  //实现类也要定义出<T>类型的
  public class InterImpl<T> implements Inter<T> {
      @Override
      public void show(T t) {
          System.out.println(t);
  	} 
  }
  
   
  public static void main(String[] args) { 
      //测试第⼀种情况
      //Inter<String> i = new InterImpl();
      //i.show("hello");
  	//第二种情况测试
  	Inter<String> ii = new InterImpl<>(); 
      ii.show("100");
  }
  ```

* 类型通配符

  方法接收一个集合参数，遍历集合并把集合元素打印出来，怎么办?

  ```java
   public void test(List<?> list){
      for(int i=0;i<list.size();i++){
          System.out.println(list.get(i));
      }
  }
  ```

  ?号通配符表示可以匹配任意类型，任意的Java类都可以匹配.....

  使用?号通配符的时候：就只能调对象与类型无关的⽅法，不能调用对象与类型有关的⽅法，因为直到外界使⽤才知道具体的类型是什么

  * 设定通配符上限

    List<? extends Number>  //List集合装载的元素只能是Number的⼦类或自身

  * 设定通配符下限

    <? super Type>   //传递进来的只能是Type或Type的父类

* ==无论是设定通配符上限还是下限，都是不能操作与对象有关的方法，只要涉及到了通配符，它的类型
  都是不确定的==

  * 如果参数之间的类型有依赖关系，或者返回值是与参数之间有依赖关系的。那么就使用泛型方法
  * 如果没有依赖关系的，就使⽤通配符，通配符会灵活⼀些

## JSON

```java
//创建json对象
1. JSONObject jo = new JSONObject();
   jo.put("name", "jon doe");
2. new JSONObject(Map);
3. new JSONObject(Object);
4. new JSONObject("{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}");

//创建json数组
1. JSONArray ja = new JSONArray();
   ja.put(Boolean.TRUE);
   ja.put(Object)
2. new JSONArray(List);
2. JSONArray ja = new JSONArray("[true, \"lorem ipsum\", 215]");

//json字符串转json对象
1. String json = "{\"2\":\"efg\",\"1\":\"abc\"}";   
   JSONObject json_test = JSONObject.fromObject(json); 

//import com.alibaba.fastjson.JSON;
String str = wsRequest.getJson();
JSONObject jsonObject = JSON.parseObject(str);
String data = jsonObject.getString("DATA");

List<HomsUserInfo> list = JSON.parseObject(data, new TypeReference<List<HomsUserInfo>>() {});
houseHolderBean.setEsb_ext(JSON.toJSONString(homsUserInfo));
JSONObject obj = new JSONObject();
obj.put("project_name", homsUserInfo.getProject_name());
houseHolderBean.setEsb_ext(obj.toJSONString());
```

```java
//@JSONField
//import com.alibaba.fastjson.annotation.JSONField;
1. 指定字段的名称
	@JSONField(name="role_name")  
	private String roleName;
2. 使用format制定日期格式
	// 配置date序列化和反序列使用yyyyMMdd日期格式  
	@JSONField(format="yyyyMMdd")  
	public Date date; 
3. 指定字段的顺序
	@JSONField(ordinal = 3)  
    private int f0;  
4. 使用serialize/deserialize指定字段不序列化
	@JSONField(serialize=false)
	public Date date;
```

## String

|            | String       | StringBuffer | StringBuilder |
| ---------- | ------------ | ------------ | ------------- |
| 存储区     | 字符串常量池 | 堆           | 堆            |
| 是否可修改 | 否           | 是           | 是            |
| 线程安全   | 是           | 是           | 否            |
| 性能       | 快           | 慢           | 快            |

==new String("hello")=>JVM会先使⽤常量池来管理"hello"直接量，再调⽤String类的构造器来创建⼀个新的String对象(堆内存中) =》共产⽣两个字符串对象==

String:equals()=〉两个字符串所包含的字符序列相同，则返回true

重写类Object的equals()=》比较对象的地址 ==

String字符串变量的连接动作，在编译阶段会被转化成StringBuilder的append操作，变量最终指向Java堆上新建的String对象

当使用+进行多个字符串连接时，实际上是产生了一个StringBuilder对象和一个String对象

* 字符串常量池

  ==常量池(constant_pool)指的是在编译期被确定，并被保存在已编译的.class文件中的一些数据==。它包括了关于类、方法、接口等中的常量，也包括字符串常量和符号引用。运行时常量池是方法区的一部分

  常量池主要用于存放两大类常量：

  * 字面量(Literal)和符号引用量(Symbolic References)，字面量相当于Java语言层面常量的概念，如==文本字符串，声明为final的常量值等==
  * 符号引用则属于编译原理方面的概念，包括了如下三种类型的常量：==类和接口的全限定名、字段名称和描述符、方法名称和描述符==

  JDK1.7及之后版本的JVM已经将运行时常量池从方法区中移了出来，在Java 堆（Heap）中开辟了一块区域存放运行时常量池

## 方法

* 方法重载（两同一不同）

  ==同⼀个类中，⽅法名相同，参数列表不同==

  不用返回值作区分:因为可以忽略方法的返回值=>可能让系统不能区分调用了那一个

* 方法重写（两同两小一大）

  ==子类包含与父类同名方法的现象(要么都实例方法，要么都类方法)==

  1. ⼦类方法访问权限 ⼤于等于 父类
  2. 子类⽅法返回值类型 小于等于 父类的方法返回值类型
  3. 方法名相同、形参列表相同
  4. ⼦类方法声明抛出的异常类 小于等于 父类

## 接口和抽象类

| 接⼝                                                         | 抽象类                                                       |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| 规范，interface，可理解为抽象类的特列；多实现                | 模版式设计，abstract修饰符；单继                             |
| 只能定义静态常量                                             | 普通成员变量+静态常量                                        |
| 不能包含初始化块                                             | 可包含初始化块                                               |
| 不包含构造器                                                 | 可包含构造器=>让子类调用完成属于抽象类的初始化操作；不能创建实例对象； |
| 能包含抽象方法、静态方法(java8)和默认⽅法（java8）；抽象方法默认public abstract修饰 | 抽象方法、普通方法、静态方法；抽象方法的访问类型可以是public，protected和默认类型 |
| 用途:定义变量，调用接口中定义的常量，被其他类实现            |                                                              |

* 共同点

  ==都不能被实例化，都可包含抽象⽅法==


## 内部类

* 作用：更好的封装，内部类成员可以直接访问外部类的私有数据

* 匿名内部类

  1. ⽐外部类多使用 private、protected、static
  2. ==适合创建那种只需要一次使用的类，创建时会立即创建⼀个该类的实例，这个类定义立即消失，匿名内部类不能重复使⽤==
  3. new 实现接口() | 父类构造器(实参列表) {...}
  4. ==必须，但最多只能继承⼀个父类或实现一个接⼝==
  5. Java8开始匿名内部类、局部内部类允许访问==⾮final的局部变量==(相当于⾃动使用了final修饰)

* 非静态内部类的构造器必须通过其外部类对象来调⽤

* ==非静态内部类不能拥有静态成员==

* 在外部类以外使⽤静态内部类(与外部类相关)，外部类当成静态内部类的包空间即可

* 静态：

  属于外部类本身，可以包含静态成员，也可以包含非静态成员，其实例方法不能访问外部类的实例属性

* 非静态:

  其实例必须寄生在外部类实例里，this.变量、外部类.this.变量

## Lambda表达式

Lambda 表达式为 Java 添加了缺失的函数式编程特点，使我们能将函数当做一等公⺠看待

在 Java 中，Lambda 表达式是对象，他们必须依附于一类特别的对象类型——函数式接⼝ (functional interface) 

1. ==主要作用：==
   1. 代替匿名内部类的繁琐语法
   2. ⽀持将代码块作为方法参数
   3. 允许使用更简洁的代码来创建只有一个抽象方法的接口(函数式接⼝)的实例

* 形参列表：允许省略形参类型，如果只有一个参数，括号可以省略
* 箭头
* 代码块：需要返回值，省略return会自动返回这条语句的值

```java
 //main⽅法中(匿名内部类)
A a = new A();
int[] t = {3, -4, 6, 4}; 
p.process(t, new Command(){
    public void process(int[] t){
        ...
	}
});
```

```java
//main⽅方法中
A a = new A();
int[] t = {3, -4, 6, 4}; 
p.process(t, (int[] t)->{
	... 
})
```

* 简介

  Lambda 表达式是⼀种匿名函数(对 Java 而言这并不完全正确，但现在姑且这么认为)，简单地说，它是没有声明的方法，也即没有访问修饰符、返回值声明和名字 )

  可以将其想做一种速记，在需要使用某个方法的地⽅写上它。当某个方法只使用一次，而且定义很简短，使⽤用这种速记替代之尤其有效，这样，你就不必在类中费力写声明与⽅法了

  ```java
  (argument) -> (body)
  (inta,intb)->{ returna+b;}
  () -> System.out.println("Hello World"); 
  (String s) -> { System.out.println(s); } 
  () -> 42
  () -> { return 3.1415 };
  ```

* 结构

  1. 一个 Lambda 表达式可以有零个或多个参数
  2. 参数的类型既可以明确声明，也可以根据上下文来推断。例如：(int a)与(a)效果相同 

  3.  所有参数需包含在圆括号内，参数之间用逗号相隔。空圆括号代表参数集为空。

  4. 当只有一个参数，且其类型可推导时，圆括号()可省略。例如：a -> return a*a 

  5. Lambda 表达式的主体可包含零条或多条语句 
  6. 如果 Lambda 表达式的主体只有⼀条语句，花括号{}可省略。匿名函数的返回类型与该主体表达式一致 
  7. 如果 Lambda 表达式的主体包含一条以上语句，则表达式必须包含在花括号{}中(形成代码块)。匿名函数的返回类型与代码块的返回类型⼀致，若没有返回则为空 

* 函数式接⼝

  1. 在 Java 中，Marker(标记)类型的接⼝是一种没有方法或属性声明的接口，简单地说， marker 接口是空接⼝。相似地，==函数式接口是只包含一个抽象方法声明的接⼝==

  2. java.lang.Runnable 就是⼀种函数式接口，在 Runnable 接口中只声明了⼀个⽅法 void run()， 相似地，ActionListener 接⼝也是一种函数式接口，我们使用匿名内部类来实例化函数式接⼝的对象，有Lambda 表达式，这一方式可以得到简化 

  3. 每个 Lambda 表达式都能隐式地赋值给函数式接口，例如，我们可以通过 Lambda 表达式创建Runnable 接口的引⽤

     Runnable r = () -> System.out.println("hello world");

  4.  当不指明函数式接口时，编译器会⾃动解释这种转化

     ```java
     new Thread(
             () -> System.out.println("hello world")
     ).start();
     ```

     因此，在上面的代码中，编译器会自动推断：根据线程类的构造函数签名 public Thread(Runnable r) { }，将该 Lambda 表达式赋给 Runnable 接⼝

     以下是一些 Lambda 表达式及其函数式接⼝：

     ```java
     Consumer<Integer>  c = (int x) -> { System.out.println(x) };
     BiConsumer<Integer, String> b = (Integer x, String y) ->
     System.out.println(x + " : " + y);
     Predicate<String> p = (String s) -> { s == null };
     ```

     @FunctionalInterface 是 Java 8 新加入的⼀种接口，⽤于指明该接口类型声明是根据 Java 语言规范定义的函数式接口。Java 8 还声明了一些 Lambda 表达式可以使⽤的函数式接口，当你注释的接口不是有效的函数式接口时，可以使用 @FunctionalInterface 解决编译层⾯的错误

     以下是一种⾃定义的函数式接⼝：

     ```java
     @FunctionalInterface
     public interface WorkerInterface {
        public void doSomeWork();
     }
     ```

     函数式接口定义好后，可以在 API 中使用它，同时利用 Lambda 表达式 创建自定义的函数式接口并与 Lambda 表达式一起使用。execute() 方法现在可以将 Lambda 表达式作为参数 

     ```java
     @FunctionalInterface
     public interface WorkerInterface {
         public void doSomeWork();
     }
     ```

     ```java
     public class WorkerInterfaceTest {
         public static void execute(WorkerInterface worker) {
             worker.doSomeWork();
     }
     public static void main(String[] args){
         execute(new WorkerInterface() {
              @Override
                 public void doSomeWork() {
                     System.out.println("Worker invoked using Anonymous class");
     			} 
         });
         execute( () -> System.out.println("Worker invoked using Lambda expression") );
       } 
     }
     ```

* 举例

  ```java
  线程可以通过以下⽅方法初始化
  //旧⽅方法:
  new Thread(new Runnable() {
  @Override
      public void run() {
          System.out.println("Hello from thread");
      }
  }).start();
  //新⽅法: 
  new Thread(
          () -> System.out.println("Hello from thread")
  ).start();
  ```

  ```java
  //Old way:
  List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
  for(Integer n: list1) {
      System.out.println(n);
  }
  常⽤的箭头语法创建 Lambda 表达式，
  List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7); 
  list2.forEach(n -> System.out.println(n));
  Java 8 全新的双冒号(::)操作符将一个常规⽅法转化为 Lambda 表达式 
  //or we can use :: double colon operator in Java 8 
  list2.forEach(System.out::println);
  ```

  ⽤断言(Predicate)函数式接口创建一个测试，并打印所有通过测试的元素，这样，就可以使用Lambda 表达式规定一些逻辑

  ```java
  List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
  System.out.println("Print all numbers:");
  evaluate(list, (n)->true);
  System.out.println("Print no numbers:");
  evaluate(list, (n)->false);
  System.out.println("Print even numbers:");
  evaluate(list, (n)-> n%2 == 0 );
  System.out.println("Print odd numbers:");
  evaluate(list, (n)-> n%2 == 1 );
  System.out.println("Print numbers greater than 5:");
  
  evaluate(list, (n)-> n > 5 );
  public static void evaluate(List<Integer> list, Predicate<Integer>
  predicate) {
      for(Integer n : list) {
          if(predicate.test(n)) {
          	System.out.println(n + " ");
  		} 
  	}  
  }
  ```

  ```java
  将 Lambda 表达式 x -> x*x 传给 map() 方法，该⽅法会作用于流中的所有元素 
  //Old way:
  List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
  for(Integer n : list) {
  	int x = n * n;
      System.out.println(x);
  }
  //New way:
  List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
  list.stream().map((x) -> x*x).forEach(System.out::println);
  ```

  ```java
  计算给定数值中每个元素平⽅后的总和
  是 MapReduce 的⼀个初级例⼦
  使⽤ map() 给每个元素求平方，再使用 reduce() 将所有元素计⼊一个数值:
  //Old way:
  List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
  int sum = 0;
  for(Integer n : list) {
  	int x = n * n;
  	sum = sum + x; 
  }
  System.out.println(sum);
  //New way:
  List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
  int sum = list.stream().map(x -> x*x).reduce((x,y) -> x + y).get();
  System.out.println(sum);
  ```

* ==与匿名类的区别==

  使⽤匿名类与 Lambda 表达式的一⼤区别在于关键词的使⽤。

  ==对于匿名类，关键词 this 解读为匿名类，⽽对于 Lambda 表达式，关键词 this 解读为写就Lambda 的外部类==

  Lambda 表达式与匿名类的另一不同在于两者的编译方法。Java 编译器编译 Lambda 表达式并将他们转化为类里面的私有函数，它使用 Java 7 中新加的 invokedynamic 指令动态绑定该⽅法

## 枚举类

==不能自由创建对象，其对象在定义类时已经固定下来，能创建的实例是有限且确定的==

* ==可以实现⼀个或多个接口，默认继承了java.lang.Enum类，⽽不是Object类==

* 默认使⽤final修饰，不能派⽣子类

* 构造器只能使⽤private修饰

* ==所有实例(系统自动添加public static final修饰)必须在枚举类第一行显示列出，否则其永远都不能产⽣实例==

* ==通过Enum的valueOf()⽅法来获取指定枚举类的枚举值==，如 

  A a = Enum.valueOf(A.class, "aa");

```java
public enum AppVersionTypeEnum {
    ENTRANCE_MACHINE("Community.EQUIPMENT_TYPE.ENTRANCE_MACHINE", "");
     
	private final String val;
    private final String desc;
    AppVersionTypeEnum(String val, String desc) {
        this.val = val;
        this.desc = desc;
    }
    public String getVal() {
        return val;
    }
    public String getDesc() {
        return desc;
    }
}
AppVersionTypeEnum.ENTRANCE_MACHINE.getVal()
```

## final

1. final修饰的变量不可改变，一旦获得了初始值，该final变量的值就不能被重新赋值

2. final修饰的==局部变量==，如在定义时没有指定默认值，则在后面可对其赋初始值，只能一次

3. final修饰引用类型变量时，只能保证其引用的地址不会改变，但对象完全可发⽣改变

4. final修饰的⽅法不可被重写

5. final修饰的类不可以有子类

6. final关键字的好处：
   * final关键字提高了性能。JVM和Java应用都会缓存final变量
   * ==final变量可以安全的在多线程环境下进行共享，而不需要额外的同步开销==
   * 使用final关键字，JVM会对方法、变量及类进行优化

7. 宏变量，定义final变量，指定初始值，并在编译时就确定下来，编译器会把程序中所有用到该变量的地方，直接替换成该变量的值

   ```java
   String str1 = "疯狂";
   String str2 = "Java";
   String str3 = str1 + str2; //编译器不会执行宏替换，无法在编译时确定s3的值 
   String s1 = "疯狂Java";
   s1 == s3 =>false //但 str1、str2使⽤final修饰=>true
   ```

8. 不可变类：创建该类的实例后，该实例的实例变量是不可以改变的，如==8个包装类和java.lang.String类==

9. ==自定义不可变类==：private和final—>成员变量—>仅提供getter方法，提供带参数的构造器，如果有必要，重写Object类的hashCode和equals，保护成员变量所引用的对象不会被修改

   如 this.name = new Name(name.getFirstN(), name.getLastN());
   ​     getter:return new Name(name.getFirstN(), name.getLastN());

# 集合

* 数组和集合的区别
  * 数组长度固定，能存储基本类型和引用类型 `int[] arr = [1,2,3,4];`
  * ==集合长度不固定，只能存储引用类型==（基本类型自动装箱）

* Iterator：仅用于遍历集合，必须有⼀个被迭代的集合，不包括Map，hasNext()、next()、remove()

* Collections工具类：

1. 同步控制：解决多线程并发访问
2. XXX xx = Collection.synchronized.Xxx(new Xxx());

* ==【强制】==不要在 foreach 循环里进行元素的 remove/add 操作。remove 元素请使⽤ Iterator方式，如果并发操作，需要对 Iterator 对象加锁
  * ![image-20190114170034030](/Users/dingyuanjie/Desktop/MyKnowledge/2.code/java/1.基础/image-20190114170034030-7456434.png)
* 【参考】合理利用好集合的有序性(sort)和稳定性(order)，避免集合的⽆序性(unsort)和不稳定性(unorder)带来的负面影响。 
  * 说明：有序性是指遍历的结果是按某种比较规则依次排列的。稳定性指集合每次遍历的元素次序是一定的。如:ArrayList 是 order/unsort;HashMap 是unorder/unsort;TreeSet 是order/sort。
* ==【参考】==利用 Set 元素唯一的特性，可以快速对⼀个集合进行去重操作，避免使用 List 的contains 方法进行遍历、对比、去重操作。

## map集合接口

底层是基于 数组 + 链表 组成的，不过在 jdk1.7 和 1.8 中具体实现稍有不同

entrySet()、keySet()

* hashMap

  1. ==可以使用null作为key或value==

  2. ==线程不安全==

     ==1.7 无同步锁保护，多线程下容易出现resize()死循环：并发执行put操纵导致触发扩容行为，从而导致环形链表，在获取数据遍历链表时形成死循环==

     1.8 无同步锁保护

  3. ==不能保证其中key-value对的顺序==

     根据hash算法计算key在数组下标位置，hash算法讲究随机性、均匀性、不具备某种规则

  4. ==判断两个key相等：equals()返回true，且hashCode值也相等==

  5. ==存储位置随时间变化==

     ==存在扩容操作，从而导致存储位置重新计算==

* treeMap

  ==是可以对key进行排序，实现有序，key不允许为null、value允许为null，线程不安全==

  1. ==红黑树数据结构==，可以保证所有的key-value对处于有序状态
  2. 自然排序：所有key应是同⼀个类的对象，且key必须实现Comparable接⼝
  3. 定制排序：创建TreeMap时，传⼊一个Comparator对象，其负责对TreeMap中的所有key进行排序，不要求Map的key实现Comparable接⼝
  4. 判断两个key相等：==两个key通过equals()返回true，那通过compareTo()返回0==

* ==Java中LinkedHashMap和PriorityQueue的区别是什么==

  - PriorityQueue 保证最高或者最低优先级的的元素总是在队列==头部==，但是 LinkedHashMap 维持的顺序是元素插入的顺序。当==遍历==一个 PriorityQueue 时，没有任何顺序保证，但是 LinkedHashMap 课保证遍历顺序是元素插入的顺序
  - PriorityQueue
    - ==优先队列的作用是能保证每次取出的元素都是队列中权值最小的==
    - ==元素大小的评判可以通过元素本身的自然顺序，也可以通过构造时传入的比较器Comparator==
      - 优先队列的头是基于自然排序或者Comparator排序的最小元素。如果有多个对象拥有同样的排序，那么就可能随机地取其中任意一个。当获取队列时，返回队列的头对象
    - 实现了*Queue*接口，不允许放入`null`元素，优先队列的大小是不受限制的，但在创建时可以指定初始大小。当向优先队列增加元素的时候，队列大小会自动增加
    - PriorityQueue是非线程安全的，所以Java提供了PriorityBlockingQueue（实现[BlockingQueue接口）用于Java多线程环境。

* hashTable

  ==hashTable不可以存放空值（key、value都不能为null），且线程安全==

* properties

  是Hashtable类的⼦类，其key、value都是字符串类型，相当于⼀个key、value都是String类型的Map

  ```java
  void load(InputStream inStream) 
      从属性⽂件中加载key-value对，把加载到的key-value对追加到Properties⾥
      new Properties().load(new FileInputStream("a.ini"));
  void store(OutputStream out, String comments) 
      将Properties中的key-value对输出到指定的属性⽂件中
  	new Properties().store(new FileOutputStream("a.ini"));
  ```

==初始容量为：16，加载因子为0.75，扩容为：1倍==

其中限制map的容量为2的次幂，是因为map中，key的定位是需要结合key的hash和map的长度-1，来决定的，==当长度为2的次幂，再减一时，转换为二进制就全都为1，在和key的hash值进行计算，更加简便，避免内存碎片，导致的浪费内存空间==

* 【强制】关于 hashCode 和 equals 的处理，遵循如下规则:
  1. 只要重写equals，就必须重写hashCode
  2. 因为Set存储的是不重复的对象，依据hashCode和equals进行判断，所以Set存储的对象必须重写这两个方法
  3. ==如果自定义对象作为Map的键，那么必须重写hashCode和equals==。 说明：String重写了 hashCode 和 equals方法，所以我们可以非常愉快地使用String 对象作为key 来使⽤

## collection集合接口

### list

==list中能存放null，有序（插⼊顺序）、可重复==

判断相等：equals()返回true即可

==ArrayList：初始容量为：10，加载因子为0.5，原来的基础上扩展0.5倍==

* ==arraylist：基于数组实现，便于查询（根据索引），插入删除效率低（但是插入和删除最后一个元素效率高）==
* ==LinkedList：基于双向链表实现，便于增删操作，查找效率低==
* ==vector：基于数组实现，便于查询，线程安全==（对所有方法添加synchronized同步关键字，Vector是扩展1倍）

arrayList和LinkedList的区别：

1. ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构
2. 对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针
3. 对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
4. Arrays.asList(Object ... a)可以把一个数组或指定个数的对象转换成一个List集合，该集合不是ArrayList实现类的实例，⽽是Arrays的内部类ArrayList的实例
5. Arrays.ArrayList是⼀个固定长度的List集合，程序只能遍历访问该集合里的元素，不可增加、删除该集合中的元素
6. ArrayList当数组无法容纳下此次添加的元素时进行扩容，扩容之后容量为原来的1.5倍
7. ArrayList的空间浪费主要体现在list列表的结尾预留⼀定的容量空间，而LinkedList的空间花费则体现在它的每一个元素都需要消耗相当的空间 

* ArrayList与Vector区别

1. Vector是线程安全的，⽽ArrayList不是。
2. ArrayList和Vector都采⽤线性连续存储空间，当存储空间不足的时候，ArrayList默认增加为原来的50%，Vector默认增加为原来的一倍
3. Vector可以设置capacityIncrement，⽽ArrayList不可以，从字⾯理解就是capacity容量，Increment增加，容量增长的参数

### set

==基于hashMap来实现==的，这是和list的根本区别

==不能储存重复值，且无序==（sortedSet可以实现有序）

==初始容量为：16，加载因子为0.75，扩容为：1倍==

Set检索效率低下，删除和插⼊效率高，插入和删除不会引起元素位置改变

* hashset

  1. 不能保证顺序、不是同步的(线程)、==值可以是null==
  2. 判断元素相等：两个对象equals()相等且hashCode()也相等

* LinkedHashSet

  1. 根据元素的hashCode值来决定元素的存储位置，同时使用链表维护元素的顺序(添加顺序来访问)，具有可预知迭代顺序的 Set 接口的哈希表和链接列表实现

* TreeSet

  1. 确保集合元素处于排序状态(元素实际值的⼤小进行排序)

  2. 采⽤红黑树的数据结构来存储集合元素

  3. 自然排序：CompareTo(Object obj)⽐较元素之间的⼤小，按升序

  4. 定制排序：int compare(T o1, T o2)

     实现：创建TreeSet集合对象时，提供⼀个Comparator对象与该TreeSet集合关联，由该
     Comparator对象负责集合元素的排序逻辑

     ```java
     TreeSet ts = new TreeSet((o1,o2)->{
         M m1 = (M)o1;
     	  M m2 = (M)o2;
         return m1.age > m2.age ? -1 : m1.age < m2.age ? 1 : 0;
     });
     ```

     TreeSet中只能添加同⼀种类型的对象，且其类必须实现CompareTo接⼝

     判断相等：两个对象equals()相等，且compareTo(Object obj)返回0

* EnumSet

  1. 有序，以枚举值在Enum类内的定义顺序来决定集合元素的顺序

  2. 不允许null元素，内部以位向量的形式存储，批量操作执行速度非常快

  3. 只能保存同一个枚举类的枚举值作为集合元素

只有当需要一个保持排序的Set时，才应该使⽤用TreeSet，否则都应该使用HashSet

==一般要对list进行除重的话，可以将list转化为set==：

> Java中在利用list生成set时，首先生成一定长度的hashmap（长度由list的大小决定，太小的话，就为16，大的话就由list的长度计算得出），然后再将list的每个元素，作为map的key存储，value随便new一个对象，由于map在存储key时，会判断是不是重复，故set能达到除重的效果；

### Queue

* Queue保持一个队列(FIFO先进先出)的顺序
* poll() 和 remove() 都是从队列中取出一个元素，但是 poll() 在获取元素失败的时候会返回空，但是 remove() 失败的时候会抛出异常
* Deque代表了双端队列(既可以作为队列使用、也可以作为栈使⽤
* Java中的队列都有哪些，有什么区别
  * BlockingQueue有四个具体的实现类，根据不同需求，选择不同的实现类: 
  * ArrayBlockingQueue：基于数组结构的有界阻塞队列，其所含的对象是以FIFO(先入先出)顺序排序的 
  * LinkedBlockingQueue：基于链表结构的阻塞队列，大⼩不定的BlockingQueue（取决于构造方法中参数），其所含的对象是以FIFO顺序排序的，吞吐量通常要高于ArrayBlockingQueue 
  * PriorityBlockingQueue：具有优先级的无限阻塞队列，依据对象的自然排序顺序或者是构造函数所带的 Comparator决定的顺序 
  * SynchronousQueue：不存储元素的阻塞队列，每 个插入操作必须等到另一个线程调用移除操作，否则插入操作⼀直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue 
  * LinkedBlockingQueue和ArrayBlockingQueue比较起来，它们背后所用的数据结构不一 样，导致LinkedBlockingQueue的数据吞吐量要⼤于ArrayBlockingQueue，但在线程数量很大时其性能的可预⻅性低于ArrayBlockingQueue 

## HashMap

### HashMap和HashTable的区别

1. ==最主要的区别在于Hashtable是线程安全，而HashMap则非线程安全==

2. ==HashMap可以使用null作为key，而Hashtable则不允许null作为key==

3. HashMap是对Map接口的实现，HashTable实现了Map接口和Dictionary抽象类

4. ==HashMap的初始容量为16，Hashtable初始容量为11，两者的填充因⼦默认都是0.75==

   ==HashMap扩容时是当前容量翻倍即：capacity2，Hashtable扩容时是容量翻倍+1
   即:capacity2+1==

5. 两者计算hash的方法不同

   ==Hashtable计算hash是直接使用key的hashcode值对数组的长度直接进行取模==

   ==HashMap计算hash对key的hashcode值进行了二次hash，以获得更好的散列值，然后对数组长度取模，JDK8将key的hashcode高16位异或下来，然后对数组长度取模==

6. HashMap和Hashtable的底层实现都是数组+链表结构实现，添加、删除、获取元素时都是先计算hash，根据hash和table.length计算index也就是table数组的下标，然后进行相应操作

### Java7

1. 基于数组和单链表实现（元素为Entry对象-键值对）

2. 初始容量16（数组长度）、加载因子0.75、扩容1倍

3. 计算数组下标方法：
  
   * （经过处理的键key的hash值）
   * 计算key的hashCode返回一个哈希码
   
   * 二次处理哈希码，最终求得键对应的hash值（扰动处理=4次位运算+5次异或运算），加大哈希码低位的随机性，使得分布更均匀，最终减少冲突
   * 哈希码和(数组长度-1)相与(&)，可获得一个哈希码与数组大小范围匹配的数组位置
   
4. put过程：
   * 对Key求Hash值，然后再根据hash算法值&(n-1)，得到数组的下标位置
   * 如果没有碰撞，直接放入桶（数组）中
   * 如果碰撞了（key的hash值冲突），以链表（链地址法）的方式链接到后面（1.7单链表是头插法、1.8转为红黑树是尾插法）
   * 如果链表长度超过阀值(8)，就把链表转为红黑树（java 1.8）
   * 如果节点已经存在就替换旧值
   * 如果桶满了（容量*加载因子），就需要resize

5. 扩容机制

   1. 保存旧的数组，根据设置的扩容大小（原来的1倍）新建数组
   2. 遍历旧数组数据，并重新计算存储下表位置，然后逐个插入新数组中
   3. 新数组table引用到HashMap的table属性上
   4. 重新弄设置扩容阀值

6. 线程不安全：

   * 并发情形下，两个线程同时对HashMap进行扩容，各线程重新对节点rehash时，会造成节点间的相互引用，从而形成一个环，当数组在该位置get寻找对应的key时，就发生了死循环

* ==与1.8比较==

  ![image-20181204170725636](/Users/dingyuanjie/Desktop/MyKnowledge/2.code/java/1.基础/java/image-20181204170725636-3914445.png)

  ![image-20181204170637163](/Users/dingyuanjie/Desktop/MyKnowledge/2.code/java/1.基础/java/image-20181204170637163-3914397.png)

  ![image-20181204170704894](/Users/dingyuanjie/Desktop/MyKnowledge/2.code/java/1.基础/java/image-20181204170704894-3914425.png)

* ==为什么HashMap中String、Integer这样的包装类适合作为key键==

  * String、Integer等包装类是final类型，即具有不可变性，保证了key的==不可更改性==，不会出现放入和获取时哈希码不同的情况；并且内部已重写equals()、hashCode()，严格遵守相关规范，不容器出现Hash值的计算错误

* ==HashMap中的key若是Object类型，则需要实现哪些方法？==

  * hashCode()：计算存储数据的存储位置，实现不恰当会导致严重的Hash碰撞

  * equals()：比较存储位置上是否存在需要存储数据的键key，若存在，则替换为新值，否则，直接插入数据。保证键key在哈希表中的唯一性

* 抛开HashMap，hash冲突有哪些解决方法

  哈希算法：根据设定的哈希函数H（key）和处理冲突方法将一组关键字映象到一个有限的地址区间上的算法。能将任意数据散列后映射到有限的空间上，通常计算机软件中用作快速查找或加密使用

  ==哈希冲突：由于哈希算法被计算的数据是无限的，而计算后的结果范围有限，因此总会存在不同的数据经过计算后得到的值相同==

  1. ==开放定址法==
* 从发生冲突的那个单元起，按照一定的次序，从哈希表中找到一个空闲的单元。然后把发生冲突的元素存入到该单元的一种方法。
  
  2. ==链地址法（拉链法）==
* 将哈希值相同的元素构成一个同义词的单链表
  
  3. ==再哈希法==
* 当发生冲突时，再次通过其他的哈希函数进行计算，直到冲突不再产生
  4. ==建立公共溢出区==
   * 将哈希表分为公共表和溢出表，当溢出发生时，将所有溢出数据统一放到溢出区

局限：

1. ==JDK 1.8 以前 HashMap 的实现是 数组+链表，即使哈希函数取得再好，也很难达到元素百分百均匀分布。当 HashMap 中有⼤量的元素都存放到同一个桶中时，这个桶下有一条⻓长的链表，这个时候 HashMap 就相当于一个单链表，假如单链表有n个元素，遍历的时间复杂度就是 O(n)，完全失去了它的优势。针对这种情况，JDK 1.8 中引⼊了红⿊树(查找时间复杂度为O(logn))来优化这个问题== 

3. ==为什么线程不安全==?
  
   * 扩容操作会让链表形成环形数据结构，形成死循环
     * 并发情形下，两个线程同时对HashMap进行扩容，各线程重新对节点rehash时，会造成节点间的相互引用，从而形成一个环，当在数组该位置get寻找对应的key时，就发生了死循环
   
   1. 在使用迭代器的过程中如果HashMap被修改，那么 ConcurrentModificationException 将被抛出，也即Fast-fail策略。
      * 使用Iterator遍历时使用Iterator的方法进行元素的修改、删除不会报错，但是期间使用map的方法时报错
   2. 单线程条件下，为避免出现ConcurrentModificationException，需要保证只通过HashMap本身或者只通过Iterator去修改数据，不能在Iterator使用结束之前使用HashMap本身的方法修改数据 
   3. 多线程条件下，可使用Collections.synchronizedMap方法构造出一个同步Map，或者直接使用线程安全的ConcurrentHashMap
   
3. ==为什么容量是2的倍数?==

   * 在根据hashcode查找数组中元素时，取模性能远远低于与性能， 且和2^n-1进行与操作能保证各种不同的hashcode对应的元素也能均匀分布在数组中  

### Java8

* 红黑树

  * ==是一种自平衡二叉查找树==
* ==在进行插入和删除操作时通过对树进行旋转保持二叉查找树的平衡，从而获得较高的查找性能==
  * 可以在==O(log n)==时间内做查找，插入和删除

  * ==性质==

    1. 节点是红色或黑色
  2. 根节点是黑色
    3. 每个叶节点是黑色的
  4. 每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
    5. 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点

  * 这些约束强制了红黑树的==关键性质==: 从根到叶子的最长的可能路径不多于最短的可能路径的两倍长。结果是这个树大致上是平衡的

## ConcurrentHashmap

key、value不允许为null

* ==java1.7==

  * ==最外层是一个Segment的数组。每个Segment包含一个与HashMap数据结构差不多的链表数组==
  * 其中`Segment`在实现上继承了`ReentrantLock`，这样就自带了锁的功能
  * `ConcurrentHashMap`初始化时，计算出`Segment`数组的大小`ssize`和每个`Segment`中`HashEntry`数组的大小`cap`，并初始化`Segment`数组的第一个元素；其中`ssize`大小为2的幂次方，默认为16，`cap`大小也是2的幂次方，最小值为2
  * 和hashTable一样，也是线程安全的，但是hashTable是对整个结构进行加锁，每次只能有一个线程访问，而concurrentHashMap是==分段锁==，将整个结构，按照key的hash转换，分成16个(桶)segMent，也就能同时让16个线程来操作。
  * 其中增删改操作，也可以实施==分段锁机制==，但是size方法，则需要对整个结构进行上锁

  ![image-20190221100831169](/Users/dingyuanjie/Desktop/mynotes/MD/Java/image-20190221100831169.png)

  * #### ==put实现==

    * 当执行`put`方法插入数据时，根据key的hash值，在`Segment`数组中找到相应的位置，如果相应位置的`Segment`还未初始化，则通过CAS进行赋值，接着执行`Segment`对象的`put`方法通过加锁机制插入数据，实现如下：
    * 场景：==线程A和线程B同时执行相同`Segment`对象的`put`方法==
      1. 线程A执行`tryLock()`方法成功==获取锁==，则把`HashEntry`对象插入到相应的位置；
      2. 线程B获取锁失败，会通过重复执行`tryLock()`方法尝试获取锁，在多处理器环境下，重复次数为64，单处理器重复次数为1。当执行`tryLock()`方法的次数超过上限时，则执行`lock()`方法挂起线程B；
      3. 当线程A执行完插入操作时，会通过`unlock()`方法释放锁，接着唤醒线程B继续执行

  * #### ==size实现==

    * 先采用不加锁的方式，连续计算元素的个数，最多计算3次：
      1. 如果前后两次计算结果相同，则说明计算出来的元素个数是准确的；
      2. 如果前后两次计算结果都不同，则给每个`Segment`进行加锁，再计算一次元素的个数；

* ==java1.8==

  * `Node` + `CAS` + `Synchronized`来保证并发安全进行实现
  * 取消了Segment分段锁的数据结构，取而代之的是==数组+链表（红黑树）的结构==
  * 只有在执行第一次`put`方法时才会初始化`Node`数组
  * ==锁的粒度调整为对每个数组元素加锁==
  * 定位节点的hash算法被简化了，这样带来的弊端是Hash冲突会加剧。因此在链表节点数量大于8时，会将链表转化为红黑树进行存储

  ![image-20190221100815834](/Users/dingyuanjie/Desktop/mynotes/MD/Java/image-20190221100815834.png)

  * #### ==put实现==

    * 当执行`put`方法插入数据时，根据key的hash值，在`Node`数组中找到相应的位置：
      1. 如果相应位置的`Node`还未初始化，则通过CAS插入相应的数据
      2. 如果相应位置的`Node`不为空，且当前该节点不处于移动状态，则对该节点加`synchronized`锁，如果该节点的`hash`不小于0，则遍历链表更新节点或插入新节点；
      3. 如果当前链表的个数达到8个，则转化为红黑树

  * #### ==size实现==

    * 元素个数保存`baseCount`中（`volatile`类型），部分元素的变化个数保存在`CounterCell`数组中，通过累加`baseCount`和`CounterCell`数组中的数量，即可得到元素的总个数

  * ==基于CAS的ConcurrentHashMap==

    * 为进一步提高并发性，放弃了分段锁，使用CAS + synchronized 来保证
    * put操作：
      * 如果Key对应的数组元素为null，则通过CAS操作将其设置为当前值。
      * 如果Key对应的数组元素(也即链表头或者树的根元素)不为null，则对该元素使用synchronized 关键字申请锁，然后进行操作。
      * 如果该put操作使得当前链表长度超过一定阈值，则将链表 (寻址时间复杂度为O(N))转换为红⿊树(寻址时间复杂度为O(log(N)) 

* ⾼性能场景下，Map家族的优化使⽤建议

  * 考虑加载因⼦地设定初始⼤小
  * 减小加载因⼦
  * String类型的key，不能用==判断或者可能有哈希冲突时，尽量减少长度
  * ⽤定制版的EnumMap
  * 使用IntObjectHashMap

# 反射机制

* 在运行时能够获得并操作一个类的所有属性和方法

* ==反射就是把java类中的各种成分映射成一个个的Java对象==

* 加载的时候：Class对象的由来是将class文件读入内存，并为之创建一个Class对象

## 应用场景

动态获取 **类文件结构信息（如变量、方法等） & 调用对象的方法**

常用的需求场景有：

* ==动态代理==
* 工厂模式优化
* `Java JDBC`数据库操作等

## 使用

反射机制的实现 主要通过 **操作java.lang.Class类**，其存放着对应类型对象的 **运行时信息**

* Class对象的获取

  1. 实例.getClass()
  2. Class类的静态方法：Class.forName("类的权限定名称");
  3. 类的Class属性：类名.Class

  ==在`Java`程序运行时，`Java`虚拟机为所有类型维护一个`java.lang.Class`对象==

  泛型形式为`Class<T>`

  每种类型的`Class`对象只有1个 = 地址只有1个

  ```java
// 对于2个String类型对象，它们的Class对象相同
  Class c1 = "Carson".getClass();
  Class c2 =  Class.forName("java.lang.String");
  // 用==运算符实现两个类对象地址的比较
  System.out.println(c1 ==c2);
  // 输出结果：true
  ```
  
* Class类中方法

  1. 获取 目标类型的`Class`对象

  2. 通过 `Class` 对象分别获取`Constructor`类对象、`Method`类对象 和 `Field` 类对象

  3. 通过 `Constructor`类对象、`Method`类对象和 `Field`类对象分别获取类的构造函数、方法和属性的具体信息，并进行后续操作

     invoke(实例,参数)

     ```java
     //在list<String>的实例中，插入int型的数据
     List<String> list = new ArrayList<String>();
     Class<?> clazz = list.getClass();
     //第一个是调用的方法名称，第二个是方法的形参类型
     Method m = clazz.getMethod("add", Object.class);
     //需要两个参数，一个是要调用的对象，一个是实参
     m.invoke(list, 1);
     m.invoke(list, "a");
     m.invoke(list, 2);
     for(Object obj : list) {
         System.out.println(obj);
     }
     //由于反射是在运行时发生，故可以躲避编译
     ```

  * 不带 "Declared"的方法支持取出包括继承、公有（Public）、不包括有（Private）的构造函数

  * ==带 "Declared"的方法是支持取出包括公共（Public）、保护（Protected）、默认（包）访问和私有（Private）的构造方法，但不包括继承的构造函数==

  * 访问权限问题

    * setAccessible(true)   //暴力访问(忽略掉访问修饰符)

# 异常处理

Exception异常类有两个主要的子类：IOException 类和 RuntimeException 类

⾃定义异常

==所有异常都必须是 Throwable 的⼦类==

```java
//throws表示⼀个方法声明可能抛出⼀个异常，throw表示此处抛出一个已定义的异常(可以是自定义需继承Exception，也可以是java⾃己给出的异常类)
```

* java.lang.Throwable
  * Error：与程序本身⽆关，不捕获
  * Exception：程序本身有关，可捕获可不捕获
    * RuntimeException
    * CheckedException
      * 如==IOException、SQLException==

## 受检异常

* ==必须捕获的异常，否则⽆法通过检查认证==
  * throws
  * try catch
* ==⾃定义受检异常：继承Exception==
* 接⼝throws XXXException
* 缺点：
  * ==异常是主逻辑的补充逻辑， 修改补充逻辑会导致主逻辑的修改==
  * ==实现类的变更会影响调用者，破坏封装性==
* 优化
  * 转为非受检异常

## 非受检异常

* ==可捕获可不捕获的异常==
  * RuntimeException
  * ==如果想写一个运行时异常类，那么需要继承 RuntimeException 类==

# ==动态代理==

## JDK动态代理

* ==JDK中所要进行动态代理的类必须要实现一个接口，只能对该类所实现接口中定义的方法进行代理`InvocationHandler`==
* 原理是在运行期间创建一个接口的实现类来完成对目标对象的代理
  * 通过反射机制生成代理类的字节码文件，调用具体方法前调用InvokeHandler来处理

```java
1. 被代理对象实现某个接口A 
2. 代理类实现 InvocationHandler
3. 代理类添加被代理对象属性
4. 添加操作
5. 返回代理对象
6. 代理类获取代理对象
public class JdkProxy implements InvocationHandler {
	//private Object proxied;  //Object类型
    private JdkSon target;   //被代理对象，实现接口A
    public  Object getInstance(JdkSon target) {
        this.target = target;
        Class<? extends JdkSon> clazz = target.getClass();
        //创建动态代理
        //类加载器，代理实现的接口列表(不是类或抽象类)、InvocationHandler接口的实现
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdkProxy before....");  //添加操作
        //执行被代理的操作，然后使用Method.invoke()将请求转发给被代理对象，并传入必须的参数
        method.invoke(this.target, args);
        System.out.println("jdkProxy after");
        return null;
    }
}
//动态代理可以将所有调用重定向到调用处理器，因此通常会向调用处理器的构造器传递给一个"实际"对象的引用，从而使得调用处理器在执行其中介任务时，可以将请求转发。
Person person = (Person)new JdkProxy().getInstance(new JdkSon());  //返回接口类型A
```

## cglib动态代理

为没有实现接口的类提供代理，为JDK的动态代理提供了很好的补充

* **CGLIB原理**：==动态生成一个要代理类的子类，子类重写要代理的类的所有不是final的方法。在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。`MethodInterceptor`==
* 它比使用java反射的JDK动态代理要快
* CGLIB底层：使用字节码处理框架ASM，来转换字节码并生成新的类
* **CGLIB缺点**：对于final方法，无法进行代理

```java
1. 代理类实现 MethodInterceptor 接口
2. 添加操作
3. 返回代理对象
4. 代理类获取代理对象
public class CglibProxy implements MethodInterceptor {
    public Object getInstance(Class<?> clazz) {
    //Enhancer允许为非接口类型创建一个Java代理。Enhancer动态创建了给定类型的子类但是拦截了所有的方法。和Proxy不一样的是，不管是接口还是类他都能正常工作
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz); //被代理对象
        enhancer.setCallback(this);
        return enhancer.create();
    }

    //Object为由CGLib动态生成的代理类实例
    //Method为上文中实体类所调用的被代理的方法引用
    //Object[]为参数值列表
    //MethodProxy为生成的代理类对方法的代理引用
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Hi, before");
        methodProxy.invokeSuper(o, objects);
        System.out.println("Hi, after");
        return null;
    }
}

Dog dogCglib = (Dog)new CglibProxy().getInstance(Dog.class); // 代理对象
```

# Servlet

* 有一个servlet提供对外服务，10个请求下创建了几个servlet实列
  * 一个，Servlet执行完它的第一个请求之后，就会驻留在内存中，等待后续的请求
* ==JSP页面其实是一个Servlet，但是不需要编译JSP页面，可以在任何文本编译器中编写，不需要在部署描述符中进行标注或映射成一个URL==
* HTTP协议
  * 使得Web服务器与浏览器之间可以通过互联网或者企业内部网来交换数据

## Servlet

* Servlet 是一种基于 Java 技术的 Web 组件，用于生成动态内容，由容器管理。

* Tomcat是Servlet/JSP容器、Web服务器，管理Servlets实例以及它们的生命周期，并监听端口，当客户端的请求发送过来后，其根据url等信息确定要将请求交给哪个Servlet去处理，然后调用该Servlet的service()方法返回一个response对象，tomcat再把这个response对象返回给客户端，并提供静态内容

* ==一个Servlet程序其实就是一个实现了Java特殊接口的类，里面有几个方法，实现接口中的方法，告诉程序，初始化需要干什么，销毁需要干什么，接收到请求该做什么==

* ==Servlet接口定义了Servlet与Servlet容器之间的一个契约：Servlet容器会把Servlet类加载到内存中，并在Servlet实例中调用特定的方法，在一个应用程序中，每个Servlet类型只能有一个实例==

  ​	Servlet处理请求，只能由其tomcat容器解决；

* Tomcat

  * `conf/server.xml`该文件用于配置server相关的信息，比如tomcat启动的端口号，配置主机(Host)

  * `web.xml`文件配置与web应用（web应用相当于一个web站点）

  * `tomcat-user.xml`配置用户名密码和相关权限.

  * webapps：放置web应用：`./web2/index.html - localhost:8080/web2/index.html`

  * 虚拟主机

    * **多个不同域名的网站共存于一个Tomcat中**

* 声明
  1. 注解类型声明Servlet`@WebServlet(...)`
  2. web.xml中声明Servlet
  
* WEB-INF目录：可以被Servlet 访问，但是不能被用户访问

* ==http://localhost:8080/模块名/servlet名==

* ServletContext，表示Servlet应用程序，每个web应用程序只有一个context

* 对于每次访问请求，Servlet引擎都会创建一个新的HttpServletRequest请求对象和一个新的HttpServletResponse响应对象，然后将这两个对象作为参数传递给它调用的Servlet的service()方法**，**service方法再根据请求方式分别调用doXXX方法

* Tomcat 有哪几种Connector 运行模式(优化)？

  * bio(blocking I/O)、nio（non-blocking I/O）、apr（阿帕奇可移植库）
  
* get：在URL地址后附带的参数是有限制的，其数据容量通常不能超过1，一般用来获取数据

* post：可以在请求的实体内容中向服务器发送数据，传送的数据量无限制，一般用来提交数据

* ==转发(forward)和重定向(redirect)==

  * 转发是发生在服务器的，只能去往当前web应用的资源，浏览器的地址栏是没有发生变化，一次http请求
    * A找B借钱，B说没有，B去找C借，借到借不到都会把消息传递给A
    * `request.getRequestDispacther()`
    * `RequestDispatcher`类的`forward()`方法
  * 重定向是发生在浏览器的，可以去往任何的资源，浏览器的地址会发生变化，两个http请求，只能传递字符串，整个页面执行完之后才执行跳转
    * A找B借钱，B说没有，让A去找C借
    * `response.sendRedirect()`
  * 给服务器用的直接从资源名开始写，给浏览器用的要把应用名写上
    * `request.getRequestDispatcher("/资源名 URI").forward(request,response)`
      * 转发时"/"代表的是本应用程序的根目录
    * `response.send("/web应用/资源名 URI");`
      * 重定向时"/"代表的是webapps目录

* ==线程安全问题==：

  * 当多个用户访问Servlet的时候，服务器会为每个用户创建一个线程**。**当多个用户并发访问Servlet共享资源的时候就会出现线程安全问题。
    1. 如果一个变量需要多个用户==共享==，则应当在访问该变量的时候，加同步机制synchronized (对象){}
    2. 如果一个变量不需要共享，则直接在 doGet() 或者 doPost()定义.这样不会存在线程安全问题

## 生命周期方法

```java
void init(ServletConfig config) throws ServletException
	//只是第一次请求Servlet时调用，可以编写一些应用程序初始化相关的代码
void service(ServletRequest request, ServletResponse response)
    //每次请求Servlet时，Servlet容器都会调用
void destroy()
    //销毁Servlet时调用
    
String getServletInfo()
    //返回Servlet的描述
ServletConfig getServletConfig()
    //返回由Servlet容器传给init方法的ServletConfig
```

## 隐式对象

* ==request、response、out、session、application、config、pageContext、page、exception==
* ==属性四种范围==：page、request、session、application

## EL

* ==被设计成能够轻松的编写无脚本或不包含Java代码的JSP页面==
* ${}

## JSTL

* ==是一个定制标签类库的集合==，是指标准标签类库，通过多个标签类库来显露其动作指令的

## 监听器

* ==创建监听器时，只要创建一个实现相关接口的Java类即可==
* ==注册监听器==：@WebListener、部署描述符中<listener>
  * ServletContext监听器
    * ServletContextListener会对ServletContext的初始化和解构做出响应
  * Session监听器
    * HttpSessionListener：当有HttpSession被创建或者销毁时，Servlet容器就会调用所有已注册的HttpSessionListener
  * ServletRequest监听器
    * ServletRequestListener对ServletRequest的创建和销毁做出响应

## 过滤器

* 拦截请求，并对传给被请求资源的ServletRequest或ServletResponse进行处理的一个对象，可以配置为拦截一个或多个资源；可通过注解或者部署描述符
* ==必须实现Filter接口==，为每个过滤器暴露3个生命周期方法
  * init()、doFilter、destroy

## servlet中Filter和SpringMVC的Interceptor区别

* Filter(过滤器)

  ==Filter是基于servlet的规范，采用函数回调机制实现的==

  ==主要功能是对资web资源访问的管理控制==，比如实现URL级别的权限访问控制、设置字符编码、过滤敏感词汇等。

  在每次请求过来时，都会调用doFilter方法，该方法有个FilterChain的对象参数，它提供了一个doFilter方法，可以根据需求决定是否调用，如果调用，则web服务器就会调用web资源的service方法，即web资源就会被访问，否则web资源不会被访问

  ```java
  public interface Filter {   
    public void init(FilterConfig filterConfig) throws ServletException;  
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException;
    public void destroy();
  }
  ```

* Interceptor

  ==Interceptor是在Spring容器内的，是Spring框架支持的==

  ==主要是提供一种机制可以在一个Action执行前后执行一些操作==

  ```java
  public interface HandlerInterceptor {
    //在请求处理之前调用，也就是Controller方法之前；
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
   //Controller 方法调用之后执行，但是它会在DispatcherServlet 进行视图返回渲染之前被调用
    void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception;
   //将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行，这个方法的主要作用是用于进行资源清理工作的
    void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception;
  }
  ```

* ==Filter和Interceptor的执行顺序==

  过滤前-拦截前-action执行-拦截后-过滤后

* ==主要区别==

  1. ==Filter依赖于Servlet容器，只能用于web程序中，而Interceptor不依赖于Servlet容器==，在Sping容器中，既可用于web容器，也可以用于Application和Swing程序中
  2. ==Filter是基于函数回调，而Interceptor是基于java的反射机制==，Spring里的HandlerInterceptor，是通过循环的方式在handler处理请求前后分别调用preHandle()方法和postHandle()方法对请求和响应进行处理
  3. ==Interceptor配置在Spring文件中，可以获取Spring里任何资源和对象，比如Controller上下文、值栈里的对象==；而Filter不能访问

# Session 

* 在Java中，HTTP的Session对象用javax.servlet.http.HttpSession来表示
  * `HttpSession session = request.getSession();`
    * 从当前request中获取session，如果获取不到session，则会自动创建一个session，并返回新创建的session；如果获取到，则返回获取到的session
  * `HttpSession session = request.getSession(true);`
    * 和`request.getSession()`一样，只是增家了一个true参数，告诉它在获取不到的时候自动创建session
  * `HttpSession session = request.getSession(false);`
    * 获取不到session的时候，不会自动创建session，而是会返回null
  * 想要存储到session中时，使用request.getSession()
  * 想要获取session中的信息时，使用request.getsession(false)，并在获取后对session变量进行是否为null的判断，再进行下一步操作
* ==Session代表服务器与浏览器的一次会话过程==，这个过程是连续的，也可以时断时续的
* Session创建的时间是：
  * 服务端程序调用`HttpServletRequest.getSession(true)`时才创建
* 引申
  * 当JSP页面没有显式禁止session的时候，在打开浏览器第一次请求该jsp的时候，服务器会自动为其创建一个session，并赋予其一个sessionID用来标识该Session对象，发送给客户端的浏览器。以后客户端接着请求本应用中其他资源的时候，会自动在请求头上添加：Cookie:JSESSIONID=客户端第一次拿到的session ID。
  * ==这样，服务器端在接到请求时候，就会收到session ID，并根据ID在内存中找到之前创建的session对象，提供给请求使用。这也是session使用的基本原理==。
* Session删除的时间是：
  * Session超时：超时指的是连续一定时间服务器没有收到该Session所对应客户端的请求，并且这个时间超过了服务器设置的Session超时的最大时间
  * 程序调用HttpSession.invalidate()
  * 服务器关闭或服务停止
  * session不会因为浏览器的关闭而删除
* session存放在哪里：

  * ==服务器端的内存中==。不过session可以通过特殊的方式做持久化管理
* session是一个容器，可以存放会话过程中的任何对象
* ==session因为请求（request对象）而产生，同一个会话中多个request共享了一session对象，可以直接从请求中获取到session对象==
* 其实，session的创建和使用总在服务端，而浏览器从来都没得到过session对象。但浏览器可以请求Servlet（jsp也是Servlet）来获取session的信息。客户端浏览器真正紧紧拿到的是session ID，而这个对于浏览器操作的人来说，是不可见的，并且用户也无需关心自己处于哪个会话过程中
* ==cookie和session的关联关系==

  * ==HTTP是无状态协议：客户端发起N次请求，服务端并不知道是同一个客户端发送的；==
  * 当客户端第一次请求服务器时，服务器为客户端用户创建对应的Session对象，并返回标识该Session对象的SessionID给客户端，下次客户端带着SessionID，即Cookie来请求服务器时，服务器就能知道是哪个客户端用户请求的，然后就可以做相应的响应等——基本原理
    * session保存用户信息，用户每次在访问同一个客户端向服务端发送请求时，服务端就能知道是同一个客户端。
    * cookie：存储客户端信息
    * 在HTTP协议有状态操作中依赖session和cookie完成有状态协议过程
  * 区别：
    * Cookie只能存储字符串，Session可以存储任何类型数据，可以把Session看成是一个容器
    * Cookie存储在浏览器中，Session存储在服务器上
    * 如果浏览器禁用了Cookie，那么Cookie是无用的了；如果浏览器禁用了Cookie，Session可以通过URL地址重写来进行会话跟踪

# 注解

* ==其实就是代码中的特殊标记，可以在编译、类加载、运行时被读取，并执行相对应的处理==

## 作用

* ==让编译器检查代码==
* 让数据注入到方法、成员变量、类上

在JDK中注解分为了：

1. 基本Annotation
   * 常用于标记方法来抑制编译器警告
2. 元Annotaion
   * 标记其他注解的注解

## 自定义注解

* 标记注解：没有任何成员变量的注解，@Overried就是一个标记注解

  ```java
  //有点像定义⼀个接⼝⼀样，只不过它多了⼀个@ 
  public @interface MyAnnotation {
  }
  ```

* 元数据Annotation：定义带成员变量的注解

  ```java
  //在注解上定义的成员变量只能是String、数组、Class、枚举类、注解 
  //注解的作⽤就是给类、方法注⼊信息
  public @interface MyAnnotation {
  	//定义了两个成员变量 
      String username(); 
      int age();
  }
  ```

* 使⽤⾃定义注解

  ```java
  //注解拥有什什么属性，在修饰的时候就要给出相对应的值 
  @MyAnnotation(username = "zhongfucheng", age = 20) 
  public void add(String username, int age) {
  }
   
  public @interface MyAnnotation { 
      //定义了两个成员变量
  	String username() default "zicheng"; 
      int age() default 23;
    	int[] arr() default {1,2,3}; //这里不能使用new int[]{1,2,3}，@MyAnn(arr={1,2,3})
  }
  //在修饰的时候就不需要给出具体的值了 
  @MyAnnotation()
  public void add(String username, int age) {
  }
  ```

  ```java
  //注解上只有一个属性，并且属性的名称为value，那么在使⽤的时候，可以不写value，直接赋值给 它就行
  public @interface MyAnnotation2 {
      String value();
  }
  //使用注解，可以不指定value，直接赋值 
  @MyAnnotation2("zhongfucheng") 
  public void find(String id) {
  }
  ```

* ==把自定义注解的基本信息注入到方法上==

  利用的是==反射技术getAnnotation(XXXAnnotation.class)==

  * 反射出该类的方法
  *   通过方法得到注解上具体的信息
  *   将注解上的信息注⼊到方法上

  ```java
  //需要在自定义注解上加入这样⼀句代码 
  @Retention(RetentionPolicy.RUNTIME)
  //反射出该类的⽅法
  Class aClass = Demo2.class;
  Method method = aClass.getMethod("add", String.class, int.class);
  //通过该方法得到注解上的具体信息
  MyAnnotation annotation = method.getAnnotation(MyAnnotation.class); 
  String username = annotation.username();
  int age = annotation.age();
  //将注解上的信息注⼊到方法上
  Object o = aClass.newInstance(); 
  method.invoke(o, username, age);
  ```

## JDK的元注解

* @Retention
  * ==⽤于指定被修饰的Annotation被保留多⻓时间==
  
    ```java
    //运行时
  @Retention(RetentionPolicy.RUNTIME)  
    ```
  
  * SOURCE、CLASS(默认)、 RUNTIME（运行时，一般反射时用）
  
* @Target
  
  * ==指定被修饰的Annotation⽤于修饰哪些程序单元（如类、方法、变量等）==
  
    ```java
  //注解只能用在类和方法上
    @Target({ElementType.TYPE, ElementType.METHOD}) 
    ```
  
  * 只有一个value成员变量的，该成员变量的值是以下的
  
    * TYPE（类）, FIELD,METHOD, PARAMETER,CONSTRUCTOR,LOCAL_VARIABLE,ANNOTATION_TYPE, PACKAGE
  
* @Documented

  * 指定被该Annotation修饰的Annotation类将被javadoc工具提取成⽂档

* @Inherited

  * 被修饰过的Annotation将具有继承性

  * @Inherited标记注解@A，@A标记类B，C类继承B类，那么C类也具有注解@A

## 使用过程

* 定义注解类：框架的工作

  ==注解的使用范围：类（接口和枚举类型）、属性、方法、构造器、包、参数、局部变量==  

  ```java
  //定义注解处理器
  //可通过反射获取method上的注解信息
  method.getAnnotation(Retry.class);
  //可通过反射执行方法
  method.invoke(new Object(), "57890");  
  ```
  

# 输入输出

* 内存⻆度

* 字节流：8位字节（InputStream、OutputStream）
* 字符流：16位字符（Reader、Writer）
* IO流：
  * 底层节点流 (连接实际数据源)—— 包装 —— 上层处理流
  * 上层处理流(对已存在的流进行连接或封装)
* windows路径下包括反斜线，应使用两条 F:\abc\a.txt，或直接使⽤ /，换⾏符 \r\n
* Linux 换行符 \n

## File

* File类：⽂件和⽬录(新建、删除、重命名文件目录)，但不能访问文件内容本身——输入/输出流

* InputStream

  ```java
  int read() 从输入流中读取单个字节，返回所读取的字节数据
  int read(byte[] b) 从输入流中最多读取b.length个字节数据，并存在b中，返回实际读取的字节数
  int read(byte[] b ,int off, int len)
  ```

  ```java
  FileOutputStream fos = new FileOutputStream("b.java"); 
  FileInputStream fis = new FileInputStream("a.java"); 
  byte[] buf = new byte[1024];
  int hasRead = 0; //用于保存实际读取的字节数
  while((hasRead = fis.read(buf)) > 0) {
      fos.write(buf, 0, hasRead);
  }
  fos != null fos.close();
  fis != null fis.close();
  ```

* Reader

  ```java
  int read() 从输⼊流中读取单个字符，返回所读取的字符数据
  int read(char[] b) 从输⼊流中最多读取b.length个字符数据，并存在b中，返回实际读取的字符数
  int read(char[] b ,int off, int len)
  ```

* 处理流

  * 使用处理流来包装节点流(构造器参数物理IO节点)，程序通过处理流(构造器参数为已存的流)来执行输入/输出的功能

  * 让节点流与底层的I/O设备、⽂件交互
  * 关闭资源时，只要关闭最上层的处理流

* 缓冲流

  * BufferedInputStream

  * flush()

  * BufferedReader readLine() != null

* 转换流

  * InputStreamReader 字节输⼊流 —— > 字符输入流

  * OutputStreamWriter 字节输出流 ——> 字符输出流

* 与系统有关的默认名称分隔符(跨系统的斜杠)：File.separator

* 与系统有关的路径分隔符：在 UNIX 系统上，此字段为 ':'；在 Microsoft Windows 系统上，它为 ';' File.pathSeparatorChar

* [文件工具](http://blog.sina.com.cn/s/blog_abb3c2e90102uyz3.html)

## JDK，JRE 和 JVM 的联系和区别

* JVM 代表 Java 虚拟机，将编译后的字节码文件，转换为特定的机器代码，提供内存回收和安全机制，它的责任是运行 Java 应用，独立于操作系统和硬件，是java语言一次编译到处运行的原因
* JRE 代表 Java 运行时环境，是运行 Java 引用所必须的，包含jvm
* JDK 代表 Java 开发工具，包含 Java 编译器、 JRE
* JIT 代表即时编译，当代码执行的次数超过一定的阈值时，会将 Java 字节码转换为本地代码，如，主要的热点代码会被准换为本地代码，这样有利大幅度提高 Java 应用的性能

## java8特性

1. ==HashMap==底层实现新增了，红黑树；

2. ==jvm内存管理==：由元空间代替了永久代；

   区别：

   元空间不再存在虚拟机内存中，而是本地内存，大小默认情况下只受本地内存控制
   
3. ==lambda表达式==：允许把函数当成参数，传递给某个方法，或者把代码本身当做数据处理；

4. ==函数式接口==：指的是只有一个方法的接口，如runnable和callnable，java8中增加注解@Functionallnterface，来表明为函数式接口

5. ==引入重复注解==：@repeatable注解，来定义注解为重复注解

6. ==接口中可以实现方法：default方法==

7. ==注解的使用场景拓宽：注解可以使用在任何元素上==

8. ==新的包，java.time包==

   该包包含了所有关于日期、时间、时区、持续时间、和时钟操作的类

   这些类都是不可变的，线程安全的

# 工具类

* replace与replaceAll的区别

  * replace既可以==支持字符的替换，也支持字符串的替换==
  * replaceAll的参数是regex，即==基于规则表达式的替换==，比如：可以通过replaceAll("\d", "*")把一个字符串所有的数字符都换成星号
  * ==都是全部替换==，即把源字符串中的某一字符或字符串全部换成指定的字符或字符串

# 简单算法

## Java实现LRU（最近最少使用）策略的缓存

```java
public class LRUCache2 {
    private HashMap<Integer, Integer> cacheMap = new HashMap<>();
    private LinkedList<Integer> recentlyList = new LinkedList<>();
    private int capacity;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
    }

    private void put(int key, int value) {
        if (cacheMap.containsKey(key)) {
           // 不带（Integer）则是根据下标删除，可能会导致数组越界异常
            recentlyList.remove((Integer) key);
        }
        if (cacheMap.size() == capacity) {
           // List没有removeFirst方法
            cacheMap.remove(recentlyList.removeFirst());
        }
        cacheMap.put(key, value);
        recentlyList.add(key);
    }
  
    private int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
       // 不带（Integer）则是根据下标删除，可能会导致数组越界异常
        recentlyList.remove((Integer) key);
        recentlyList.add(key);
        return cacheMap.get(key);
    }
  
    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // returns 1
        cache.put(3, 3); // 驱逐 key 2
        System.out.println(cache.get(2)); // returns -1 (not found)
        cache.put(4, 4); // 驱逐 key 1
        System.out.println(cache.get(1)); // returns -1 (not found)
        System.out.println(cache.get(3)); // returns 3
        System.out.println(cache.get(4)); // returns 4
    }
}
```

## 排序

* 冒泡

  ```java
  public static int[] bubbleSort(int[] arr) {
      int len = arr.length;
      for (int i = 0; i < len - 1; i++) {
          for (int j = 0; j < len - 1 - i; j++) {
              //内循环比较
              if (arr[j] > arr[j+1]) {        // 相邻元素两两对比
                  int temp = arr[j+1];        // 元素交换
                  arr[j+1] = arr[j];
                  arr[j] = temp;
              }
          }
      }
      return arr;
  }
  ```

* 选择

  ```java
  public static int[] selectionSort(int[] arr) {
      int len = arr.length;
      int minIndex, temp;
      for (int i = 0; i < len - 1; i++) {
          minIndex = i;
          for (int j = i + 1; j < len; j++) {
              if (arr[j] < arr[minIndex]) {     // 寻找最小的数
                  minIndex = j;                 // 将最小数的索引保存
              }
          }
          temp = arr[i];
          arr[i] = arr[minIndex];
          arr[minIndex] = temp;
      }
      return arr;
  }
  ```

* 插入

  ```java
  public static int[] insertionSort(int[] arr) {
      int len = arr.length;
      int preIndex, current;
      for (int i = 1; i < len; i++) {
          preIndex = i - 1;
          current = arr[i];
          while (preIndex >= 0 && arr[preIndex] > current) {
              arr[preIndex + 1] = arr[preIndex];
              preIndex--;
          }
          arr[preIndex + 1] = current;
      }
      return arr;
  }
  ```

* 快速

  ```java
  /*
  1. 设置两个变量i和j，排序开始的时候i=0, j=6;
  2. 以第一个数组元素作为关键数据，赋值给key，即key=a[0]
  3. 从j开始向前搜索，即由后开始向前搜索(j--)，找到第一个小于key的值，两者交换
  4. 从i开始向后搜索，即由前开始向后搜索(i++)，找到第一个大于key的值，两者交换
  5. 重复第3、4步，直到i=j；此时将key赋值给a[i]
  */
   public static int[] quickSort(int[]a, int low, int high) {
       int i = low;
       int j = high;
       //当数组的第一位序号小于最后一位序号时，需要排序；也就是传进来的低位和高位是一样的，数组长度为1的时候，不需要排序
       if(low < high) {
           int key = a[low];
           //由于每次比较i++、j--，直到i = j时停止循环
           while (i < j) {
               //先将最后一位和key比较，直到找到比key小的，进行互换位置，然后再从头和key比较
               while (key <= a[j] && i < j) {
                   j --;
               }
               a[i] = a[j];
               while (key >= a[i] && i < j) {
                   i++;
               }
               a[j] = a[i];
               a[i] = key;
           }
           //采用递归，分别对两边再进行排序
           //左边
           quickSort(a, low, i - 1);
           //右边
           quickSort(a, i + 1, high);
       }
       return a;
   }
  ```

## 二分法查找

```java
//递归
public static int halfSearch(int[] a, int left, int right, int target) {
    int mid = (left + right) / 2;
    int midValue = a[mid];
    if(left <= right) {
        if(midValue > target) {
            return halfSearch(a, left, mid - 1, target);
        } else if(midValue < target) {
            return halfSearch(a, mid + 1, right, target);
        } else {
            return mid;
        }
    }
    return -1;
}
```

```java
//非递归
// 前提是数组已排序
// 最小值下标、最大值下标
// while循环(最小值下标 < 最大值下标)
// 取中间值下标与目标值比较：
// 如果大于目标值，则最大值下标-1；
// 如果小于目标值，则最小值下标+1，
// 否则返回中间值下标
public static int halfSearch2(int []a, int target) {
    int i = 0;
    int j = a.length - 1;

    while (i <= j) {
        int mid = (i + j) / 2;
        int midValue = a[mid];
        if(midValue > target) {
            j = mid - 1;
        } else if(midValue < target) {
            i = mid + 1;
        } else {
            return mid;
        }
    }
    return -1;
}
```

## 斐波拉契数列

```java
//1、1、2、3、5、8、13、21、34 ...
// 返回斐波那契数第n个值,n从1开始
public static int fibM(int n) {
  if(n < 0) {
    return -1;
  }
  if(n ==1 || n == 2) {
    return 1;
  } else {
    return fibM(n-1) + fibM(n-2);
  }
}
```

```java
public static int fibN(int n) {
	if(n < 0) {
    return -1;
  }
  if(n == 1 || n == 2) {
    return 1;
  }
  //        int a = 3, b = 5, c = 0;
  //不是递归，从第一个开始
  int a = 1, b = 1, c = 0;
  for(int i=3; i <= n; i++) {
    c = a + b;
    // a = b + c;
    // b = c + a;
    //依次向前移动
    a = b;
    b = c;
  }
  return c;
}
```







