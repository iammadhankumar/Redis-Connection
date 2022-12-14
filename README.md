# Redis-Connection with spring boot

<html>
<h3> ๐ช๐ต๐ฎ๐ ๐ถ๐ ๐ฅ๐ฒ๐ฑ๐ถ๐ ? </h3>
<p>Redis is an open source(BSD licensed) in-memory remote data structure store(database) that offers high performance, replication, and a unique data model. The full form of Redis is Remote Directory Server. Moreover, we can use it in multiple forms. Redis provides data structures such as strings, hashes, lists, sets, sorted sets with range queries, bitmaps, hyperloglogs, geospatial indexes, and streams. </p>
<p> You can use Redis from most programming languages. Redis is written in ANSI C and works in most POSIX systems like Linux, *BSD, and OS X, without external dependencies. Linux and OS X are the two operating systems where Redis is developed and tested the most.</p>

<h3> ๐ช๐ต๐ฎ๐ ๐ถ๐ ๐ฅ๐ฒ๐ฑ๐ถ๐ ๐๐ฎ๐๐ฎ๐ฏ๐ฎ๐๐ฒ ? </h3>
<p> Redis is a NoSQL DB of type Key-Value stores. In fact, Redis Database is an in-memory database that persists on disk. It means when we use Redis Database, we occupy a memory on the disk to use as a Database. The data model is key-value, but many several kind of values are supported such as Strings, Lists, Sets, Sorted Sets, Hashes, Streams, HyperLogLogs, Bitmaps etc. </p>

<h3> ๐๐ผ๐ ๐ฑ๐ผ๐ฒ๐ ๐ฅ๐ฒ๐ฑ๐ถ๐ ๐ฑ๐ฎ๐๐ฎ๐ฏ๐ฎ๐๐ฒ ๐๐ผ๐ฟ๐ธ ? </h3>
<p> Unlike other databases such as MongoDB, PostgreSQL, Oracle, Cassandra that store data on disk or SSDs, all Redis data resides in-memory. As it doesnโt need to access disks, it avoids seek time delays. As a result, it can access data in microseconds. Each Redis database instance has a key space linked with it which is nothing but a wrapper on hash table implementation. Whatever data Redis stores such as string, redis set or redis hash, everything is saved inside the hash tables.</p>

<h3> ๐ช๐ต๐ฎ๐ ๐ฎ๐ฟ๐ฒ ๐๐ต๐ฒ ๐ฏ๐ฒ๐ป๐ฒ๐ณ๐ถ๐๐ ๐ผ๐ณ ๐ฅ๐ฒ๐ฑ๐ถ๐ ? </h3>
<p>
i) In-memory data store<br>
ii) Flexible data structures : like Strings, Lists, Sets, Sorted Sets, Hashes, Bitmaps, HyperLoglogs<br>
iii) Simplicity and ease-of-use<br>
iv) High availability and scalability<br>
v) Easy for Replication and persistence<br>
vi) High Extensibility<br>
</p>

<h1> ๐ฆ๐ฝ๐ฟ๐ถ๐ป๐ด ๐๐ผ๐ผ๐ ๐ฅ๐ฒ๐ฑ๐ถ๐ ๐๐ฅ๐จ๐ ๐๐๐ฎ๐บ๐ฝ๐น๐ฒ </h1>
<p> Letโs consider an Employee entity to develop our example. </p>

<h3> ๐ฆ๐๐ฒ๐ฝ#๐ญ: ๐๐ฟ๐ฒ๐ฎ๐๐ฒ ๐ฎ ๐ป๐ฒ๐ ๐ฆ๐ฝ๐ฟ๐ถ๐ป๐ด ๐๐ผ๐ผ๐ ๐ฆ๐๐ฎ๐ฟ๐๐ฒ๐ฟ ๐ฃ๐ฟ๐ผ๐ท๐ฒ๐ฐ๐ ๐๐๐ถ๐ป๐ด ๐ฆ๐ง๐ฆ </h3>
<p> Letโs create a Spring Boot Starter project using STS. While creating Starter Project select โSpring Data Redisโ, โLombokโ, and โSprong Boot DevToolsโ as starter project dependencies. Please note that like โSpring Data JPAโ in relational databases, we will use โSpring Data Redisโ here in case of redis database. </p>

<h3> ๐ฆ๐๐ฒ๐ฝ#๐ฎ: ๐จ๐ฝ๐ฑ๐ฎ๐๐ฒ ๐ฎ๐ฝ๐ฝ๐น๐ถ๐ฐ๐ฎ๐๐ถ๐ผ๐ป.๐ฝ๐ฟ๐ผ๐ฝ๐ฒ๐ฟ๐๐ถ๐ฒ๐ </h3>
<p> For this example, updating the application.properties is optional. This is because the value of below properties are internally configured by Redis by default.

spring.redis.host=localhost<br>
spring.redis.port=6379 </p>

