# Redis-Connection with spring boot

<html>
<h3> 𝗪𝗵𝗮𝘁 𝗶𝘀 𝗥𝗲𝗱𝗶𝘀 ? </h3>
<p>Redis is an open source(BSD licensed) in-memory remote data structure store(database) that offers high performance, replication, and a unique data model. The full form of Redis is Remote Directory Server. Moreover, we can use it in multiple forms. Redis provides data structures such as strings, hashes, lists, sets, sorted sets with range queries, bitmaps, hyperloglogs, geospatial indexes, and streams. </p>
<p> You can use Redis from most programming languages. Redis is written in ANSI C and works in most POSIX systems like Linux, *BSD, and OS X, without external dependencies. Linux and OS X are the two operating systems where Redis is developed and tested the most.</p>

<h3> 𝗪𝗵𝗮𝘁 𝗶𝘀 𝗥𝗲𝗱𝗶𝘀 𝗗𝗮𝘁𝗮𝗯𝗮𝘀𝗲 ? </h3>
<p> Redis is a NoSQL DB of type Key-Value stores. In fact, Redis Database is an in-memory database that persists on disk. It means when we use Redis Database, we occupy a memory on the disk to use as a Database. The data model is key-value, but many several kind of values are supported such as Strings, Lists, Sets, Sorted Sets, Hashes, Streams, HyperLogLogs, Bitmaps etc. </p>

<h3> 𝗛𝗼𝘄 𝗱𝗼𝗲𝘀 𝗥𝗲𝗱𝗶𝘀 𝗱𝗮𝘁𝗮𝗯𝗮𝘀𝗲 𝘄𝗼𝗿𝗸 ? </h3>
<p> Unlike other databases such as MongoDB, PostgreSQL, Oracle, Cassandra that store data on disk or SSDs, all Redis data resides in-memory. As it doesn’t need to access disks, it avoids seek time delays. As a result, it can access data in microseconds. Each Redis database instance has a key space linked with it which is nothing but a wrapper on hash table implementation. Whatever data Redis stores such as string, redis set or redis hash, everything is saved inside the hash tables.</p>

<h3> 𝗪𝗵𝗮𝘁 𝗮𝗿𝗲 𝘁𝗵𝗲 𝗯𝗲𝗻𝗲𝗳𝗶𝘁𝘀 𝗼𝗳 𝗥𝗲𝗱𝗶𝘀 ? </h3>
<p>
i) In-memory data store<br>
ii) Flexible data structures : like Strings, Lists, Sets, Sorted Sets, Hashes, Bitmaps, HyperLoglogs<br>
iii) Simplicity and ease-of-use<br>
iv) High availability and scalability<br>
v) Easy for Replication and persistence<br>
vi) High Extensibility<br>
</p>

<h1> 𝗦𝗽𝗿𝗶𝗻𝗴 𝗕𝗼𝗼𝘁 𝗥𝗲𝗱𝗶𝘀 𝗖𝗥𝗨𝗗 𝗘𝘅𝗮𝗺𝗽𝗹𝗲 </h1>
<p> Let’s consider an Employee entity to develop our example. </p>

<h3> 𝗦𝘁𝗲𝗽#𝟭: 𝗖𝗿𝗲𝗮𝘁𝗲 𝗮 𝗻𝗲𝘄 𝗦𝗽𝗿𝗶𝗻𝗴 𝗕𝗼𝗼𝘁 𝗦𝘁𝗮𝗿𝘁𝗲𝗿 𝗣𝗿𝗼𝗷𝗲𝗰𝘁 𝘂𝘀𝗶𝗻𝗴 𝗦𝗧𝗦 </h3>
<p> Let’s create a Spring Boot Starter project using STS. While creating Starter Project select ‘Spring Data Redis’, ‘Lombok’, and ‘Sprong Boot DevTools’ as starter project dependencies. Please note that like ‘Spring Data JPA’ in relational databases, we will use ‘Spring Data Redis’ here in case of redis database. </p>

