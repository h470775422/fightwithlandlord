package Landlord;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.xjtu.poke.Card;

public class RoleOfLandlord {
	/*********************************
	 (1) 单张：前面提到过，大小顺序从3(最小)到大怪(最大)；
　　(2) 一对：两张大小相同的牌，从3(最小)到2(最大)；
　　(3) 三张：三张大小相同的牌；
　　(4) 三张带一张：三张并带上任意一张牌，例如6-6-6-8，根据三张的大小来比较，例如9-9-9-3盖过8-8-8-A；
　　(5) 三张带一对：三张并带上一对，类似扑克中的副路(Full House)，根据三张的大小来比较，
				例如Q-Q-Q-6-6盖过10-10-10-K-K；
　　(6) 顺子：至少5张连续大小(从3到A，2和怪不能用)的牌，例如8-9-10-J-Q；
　　(7) 连对：至少3个连续大小(从3到A，2和怪不能用)的对子，例如10-10-J-J-Q-Q-K-K；
　　(8) 三张的顺子：至少2个连续大小(从3到A)的三张，例如4-4-4-5-5-5；
　　(9) 三张带一张的顺子：每个三张都带上额外的一个单张，例如7-7-7-8-8-8-3-6，
				尽管三张2不能用，但能够带上单张2和怪；
　　(10) 三张带一对的顺子：每个三张都带上额外的一对，只需要三张是连续的就行，
				例如8-8-8-9-9-9-4-4-J-J，尽管三张2不能用，但能够带上一对2【不能带一对怪，因为两张怪的大小不一样】，
				这里要注意，三张带上的单张和一对不能是混合的，例如3-3-3-4-4-4-6-7-7就是不合法的；
　　(11) 炸弹：四张大小相同的牌，炸弹能盖过除火箭外的其他牌型，大的炸弹能盖过小的炸弹；
　　(12) 火箭：一对怪，这是最大的组合，能够盖过包括炸弹在内的任何牌型；
　　(13) 四张套路(四带二)：有两种牌型，一个四张带上两个单张，例如6-6-6-6-8-9，
				或一个四张带上两对，例如J-J-J-J-9-9-Q-Q，四带二只根据四张的大小来比较，
				只能盖过比自己小的同样牌型的四带二【四张带二张和四张带二对属于不同的牌型，不能彼此盖过】，
				不能盖过其他牌型，四带二能被比自己小的炸弹盖过。
				
				
				
	NONE,
	SOLO,//单张     A      1张
	PAIR,//一对       44        2张 
	TRIO,//三张       555          3张
	TRIOWITHSOLO,//三带一   5556             4张
	TRIOWITHPAIR,//三带二    55566              5张
	CHAIN,//顺子      45678   最少五张            最少5张  5=<count<=20
	PAIRSCHAIN,//连对/双顺    至少3个连续大小   不能有2和怪    445566       6=<count<=20
	AIRPLANE,//三顺 /飞机   不能有2和怪        444555     最多6连          6张
	AIRPLANEWITHSMALLSWINGS,//飞机带小翼   44455578                 8张
	AIRPLANEWITHLARGESWINGS,//飞机带大翼   4445557788            10张
	FOURWITHTWOSOLO,//四带二   444467                     6张
	FOURWITHTWOPAIRS,//四带两对  44446677               8张
	SPACESHUTTLE,//航天飞机    33334444                       8张
	SPACESHUTTLEWITHSMALLSWINGS,//航天飞机带小翼     333344446789              12张
	SPACESHUTTLEWITHLARGESWINGS,//航天飞机带大翼     3333444466778899        16张
	BOMB,//炸弹  4444             4张
	KINGBOMB,//王炸   大王小王        2张
	 */
	
	
	private LandlordType pokeType = LandlordType.NONE;
	private List<Card> handCards = null;
	private int count = 0;
	
	
	public boolean ifWin(List<Card> handCards,List<Card> desktopCards){
		this.handCards = handCards;
		count = handCards.size();
		return false;
	}
	public void sort(){
		Collections.sort(handCards);
	}

	public LandlordType judgeType(List<Card> cards){
		switch(count){
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
			
		}
		return LandlordType.NONE;
	}

	private boolean isPair(){//2
		if(getKey(0) == getKey(1))
			return true;
		return false;
	}
	private boolean isTrio(){//3
		if(getKey(0) == getKey(1) && getKey(0) == getKey(2))
			return true;
		return false;
	}
	private boolean isTrioWithSolo(){//4   1+3/3+1
		if(getKey(0) == getKey(1) && getKey(0) == getKey(2) && getKey(0) != getKey(3))
			return true;
		else if(getKey(1) == getKey(2) && getKey(1) == getKey(3) && getKey(0) != getKey(1))
			return true;
		return false;
	}
	private boolean isTrioWithPair(){//5   2+3/3+2
		if(getKey(0) == getKey(1) && getKey(0) == getKey(2) && getKey(3) == getKey(4))
			return true;
		else if(getKey(2) == getKey(3) && getKey(2) == getKey(4) && getKey(0) == getKey(1))
			return true;
		return false;
	}
	private boolean isChain(){
		if(findKey(2,handCards))
			return false;
		for(int i = 1; i < handCards.size(); i++){
			if(getKey(i) - getKey(i-1) != 1)
				return false;
		}
		return true;
	}
	private boolean isPairsChain(){
		if(findKey(2,handCards))
			return false;
		if(count % 2 == 0){
			for(int i = 0; i < count-1; i=i+2){
				if(getKey(i) != getKey(i+1))
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean  isAirplane(){
		if(findKey(2,handCards))
			return false;
		if(getKey(0) == getKey(1) && getKey(0) == getKey(2) &&
				getKey(3) == getKey(4) && getKey(3) == getKey(5))
			return true;
		return false;
	}
	
	
	private int getKey(int i){
		return handCards.get(i).getKey();
	}
	
	private boolean findKey(int key,List<Card> cards){
		for(Card c:cards){
			if(c.getKey() == key){
				return true;
			}
		}
		return false;
	}
	
	
}
