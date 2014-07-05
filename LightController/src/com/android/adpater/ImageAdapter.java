package com.android.adpater;

import com.android.ui.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	
	// ����Context
		private Context		mContext;
		// ������������ ��ͼƬԴ
		private Integer[]	mImageIds	= 
		{ 
				R.drawable.firstui, 
				R.drawable.secondui, 
				R.drawable.thirdui,
				R.drawable.fourthui,
				
		};

		public ImageAdapter(Context c)
		{
			mContext = c;
		}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mImageIds.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView;
		if (convertView == null)
		{
			// ��ImageView������Դ
			imageView = new ImageView(mContext);
			// ���ò��� ͼƬ��ʾ,���Ըı�ͼƬ��С
			imageView.setLayoutParams(new GridView.LayoutParams(360, 360));
			// ������ʾ��������
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		}
		else
		{
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mImageIds[position]);
		return imageView;
	}
		
	

}
