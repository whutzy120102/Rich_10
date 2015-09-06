package bigRich_10shi;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;


public class rich_10  extends JFrame implements ActionListener
{
	//游戏开始界面
	startUi  start = null;
	JMenuBar   menuBar = null;
	JMenu      menu = null;
	JMenuItem  newGame= null;
	JMenuItem  exit= null;
	//游戏运行界面
	JPanel map = null;
	JButton row = null;
	JLabel tip = null;
	JTextField gameTip = null;
	JButton sureBuy = null;
	JButton abolishBuy = null;
	JButton sureUseProp = null;
	JButton abolishUseProp = null;
	JButton sureUpLevel = null;
	JButton abolishUpLevel = null;
	JButton sure = null;
	JButton pay = null;
	JButton sureJump = null; 
	mapElements[] elements = new mapElements[70] ;
	JTextField numTip=null;
	JButton sureBomb = null;
	
	JButton chooseone = null;//到达礼品屋所进行的选择
	JButton choosetwo = null;
	//玩家信息
	JLabel showNameTip = null;
	JLabel showName = null;
	JLabel showMoneyTip = null;
	JLabel showMoney = null;
	JLabel showPointTip = null;
	JLabel showPoint = null;
	JLabel showPropTip = null; 
	JLabel showProp = null; 
	JTextField PropLocation  = null;
	

	JButton sureSet = null;
	Player[] players = new Player[4] ; 
	String mapEle = "S0000000000000H0000000000000T000000G0000000000000P0000000000000M$$$$$$";
	String[] names= {"钱夫人","阿土伯","孙小美","金贝贝"};
	String[] nameCodes = {"Q","A","S","J"};
	int[] colors = {TYPE.COLOR_RED,TYPE.COLOR_BLUE,TYPE.COLOR_YELLOW,TYPE.COLOR_GREEN};
	int[] mine_points = {20,80,100,40,80,60};
	int result = 0;
	public static int currentPlayerNum ; 
	
	public static int playernumber=0;//玩家人数
	boolean buyed = false;
	boolean payed = false;
	
	public static String toolsbuy="";
	
	//选择游戏人数界面
	JPanel selectPlarerNum = null;
	JLabel welcomeTip = null;
	JLabel chooseTip = null;
	JLabel isNullTip = null;
	JButton ok=null;
	JButton cancle=null;
	JRadioButton playersNumTwo = null;
	JRadioButton playersNumThree = null;
	JRadioButton playersNumFour = null;
	ButtonGroup choose = null;
	//选择道具界面
	JRadioButton ToolBomb= null;
	JRadioButton ToolRobot = null;
	JRadioButton ToolRoadBlock = null;
	ButtonGroup choosetools = null;
	JButton Toolsok=null;
	JButton Toolscancle=null;
	//魔法选择界面
	JButton Magicone=null;
	JButton Magictwo=null;
	//监狱确定按钮
	JButton Sureprison=null;

	public static void main(String args[]){
		rich_10  bigRich = new rich_10();
	}
	
	 rich_10(){
		  
		  menuBar =new JMenuBar();
		  menu = new JMenu("游戏");
		  newGame = new JMenuItem("开始游戏");
		  newGame.addActionListener(this);
		  newGame.setActionCommand("newgame");
		  exit = new JMenuItem("退出游戏");
		  exit.addActionListener(this);
		  exit.setActionCommand("exit");
		  
		  menu.add(newGame); menu.add(exit);
		  menuBar.add(menu);
		  
		  start = new startUi();
		  Thread startShow=new Thread(start);
		  startShow.start();
			
 		 this.add(start);
		 this.setJMenuBar(menuBar);
		 this.setTitle("大富翁");
		 this.setSize(800, 600);
		 this.setLocation(220, 80);
		 this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.setVisible(true);
	 }

