package GUI;

import Controles.Cooldown;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class HackingUI{
    DecimalFormat df = new DecimalFormat("#,###.###");

    GamePanel gp;
    Graphics2D g2;
    Font arial_16,arial_14,arial_10,arial_20;
    Cooldown cd = new Cooldown(200);
    public Cooldown cdToHack = new Cooldown(30000);
    int foundCounter = 0;
    int wrongNumber = 0;
    int selected = -1;
    BufferedImage  select;
    public int slotCol = 0;
    public int slotRow = 0;

    boolean[] ipfound = {false,false,false,false};



    int[] loc ={0,0,0,0};
    public int [] ip = {0,0,0,0};

    BufferedImage invHud;
    ArrayList<Integer>  nubm = new ArrayList<>();
    UI ui ;
    Random rand = new Random();

    public HackingUI(GamePanel gp, UI ui  ) {
        this.gp = gp;
        this.ui = ui;



        arial_10 = new Font("Arial", Font.PLAIN,10);
        arial_14 = new Font("Arial", Font.PLAIN,14);
        arial_20 = new Font("Arial", Font.PLAIN,20);
        for(int i = 0;i<4;i++){
            int k = randomizer();
            while(k == ip[0] || k == ip[1]||k == ip[2]||k == ip[3] )
                k = randomizer();
            ip[i]=k;
        }
        nubm = new ArrayList<>();
        for(int i= 0,x=0 ;i<48;i++){
            if(i == loc[0] || i==loc[1] ||i==loc[2] || i==loc[3] ) {
                nubm.add(ip[x]);
                x++;
            }
            else{
                nubm.add(randomizer());
            }
        }
    }



    public void draw(Graphics2D g2){
        this.g2 = g2;
        drawHackingScreen();
        g2.setFont(arial_20);
        printIP();
        g2.setColor(Color.YELLOW);

    }

    public void drawHackingScreen(){

        int frameX = (gp.tileSize*8);
        int frameY = gp.tileSize+20;
        int frameWidth = gp.tileSize * 6 ;
        int frameHeight= gp.tileSize * 6;


        //Slot
        final int slotXStart = frameX + 10;
        final int slotYStart = frameY + 30;
        int slotX = slotXStart + 35;
        int slotY = slotYStart + 45;
        int slotSize = gp.tileSize;
        if(!cd.isOnCooldown()){
            updateNumbers();
            cd.trigger();
        }

        for(int i=0; i<nubm.size();i++){

            g2.setFont(arial_20);
            g2.setColor(Color.green);
            if(!nubm.isEmpty()) {
                if((nubm.get(i) == ip[0] && ipfound[0])||(nubm.get(i) == ip[1] && ipfound[1]) ||
                        (nubm.get(i) == ip[2] && ipfound[2]) ||(nubm.get(i) == ip[3] && ipfound[3]))
                    g2.setColor(Color.RED);
                else
                    g2.setColor(Color.GREEN);
                g2.drawString(String.valueOf(nubm.get(i)), slotX, slotY);
            }
            g2.setFont(arial_14);

            slotX += slotSize;
            if( (i+1) % 8 == 0){
                slotX = slotXStart+35;
                slotY += slotSize;
            }
        }

        //cursor
        int cursorX = slotXStart + (gp.tileSize * slotCol);
        int cursorY = slotYStart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        // Draw Cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));

        try {
            select = ImageIO.read(getClass().getResourceAsStream("/res/Inventory/selectItem.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        g2.drawImage(select, cursorX+10, cursorY, cursorWidth, cursorHeight, null);

        int dFrameX = frameX;
        int dFrameY = frameY +frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize;
        ui.drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight,0);

        int textX = dFrameX + 20;
        int textY = (dFrameY + gp.tileSize)-70;
        g2.setFont(g2.getFont().deriveFont(12F));

        int ipIndex = getIPIndexOnSlot();
        if(ipIndex < nubm.size()){
            g2.setFont(arial_20);
            g2.drawString(nubm.get(ipIndex).toString(),1480,490);
            g2.setFont(arial_16);

        }
        if(gp.keyH.selectIp){
            selected = nubm.get(ipIndex);
            checker();

        }

        if(foundCounter == 4) {
            gp.keyH.doorOpen = true;
            gp.gameState = gp.playState;
        }


    }
    public int randomizer(){
        return rand.nextInt(0,254)+1;
    }

    private void pickLocation(int[] loc){
        boolean valid = true;
        for(int  i = 3 ; i>-1 ; i--){
            do{
                loc[i] = rand.nextInt(1,48);
                for(int x = 0;x < i ;x++){
                    if (loc[i] == loc[x]) {
                        valid = false;
                        break;
                    }
                }
            }while(!valid);
        }
    }
    private void updateNumbers(){
        for(int i= 0,x=0 ;i<48;i++){
            if(i == loc[0] || i==loc[1] ||i==loc[2] || i==loc[3] ) {
                nubm.set(i,ip[x]);
                x++;
            }
            else{
                int k =randomizer();
               while(k == ip[0] || k == ip[1]||k == ip[2]||k == ip[3] )
                   k = randomizer();
                nubm.set(i,k);
            }
        }
    }
    public int getIPIndexOnSlot(){
        return slotCol + (slotRow * 8);
    }
    public void checker(){

        int num = selected;
        for (int i = 0; i < 4; i++) {
            if (num == ip[i] && !ipfound[i]) {
                ipfound[i] = true;
                foundCounter++;
                break;
            }
        }
            wrongNumber++;

        gp.keyH.selectIp = false;
    }

    void printIP() {
        g2.drawString("" + df.format(cdToHack.timeRemaining()), 990, 100);
        g2.setColor(Color.RED);
        for (int i = 0,x=908; i < 4; i++,x+=40) {
            if (ipfound[i]) {
                g2.setColor(Color.YELLOW);
            }
            if(i>0)
                g2.drawString("." + ip[i], x, 125);
            else
                g2.drawString(" " + ip[i], x, 125);

            g2.setColor(Color.RED);

            }
        }


}