<h3> 𝗦𝘁𝗲𝗽#𝟮: 𝗨𝗽𝗱𝗮𝘁𝗲 𝗮𝗽𝗽𝗹𝗶𝗰𝗮𝘁𝗶𝗼𝗻.𝗽𝗿𝗼𝗽𝗲𝗿𝘁𝗶𝗲𝘀 </h3>
<p> For this example, updating the application.properties is optional. This is because the value of below properties are internally configured by Redis by default.

spring.redis.host=localhost<br>
spring.redis.port=6379 </p>

<h3> 𝗦𝘁𝗲𝗽#𝟯: 𝗖𝗿𝗲𝗮𝘁𝗲 𝗮 𝗖𝗼𝗻𝗳𝗶𝗴 𝗰𝗹𝗮𝘀𝘀 𝗮𝘀 𝗔𝗽𝗽𝗖𝗼𝗻𝗳𝗶𝗴.𝗷𝗮𝘃𝗮 </h3>
<p> This class will work as a configuration class, just to create a DB connection between our application and Redis DB. </p>

     //configuration class
     @Configuration
     public class AppConfig {
 
      //Creating Connection with Redis
      @Bean
      public RedisConnectionFactory redisConnectionFactory() {
       return new LettuceConnectionFactory();
       }
        //Creating RedisTemplate for Entity 'Employee'
       @Bean
       public RedisTemplate<String, Employee> redisTemplate(){
       RedisTemplate<String, Employee> empTemplate = new RedisTemplate<>();
       empTemplate.setConnectionFactory(redisConnectionFactory());
       return empTemplate;
       }
       }
      
<h3> 𝗦𝘁𝗲𝗽#𝟰: 𝗖𝗿𝗲𝗮𝘁𝗲 𝗮 𝗺𝗼𝗱𝗲𝗹 𝗰𝗹𝗮𝘀𝘀 𝗮𝘀 𝗘𝗺𝗽𝗹𝗼𝘆𝗲𝗲.𝗷𝗮𝘃𝗮 </h3>
<p> Now create a model class Entity.java as below. For the simplicity, we have taken three fields of different data types here. Moreover, we don’t need to apply @Entity annotation to tell DB that this is an Entity because there is even no concept of Entity. But while using Redis concept, make sure that our model class should implement java.io.Serializable interface.  </p>

     //Employee Class
     @Data
     @NoArgsConstructor
     @AllArgsConstructor
      //No @Entity concept here
     public class Employee implements Serializable {

     private static final long serialVersionUID = -7817224776021728682L;

     private Integer empId;
     private String empName;
     private Double empSalary;
     }
 
