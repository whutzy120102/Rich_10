package bigRich_10shi;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;

 class ShowHelpInformation extends JFrame{
	    //private String rules
	    ShowHelpInformation() {
		this.setSize(600, 600);
		this.setTitle("游戏规则说明");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		//显示帮助信息的面板
		JPanel jPanelText=new JPanel();
		jPanelText.setLayout(new BorderLayout());
		//显示帮助信息的文本域
		JTextArea helpInformation=new JTextArea(20,1);
		JScrollPane jtxt=new JScrollPane(helpInformation);
		String Rule_1="一、  S代表起点，T代表道具屋，G代表礼品屋，H代表医院，P代表监狱，$代表矿地;\n";
		String Rule_2="二、  0代表空地，地段1（最上面的一行）每块200元，地段2（最右端的一列）每块500元，\n          地段3（最下面的一行）每行300元;\n";
		String Rule_3="三、  H：医院，玩家被炸伤后，将被送到医院医治3天（轮空3次）   \n";
		String Rule_4="四、 S：矿地，从下至上依次为20，80，100，40，80，60点 \n";
		String Rule_5="五、 资金：可用于购地、盖房、支付过路费\n";
		String Rule_6="六、 点数：用于购买道具，经过矿地时可以获取点数\n";
		String Rule_7="七、 道具：可以使用炸弹\n";
		String Rule_8="八、 炸弹：可以将炸弹放置到离当前位置前后10步的距离，\n        在一玩家停留该位置将被炸伤，送往医院，住院三天\n";
		String Rule_9="九、 礼品：奖金：2000元， 点数卡;200点\n";
		String Rule_10="十、 玩家停留在自己的空地或楼房处，系统提示是否升级，\n        且现有资金足够时，升级成功。房屋分三级，每升一级的费用同购买空地的费用\n";
		String Rule_11="十一、  玩家停留在其他玩家的房产上时，则需要支付过路费，费用为该处房产价值的1/2\n";
		String Rule_12="十二、  玩家资金低于0时即宣告破产，该玩家占有的土地归还系统，初始化为空地，可供其它玩家重新购买\n";
		String Rule_13="十三、  只剩一个玩家时，游戏结束，该玩家获胜\n";
		//////////////////////////////////////////////////////////
		helpInformation.setFont(new Font(null, 10, 15));
		helpInformation.setText(Rule_1+Rule_2+Rule_3+Rule_4+Rule_5+Rule_6+Rule_7+Rule_8+Rule_9+Rule_10+Rule_11+Rule_12+Rule_13);
		helpInformation.setEditable(false);
		jPanelText.add(jtxt,BorderLayout.CENTER);
		this.add(jPanelText,BorderLayout.CENTER);
		
	}
	

}
