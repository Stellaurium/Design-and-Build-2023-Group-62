
#include <Servo.h>
#include <SD.h>
#include <SPI.h>
#include <SoftwareSerial.h>


Servo myservo1;  // 定义横向Servo对象来控制
Servo myservo2;  // 定义纵向Servo对象来控制


char val;


int pos = 90;
int pos2 = 80;

//定义PID参数
float kp = 1.0;
float ki = 0.5;
float kd = 0.2;
float error = 0;
float last_error = 0;
float integral = 0;
float derivative = 0;
//调整转向delay
int t = 0;
int t_max = 200;

#define STOP      0
#define FORWARD   1
#define BACKWARD  2
#define TURNLEFT  3
#define TURNRIGHT 4

File myFile;

unsigned long T_time = 0;
int T_num = 0;


int pinCS = 53;

const int d_wall = 20 ;
const int lr_wall = 18;

int l = 0,m = 0, r = 0;

int leftMotor1 = 4;
int leftMotor2 = 5;
int rightMotor1 = 6;
int rightMotor2 = 7;

int VAL = 10;

int inputPin1=8;   // 定义左侧超声波信号接收接口
int outputPin1=9;  // 定义左侧超声波信号发出接口

int inputPin2=12;   // 定义中间超声波信号接收接口
int outputPin2=13;  // 定义中间超声波信号发出接口

int inputPin3=10;   // 定义右侧超声波信号接收接口
int outputPin3=11;  // 定义右侧超声波信号发出接口




//控制电机
void motorRun(int cmd)
{
  switch(cmd){
    case FORWARD:
      digitalWrite(leftMotor1, 0);
      digitalWrite(leftMotor2, VAL);
      digitalWrite(rightMotor1, 0);
      digitalWrite(rightMotor2, VAL);
      break;
     case BACKWARD:
      digitalWrite(leftMotor1, VAL);
      digitalWrite(leftMotor2, 0);
      digitalWrite(rightMotor1, VAL);
      digitalWrite(rightMotor2, 0);
      break;
     case TURNLEFT:
      digitalWrite(leftMotor1, VAL);
      digitalWrite(leftMotor2, 0);
      digitalWrite(rightMotor1, 0);
      digitalWrite(rightMotor2, VAL);
      break;
     case TURNRIGHT:
      digitalWrite(leftMotor1, 0);
      digitalWrite(leftMotor2, VAL);
      digitalWrite(rightMotor1, VAL);
      digitalWrite(rightMotor2, 0);
      break;
     default:
      digitalWrite(leftMotor1, 0);
      digitalWrite(leftMotor2, 0);
      digitalWrite(rightMotor1, 0);
      digitalWrite(rightMotor2, 0);
  }
}





//舵机转动
void servo(){

for ( pos = 45; pos <= 135; pos ++) { // 0°到180°
    // in steps of 1 degree
    myservo1.write(pos);              // 舵机角度写入
    if(pos == 45){
      delay(1000);                       // 等待转动到指定角度
      for (pos2 = 10; pos2 <= 80; pos2 ++) { // 0°到180°
    // in steps of 1 degree
    myservo2.write(pos2);              // 舵机角度写入
    delay(20);                       // 等待转动到指定角度
  }
  for (pos2 = 80; pos2 >= 10; pos2 --) { // 从180°到0°
    myservo2.write(pos2);              // 舵机角度写入
    delay(20);                       // 等待转动到指定角度
  }
      
    }

    if(pos == 90){
      delay(1000);                       // 等待转动到指定角度
      for (pos2 = 10; pos2 <= 80; pos2 ++) { // 0°到180°
    // in steps of 1 degree
    myservo2.write(pos2);              // 舵机角度写入
    delay(20);                       // 等待转动到指定角度
  }
  for (pos2 = 80; pos2 >= 10; pos2 --) { // 从180°到0°
    myservo2.write(pos2);              // 舵机角度写入
    delay(20);                       // 等待转动到指定角度
  }
      
}
    if(pos == 135){
      delay(1000);                       // 等待转动到指定角度
      for (pos2 = 10; pos2 <= 80; pos2 ++) { // 0°到180°
    // in steps of 1 degree
    myservo2.write(pos2);              // 舵机角度写入
    delay(20);                       // 等待转动到指定角度
  }
  for (pos2 = 80; pos2 >= 10; pos2 --) { // 从180°到0°
    myservo2.write(pos2);              // 舵机角度写入
    delay(20);                       // 等待转动到指定角度
  }  
  } 
  }
  pos = 90;
  pos2 = 80;
  myservo1.write(pos);
  myservo2.write(pos2);
}





