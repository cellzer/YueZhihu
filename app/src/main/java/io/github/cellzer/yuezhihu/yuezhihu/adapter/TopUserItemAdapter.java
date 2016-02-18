package io.github.cellzer.yuezhihu.yuezhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.github.cellzer.yuezhihu.yuezhihu.Constant;
import io.github.cellzer.yuezhihu.yuezhihu.R;
import io.github.cellzer.yuezhihu.yuezhihu.model.TopUser;

/**
 * Created by walmand_ on 2016/2/18 0018.
 */
public class TopUserItemAdapter extends BaseAdapter {

    private List<TopUser.TopuserEntity> topuserEntityList;
    private TopUser topUser;
    private Context context;
    private ImageLoader mImageloader;
    private DisplayImageOptions options;

    public TopUserItemAdapter(Context context, TopUser topUser) {
        this.topUser = topUser;
        this.context = context;
        topuserEntityList = topUser.getTopuser();
        mImageloader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.drawable.avatar) // resource or drawable
                .showImageForEmptyUri(R.drawable.avatar) // resource or drawable
                .showImageOnFail(R.drawable.avatar) // resource or drawable
                .build();
    }


    @Override
    public int getCount() {
        return topuserEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return topuserEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.topuser_item, viewGroup, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.authorname.setText(topuserEntityList.get(position).getName());
        viewHolder.signature.setText(topuserEntityList.get(position).getSignature());

        if(topUser.getRankType()== Constant.TYPE_AGREE){
            viewHolder.ranking.setText("赞同数："+topuserEntityList.get(position).getAgree());

        }else if(topUser.getRankType()== Constant.TYPE_FOLLOWER){
            viewHolder.ranking.setText("关注数："+topuserEntityList.get(position).getFollower());
        }else{
            throw  new IllegalArgumentException();
            //数据有误 ，topUser.getRankType()出错
        }
        mImageloader.displayImage(topuserEntityList.get(position).getAvatar(),viewHolder.ivAvatar,options);
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'topuser_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.iv_avatar)
        ImageView ivAvatar;
        @InjectView(R.id.authorname)
        TextView authorname;
        @InjectView(R.id.signature)
        TextView signature;
        @InjectView(R.id.ranking)
        TextView ranking;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
