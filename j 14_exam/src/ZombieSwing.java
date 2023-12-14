import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class ZombieSwing {
	Random r = new Random();
	
	final int MOVE_STEP = 5;
	final int MAX_X = 400;
	final int MAX_Y = 300;
	
	int x, y; //좀비 좌표
	int dir; //방향
	boolean toggle;
	int imgwidth = 20; //이미지 크기
	int imgheight = 20;
	
	ImageIcon imgIcon[] = new ImageIcon[2];
	Image img[] = new Image[2];
	
	public ZombieSwing(int x, int y) {
		this.x = x;
		this.y = y;
		for(int i=0; i<2; i++)
		{
			imgIcon[i] = new ImageIcon("images/enemy" +(i+1)+".png");
			img[i] = imgIcon[i].getImage();
		}
	}
	
	public void moveLeft() 
	{
		x = x - MOVE_STEP;
		if(x < 0) x = 0;
	}
	public void moveRight()
	{
		x = x + MOVE_STEP;
		if(x > MAX_X - imgwidth) x = MAX_X - imgwidth;
	}
	public void randomMove()
	{
		int n = r.nextInt(3);
		if (n==0) moveLeft();
		else if (n==1) moveRight();
		toggle = !toggle;
	}
	
	public boolean crush(HeroSwing z)
	{
		if(((z.x < x)&&(x < z.x + z.imgwidth))&&((z.y < y)&&(y < z.y + z.imgheight)))
			return true;
		if(((z.x < x+imgwidth)&&(x+imgwidth < z.x + z.imgwidth))&&((z.y < y)&&(y < z.y + z.imgheight)))
			return true;
		if(((z.x < x)&&(x < z.x + z.imgwidth))&&((z.y < y+imgheight)&&(y+imgheight < z.y + z.imgwidth)))
			return true;
		if(((z.x < x+imgwidth)&&(x+imgwidth < z.x + z.imgwidth))&&((z.y < y+imgheight)&&(y+imgheight < z.y + z.imgwidth)))
			return true;
		
		return false;
	}
	
	//좀비 그려주는 메소드 좀비
	public void paint(Graphics g) {
		if(toggle)
			g.drawImage(img[0], x, y, null);
		else
			g.drawImage(img[1], x, y, null);
	}
}
