package com.xjtu.gamestate;

import java.io.Serializable;

public enum GameState implements Serializable{
		CHOSE,//选择界面
		WAITCLIENT,//等待客机连接
		SHUFFLE,//洗牌
		DEAL,//发牌
		CALLING,//叫地主
		WAITCALL,
		PLAYING,//出牌
		WAITPLAY,
		END,//游戏结束
		WAITNEXT//等待下一局
		
		
		//  int mode;
		//  mode = GameState.CHOSE;
		
		//switch(mode){
		
}