	//@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("row")){
			//int  num  = (int)(Math.random()*6)+1;
			int num =49;
			numTip.setFont(new Font("",1,20));
			numTip.setText(num+"");
			int nextPosition =players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()+num;
			if(nextPosition>69){
				nextPosition -= 70;
			}
			elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()].setText(mapEle.split("")[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()]);
			players[currentPlayerNum%playerNum.getPlayerNumber()].setLocation(nextPosition);
			elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()].setText(players[currentPlayerNum%playerNum.getPlayerNumber()].getNameCode());
			row.setVisible(false);
			if(elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()].getPropType()==TYPE.TOOL_BOMB){
			this.isBomb(elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()], players[currentPlayerNum%playerNum.getPlayerNumber()]);
			}
			//升级土地
			else if(elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()].getOwener().equals(players[currentPlayerNum%playerNum.getPlayerNumber()].getName())){ //判断当前土地owner为当前玩家
			    gameTip.setText("是否升级当前已经购买的土地？");
			    gameTip.setVisible(true);
			    sureUpLevel.setVisible(true);
			    abolishUpLevel.setVisible(true);
			}//购买土地
			else if(elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()].getOwener().equals("")&&elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()].isFloor()){
				gameTip.setText("是否购买土地?");
				gameTip.setVisible(true);
				sureBuy.setVisible(true);
				abolishBuy.setVisible(true);
			}//给owner钱
			else if(!elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()].getOwener().equals(players[currentPlayerNum%playerNum.getPlayerNumber()].getName())&&elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()].isFloor()){
				gameTip.setText("你需要向玩家"+elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()].getOwener()+"支付"+elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()].getElementType()*elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()].getLevel()*0.5+"过路费！！！");
				gameTip.setVisible(true);
				pay.setVisible(true);
			}else{
				this.getType(elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()], players[currentPlayerNum%playerNum.getPlayerNumber()]);
			}
		  
		  	 
			 
		}
		if(e.getActionCommand().equals("newgame")){
			  this.selectPlarerNum();
			  this.remove(start);
			  this.add(selectPlarerNum);
			  this.setVisible(true);
		}
		if(e.getActionCommand().equals("exit")){
			System.exit(-1);
		}
		if(e.getActionCommand().equals("ok")){
			if(playerNum.getPlayerNumber() == 0){
				isNullTip.setVisible(true);
			}else{
			  this.drawMap();
			  //玩家信息显示
			  showNameTip = new  JLabel("姓名:");
			  showNameTip.setFont(new Font("",Font.ITALIC,20));
			  showNameTip.setBounds(100, 400, 50, 50);
			  showName = new JLabel();
			  showName.setFont(new Font("",1,20));
			  showName.setBounds(150, 400, 100, 50);
			  showMoneyTip = new JLabel("资金:");
			  showMoneyTip.setFont(new Font("",Font.ITALIC,20));
			  showMoneyTip.setBounds(100, 420, 50, 50);
			  showMoney = new JLabel();
			  showMoney.setFont(new Font("",1,20));
			  showMoney.setBounds(150, 420, 100, 50);
			  showPointTip = new JLabel("点数:");
			  showPointTip.setFont(new Font("",Font.ITALIC,20));
			  showPointTip.setBounds(100, 440, 50, 50);
			  showPoint = new JLabel();
			  showPoint.setFont(new Font("",1,20));
			  showPoint.setBounds(150, 440, 100, 50);
			  
			  //游戏提示操作
			  gameTip = new JTextField();
			  gameTip.setFont(new Font("",1,20));
			  gameTip.setBounds(140, 30, 500, 30);
			  gameTip.setEditable(false);
			  gameTip.setVisible(false);
			  sureBuy = new JButton("确定");
			  abolishBuy = new JButton("取消");
			  sureBuy.setBounds(305, 80, 60, 40);
			  abolishBuy.setBounds(405, 80, 60, 40);
			  sureBuy.setVisible(false);
			  abolishBuy.setVisible(false);
			  sureBuy.addActionListener(this);
			  sureBuy.setActionCommand("surebuy");
			  abolishBuy.addActionListener(this);
			  abolishBuy.setActionCommand("abolishbuy");
			  
			  sureUseProp = new JButton("确定");
			  abolishUseProp = new JButton("取消");
			  sureUseProp.setBounds(305, 80, 60, 40);
			  abolishUseProp.setBounds(405, 80, 60, 40);
			  sureUseProp.setVisible(false);
			  abolishUseProp.setVisible(false);
			  sureUseProp.addActionListener(this);
			  sureUseProp.setActionCommand("sureuserprop");
			  abolishUseProp.addActionListener(this);
			  abolishUseProp.setActionCommand("abolishuserprop");
			  
			//选择道具界面
			     choosetools = new ButtonGroup();
			  
				 ToolBomb = new JRadioButton("炸弹");
				 ToolBomb.setBounds(260, 180, 80, 60);
				 ToolBomb.addActionListener(this);
				 ToolBomb.setActionCommand("toolbmob");
				 ToolBomb.setVisible(false);
				 ToolRobot = new JRadioButton("机器人");
				 ToolRobot.setBounds(340, 180, 80, 60);
				 ToolRobot.addActionListener(this);
				 ToolRobot.setActionCommand("toolrobot");
				 ToolRobot.setVisible(false);
				 ToolRoadBlock = new JRadioButton("路障");
				 ToolRoadBlock.setBounds(420, 180, 80, 60);
				 ToolRoadBlock.addActionListener(this);
				 ToolRoadBlock.setActionCommand("toolroadblock");
				 ToolRoadBlock.setVisible(false);
				 Toolsok= new JButton("确定");
				 Toolsok.setBounds(305, 80, 60, 40);
				 Toolsok.addActionListener(this);
				 Toolsok.setActionCommand("Toolsok");
				 Toolsok.setVisible(false);
				 Toolscancle = new JButton("取消");
				 
				 Toolscancle.setBounds(405,  80, 60, 40);
				 Toolscancle.addActionListener(this);
				 Toolscancle.setActionCommand("Toolscancle");
				 Toolscancle.setVisible(false);
				 choosetools.add(ToolBomb);
				 choosetools.add(ToolRobot);
				 choosetools.add(ToolRoadBlock);

	
			  sureUpLevel = new JButton("确定");
			  abolishUpLevel = new JButton("取消");
			  sureUpLevel.setBounds(305, 80, 60, 40);
			  abolishUpLevel.setBounds(405, 80, 60, 40);
			  sureUpLevel.setVisible(false);
			  abolishUpLevel.setVisible(false);
			  sureUpLevel.addActionListener(this);
			  sureUpLevel.setActionCommand("sureuplevel");
			  abolishUpLevel.addActionListener(this);
			  abolishUpLevel.setActionCommand("abolishuplevel");
			  
			  pay = new JButton("给钱");
			  pay.setBounds(305, 80, 60, 40);
			  pay.setVisible(false);
			  pay.addActionListener(this);
			  pay.setActionCommand("pay");
			  
			  sure = new JButton("确定");
			  sure.setBounds(305, 80, 60, 40);
			  sure.setVisible(false);
			  sure.addActionListener(this);
			  sure.setActionCommand("sure");
			  
			  sureJump = new JButton("确定");
			  sureJump.setBounds(305, 80, 60, 40);
			  sureJump.setVisible(false);
			  sureJump.addActionListener(this);
			  sureJump.setActionCommand("surejump");
			  
			  PropLocation = new JTextField();
			  PropLocation.setBounds(305, 80, 45, 30);
			  PropLocation.setVisible(false);
			  PropLocation.addActionListener(this);
			  PropLocation.setActionCommand("proplocation");
			  
			  
			  sureSet = new JButton("安放");
			  sureSet.setBounds(385, 80, 60, 30);
			  sureSet.setVisible(false);
			  sureSet.addActionListener(this);
			  sureSet.setActionCommand("sureset");
			  
			  sureBomb = new JButton("确定");
			  sureBomb.setBounds(305, 80, 60, 40);
			  sureBomb.setVisible(false);
			  sureBomb.addActionListener(this);
			  sureBomb.setActionCommand("surebomb");
			  //礼品屋选择
			  chooseone=new JButton("1");
			  chooseone.setBounds(305, 80, 60, 40);
			  chooseone.setVisible(false);
			  chooseone.addActionListener(this);
			  chooseone.setActionCommand("chooseone");
			  choosetwo=new JButton("2");
			  choosetwo.setBounds(405, 80, 60, 40);
			  choosetwo.setVisible(false);
			  choosetwo.addActionListener(this);
			  choosetwo.setActionCommand("choosetwo");
			  //魔法屋选择
			  Magicone=new JButton("1");
			  Magicone.setBounds(305, 80, 60, 40);
			  Magicone.setVisible(false);
			  Magicone.addActionListener(this);
			  Magicone.setActionCommand("magicone");
			  Magictwo=new JButton("2");
			  Magictwo.setBounds(405, 80, 60, 40);
			  Magictwo.setVisible(false);
			  Magictwo.addActionListener(this);
			  Magictwo.setActionCommand("magictwo");
			  //监狱确定
			  Sureprison=new JButton("确定");
			  Sureprison.setBounds(305, 80, 60, 40);
			  Sureprison.setVisible(false);
			  Sureprison.addActionListener(this);
			  Sureprison.setActionCommand("sureprison");
			  
			  
			  
			  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
			  
			  row = new JButton("掷色子"); 
			  row.addActionListener(this);
			  row.setActionCommand("row");
			  row.setBounds(240, 350, 80, 40);
			  tip = new JLabel("你掷的色子数为:");
			  tip.setFont(new Font("",1,20));
			  tip.setBounds(320, 350, 200, 40);
			  numTip = new JTextField();
			  numTip.setEditable(false);
			  numTip.setBounds(480, 350, 55, 35);
			  map.add(gameTip);   map.add(sureBuy);    map.add(abolishBuy);
			  map.add(sureUseProp);       map.add(abolishUseProp);
			  map.add(sureUpLevel);      map.add(abolishUpLevel);
			  map.add(sure);        map.add(pay);      map.add(sureJump);
			  map.add(row);         map.add(tip);     map.add(numTip);  
			  map.add(showNameTip);     map.add(showName);   
			  map.add(showMoneyTip);    map.add(showMoney);
			  map.add(showPointTip);    map.add(showPoint);
			  map.add(ToolBomb);        map.add(sureBomb);
			  map.add(ToolRobot); map.add(ToolRoadBlock);  map.add(Toolsok);
			  map.add(Toolscancle);
			  map.add(PropLocation);      map.add(sureSet);
			  map.add(chooseone);         map.add(choosetwo);
			  map.add(Magicone);          map.add(Magictwo);
			  map.add(Sureprison);
			this.remove(selectPlarerNum);
			this.add(map);
			this.setVisible(true);
				}
		}if(e.getActionCommand().equals("cancle")){
			this.remove(selectPlarerNum);
			this.add(start);
			this.setVisible(true);
		}if(e.getActionCommand().equals("2")){
			playernumber=2;        //玩家人数
			playerNum.setPlayerNumber(2);
			isNullTip.setVisible(false);
			currentPlayerNum = playerNum.getPlayerNumber();
		}if(e.getActionCommand().equals("3")){
			playernumber=3;
			playerNum.setPlayerNumber(3);
			isNullTip.setVisible(false);
			currentPlayerNum = playerNum.getPlayerNumber();
		}if(e.getActionCommand().equals("4")){
			playernumber=4;
			playerNum.setPlayerNumber(4);
			isNullTip.setVisible(false);
			currentPlayerNum = playerNum.getPlayerNumber();
		}if(e.getActionCommand().equals("surebuy")){
			result = TYPE.BUYED;
			this.isBuyed(elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()], players[currentPlayerNum%playerNum.getPlayerNumber()]);
			sureBuy.setVisible(false);
			abolishBuy.setVisible(false);
			if(buyed){
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()==0){
					gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");
					currentPlayerNum += 1;
					this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
					if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
						row.setVisible(true);
					}else{
						  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
						  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
						  gameTip.setVisible(true);
						  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
						  sureJump.setVisible(true);
						  currentPlayerNum += 1;
						  row.setVisible(false);
					   }

					
				}else{
					gameTip.setText("是否使用道具？");
					sureUseProp.setVisible(true);
					abolishUseProp.setVisible(true);
				}
			}
