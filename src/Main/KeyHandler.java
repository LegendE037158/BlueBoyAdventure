package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, cPressed, shotKeyPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.titleState){
            if (gp.ui.titleScreenRate == 0){
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.cmdIndex--;
                    if (gp.ui.cmdIndex < 0){
                        gp.ui.cmdIndex = 2;
                    }
                }
                
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.cmdIndex++;   
                    if (gp.ui.cmdIndex > 2){
                        gp.ui.cmdIndex = 0;
                    }
                } 
    
                if (code == KeyEvent.VK_ENTER){
                    if (gp.ui.cmdIndex == 0){
                        gp.ui.titleScreenRate = 1;
                        gp.loadManager.loadSettings();
                        gp.playMusic(0);
                        gp.ui.cmdIndex = 0;
                      }
                    if (gp.ui.cmdIndex == 1){
                        gp.loadManager.load();
                        gp.gameState = gp.playState;
                        gp.loadManager.loadSettings();
                        gp.playMusic(0);
                        gp.ui.cmdIndex = 0;
                    }
                    if (gp.ui.cmdIndex == 2){
                        System.exit(0);
                    } 
                }
            } else if (gp.ui.titleScreenRate == 1){
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.cmdIndex--;
                    if (gp.ui.cmdIndex < 0){
                        gp.ui.cmdIndex = 2;
                    }
                }
                
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.cmdIndex++;   
                    if (gp.ui.cmdIndex > 3){
                        gp.ui.cmdIndex = 0;
                    }
                } 
    
                if (code == KeyEvent.VK_ENTER){
                    if (gp.ui.cmdIndex == 0){
                        gp.player.playerRole = gp.player.fighterRole;
                        gp.gameState = gp.playState;
                        gp.ui.cmdIndex = 0;
                        gp.loadManager.loadSettings();
                    }
                    if (gp.ui.cmdIndex == 1){
                        gp.player.playerRole = gp.player.theifRole;
                        gp.gameState = gp.playState;
                        gp.ui.cmdIndex = 0;
                        gp.loadManager.loadSettings();
                    }
                    if (gp.ui.cmdIndex == 2){
                        gp.player.playerRole = gp.player.scorererRole;
                        gp.gameState = gp.playState;
                        gp.ui.cmdIndex = 0;
                        gp.loadManager.loadSettings();
                    } 
                    if (gp.ui.cmdIndex == 3){
                        gp.ui.titleScreenRate = 0;
                        gp.stopMusic();
                        gp.ui.cmdIndex = 0;
                    }
                }
            } 
        }
        if (gp.gameState == gp.playState){
            if (gp.ui.gameFinished == false){
                if (code == KeyEvent.VK_W) {
                    upPressed = true;
                }
                
                if (code == KeyEvent.VK_S) {
                    downPressed = true;    
                } 

                if (code == KeyEvent.VK_A) {
                    leftPressed = true;    
                } 

                if (code == KeyEvent.VK_D) {
                    rightPressed = true;    
                }
                
                if (code == KeyEvent.VK_P) {
                    gp.gameState = gp.pauseState;
                }
                if (code == KeyEvent.VK_ENTER) {
                    int tileIndex = gp.cChecker.checkEntity(gp.player, gp.tileInteractiveObject);
                    if (gp.player.playerRole == gp.player.fighterRole || gp.player.playerRole == gp.player.scorererRole || tileIndex != 999){
                        enterPressed = true;
                    }
                }
                if (code == KeyEvent.VK_C){
                    if (gp.player.playerRole == gp.player.fighterRole){
                        cPressed = true;
                    }
                }
                if (code == KeyEvent.VK_E) {
                    gp.gameState = gp.inventoryState;
                }
                if (code == KeyEvent.VK_F){
                    shotKeyPressed = true;
                }
                if(code == KeyEvent.VK_ESCAPE){
                    gp.gameState = gp.menuState;
                }
            } else if (gp.ui.gameFinished == true){
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.cmdIndex--;
                    if (gp.ui.cmdIndex < 0){
                        gp.ui.cmdIndex = 1;
                    }
                }
                
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.cmdIndex++;   
                    if (gp.ui.cmdIndex > 1){
                        gp.ui.cmdIndex = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER){

                    if (gp.ui.cmdIndex == 0){
                        gp.StoreDataInRAM();
                        if (!gp.goalReached){
                            gp.ResetGame();
                            gp.LoadDataFromRAM();
                        } else {
                            gp.ResetEveryThing();
                        }
                        gp.ui.gameFinished = false;
                        gp.gameState = gp.playState;
                    }
                    if (gp.ui.cmdIndex == 1){
                        gp.ResetEveryThing();
                        gp.gameState = gp.titleState;
                        gp.ui.titleScreenRate = 0;
                        gp.ui.gameFinished = false;
                        gp.ui.cmdIndex = 0;
                    }
                } 
            }
            
        }
        if (gp.gameState == gp.pauseState){
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }
        if (gp.gameState == gp.dialogueState){
            if (code == KeyEvent.VK_ENTER) {
                int npcIndex = gp.cChecker.checkEntity(gp.player, gp.npc);
                if (npcIndex != 999){
                    gp.npc[gp.currentMap][npcIndex].speak();
                } else {
                    gp.player.speak();
                }
            }
        }
        if (gp.gameState == gp.inventoryState){
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
            if (code == KeyEvent.VK_W) {
                if (gp.ui.slotRow > 0){
                    gp.ui.slotRow--;
                }
            }
            if (code == KeyEvent.VK_S) {
                if (gp.ui.slotRow < 3){
                    gp.ui.slotRow++;
                }
            }
            if (code == KeyEvent.VK_A) {
                if (gp.ui.slotCol > 0){
                    gp.ui.slotCol--;
                }
            }
            if (code == KeyEvent.VK_D) {
                if (gp.ui.slotCol < 4){
                    gp.ui.slotCol++;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.player.selectItem();
            }
        }
        if (gp.gameState == gp.menuState){
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.cmdIndex--;
                if (gp.ui.cmdIndex < 0){
                    gp.ui.cmdIndex = 4;
                }
            }
            
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.cmdIndex++;   
                if (gp.ui.cmdIndex > 4){
                    gp.ui.cmdIndex = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if (gp.ui.cmdIndex == 0){
                    gp.music.volUp();
                } else if (gp.ui.cmdIndex == 1){
                    gp.playSE(1);
                    gp.se.volUp();
                } else if (gp.ui.cmdIndex == 2){
                    gp.loadManager.save();
                } else if (gp.ui.cmdIndex == 3){
                    gp.loadManager.save();
                    gp.ui.titleScreenRate = 0;
                    gp.stopMusic();
                    gp.gameState = gp.titleState;
                    gp.ResetEveryThing();
                    gp.ui.cmdIndex = 0;
                } else if (gp.ui.cmdIndex == 4){
                    gp.gameState = gp.playState;
                    gp.ui.cmdIndex = 0;
                    gp.loadManager.saveSettings();
                }
            }
        }
        if (gp.gameState == gp.tradeState){
            if (code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.cmdIndex--;
                if (gp.ui.cmdIndex < 0){
                    gp.ui.cmdIndex = 4;
                }
            }
            
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.cmdIndex++;   
                if (gp.ui.cmdIndex > 4){
                    gp.ui.cmdIndex = 0;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        
        if (code == KeyEvent.VK_S) {
            downPressed = false;    
        } 

        if (code == KeyEvent.VK_A) {
            leftPressed = false;    
        } 

        if (code == KeyEvent.VK_D) {
            rightPressed = false;    
        } 
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_C){
            if (gp.player.playerRole == gp.player.fighterRole){
                cPressed = false;
            }
        }
        if (code == KeyEvent.VK_F){
            shotKeyPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
  
        
    }
    
    
}
