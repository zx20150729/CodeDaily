# android练习
### toast_test:当关闭通知消息权限后无法显示系统Toast的解决方案
  * 自己仿照系统的Toast,用自己的消息队列维护,让其不受NotificationManagerService的影响
  * 通过WindowManager自己写一个通知
  * 通过Dialog,PopupWindow来编写一个自定义通知
  * 通过直接去当前页的最外层content布局来添加view

