package murphy.christopher.popularmoviesstage1.view_holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.popularmoviesstage1.R;
import murphy.christopher.popularmoviesstage1.model.ReviewResults;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    private Context context;

    @BindView(R.id.author)
    TextView author;

    @BindView(R.id.content)
    TextView content;

    public ReviewViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }
    public void bind(ReviewResults results) {
        author.setText(results.getAuthor());
        content.setText(results.getContent());
    }
}
