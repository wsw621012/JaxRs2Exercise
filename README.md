How to create spring-boot + jersey + jax-rs + gradle:

1. go to https://start.spring.io/ and create project that you want.
	- choose 'Gradle project' and fill 'Jersey(JAX-RS)' in dependency field.
2. download that zip file and extract to local. then 'import' this gradle project by eclipse.
3. create models, just POJO or any kind of Entity.
4. create services, remember annotation @Service. you can use @PostConstruct so that we can use @Inject later to make this initialization occurred.
5. create end-point, it is same as Spring-MVC.Controller, write rest spec here.
6. register resources that you created, usually using JerseyConfig extends ResourceConfig and put package to it.
7. fill up SpringBootApplication class, you can use @ComponentScan with package name to autoscan.
8. use eclipse 'Run as', choose 'java application' and then choose that one you put @SpringBootApplaction on.
9. try request by PostMan, that's it.
10. you can also adopt swagger by adding config in JerseyConfig