<h3> 𝗦𝘁𝗲𝗽#𝟱: 𝗖𝗿𝗲𝗮𝘁𝗲 𝗮𝗻 𝗜𝗻𝘁𝗲𝗿𝗳𝗮𝗰𝗲 𝗮𝘁 𝗗𝗔𝗢 𝗹𝗮𝘆𝗲𝗿 </h3>
<p> Let’s create an interface at DAO layer and declare the methods of CRUD operations. We will name it as IEmployeeDao.java. </p>
    
    //Employee interface DAO
    public interface IEmployeeDao {
    void saveEmployee(Employee emp);
    Employee getOneEmployee(Integer id);
    void updateEmployee(Employee emp);
    Map<Integer, Employee> getAllEmployees();
    void deleteEmployee(Integer id);
    void saveAllEmployees(Map<Integer, Employee> map);
     }
   
 <h3> 𝗦𝘁𝗲𝗽#𝟲: 𝗖𝗿𝗲𝗮𝘁𝗲 𝗮𝗻 𝗶𝗺𝗽𝗹𝗲𝗺𝗲𝗻𝘁𝗮𝘁𝗶𝗼𝗻 𝗰𝗹𝗮𝘀𝘀 𝗳𝗼𝗿 𝗜𝗻𝘁𝗲𝗿𝗳𝗮𝗰𝗲 𝗮𝘁 𝗗𝗔𝗢 𝗹𝗮𝘆𝗲𝗿 </h3>
 <p> Let’s create EmployeeDaoImpl.java class and implement the methods from the interface IEmployeeDao.java as below. This is the class of our primary interest as it will have implementation of all methods. Here, we need to declare a variable of type HashOperations<String, Integer, Employee> as a HAS-A relation. It will help us to implement CRUD methods and reduce the coding efforts. </p>
 
    //Employee impl
    @Repository
    public class EmployeeDaoImpl implements IEmployeeDao {

    private final String hashReference= "Employee";

    @Resource(name="redisTemplate")          // 'redisTemplate' is defined as a Bean in AppConfig.java
    private HashOperations<String, Integer, Employee> hashOperations;

    @Override 
    public void saveEmployee(Employee emp) {
        //creates one record in Redis DB if record with that Id is not present
        hashOperations.putIfAbsent(hashReference, emp.getEmpId(), emp);
    }

    @Override
    public void saveAllEmployees(Map<Integer, Employee> map) {
        hashOperations.putAll(hashReference, map);
    }

    @Override
    public Employee getOneEmployee(Integer id) {
       return hashOperations.get(hashReference, id);
    }

    @Override
    public void updateEmployee(Employee emp) {
       hashOperations.put(hashReference, emp.getEmpId(), emp);
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() {
       return hashOperations.entries(hashReference);
    }

    @Override
    public void deleteEmployee(Integer id) {
       hashOperations.delete(hashReference, id);
    }
    }
 
 <h1> 𝗛𝗼𝘄 𝘁𝗼 𝘁𝗲𝘀𝘁 𝘁𝗵𝗲 𝗶𝗺𝗽𝗹𝗲𝗺𝗲𝗻𝘁𝗲𝗱 𝗺𝗲𝘁𝗵𝗼𝗱𝘀 ? </h1>
 <p> In order to test the functionality of methods, we will create a Runner class that will implement CommandLineRunner. </p>
 
 <h3> 𝗦𝘁𝗲𝗽#𝟭 : 𝗖𝗿𝗲𝗮𝘁𝗲 𝗮 𝗥𝘂𝗻𝗻𝗲𝗿 𝗰𝗹𝗮𝘀𝘀 𝘁𝗼 𝘁𝗲𝘀𝘁 𝗮𝗹𝗹 𝘁𝗵𝗲 𝗺𝗲𝘁𝗵𝗼𝗱𝘀 </h3>
 <p> Now create a Runner class and test all methods that we defined in EmployeeDaoImpl.java class. </p>
 
    //Command line runner for redis operations
    @Component
    public class RedisOpertionsRunner implements CommandLineRunner {

    @Autowired
    private IEmployeeDao empDao;

    @Override
    public void run(String... args) throws Exception {

           //saving one employee
       empDao.saveEmployee(new Employee(500, "Emp0", 2150.0));

          //saving multiple employees
       empDao.saveAllEmployees(
            Map.of( 501, new Employee(501, "Emp1", 2396.0),
                    502, new Employee(502, "Emp2", 2499.5),
                    503, new Employee(503, "Emp4", 2324.75)
                  )
       );

         //modifying employee with empId 503
       empDao.updateEmployee(new Employee(503, "Emp3", 2325.25));

         //deleting employee with empID 500
       empDao.deleteEmployee(500);

        //retrieving all employees
       empDao.getAllEmployees().forEach((k,v)-> System.out.println(k +" : "+v));

        //retrieving employee with empID 501
       System.out.println("Emp details for 501 : "+empDao.getOneEmployee(501));
      }
      }
     
 <h3> 𝗦𝘁𝗲𝗽#𝟮 : 𝗦𝘁𝗮𝗿𝘁 𝗥𝗲𝗱𝗶𝘀 𝗦𝗲𝗿𝘃𝗲𝗿 </h3>
  <p> In order to test the implemented method, we need to start the Redis Server. </p>
  
<h3> 𝗦𝘁𝗲𝗽#𝟯 : 𝗥𝘂𝗻 𝗦𝗽𝗿𝗶𝗻𝗴 𝗕𝗼𝗼𝘁 𝗣𝗿𝗼𝗷𝗲𝗰𝘁 </h3>
<p> Right Click on the project, select Run As -> Spring Boot Project.</p> <br>

<p>Finally you can get output including all operations. However, we can test individual methods separately by commenting other methods.</p>
</html>
