# PhotographyExhibition APIServer

## 待解决的问题

- [x] （此条废弃，没有如果，就用redis）如果没有redis，或者获取redis连接失败就使用本地缓存（map）作为替代方案
  
    - [x] 添加本地缓存管理工具 com.cc.pic.api.utils.sys.CacheUtil 目前只能算是个半成品，后续需对其进行封装以更适用于SpringBoot，详见下方几点
    
    - [x] 调整本地缓存类，使其更好的集成SpringBoot；有两种方案。
      
        - 方案1：在原有的RedisUtil中的每个方法中，或者首次加载时（建议使用前者在每个方法中判断，因为redis每次执行时都会获取连接，他没有池的概念，每次都是直接请求Redis，所以配置也是实时的，而不是如Mysql或Oracle一样；虽然可能有效率问题，但是这样准确性更高些）判断是否有连接，redis是否正常，可通过执行一个命令看是否报错，或者看有没有获取连接状态的方法来判断。如果正常就用redis即可，否则就以静态方式调用CacheUtil。
        
        - 方案2：添加一个接口，里面定义RedisUtil中的所有方法；使RedisUtil实现此接口，其中的方法不用改变；再使CacheUtil也实现此接口，根据原有代码再根据接口方法具体实现方法，此时它不再是静态方法了，而是可以被Spring IOC注册的Bean；此时就有两个类实现了这个接口，注入时使用接口引用的方式注入，并通过Conditional来进行条件注入，条件即方案一一样的判断方式，择优选择。
- [x] （此条废弃 无意义）针对查询数据库返回数据时，对返回值中为空的字段进行默认值设置，这样的好处是可减少前端过多的非空校验（经过百度暂无思路）
- [x] com.cc.pic.api.utils.sys.MybatisPlusCodeGenerator 完善Mybatis逆向工程 ：废弃，不使用官方的
- [x] 自己写一个数据库表生成java代码的工具（逆向工程），关于此框架；**移步仓库** [MyBatisPlusGenerator](https://github.com/CandyMuj/MyBatisPlusGenerator)
- [x] 关于接口鉴权，需要做一个优化，现在是所有的接口都鉴权了，但有些东西并不需要鉴权，也不能鉴权，比如swagger内置的文档或接口等；鉴权对于接口鉴权和方法鉴权的判断及校验逻辑需要优化一下
- [ ] 关于系统权限管理，初期不做，后续先做菜单级权限，再做按钮级的，两种级别权限可共存；按钮级别的权限还需新增按钮注册表
- [ ] 关于数据安全，初期不做，后续优先做请求加密，后台解密；最好是双向都加密（请求和响应都做加密）
- [ ] （此条如果遇到这种业务需求再去做吧）user添加usertype，清token可根据type来清，前提是生成token时必须要设置usertype，且usertype和id一样不会再被修改，因为token无法做到实时更新（除非每次修改usertype都清token，强制用户重新登录，生成新的token才行）
- [ ] baseController中验证账号是否是管理员，改为位运算，同步修改登录注册逻辑



## 当前进度
- [x] 接口日志
- [x] 系统配置管理
- [x] 字典管理
- [x] 
- [x] 
- [x] 
- [x] 
- [x] 
- [x] 
- [x] 
- [x] 