//			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
//				//row.setVisible(true);
//			}else{
//				  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
//				  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
//				  gameTip.setVisible(true);
//				  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
//				  sureJump.setVisible(true);
//				  currentPlayerNum += 1;
//				  row.setVisible(false);
//			   }
			
		}if(e.getActionCommand().equals("Toolsok")){//玩家点击购买道具
			if(toolsbuy.equals("")){
				gameTip.setText("请选择一种道具");
				}else{
			 if(toolsbuy.equals("ToolBomb"))
			{
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getPoints()>=50){
				players[currentPlayerNum%playerNum.getPlayerNumber()].setToolBombs(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()+1);
				players[currentPlayerNum%playerNum.getPlayerNumber()].setPoints(players[currentPlayerNum%playerNum.getPlayerNumber()].getPoints()-50);
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				}else{
					gameTip.setText("对不起，你的点数不足，无法购买");
				}
			}
			else if(toolsbuy.equals("ToolRobot"))
			{
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getPoints()>=30){
				players[currentPlayerNum%playerNum.getPlayerNumber()].setToolRobots(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()+1);
				players[currentPlayerNum%playerNum.getPlayerNumber()].setPoints(players[currentPlayerNum%playerNum.getPlayerNumber()].getPoints()-30);
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				}else{
					gameTip.setText("对不起，你的点数不足，无法购买");
				}
			}
			else {
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getPoints()>=50){
				players[currentPlayerNum%playerNum.getPlayerNumber()].setToolRoadBlock(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()+1);
				players[currentPlayerNum%playerNum.getPlayerNumber()].setPoints(players[currentPlayerNum%playerNum.getPlayerNumber()].getPoints()-50);
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				}else{
					gameTip.setText("对不起，你的点数不足，无法购买");
				}
			}
			Toolsok.setVisible(false);
			Toolscancle.setVisible(false);
			ToolBomb.setVisible(false);
			ToolRoadBlock.setVisible(false);
			ToolRobot.setVisible(false);
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()==0){
				gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");
				//row.setVisible(true);
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				currentPlayerNum += 1;
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
					row.setVisible(true);
				}else{
					  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
					  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
					  gameTip.setVisible(true);
					  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
					  sureJump.setVisible(true);
					  currentPlayerNum += 1;
					  row.setVisible(false);
				   }

				
			}else{
				gameTip.setText("是否使用道具？");
				sureUseProp.setVisible(true);
				abolishUseProp.setVisible(true);
			}
		}
		}if(e.getActionCommand().equals("Toolscancle")){//不购买道具
			Toolsok.setVisible(false);
			Toolscancle.setVisible(false);
			ToolBomb.setVisible(false);
			ToolRoadBlock.setVisible(false);
			ToolRobot.setVisible(false);
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()==0){
				gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");
				//row.setVisible(true);
				currentPlayerNum += 1;
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
					row.setVisible(true);
				}else{
					  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
					  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
					  gameTip.setVisible(true);
					  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
					  sureJump.setVisible(true);
					  currentPlayerNum += 1;
					  row.setVisible(false);
				   }

				
			}else{
				gameTip.setText("是否使用道具？");
				sureUseProp.setVisible(true);
				abolishUseProp.setVisible(true);
			}
		}
		if(e.getActionCommand().equals("toolbmob")){
			toolsbuy="ToolBomb";
		}if(e.getActionCommand().equals("toolrobot")){
			toolsbuy="ToolRobot";
		}if(e.getActionCommand().equals("toolroadblock")){
			toolsbuy="ToolRoadBlock";
		}if(e.getActionCommand().equals("abolishbuy")){
			sureBuy.setVisible(false);
			abolishBuy.setVisible(false);
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()==0){
				gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");	
				currentPlayerNum += 1;
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);//
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
					row.setVisible(true);
				}else{
					  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
					  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
					  gameTip.setVisible(true);
					  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
					  sureJump.setVisible(true);
					  currentPlayerNum += 1;
					  row.setVisible(false);
				   }

			}else{
				gameTip.setText("是否使用道具？");
				sureUseProp.setVisible(true);
				abolishUseProp.setVisible(true);
			}
			
			
		}if(e.getActionCommand().equals("sureuserprop")){
			gameTip.setText("请选择使用的道具及安放的位置(-10到10之间的整数)");
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()!=0){
				ToolBomb.setVisible(true);
			}
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()!=0){
				ToolRobot.setVisible(true);
			}
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()!=0){
				ToolRoadBlock.setVisible(true);
			}
			sureUseProp.setVisible(false);
			abolishUseProp.setVisible(false);
			PropLocation.setVisible(true);
			sureSet.setVisible(true);
		}if(e.getActionCommand().equals("abolishuserprop")){
			gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");
			sureUseProp.setVisible(false);
			abolishUseProp.setVisible(false);
			//row.setVisible(true);
			currentPlayerNum += 1;
			this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
				row.setVisible(true);
			}else{
				  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
				  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
				  gameTip.setVisible(true);
				  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				  sureJump.setVisible(true);
			   }
		}if(e.getActionCommand().equals("sureuplevel")){
			this.isUpLevel(elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()], players[currentPlayerNum%playerNum.getPlayerNumber()]);
			sureUpLevel.setVisible(false);
			abolishUpLevel.setVisible(false);
			sure.setVisible(true);
		}if(e.getActionCommand().equals("abolishuplevel")){
			sureUpLevel.setVisible(false);
			abolishUpLevel.setVisible(false);
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()==0){
				gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");
				currentPlayerNum += 1;
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
					row.setVisible(true);
				}else{
					  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
					  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
					  gameTip.setVisible(true);
					  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
					  sureJump.setVisible(true);
					  currentPlayerNum += 1;
				   }
				
			}else{
				gameTip.setText("是否使用道具？");
				sureUseProp.setVisible(true);
				abolishUseProp.setVisible(true);
			}
