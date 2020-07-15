package com.cjh.community2.enums;

//枚举类的特点：需要定义一个或多个私有属性，然后有对于这些属性的构造方法，构造方法中有什么样的属性，则枚举类对象定义时需要有什么样的属性，并且可以用get方法取出枚举
//类对象的内容。在枚举类中也能定义其它的关于私有属性的方法。
public enum NotificationTypeEnum {

    //数据库中存入少量内容，只存如1，拿出来后映射到字符串。
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论")

    ;
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static String nameOfType(int type){
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if (notificationTypeEnum.getType()==type){
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
