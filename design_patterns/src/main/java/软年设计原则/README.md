开闭原则 里氏替换原则 依赖倒转原则 都是通过定义抽象类，提高扩展性
接口隔离原则是通过接口提高扩展性。
何时使用抽象类 何时使用接口？
    需要对同一类别的功能扩展时，使用抽象类（如皮肤属性。皮肤可以有多种类别，此时可以抽象出一个皮肤类）
    为同一功能拓展不同的额外功能时，使用接口 （如门锁，他有多种功能。此时每种功能都是一个接口）