//PID控制函数 输出转向时间
void PID_control(int l,int m,int r){
  //计算误差
  error = (l-r)*(l-r);
  //计算积分项
  integral += error;
  //限制积分项大小
  if(integral>t_max){
    integral = t_max;
  }
  //计算微分项
  derivative = error - last_error;
  //计算PID输出
  t = kp*error + ki*integral + kd*derivative;
  //更新误差值
  last_error = error;
  Serial.println(t);
}



//超声波测距
int getLeftDistance()
{
  digitalWrite(outputPin1, LOW); // 使发出发出超声波信号接口低电平2μs
  delayMicroseconds(2);
  digitalWrite(outputPin1, HIGH); // 使发出发出超声波信号接口高电平10μs，这里是至少10μs
  delayMicroseconds(10);
  digitalWrite(outputPin1, LOW); // 保持发出超声波信号接口低电平
  int distance = pulseIn(inputPin1, HIGH); // 读出脉冲时间
  distance= distance/58; // 将脉冲时间转化为距离（单位：厘米）
 
if (distance <=50 && distance>=0)
  {
    //如果距离小于50厘米返回左侧超声波测距数据
    return distance;
  }
  else
    return 100;
}

int getMidDistance()
{
  digitalWrite(outputPin2, LOW); // 使发出发出超声波信号接口低电平2μs
  delayMicroseconds(2);
  digitalWrite(outputPin2, HIGH); // 使发出发出超声波信号接口高电平10μs，这里是至少10μs
  delayMicroseconds(10);
  digitalWrite(outputPin2, LOW); // 保持发出超声波信号接口低电平
  int distance = pulseIn(inputPin2, HIGH); // 读出脉冲时间
  distance= distance/58; // 将脉冲时间转化为距离（单位：厘米）
 
if (distance <=50 && distance>=0)
  {
    //如果距离小于50厘米返回左侧超声波测距数据
    return distance;
  }
  else
    return 100;
}

int getRightDistance()
{
  digitalWrite(outputPin3, LOW); // 使发出发出超声波信号接口低电平2μs
  delayMicroseconds(2);
  digitalWrite(outputPin3, HIGH); // 使发出发出超声波信号接口高电平10μs，这里是至少10μs
  delayMicroseconds(10);
  digitalWrite(outputPin3, LOW); // 保持发出超声波信号接口低电平
  int distance = pulseIn(inputPin3, HIGH); // 读出脉冲时间
  distance= distance/58; // 将脉冲时间转化为距离（单位：厘米）
 
  if (distance <=100 && distance>=0)
  {
    //如果距离小于50厘米返回左侧超声波测距数据
    return distance;
  }
  else
    return 100;
}




void setup() {



  myservo1.write(pos);
  myservo2.write(pos2);

  T_num = 0 ;
  T_time = 0;

  // put your setup code here, to run once:
  //串口初始化
  Serial.begin(9600); //设定硬串口波特率
  Serial.println("BT is ready!");
  Serial3.begin(9600); 
  //测速引脚初始化
  pinMode(leftMotor1, OUTPUT);
  pinMode(leftMotor2, OUTPUT);
  pinMode(rightMotor1, OUTPUT);
  pinMode(rightMotor2, OUTPUT);

  myservo1.attach(2);  // 控制线连接数字6
  myservo2.attach(3);  // 控制线连接数字7

  //超声波控制引脚初始化
  pinMode(inputPin1, INPUT);
  pinMode(outputPin1, OUTPUT);
  pinMode(inputPin2, INPUT);
  pinMode(outputPin2, OUTPUT);
  pinMode(inputPin3, INPUT);
  pinMode(outputPin3, OUTPUT);

  // //寻迹模块引脚初始化
  // pinMode(13, OUTPUT);
  // pinMode(14, OUTPUT);
  // pinMode(15, OUTPUT);
  // pinMode(16, OUTPUT);

  pinMode(pinCS, OUTPUT);
  // SD Card Initialization
  if (SD.begin())
  {
  Serial.println("SD card is ready to use.");
  } else{
  Serial.println("SD card initialization failed");
  return;
  }

  // Create/Open file
  myFile = SD.open("test.txt", FILE_WRITE);
  // if the file opened okay, write to it
 
}

