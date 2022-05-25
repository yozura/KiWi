package kiwi.mgr;

import kiwi.header.Define.BGM_TYPE;
import kiwi.header.Define.SFX_TYPE;

public class SoundMgr {
	private static SoundMgr instance = new SoundMgr();
	
	public SoundMgr getInstance() {
		return instance;
	}
	
	public void playBGM(BGM_TYPE bgmType) {
		// BGM Play
		
	}
	
	public void playSFX(SFX_TYPE sType) {
		
	}
}