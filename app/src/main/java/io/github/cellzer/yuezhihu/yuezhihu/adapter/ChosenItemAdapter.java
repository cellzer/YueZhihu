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
import io.github.cellzer.yuezhihu.yuezhihu.R;
import io.github.cellzer.yuezhihu.yuezhihu.model.Chosen;
import io.github.cellzer.yuezhihu.yuezhihu.util.PreUtils;

/**
 * Created by walmand_ on 2016/2/17 0017.
 */
public class ChosenItemAdapter extends BaseAdapter {

    private List<Chosen.AnswersEntity> answerList;
    private Context context;
    private ImageLoader mImageloader;
    private DisplayImageOptions options;


    public ChosenItemAdapter(Context context, List<Chosen.AnswersEntity> answerList) {
        this.context = context;
        this.answerList = answerList;
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
        return answerList.size();
    }

    @Override
    public Object getItem(int position) {
        return answerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.chosen_item, viewGroup, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        String readSeq = PreUtils.getStringFromDefault(context, "chosen_read", "");
        if (readSeq.contains(answerList.get(position).getAnswerid() + "")) {
            viewHolder.tvTitle.setTextColor(context.getResources().getColor(R.color.clicked_tv_textcolor));
        } else {
            viewHolder.tvTitle.setTextColor(context.getResources().getColor(android.R.color.black));
        }
        viewHolder.tvTitle.setText(answerList.get(position).getTitle());
        viewHolder.tvVote.setText(answerList.get(position).getVote());
        viewHolder.tvAuthorname.setText(answerList.get(position).getAuthorname());
        viewHolder.tvSummary.setText(answerList.get(position).getSummary());
        mImageloader.displayImage(answerList.get(position).getAvatar(),viewHolder.ivAvatar,options);

        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'chosen_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.iv_avatar)
        ImageView ivAvatar;
        @InjectView(R.id.authorname)
        TextView tvAuthorname;
        @InjectView(R.id.vote)
        TextView tvVote;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.tv_summary)
        TextView tvSummary;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
