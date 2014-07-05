package com.android.server;

import android.graphics.Bitmap;
import android.graphics.Color;

public class RGB2HSV {
	
	private double min,max,delta,tmp;
	private double h;
	private int rgbPixel;
	private int r;
	private int g;
	private int b;
	
			
	public RGB2HSV() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getRGB2HSV(int x, int y, Bitmap bitmap) {
			// TODO Auto-generated method stub	
			
			rgbPixel = bitmap.getPixel((int)x, (int)y);				
			r = Color.red(rgbPixel);
			g = Color.green(rgbPixel);
			b = Color.blue(rgbPixel);
			
			tmp = r>g?g:r;
			min = tmp>b?b:tmp;
			tmp = r>g?r:g;
			max = tmp>b?tmp:b;
			delta = max - min;
			if(max == 0)
				h = 0;
			if(delta == 0)
				h = 0;
			if(r == max){			
					h = (g-b)/delta;								
				}else if(g == max){
					h = 2+(b-r)/delta;
			}else if(b == max){
				h = 4+(r-b)/delta;
			}
			h *= 60;
			if(h < 0)
				h += 360;			
			return (int)(h*255/360);
			
		
	}
}


