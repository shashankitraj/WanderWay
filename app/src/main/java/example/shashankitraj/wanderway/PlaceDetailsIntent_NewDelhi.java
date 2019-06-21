package example.shashankitraj.wanderway;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlaceDetailsIntent_NewDelhi extends AppCompatActivity {

    int[] mResourcesImg={
            R.drawable.ic_launcher_foreground,R.drawable.ic_menu_gallery
    };
    String[] mResourcesTitle={
            "New Delhi","Second Tile"
    };
    TextView tvDetails;
    ViewPager vp;
    CustomPagerAdapter mCustomPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details_intent);

        mCustomPagerAdapter = new CustomPagerAdapter(this);

        tvDetails=findViewById(R.id.detailsBody);
        tvDetails.setText("This part is working!");

        vp=findViewById(R.id.viewPager);
        vp.setAdapter(mCustomPagerAdapter);

    }
    class CustomPagerAdapter extends PagerAdapter{
        Context mContext;
        LayoutInflater mLayoutInflator;

        public CustomPagerAdapter(Context context){
            mContext=context;
            mLayoutInflator=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount(){return mResourcesImg.length;}

        public boolean isViewFromObject(View view, Object object){
            return view==(LinearLayout)object;
        }

        public Object instantiateItem(ViewGroup container, int position){
            View itemView = mLayoutInflator.inflate(R.layout.pager_item, container, false);
            ImageView imageView=(ImageView)itemView.findViewById(R.id.detailsImage);
            imageView.setImageResource(mResourcesImg[position]);

            TextView tvtitle=(TextView)itemView.findViewById(R.id.pager_item_title);
            tvtitle.setText(mResourcesTitle[position]);

            container.addView(itemView);
            return itemView;
        }

        public void destroyItem(ViewGroup container, int position, Object object){
            container.removeView((LinearLayout)object);
        }
    }
}