//			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){//修改此时不轮空
//				row.setVisible(true);
//			}else{
//				  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
//				  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
//				  gameTip.setVisible(true);
//				  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
//				  sureJump.setVisible(true);
//				  currentPlayerNum += 1;
//			   }
		}if(e.getActionCommand().equals("pay")){
			this.payMoney(elements[players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()], players[currentPlayerNum%playerNum.getPlayerNumber()]);
				pay.setVisible(false);
				if(payed){
					if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()==0){
						gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");
						row.setVisible(true);
						
						currentPlayerNum += 1;
						
						this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
					}else{
						gameTip.setText("是否使用道具？");
						sureUseProp.setVisible(true);
						abolishUseProp.setVisible(true);
					}
				}
//				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
//					row.setVisible(true);
//				}else{
//					  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
//					  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
//					  gameTip.setVisible(true);
//					  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
//					  sureJump.setVisible(true);
//					  currentPlayerNum += 1;
//					  row.setVisible(false);
//				   }
		}if(e.getActionCommand().equals("sure")){
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()==0){
				gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");
				//row.setVisible(true);
				currentPlayerNum += 1;
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
					row.setVisible(true);
				}else{
					  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
					  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
					  gameTip.setVisible(true);
					  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
					  sureJump.setVisible(true);
					  currentPlayerNum += 1;
					  row.setVisible(false);
				   }
			}else{
				gameTip.setText("是否使用道具？");
				sureUseProp.setVisible(true);
				abolishUseProp.setVisible(true);
			}
		    sure.setVisible(false);
		}if(e.getActionCommand().equals("surejump")){
			
			gameTip.setVisible(false);
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()!=0){
				players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
				gameTip.setVisible(true);	
			    gameTip.setText("当前玩家处于轮空状态，跳过当前回合！！！");
			    sureJump.setVisible(true);
			    this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
			    currentPlayerNum+=1;
			    
			}else{
				row.setVisible(true);
				sureJump.setVisible(false);
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
			}
		}if(e.getActionCommand().equals("sureset")){
			try{
			int temp=Integer.parseInt(PropLocation.getText());
			if(temp>=-10&&temp<=10){
				int position=players[currentPlayerNum%playerNum.getPlayerNumber()].getLocation()+temp;
				if(position<0) {
					position+=70;
					}
				if(elements[position].getElementType()!=TYPE.FLOOR_ONE&&elements[position].getElementType()!=TYPE.FLOOR_TWO&&elements[position].getElementType()!=TYPE.FLOOR_THREE){
					gameTip.setText("该位置无法安放炸弹，请重新输入");
				}else{
					if(elements[position].getPropType()!=TYPE.TOOL_BOMB){
				elements[position].setPropType(TYPE.TOOL_BOMB);
				gameTip.setText("安放成功，回合结束，请下一位玩家掷色子");
				players[currentPlayerNum%playerNum.getPlayerNumber()].setToolBombs( players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()-1);
				sureUseProp.setVisible(false);
				abolishUseProp.setVisible(false);
				sureSet.setVisible(false);
				PropLocation.setVisible(false);
				ToolBomb.setVisible(false);
				currentPlayerNum += 1;
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
					row.setVisible(true);
				}else{
					  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
					  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
					  gameTip.setVisible(true);
					  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
					  sureJump.setVisible(true);
					  currentPlayerNum += 1;
				   }
					}else{
						gameTip.setText("该位置已有炸弹，请重新输入！！！");
					}
				}
			}else{
				gameTip.setText("请输入-10到10之间的整数");
			}
			
			}catch(Exception exception){
				gameTip.setText("请输入正确的安放位置！！！");
			}
		}if(e.getActionCommand().equals("surebomb")){
			currentPlayerNum+=1;
			this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
			//row.setVisible(true);
			sureBomb.setVisible(false);
			gameTip.setText("请下一个玩家掷色子");
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
				row.setVisible(true);
			}else{
				  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
				  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
				  gameTip.setVisible(true);
				  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				  sureJump.setVisible(true);
				  currentPlayerNum += 1;
				  row.setVisible(false);
			   }
		}
		if(e.getActionCommand().equals("chooseone")){
			players[currentPlayerNum%playerNum.getPlayerNumber()].setMoney(players[currentPlayerNum%playerNum.getPlayerNumber()].getMoney()+2000);
			chooseone.setVisible(false);
			choosetwo.setVisible(false);
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()==0){
				gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");
				//row.setVisible(true);
				currentPlayerNum += 1;
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
					row.setVisible(true);
				}else{
					  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
					  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
					  gameTip.setVisible(true);
					  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
					  sureJump.setVisible(true);
					  currentPlayerNum += 1;
					  row.setVisible(false);
				   }
			}else{
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				gameTip.setText("是否使用道具？");
				sureUseProp.setVisible(true);
				abolishUseProp.setVisible(true);
			}
		}
		if(e.getActionCommand().equals("choosetwo")){
			players[currentPlayerNum%playerNum.getPlayerNumber()].setPoints(players[currentPlayerNum%playerNum.getPlayerNumber()].getPoints()+200);
			chooseone.setVisible(false);
			choosetwo.setVisible(false);
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()==0){
				gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");
				//row.setVisible(true);
				currentPlayerNum += 1;
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
					row.setVisible(true);
				}else{
					  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
					  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
					  gameTip.setVisible(true);
					  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
					  sureJump.setVisible(true);
					  currentPlayerNum += 1;
					  row.setVisible(false);
				   }
			}else{
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				gameTip.setText("是否使用道具？");
				sureUseProp.setVisible(true);
				abolishUseProp.setVisible(true);
			}
		}if(e.getActionCommand().equals("magicone")){//魔法屋的选择
			System.out.println(playernumber);
			int random=(int)Math.random()*playernumber;
			players[random].setMoney((int)(players[random].getMoney()*0.8));
			gameTip.setText("玩家"+players[random].getName()+"增加20%的金钱");
			Magicone.setVisible(false);
			Magictwo.setVisible(false);
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()==0){
				gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");
				//row.setVisible(true);
				currentPlayerNum += 1;
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
					row.setVisible(true);
				}else{
					  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
					  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
					  gameTip.setVisible(true);
					  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
					  sureJump.setVisible(true);
					  currentPlayerNum += 1;
					  row.setVisible(false);
				   }
			}else{
				gameTip.setText("是否使用道具？");
				sureUseProp.setVisible(true);
				abolishUseProp.setVisible(true);
			}
			
		}if(e.getActionCommand().equals("magictwo"))
		{
			System.out.println(playernumber);
			int random=(int)Math.random()*playernumber;
			players[random].setMoney((int)(players[random].getMoney()*0.8));
			gameTip.setText("玩家"+players[random].getName()+"损失20%的金钱");
			Magicone.setVisible(false);
			Magictwo.setVisible(false);
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getToolBombs()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRobots()==0&&players[currentPlayerNum%playerNum.getPlayerNumber()].getToolRoadBlock()==0){
				gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");
				//row.setVisible(true);
				currentPlayerNum += 1;
				this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
					row.setVisible(true);
				}else{
					  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
					  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
					  gameTip.setVisible(true);
					  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
					  sureJump.setVisible(true);
					  currentPlayerNum += 1;
					  row.setVisible(false);
				   }
				
			}else{
				gameTip.setText("是否使用道具？");
				sureUseProp.setVisible(true);
				abolishUseProp.setVisible(true);
			}
		}if(e.getActionCommand().equals("sureprison")){
			Sureprison.setVisible(false);
			gameTip.setText("当前玩家回合已经结束，请下一位玩家掷色子");
			//row.setVisible(true);
			currentPlayerNum += 1;
			this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
			if(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()==0){
				row.setVisible(true);
			}else{
				  gameTip.setText("当前玩家处于轮空状态，跳过当前回合!!!");
				  players[currentPlayerNum%playerNum.getPlayerNumber()].setIsJump(players[currentPlayerNum%playerNum.getPlayerNumber()].getIsJump()-1);
				  gameTip.setVisible(true);
				  this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
				  sureJump.setVisible(true);
				  currentPlayerNum += 1;
				  row.setVisible(false);
			   }
      }
		
	}
	
	//绘制地图
	public void drawMap(){
		int x=100, y= 140;
		map = new JPanel();
		map.setLayout(null);
		
		for(int i= 0;i<70;i++){
			    elements[i] = new mapElements();
			    elements[i].setFont(new Font("",1,20));
				elements[i].setText(mapEle.split("")[i]);
			if(i==0){
					elements[i].setFloor(false);
					elements[i].setBounds(x, y, 20, 20);
				}else if(i>0&&i<=28){
				if(i==14){
					elements[i].setElementType(TYPE.HOSPITAL);
					elements[i].setFloor(false);
				}else if(i==28){
					elements[i].setElementType(TYPE.PROP_HUOUSE);
					elements[i].setFloor(false);
				}else{
					elements[i].setElementType(TYPE.FLOOR_ONE);
				}
				elements[i].setBounds(x+i*20, y, 20, 20);
			}else if(i>28&&i<=35){
				if(i==35){
				elements[i].setElementType(TYPE.GIFT_HOUSE);
				elements[i].setFloor(false);
				}else{
					elements[i].setElementType(TYPE.FLOOR_TWO);
				}
				elements[i].setBounds(x+28*20, y+(i-28)*20, 20, 20);
			}else if(i>35&&i<=63){
				if(i==49){
					elements[i].setElementType(TYPE.PRISON);
					elements[i].setFloor(false);
				}
				else if(i==63){
					elements[i].setElementType(TYPE.MAGIC_HOUSE);
					elements[i].setFloor(false);
				}
				else{
					elements[i].setElementType(TYPE.FLOOR_THREE);
				}
				elements[i].setBounds(x+28*20-(i-35)*20,  y+(35-28)*20, 20, 20);
			}else{
				elements[i].setElementType(TYPE.MINE);
				elements[i].setFloor(false);
				elements[i].setMinePoints(mine_points[i-64]);
				elements[i].setBounds(x+28*20-(63-35)*20,  y+(35-28)*20-(i-63)*20, 20, 20);
			}
			map.add(elements[i]);
		}
		
		for(int i=0;i<playerNum.getPlayerNumber();i++){
				 players[i] = new Player();
                 players[i].setName(names[i]);
                 players[i].setNameCode(nameCodes[i]);
                 players[i].setColorType(colors[i]);
		}
		
	}
	
	//选择游戏人数界面
	public void selectPlarerNum(){
		selectPlarerNum = new JPanel();
		selectPlarerNum.setLayout(null);
		
		isNullTip = new JLabel("对不起,还未选择游戏玩家人数,请选择");
		isNullTip.setFont(new Font("",1,20));
		isNullTip.setForeground(Color.red);
		isNullTip.setBounds(220, 230, 700, 100);
		isNullTip.setVisible(false);
		welcomeTip = new JLabel("欢迎来到大富翁的游戏世界");
		welcomeTip.setFont(new Font("",1,50));
		welcomeTip.setForeground(Color.yellow);
		welcomeTip.setBounds(80,50, 700, 150);
		chooseTip = new JLabel("请选择游戏玩家的数量");
		chooseTip.setFont(new Font("",1,30));
		chooseTip.setForeground(Color.blue);
		chooseTip.setBounds(230, 150, 600, 100);
		choose = new ButtonGroup();
		playersNumTwo = new JRadioButton("2");
		playersNumTwo.setBounds(280, 220, 50, 50);
		playersNumTwo.addActionListener(this);
		playersNumTwo.setActionCommand("2");
		playersNumThree = new JRadioButton("3");
		playersNumThree.setBounds(350, 220, 50, 50);
		playersNumThree.addActionListener(this);
		playersNumThree.setActionCommand("3");
		playersNumFour = new JRadioButton("4");
		playersNumFour.setBounds(420, 220, 50, 50);
		playersNumFour.addActionListener(this);
		playersNumFour.setActionCommand("4");
		ok= new JButton("确定");
		ok.setBounds(280, 300, 60, 45);
		ok.addActionListener(this);
		ok.setActionCommand("ok");
		cancle = new JButton("取消");
		cancle.setBounds(400, 300, 60, 45);
		cancle.addActionListener(this);
		cancle.setActionCommand("cancle");
		
		choose.add(playersNumTwo);            choose.add(playersNumThree);          choose.add(playersNumFour);
		selectPlarerNum.add(isNullTip);
		selectPlarerNum.add(welcomeTip);      selectPlarerNum.add(chooseTip);       selectPlarerNum.add(playersNumTwo);
		selectPlarerNum.add(playersNumThree); selectPlarerNum.add(playersNumFour);  selectPlarerNum.add(ok);
		selectPlarerNum.add(cancle);
	}
	
	//显示玩家基本信息
	public void setPlayerInfo(Player player){
		showName.setText(player.getName());
		showMoney.setText(player.getMoney()+"");
		showPoint.setText(player.getPoints()+"");
	}
	
	//判断当前位置的地段类型
	public void  getType(mapElements floor ,Player p) {
		if(floor.getElementType()==TYPE.HOSPITAL){
			gameTip.setText("你当前到达医院！");
			sure.setVisible(true);
		}if(floor.getElementType()==TYPE.PROP_HUOUSE){
			gameTip.setText("你当前到达道具屋，可以进行道具的购买！");
			ToolBomb.setVisible(true);
			//ToolRoadBlock.setVisible(true);
			//ToolRobot.setVisible(true);
			Toolsok.setVisible(true);
			Toolscancle.setVisible(true);
		}if(floor.getElementType()==TYPE.GIFT_HOUSE){
			gameTip.setText("到达礼品屋，请选择：1.资金+2000，2.点数+200");
			chooseone.setVisible(true);
			choosetwo.setVisible(true);
		}if(floor.getElementType()==TYPE.PRISON){
			gameTip.setText("很不幸，你到达监狱，你将轮空两个回合！！！");
			p.setIsJump(2);
			Sureprison.setVisible(true);
		}if(floor.getElementType()==TYPE.MAGIC_HOUSE){
			gameTip.setText("到达魔法屋，请选择。1.随机玩家金钱增加20%，2.随机玩家金钱减少20%");
			Magicone.setVisible(true);
			Magictwo.setVisible(true);
		}if(floor.getElementType()==TYPE.MINE){
			gameTip.setText("恭喜你达到矿地，你将获得额外的"+floor.getMinePoints()+"点数!!!");
			gameTip.setVisible(true);
			p.setPoints(p.getPoints()+floor.getMinePoints());
			this.setPlayerInfo(p);
			sure.setVisible(true);
		}if(floor.getElementType()==TYPE.START){
			gameTip.setText("你已在此回到起点");
			sure.setVisible(true);
		}
	}
	
	public void isBomb(mapElements floor,Player p1){

		if(floor.getPropType()==TYPE.TOOL_BOMB)//"炸弹编号"//玩家被炸
		{
			elements[p1.getLocation()].setText(mapEle.split("")[p1.getLocation()]);
			p1.setLocation(14);//"医院位置"
			floor.setPropType(0);
			p1.setIsJump(3);
			gameTip.setText("你已中招，立即送往医院，您在三回合内将不能动！");
			sureBomb.setVisible(true);
		}

	}
	
	public void isBuyed(mapElements floor,Player p1){
		if(floor.getOwener().equals("")){//土地没有拥有者
			if(result==TYPE.BUYED)//玩家准备购买土地
			{
				if((p1.getMoney()-floor.getElementType())>=0)//当玩家资金能购买土地，玩家减钱，土地设置玩家为owner,土地升为一级
				{
					floor.setOwener(p1.getName());
					floor.setLevel(1);
					floor.setText("1");
					String newMap="";
					for(int j=0;j<mapEle.split("").length;j++){   
						if(j==p1.getLocation()){
							newMap += "1";
						}else{
						newMap+= mapEle.split("")[j]; 
						}
					}
					mapEle = newMap;
					if(p1.getColorType()==TYPE.COLOR_BLUE){
						floor.setForeground(Color.BLUE);
					}else if(p1.getColorType()==TYPE.COLOR_YELLOW){
						floor.setForeground(Color.YELLOW);
					}else if(p1.getColorType()==TYPE.COLOR_RED){
						floor.setForeground(Color.RED); 
					}else{
						floor.setForeground(Color.green);
					}
					p1.setMoney(p1.getMoney()-floor.getElementType());
					this.setPlayerInfo(p1);
					buyed= true;
				}
				else {
					buyed=false;
					gameTip.setText("您所剩资金不足，不能购买土地");//玩家不能购买土地
					sure.setVisible(true);
					
				}
			}
			
		}
		
		
	}


