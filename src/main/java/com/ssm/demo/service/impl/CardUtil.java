package com.ssm.demo.service.impl;
import com.ssm.demo.dao.UsersDao;
import com.ssm.demo.entity.*;
import com.ssm.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class CardUtil{
    private DecimalFormat format= new DecimalFormat("#.0");  //用于对小数输出进行格式控制（输出一位）
    @Autowired
    private UsersDao userMapper;

    /*
    CardUtil的构造函数
     */
   // @Autowired
    private CardUtil() throws IOException {}

    /*

    主场景函数，主体是do-while（true）死循环，只有在选择退出系统与注销卡号时才会退出循环，程序也随之结束
     */
    public void initScene() throws IOException {
        do {
            System.out.print("************欢迎使用嗖嗖业务大厅************"+'\n'+
                    "1.用户登录 2.用户注册 3.使用嗖嗖 4.话费充值 5.资费说明 6.退出系统"+'\n'+"请选择：");
            boolean isSix = false;   //该变量用于检验输入是否为6或者是否有注销卡号的操作
            Scanner in = new Scanner(System.in);
            String input = in.next();   //用于输入的函数，以后获取输入均是这样的Scanner 对象，对象.next()结构
            switch (input) {
                case "6":
                    System.out.println("谢谢使用！");
                    isSix = true;
                    break;      //输入为6，isSix变量置为true，跳出循环，程序结束
                case "1":
                    String cardNumber;
                    try {
                        System.out.print("请输入手机卡号:");
                        Scanner getCardNumber = new Scanner(System.in);
                        cardNumber = getCardNumber.next();
                        System.out.print("请输入密码:");
                        Scanner getPassword = new Scanner(System.in);
                        String password = getPassword.next();
                        if (!isExistCard(cardNumber, password))
                            throw new IllegalArgumentException(); //调用isExistCard函数，当卡号不存在或卡密不一致时抛出异常
                        isSix = initScene2(thisCard(cardNumber)); /*卡密正确，进入场景2，initScene2函数返回一个Boolean值
                                                                  当注销卡号成功时返回true，isSix置为true，跳出循环结束程序*/
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("用户名或密码错误，请重新操作");
                    }   //接收卡密错误的异常
                    break;
                case "2":
                    getNewCard(); //输入为2时，调用getCard函数，完成用户注册
                    break;
                case"3":
                    try {
                        while(true){
                            System.out.print("请输入手机卡号:");
                            Scanner getNumb = new Scanner(System.in);
                            String numb =getNumb.next();
                            if(numb.equals("0"))break; //用户输入0即可退出
                            if(!isExistCard(numb))throw new IllegalArgumentException(); //调用isExistCard函数，当卡号不存在时抛出异常
                            if(useSoso(numb))break; //调用useSoso函数进入使用，useSoso返回值为Boolean类型，当顺利完成场景任务时返回true跳出循环，否则继续重新输入卡号
                        }

                    }
                    catch(IllegalArgumentException e){
                        System.out.println("卡号不存在！请您检测后重新输入。");
                } //处理卡号不存在的异常
                    break;
                case "4":
                    try {
                        System.out.print("请输入充值卡号:");
                        Scanner getCharge = new Scanner(System.in);
                        String charge = getCharge.next();if(!isExistCard(charge))throw new IllegalArgumentException();//卡号不存在则抛出异常
                        System.out.print("请输入充值金额:");
                        getCharge = new Scanner(System.in);
                        String countStr = getCharge.next();
                        int count = Integer.parseInt(countStr); //调用parseInt方法将输入的金额转化为int类型
                        chargeMoney(charge, count); //调用chargeMoney完成充值操作
                    }
                    catch (IllegalArgumentException e){
                        System.out.println("卡号不存在！");
                    }//处理卡号不存在的异常
                    break;
                case"5":
                    showDescription();//调用showDescription函数将文件中的资费说明打印出来
                    break;
                default:
                    System.out.println("执行使用嗖嗖功能");//其他输入只显示此信息
            }
            if(isSix)
                break;//isSix为true跳出死循环，程序结束
        }while(true);
    }

    /*

    第二场景函数，主体是while（true）死循环，只有选择其他键时才返回上一级
     */
    public boolean initScene2(MobileCard thisCard) throws IOException {
        System.out.print("*****嗖嗖移动用户菜单*****" + '\n' + "1.本月账单查询" + '\n' + "2.套餐余量查询" +
                '\n' + "3.打印消费详单" + '\n' + "4.套餐变更" + '\n' + "5.办理退网" + '\n');
        while (true) {
            boolean elseKey = false; //这里的elseKey变量用于判断输入是否为除1~5的其他键
            System.out.print("请选择（输入1~5选择功能，其他键返回上一级）:");
            Scanner in = new Scanner(System.in);
            String input = in.next();
            switch (input) {
                case "1":
                    showAmountDetail(thisCard);//选择1，调用showAmountDetail，显示账单
                    break;
                case "2":
                    showRemainDetail(thisCard);//选择2，调用showRemainDetail，显示套餐余量
                    break;
                case "3":
                    printConsumeInfo(thisCard.cardNumber);//选择3，调用printConsumeInfo，把Map集合里的消费清单写入文件中
                    break;
                case "4":
                    System.out.print("*****套餐变更*****"+'\n'+"1.话痨套餐 2.网虫套餐 3.超人套餐 ，请选择（输入1~3的序号）:");
                    Scanner getPackNum = new Scanner(System.in);
                    String packNum = getPackNum.next();
                    changingPack(thisCard,packNum);//选择4，调用changingPack，进入变更套餐界面
                    break;
                case "5":
                    delCard(thisCard.cardNumber); //选择5，调用delCard，销户
                    return true; //销户后第二场景函数返回true，提醒程序结束
                default:
                    elseKey =true; //如果是输入其他键，跳出循环，返回上级函数
                    break;
            }
            if(elseKey)return false; //第二场景函数返回false，提醒程序未结束
        }
    }
    /*
    函数输入:卡号，密码
    函数功能:判断卡密是否一致
    函数返回值：卡密一致返回true，卡密不一致或卡号不存在返回false
     */
    private boolean isExistCard(String number, String password){
        if(password.equals(userMapper.passWordGetByNumber(number)))return true;
        else return false;
    }
    /*
'    函数输入:卡号
    函数功能:判断卡号码是否存在
    函数返回值：azz卡号存在返回true，否则返回false
     */
    private boolean isExistCard(String number){
        if(userMapper.containNumber(number)!=null)return true;
        else return false;
         //调用containNumber方法，判断数据库中是否包含卡号
    }
    /*
    函数输入:无
    函数功能:生成139打头的随机11位号码
    函数返回值：号码
     */
    private String createNumber(){
        StringBuilder val = new StringBuilder("139");
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            val.append(String.valueOf(random.nextInt(10)));
        }
        return val.toString();  //调用random类的nextInt方法生成0~9的随机数，并转为String加到字符串上
    }
    /*
    函数功能:实现用户注册
     */
    private void getNewCard(){
        System.out.println("*****可选择的卡号*****");
        MobileCard[] alternativeCard = new MobileCard[9];
        for(int i=0;i<9;i++) {
            alternativeCard[i]=new MobileCard();
            while(true) {
                String temp = this.createNumber();
                if (!isExistCard(temp)){
                    alternativeCard[i].cardNumber = temp;
                    break;
                }
            }//循环调用createNumber()方法，生成9个卡号，并使用isExistCard判断卡号是否已经存在，存在则重新生成
        }
        for(int i=1;i<10;i++){
            System.out.print(i+"."+alternativeCard[i-1].cardNumber+"  ");
            if(i%3==0){
                System.out.print('\n');
            }
        }  //格式输出生成的卡号
        try {
            System.out.print("请选择卡号（输入1~9的序号）:");
            Scanner getCardID = new Scanner(System.in);
            String CardID = getCardID.next();
            int ID = Integer.parseInt(CardID);
            if((CardID.charAt(0)<'1'||CardID.charAt(0)>'9')||CardID.length()>1) throw new IllegalArgumentException(); //输入的字符非1~9抛出异常
            System.out.print("1.话痨套餐 2.网虫套餐 3.超人套餐 ，请选择套餐（输入1~3的序号）:");
            Scanner getPackage = new Scanner(System.in);
            String Package = getPackage.next();
            int PackageID = Integer.parseInt(Package);
            if(Package.charAt(0)<'1'||Package.charAt(0)>'3')throw new IllegalArgumentException(); //输入的字符非1~3抛出异常
            switch (PackageID) {
                case 1:
                    alternativeCard[ID - 1].serPackage = new TalkPackage();
                    break;
                case 2:
                    alternativeCard[ID - 1].serPackage = new NetPackage();
                    break;
                case 3:
                    alternativeCard[ID - 1].serPackage = new SuperPackage(); //根据输入不同，选择的卡的套餐类指向的子类对象不同
            }
            System.out.print("请输入姓名:");
            Scanner getName = new Scanner(System.in);
            alternativeCard[ID - 1].userName = getName.next();

            System.out.print("请输入密码:");
            Scanner getNewPassword = new Scanner(System.in);
            alternativeCard[ID - 1].passWord = getNewPassword.next();

            System.out.print("请输入预存金额:");
            Scanner getMoney = new Scanner(System.in);
            String money = getMoney.next();
            int myMoney = Integer.parseInt(money);
            while (myMoney < alternativeCard[ID - 1].serPackage.price) {   //若输入的预存金额低于套餐资费则不断重新输入
                System.out.print("您预存的话费金额不足以支付本月固定套餐资费，请重新充值:");
                getMoney = new Scanner(System.in);
                money = getMoney.next();
                myMoney = Integer.parseInt(money);
            }
            alternativeCard[ID - 1].money = myMoney;

            System.out.println("注册成功！卡号:" + alternativeCard[ID - 1].cardNumber
                    + " 用户名:" + alternativeCard[ID - 1].userName + " 当前余额:" + (myMoney - alternativeCard[ID - 1].serPackage.price));
            alternativeCard[ID - 1].serPackage.showInfo();
            alternativeCard[ID - 1].money -= alternativeCard[ID - 1].serPackage.price;
            try {
                addCard(alternativeCard[ID - 1]);  //完成注册，调用addCard把选择的卡放入数据库
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch (IllegalArgumentException e){
            System.out.println("输入数值范围有误，请检查后再输入！");
        }
    }

    /*
   函数输入:卡号
   函数功能:调用getByNumber方法，由卡号找到卡
   函数返回值：卡
    */
    public MobileCard thisCard(String number){
        return userMapper.getByNumber(number);
    }

    /*
函数输入:卡
函数功能:调用save方法把卡放入数据库，并在数据库中生成其消费记录表
函数返回值：无
 */
    private void addCard(MobileCard card) throws SQLException, ClassNotFoundException {
        this.userMapper.save(card);
       // this.mysql.newTable(card.cardNumber);
    }

    /*
函数输入:卡
函数功能:调用del方法把卡从数据库中删除，并在数据库中删除其消费记录表
函数返回值：无
*/
    private void delCard(String Number){
        userMapper.del(Number);
        userMapper.deleteInfo(Number);
        System.out.println("*****办理退网*****"+'\n'+"卡号"+Number+"办理退网成功！"+'\n'+"谢谢使用！");
    }
    /*
函数输入:卡
函数功能:使用关键字instanceof判断套餐类型，根据不同的套餐类型输出不同的套餐余量
函数返回值：无
*/
    private void showRemainDetail(MobileCard thisCard){
        System.out.println("*****套餐余量查询*****");
        StringBuffer str=new StringBuffer("您的卡号:");
        str.append(thisCard.cardNumber).append(",套餐内剩余:").append('\n');
        if(thisCard.serPackage instanceof TalkPackage) {
            str.append("通话时长:")
                    .append(((TalkPackage) thisCard.serPackage).talkTime>thisCard.realTalkTime?((TalkPackage) thisCard.serPackage).talkTime-thisCard.realTalkTime:0)
                    .append("分钟").append('\n').append("短信条数:")
                    .append(((TalkPackage) thisCard.serPackage).smsCount>thisCard.realSMSCount?((TalkPackage) thisCard.serPackage).smsCount-thisCard.realSMSCount:0)
                    .append("条");
        }
        if (thisCard.serPackage instanceof  NetPackage){
            str.append("上网流量:")
                    .append(((NetPackage) thisCard.serPackage).flow>thisCard.realFlow?format.format((double)(((NetPackage) thisCard.serPackage).flow-thisCard.realFlow)/1024):0)
                    .append("GB");
        }
        if(thisCard.serPackage instanceof SuperPackage){
            str.append("通话时长:")
                    .append(((SuperPackage) thisCard.serPackage).talkTime>thisCard.realTalkTime?((SuperPackage) thisCard.serPackage).talkTime-thisCard.realTalkTime:0)
                    .append("分钟").append('\n').append("短信条数:")
                    .append(((SuperPackage) thisCard.serPackage).smsCount>thisCard.realSMSCount?((SuperPackage) thisCard.serPackage).smsCount-thisCard.realSMSCount:0)
                    .append("条").append('\n').append("上网流量:")
                    .append(((SuperPackage) thisCard.serPackage).flow>thisCard.realFlow?format.format((double)(((SuperPackage) thisCard.serPackage).flow-thisCard.realFlow)/1024):0)
                    .append("GB");;
        }
        System.out.println(str);
    }
    /*
函数输入:卡
函数功能:显示本月套餐，这里使用了StringBuffer类的append拼接字符串
函数返回值：无
*/
    private void showAmountDetail(MobileCard thisCard){
        System.out.println("*****本月账单查询*****");
        StringBuffer str=new StringBuffer("您的卡号:");
        str.append(thisCard.cardNumber).append(",当月账单:").append('\n').append("套餐资费:").append(format.format(thisCard.serPackage.price))
                .append("元").append('\n').append("合计:").append(format.format(thisCard.serPackage.price +thisCard.consumeAmount)).append("元").append('\n')
                .append("账户余额:").append(format.format(thisCard.money)).append("元");
        System.out.println(str);
    }
    /*
函数输入:卡号，消费记录
函数功能:调用isEmpty方法判断从数据库获取的List是否为空，实现不同输出；调用addInfo方法把消费详情加入数据库
函数返回值：无
*/
    private void addConsumeInfo(String number, ConsumeInfo info){
        if(userMapper.getInfo(number).isEmpty()){
            System.out.println("不存在此卡的消费记录，已添加一条消费记录");
        }
        else {
            System.out.println("已存在此卡的消费记录，已添加一条消费记录");
        }
       userMapper.addInfo(number,info);
    }

    /*
函数输入:卡号
函数功能:使用Soso
函数返回值：使用成功返回true，否则返回false
*/
    private boolean useSoso(String number){
        MobileCard m = thisCard(number);
        Random random = new Random();
        int Sce = random.nextInt(6) + 1;  //随机生成某个1~6的序号
        int talkCount = 0,sendCount = 0,SurfaceCount = 0;   //用于接收call,send,netPlay的返回值
        Scene thisScene = userMapper.getScene(Sce);   //根据序号找到场景赋给thisScene
        if(((Sce == 5 || Sce == 6) && Objects.requireNonNull(m).serPackage instanceof TalkPackage) || ((Sce != 5 && Sce != 6)&&Objects.requireNonNull(m).serPackage instanceof NetPackage)){
            assert thisScene != null;
            System.out.println("对不起，您的套餐不包含"+thisScene.type+"服务,请重新操作（输入“0”即可退出）");
            return false;
        }  //卡的套餐不存在场景对应的服务时返回false，重新输入卡号进行使用操作
        try{
            CallService A;
            SendService B;
            NetService C; //创建3个服务接口
            if(Sce == 1 ||Sce == 4) {
                if(Objects.requireNonNull(m).serPackage instanceof TalkPackage){
                    A = new TalkPackage();
                    assert thisScene != null;
                    talkCount=A.call(thisScene.data,m);
                    if(talkCount!=0)throw new IllegalArgumentException();
                }
                if(Objects.requireNonNull(m).serPackage instanceof SuperPackage){
                    A = new SuperPackage();
                    assert thisScene != null;
                    talkCount=A.call(thisScene.data,m);
                    if(talkCount!=0)throw new IllegalArgumentException();
                }

            }
            if(Sce == 2 ||Sce == 3) {
                if(Objects.requireNonNull(m).serPackage instanceof TalkPackage) {
                    B = new TalkPackage();
                    assert thisScene != null;
                    sendCount = B.send(thisScene.data, m);
                    if (sendCount != 0) throw new IllegalArgumentException();
                }
                if(Objects.requireNonNull(m).serPackage instanceof SuperPackage) {
                    B = new SuperPackage();
                    assert thisScene != null;
                    sendCount = B.send(thisScene.data, m);
                    if (sendCount != 0) throw new IllegalArgumentException();
                }
            }
            if(Sce == 5 ||Sce == 6){
                if(Objects.requireNonNull(m).serPackage instanceof NetPackage) {
                    C = new NetPackage();
                    assert thisScene != null;
                    SurfaceCount = C.netPlay(thisScene.data, m);
                    if (SurfaceCount != 0) throw new IllegalArgumentException();
                }
                if(Objects.requireNonNull(m).serPackage instanceof SuperPackage) {
                    C = new SuperPackage();
                    assert thisScene != null;
                    SurfaceCount = C.netPlay(thisScene.data, m);
                    if (SurfaceCount != 0) throw new IllegalArgumentException();
                }
                return true;
            }/*根据不同的场景使用不同的接口;每个接口指向何类对象用卡的套餐和instanceof关键字判断
               call,send,netPlay函数返回值不为0说明余额不足，抛出异常
            */


        }
        catch (IllegalArgumentException e) {
            if(Sce == 1 ||Sce == 4) {
                System.out.println("本次已通话"+talkCount+"分钟，您的余额不足，请充值后再使用");
            }
            if(Sce == 2 ||Sce == 3) {
                System.out.println("本次已发送短信"+sendCount+"条，您的余额不足，请充值后再使用");
            }
            if(Sce == 5 ||Sce == 6){
                System.out.println("本次已使用上网流量"+SurfaceCount+"MB，您的余额不足，请充值后再使用");
            }
            //根据场景的不同操作异常处理中输出不同的提示信息
        }
        finally {
            assert thisScene != null;
            ConsumeInfo e =new ConsumeInfo(thisScene.type,thisScene.data);
            userMapper.update(m);
            System.out.println(thisScene.description);
            addConsumeInfo(number,e); //无论是否抛出异常，本代码块都执行，完成打印提示信息和将消费加入清单操作
        }
        return true;
        }
    /*
函数输入:无
函数功能:将文件中的资费说明显示
函数返回值：无
*/
    private void showDescription() throws IOException {
        try {
            FileInputStream in = new FileInputStream("D:/资费说明.txt");
            int tempByte;
            while ((tempByte = in.read()) != -1) {
                System.out.write(tempByte);
            }   //调用FileInputStream流类对象的read方法，并使用out的write方法把读入的int输出为原字符串
            System.out.print('\n');
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
函数输入:字符1~3
函数功能:根据输入返回不同子类对象，供更改套餐使用
函数返回值：不同子类对象
*/
    private ServicePackage packageType(String packNum){
        switch (packNum){
            case "1":
                return new TalkPackage();
            case"2":
                return new NetPackage();
            case"3":
                return new SuperPackage();
            default:
                return null;
        }
    }

    /*
函数输入:卡号、1~3
函数功能:更改套餐
函数返回值：无
*/
    private void changingPack(MobileCard thisCard, String packNum){
        if(thisCard.serPackage.getClass().equals(Objects.requireNonNull(packageType(packNum)).getClass())){
            System.out.println("对不起，您已是该套餐用户，无需换套餐！");
        }//调用getClass方法，判断当前套餐与由packageType得到的套餐是否类相同，相同提示错误信息
        else {
            if (thisCard.money < Objects.requireNonNull(packageType(packNum)).price) {
                System.out.println("对不起，您的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务！");
            }//若卡余额低于由packageType得到的套餐资费，提示错误信息
            else {
                thisCard.money -= Objects.requireNonNull(packageType(packNum)).price;
                thisCard.serPackage = packageType(packNum);
                thisCard.realFlow = thisCard.realSMSCount = thisCard.realTalkTime = 0;
                userMapper.update(thisCard);
                System.out.println("变更成功！"); //扣除卡余额，使用套餐量清零
            }
        }
    }
    /*
函数输入:卡号
函数功能:将List集合中的消费清单存储到文件中
函数返回值：无
*/
    private void printConsumeInfo(String number) throws IOException {
        FileOutputStream print = new FileOutputStream("D:/JavaHomework/"+number+"ConsumeInfo.txt");
        StringBuilder myOutput = new StringBuilder("******" + number + "消费记录******" + '\n' + "序号" + '\t' + "类型" + '\t' + "数据 (通话(分钟)/上网(MB)/短信(条))" + '\n');
        int i = 1;

        Iterator<ConsumeInfo> it = userMapper.getInfo(number).iterator();
        while(it.hasNext()) {
            ConsumeInfo e = new ConsumeInfo();
            e=it.next();
            myOutput.append(i).append(".  ").append('\t').append(e.consumeType).append('\t').append(e.consumeData).append('\n');
            i++;
        }  //利用Iterator容器遍历list集合，获取消费信息
        print.write(myOutput.toString().getBytes());
        print.close(); //利用FileOutputStream类的write方法写入文件中
    }
    /*
函数输入:卡号，金额
函数功能:充值
函数返回值：无
*/
    private void chargeMoney(String number, double money){
        try{
            MobileCard m =thisCard(number);
            System.out.println(m.serPackage);
            if(money<50)throw new IllegalArgumentException(); //充值金额小于50抛出异常
            m.money+=money;
            userMapper.update(m);
            System.out.println("充值成功，您当前的余额为"+format.format(m.money)+"元。");
        }
        catch(IllegalArgumentException e) {
            System.out.println("错误！您充值的金额不足50元");
        }
    }
    public static void main(String[]args) throws IOException {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:config/spring/spring-applicationContext.xml");
        CardUtil SosoUtil = (CardUtil) ac.getBean("cardUtil");
        SosoUtil.initScene();
    }
}