void loop() {
  //  int dis[3];//记录三个方向超声波探测到的障碍物距离 行走优先级左、中、右
  //      dis[0]=getLeftDistance(); //左边
  //      dis[1]=getMidDistance(); //中间
  //      dis[2]=getRightDistance(); //右边
      // l=getLeftDistance(); //左边
      // m=getMidDistance(); //中间
      // r=getRightDistance(); //右边

      //输出测距值
      // Serial.print("左边：   "); //输出距离值
      // Serial.println(dis[0]); //输出距离值
      // Serial.print("中间：   "); //输出距离值
      // Serial.println(dis[1]); //输出距离值
      // Serial.print("右边：  "); //输出距离值
      // Serial.println(dis[2]); //输出距离值
  //PID控制转向时间t
  // PID_control(l,m,r);
  //执行任务
  searchT();

  //蓝牙传输信息
if (Serial.available()) {
    val = Serial.read();
        if(val = 0){
      motorRun(STOP);
      delay(5000);
    }
    if(val = 1){
      motorRun(FORWARD);
      delay(2000);
    }
        if(val = 2){
      motorRun(BACKWARD);
      delay(2000);
    }
        if(val = 4){
      motorRun(TURNLEFT);
      delay(1000);
    }
        if(val = 5){
      motorRun(TURNRIGHT);
      delay(1000);
    }
    Serial3.print(val);
    delay(20);
  }

  if (Serial3.available()) {
    val = Serial3.read();
    if(val = 0){
      motorRun(STOP);
      delay(5000);
    }
        if(val = 1){
      motorRun(FORWARD);
      delay(2000);
    }
        if(val = 2){
      motorRun(BACKWARD);
      delay(2000);
    }
        if(val = 4){
      motorRun(TURNLEFT);
      delay(1000);
    }
        if(val = 5){
      motorRun(TURNRIGHT);
      delay(1000);
    }
    Serial.print(val);
    delay(20);
  }
  delay(20);
}