<h3> ๐ฆ๐๐ฒ๐ฝ#๐ฏ: ๐๐ฟ๐ฒ๐ฎ๐๐ฒ ๐ฎ ๐๐ผ๐ป๐ณ๐ถ๐ด ๐ฐ๐น๐ฎ๐๐ ๐ฎ๐ ๐๐ฝ๐ฝ๐๐ผ๐ป๐ณ๐ถ๐ด.๐ท๐ฎ๐๐ฎ </h3>
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
      
<h3> ๐ฆ๐๐ฒ๐ฝ#๐ฐ: ๐๐ฟ๐ฒ๐ฎ๐๐ฒ ๐ฎ ๐บ๐ผ๐ฑ๐ฒ๐น ๐ฐ๐น๐ฎ๐๐ ๐ฎ๐ ๐๐บ๐ฝ๐น๐ผ๐๐ฒ๐ฒ.๐ท๐ฎ๐๐ฎ </h3>
<p> Now create a model class Entity.java as below. For the simplicity, we have taken three fields of different data types here. Moreover, we donโt need to apply @Entity annotation to tell DB that this is an Entity because there is even no concept of Entity. But while using Redis concept, make sure that our model class should implement java.io.Serializable interface.  </p>

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
 
<h3> ๐ฆ๐๐ฒ๐ฝ#๐ฑ: ๐๐ฟ๐ฒ๐ฎ๐๐ฒ ๐ฎ๐ป ๐๐ป๐๐ฒ๐ฟ๐ณ๐ฎ๐ฐ๐ฒ ๐ฎ๐ ๐๐๐ข ๐น๐ฎ๐๐ฒ๐ฟ </h3>
<p> Letโs create an interface at DAO layer and declare the methods of CRUD operations. We will name it as IEmployeeDao.java. </p>
    
    //Employee interface DAO
    public interface IEmployeeDao {
    void saveEmployee(Employee emp);
    Employee getOneEmployee(Integer id);
    void updateEmployee(Employee emp);
    Map<Integer, Employee> getAllEmployees();
    void deleteEmployee(Integer id);
    void saveAllEmployees(Map<Integer, Employee> map);
     }
   
 <h3> ๐ฆ๐๐ฒ๐ฝ#๐ฒ: ๐๐ฟ๐ฒ๐ฎ๐๐ฒ ๐ฎ๐ป ๐ถ๐บ๐ฝ๐น๐ฒ๐บ๐ฒ๐ป๐๐ฎ๐๐ถ๐ผ๐ป ๐ฐ๐น๐ฎ๐๐ ๐ณ๐ผ๐ฟ ๐๐ป๐๐ฒ๐ฟ๐ณ๐ฎ๐ฐ๐ฒ ๐ฎ๐ ๐๐๐ข ๐น๐ฎ๐๐ฒ๐ฟ </h3>
 <p> Letโs create EmployeeDaoImpl.java class and implement the methods from the interface IEmployeeDao.java as below. This is the class of our primary interest as it will have implementation of all methods. Here, we need to declare a variable of type HashOperations<String, Integer, Employee> as a HAS-A relation. It will help us to implement CRUD methods and reduce the coding efforts. </p>
 
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
 
 <h1> ๐๐ผ๐ ๐๐ผ ๐๐ฒ๐๐ ๐๐ต๐ฒ ๐ถ๐บ๐ฝ๐น๐ฒ๐บ๐ฒ๐ป๐๐ฒ๐ฑ ๐บ๐ฒ๐๐ต๐ผ๐ฑ๐ ? </h1>
 <p> In order to test the functionality of methods, we will create a Runner class that will implement CommandLineRunner. </p>
 
 <h3> ๐ฆ๐๐ฒ๐ฝ#๐ญ : ๐๐ฟ๐ฒ๐ฎ๐๐ฒ ๐ฎ ๐ฅ๐๐ป๐ป๐ฒ๐ฟ ๐ฐ๐น๐ฎ๐๐ ๐๐ผ ๐๐ฒ๐๐ ๐ฎ๐น๐น ๐๐ต๐ฒ ๐บ๐ฒ๐๐ต๐ผ๐ฑ๐ </h3>
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
     
 <h3> ๐ฆ๐๐ฒ๐ฝ#๐ฎ : ๐ฆ๐๐ฎ๐ฟ๐ ๐ฅ๐ฒ๐ฑ๐ถ๐ ๐ฆ๐ฒ๐ฟ๐๐ฒ๐ฟ </h3>
  <p> In order to test the implemented method, we need to start the Redis Server. </p>
  
<h3> ๐ฆ๐๐ฒ๐ฝ#๐ฏ : ๐ฅ๐๐ป ๐ฆ๐ฝ๐ฟ๐ถ๐ป๐ด ๐๐ผ๐ผ๐ ๐ฃ๐ฟ๐ผ๐ท๐ฒ๐ฐ๐ </h3>
<p> Right Click on the project, select Run As -> Spring Boot Project.</p> <br>

<p>Finally you can get output including all operations. However, we can test individual methods separately by commenting other methods.</p>
</html>