public void isUpLevel(mapElements floor,Player p1){
	
	
		if(floor.getLevel()!=3)//当前土地不为3级
		{
			if((p1.getMoney()-floor.getElementType())>0)//玩家所剩资金能升级土地,土地等级加1，玩家资金减少
				{
					p1.setMoney(p1.getMoney()-floor.getElementType());
					floor.setLevel(floor.getLevel()+1);
					floor.setText(floor.getLevel()+"");
					String newMap="";
					for(int j=0;j<mapEle.split("").length;j++){   
						if(j==p1.getLocation()){
							newMap += floor.getLevel();
						}
						else{
						newMap+= mapEle.split("")[j]; 
						}
					}
					mapEle = newMap;
					this.setPlayerInfo(p1);
				}else{
					gameTip.setText("资金不足，无法升级!!!");
				}
		}else{
			gameTip.setText("当前土地已达最高级，无法继续升级！！！");
		}
	}
	
public void payMoney(mapElements floor,Player p){
	p.setMoney((int)(p.getMoney()-floor.getLevel()*floor.getElementType()*0.5));
	if(p.getMoney()>0){
		int i;
		for(i=0;i<players.length;i++){
			if(players[i].getName().equals(floor.getOwener())){
				break;
			}
		}
		players[i].setMoney((int)(players[i].getMoney()+floor.getLevel()*floor.getElementType()*0.5));
		this.setPlayerInfo(p);
		payed=true;
	}else{
		playernumber-=1;
		p.setPoor(true);
		gameTip.setText("玩家"+p.getName()+"已破产，退出游戏");
		Player[] tempPlayers = new Player[playerNum.getPlayerNumber()-1];
		int j =0;
		for(int i=0;i<playerNum.getPlayerNumber();i++){
			if(!players[i].isPoor()){
				tempPlayers[j]=new Player();
				tempPlayers[j]=players[i];
				j++;
			}
		}
		
		for(int i=0;i<tempPlayers.length;i++){
			players[i]=tempPlayers[i];
		}
		currentPlayerNum-=1;
		playerNum.setPlayerNumber(playerNum.getPlayerNumber()-1);
		this.isEnd(tempPlayers.length);
		payed=false;
	}
	
}

public void isEnd(int length){
	int flag=0;
	
	for(int i=0;i<length;i++){
		if(players[i].isPoor()){
			flag+=1;
		}        
	}
	if((length-flag)==1){
		gameTip.setText("游戏结束 ,恭喜玩家"+players[0].getName()+"获得胜利！！！");
		tip.setVisible(false);              numTip.setVisible(false);
		showNameTip.setVisible(false);      showName.setVisible(false);
		showMoneyTip.setVisible(false);     showMoney.setVisible(false);
		showPointTip.setVisible(false);     showPoint.setVisible(false);
	}else{
		row.setVisible(true);
		this.setPlayerInfo(players[currentPlayerNum%playerNum.getPlayerNumber()]);
	}
}
	

}
 //程序运行后出现的界面
class startUi  extends JPanel implements Runnable
{
	int time=0;
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 800, 600);
		if(time%2==0)
		{
			g.setColor(Color.yellow);
			Font myFont=new Font("",Font.ITALIC,50);
			g.setFont(myFont);
			g.drawString("大富翁", 310, 250);
		}
	}
	//@Override
	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(600);
			}
			catch(Exception e){}
			time++;
			this.repaint();
		}
		
	}
	
 }