void searchT()
{
  int dis[3];//记录三个方向超声波探测到的障碍物距离 行走优先级左、中、右
      dis[0]=getLeftDistance(); //左边
      dis[1]=getMidDistance(); //中间
      dis[2]=getRightDistance(); //右边
  //          输出测距值
      Serial.print("左边：   "); //输出距离值
      Serial.println(dis[0]); //输出距离值
      Serial.print("中间：   "); //输出距离值
      Serial.println(dis[1]); //输出距离值
      Serial.print("右边：  "); //输出距离值
      Serial.println(dis[2]); //输出距离值
  
  // if(dis[1] > d_wall)//如果正面没有障碍，前进一点
  // {
  //   Serial.println("直走");
  //   motorRun(FORWARD);
  //   delay(100);
  //   motorRun(STOP);
  //   delay(50);
  //   //到达空地停止写入文件
  //   if(dis[0]>=50 && dis[1]>= 50 && dis[2] >=50){
  //     motorRun(STOP);
  //     delay(10000);
  //     //写入文件
  //     T_time = int(millis()/1000);
  //     if (myFile) {
  //     Serial.println("Writing to file...");
  //     // Write to file
  //     myFile.println();
  //     myFile.print(T_time);
  //     myFile.print(",");
  //     myFile.print(T_num);
  //     myFile.close(); // close the file
  //     Serial.println("Write Done.");
  //     }
  //     // if the file didn't open, print an error:
  //     else {
  //     Serial.println("error opening test.txt");
  //     }
  //     // Reading the file
  //     myFile = SD.open("test.txt");
  //     if (myFile) {
  //     Serial.println("Read:");
  //     // Reading the whole file
  //     while (myFile.available()) {
  //     Serial.write(myFile.read());
  //     }
  //     myFile.close();
  //     }
  //     else {
  //     Serial.println("error opening test.txt");
  //     }
  //   }


  //   //在前进回进行靠墙测试
  //   if(dis[2] <= lr_wall)//如果贴右边墙
  //   {
  //     //倒车直到脱离危险区
  //     motorRun(BACKWARD);
  //     delay(100);
  //     motorRun(STOP);
  //     delay(100);
  //     //微量原地左转
  //     Serial.println("左偏");
  //     motorRun(TURNLEFT);
  //     delay(150);
  //     motorRun(STOP);
  //     delay(100);
  //     //微量前进
  //     motorRun(FORWARD);
  //     delay(100);
  //     motorRun(STOP);
  //     delay(100);
  //   }else if(dis[0] <= lr_wall)//如果贴左边墙
  //   {
  //     //倒车直到脱离危险区
  //     motorRun(BACKWARD);
  //     delay(100);
  //     motorRun(STOP);
  //     delay(100);
  //     //微量原地右转
  //     Serial.println("右偏");
  //     motorRun(TURNRIGHT);
  //     delay(150);
  //     motorRun(STOP);
  //     delay(100);
  //     //微量前进
  //     motorRun(FORWARD);
  //     delay(100);
  //     motorRun(STOP);
  //     delay(100);
  //   }
    
  // }
  
  // else{ 
  //   //如果正面有障碍但是左右有一侧没有，就准备转弯   
  //    if(dis[0]>=dis[2] && dis[0]>20){
  //     //左转
  //     Serial.println("左转");
  //     motorRun(TURNLEFT);
  //     delay(350);
  //     motorRun(STOP);
  //     delay(50);
  //    }
  //     if(dis[0] < dis[2] && dis[2]>20){
  //     //右转
  //     Serial.println("右转");
  //     motorRun(TURNRIGHT);
  //     delay(350);
  //     motorRun(STOP);
  //     delay(50);
  //   }

     
  //   if(dis[0]<=20 &&dis[2]<=20){
  //     motorRun(BACKWARD);
  //     delay(200);
  //     motorRun(STOP);
  //     delay(50);
  //     //servo();
  //     delay(10000);
  //     T_num += 1;
  //     Serial.println("掉头");
  //     motorRun(TURNRIGHT);
  //     delay(600);
  //     motorRun(STOP);
  //     delay(50);
  //   }
  //}
  if(dis[1] > d_wall)//如果正面没有障碍，前进一点
  {
    motorRun(FORWARD);
    delay(100);
    motorRun(STOP);
    delay(50);

//到达空地停止写入文件
    if(dis[0]>=100 && dis[1]>= 100 && dis[2] >=100){
      motorRun(STOP);
      delay(5000);
      //写入文件
      T_time = int(millis()/1000);
      if (myFile) {
      Serial.println("Writing to file...");
      // Write to file
      myFile.println();
      myFile.print(T_time);
      myFile.print(",");
      myFile.print(T_num);
      myFile.close(); // close the file
      Serial.println("Write Done.");
      }
      // if the file didn't open, print an error:
      else {
      Serial.println("error opening test.txt");
      }
      // Reading the file
      myFile = SD.open("test.txt");
      if (myFile) {
      Serial.println("Read:");
      // Reading the whole file
      while (myFile.available()) {
      Serial.write(myFile.read());
      }
      myFile.close();
      }
      else {
      Serial.println("error opening test.txt");
      }
    }
        //在前进回进行靠墙测试
    if(dis[2] <= lr_wall)//如果贴右边墙
    {
      //倒车直到脱离危险区
      motorRun(BACKWARD);
      delay(100);
      motorRun(STOP);
      delay(100);
      //微量原地左转
      motorRun(TURNLEFT);
      delay(150);
      motorRun(STOP);
      delay(100);
      //微量前进
      motorRun(FORWARD);
      delay(100);
      motorRun(STOP);
      delay(50);
    }
    if(dis[0] <= lr_wall)//如果贴左边墙
    {
      //倒车直到脱离危险区
      motorRun(BACKWARD);
      delay(100);
      motorRun(STOP);
      delay(100);
      //微量原地右转
      motorRun(TURNRIGHT);
      delay(150);
      motorRun(STOP);
      delay(100);
      //微量前进
      motorRun(FORWARD);
      delay(100);
      // motorRun(STOP);
      // delay(100);
    }
    
  }
  
  else if ((dis[0]>=30 || dis[2]>= 30 ) &&dis[1]<18) //如果正面有障碍但是左右有一侧没有，就准备转弯
  {
      if(dis[0]>=dis[2]){
            //左转
      Serial.println("turn left");
      motorRun(TURNLEFT);
      delay(300);
      motorRun(STOP);
      delay(100);
      }
      else{
      //右转
      Serial.println("turn right");
      motorRun(TURNRIGHT);
      delay(300);
      motorRun(STOP);
      delay(100);
    }
  }
  else {
    motorRun(BACKWARD);
    delay(100);
    motorRun(STOP);
    servo();
    Serial.println("掉头");
    motorRun(TURNRIGHT);
    delay(500);
    motorRun(STOP);
    delay(100);
  }
}





















